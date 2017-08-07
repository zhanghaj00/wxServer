package shop.haj.controller;

import me.chanjar.weixin.common.exception.WxErrorException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.haj.entity.Customer;
import shop.haj.entity.Order;
import shop.haj.entity.OrderGoodsInfo;
import shop.haj.entity.wxPay.WxPayConfig;
import shop.haj.entity.wxPay.WxPayOrderNotifyResponse;
import shop.haj.entity.wxPay.request.WxPayUnifiedOrderRequest;
import shop.haj.entity.wxPay.result.WxPayOrderNotifyResult;
import shop.haj.service.CustomerService;
import shop.haj.service.OrderService;
import shop.haj.service.WxAuthService;
import shop.haj.service.WxPayService;
import shop.haj.utils.DefaultPagination;
import shop.haj.utils.OnlinePayType;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: shop.ha.controller</p>
 * <p/>
 * <p>
 * Description: 微信支付接口
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/15/17
 */
@RestController
@RequestMapping(value = "/v1")
public class WxPayController extends BaseController{
	
	private Logger logger = LogManager.getLogger(WxPayController.class);
	
	private final String notifyURL = "http://ha.shop:9999/v1/wxpay/notify";
	
	@Autowired
	private WxPayService wxPayService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private WxAuthService wxAuthService;
	
	@Autowired
	private CustomerService customerService;
	
	/**
	 * 统一下单
	 * @param shop_id
	 * @param order_id
	 * @return
	 */
	@GetMapping(value = "/customer/orders/{order_id}/wxpay")
	public Map<String,Object> unifiedOrderPay(@RequestHeader("shop_id") int shop_id,
	                              @PathVariable("order_id") String order_id,
	                              @RequestHeader("session_id") String session_id){
		
		//1. 根据shop_id拿到店铺对应的支付信息，从表wxpay_info获取
		WxPayConfig wxPayConfig = wxPayService.getWxConfig(shop_id);
		logger.info("根据shop_id:{}, 拿到店铺对应的支付信息:{}", shop_id, wxPayConfig);
		
		//2. 根据order_id拿到对应的订单信息
		Order order = orderService.findShopOrderByID(order_id, DefaultPagination.getOrder());
		
		//3. 拿到open_id
		String customer_id = wxAuthService.decodehaSession(session_id);
		
		Customer customer = customerService.findById(customer_id);
		
		String open_id = customer.getOpenId();
		
		//4. 组装WxPayUnifiedOrderRequest
		List<OrderGoodsInfo> orderGoodsInfos = order.getOrderGoodsInfos();
		
		//设置body字段信息
		String body = order.getShopName() + "-默认商品";
		if(orderGoodsInfos != null && orderGoodsInfos.size() > 0){
			OrderGoodsInfo orderGoodsInfo = orderGoodsInfos.get(0);
			body = order.getShopName() + "-" + orderGoodsInfo.getGoodsName();
		}
		
		WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = WxPayUnifiedOrderRequest.builder()
				.body(body)
				.deviceInfo(String.valueOf(order.getShopId()))
				.nonceStr(RandomStringUtils.randomAlphanumeric(32).toUpperCase())
				.outTradeNo(order.getUuid())
				.totalFee((int)(order.getFinalPrice() * 100))
				.spbillCreateIp("127.0.0.1")
				.notifyURL(notifyURL)
				.tradeType("JSAPI")
				.openid(open_id)
				.build();
		
		try {
			//加密
			wxPayUnifiedOrderRequest.checkAndSign(wxPayConfig);
		} catch (WxErrorException e) {
			logger.error(e.toString(), e);
		}
		
		
		//5. 向微信服务器发送统一下单请求，并生成新的sign
		Map<String, String> payInfo = null;
		try {
			payInfo = wxPayService.getPayInfo(wxPayUnifiedOrderRequest, wxPayConfig.getMchKey(), 0);//TODO
			
		} catch (WxErrorException e) {
			logger.error(e.toString(), e);
		}
		
		//6. 返回数据报文给小程序端
		return rtnParam(0, payInfo);
	}
	
	/**
	 * 微信支付成功后，接收微信端通知
	 * @param xmlData
	 *  return_code:
	 *              SUCCESS/FAIL
	 *              此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
	 *  return_msg:
	 *              返回信息，如非空，为错误原因签名失败
	 *              参数格式校验错误
	 * @return
	 */
	@PostMapping(value = "/wxpay/notify")
	public String wxPayNotify(@RequestBody String xmlData){
		logger.info("wxPayNotify >>> wxPayNotify={}", xmlData);
		
		String return_msg = null;
		try {
			WxPayOrderNotifyResult wxPayOrderNotifyResult = WxPayOrderNotifyResult.fromXML(xmlData);
			
			String outTradeNo = wxPayOrderNotifyResult.getOutTradeNo();//内部订单号
			
			Order order = orderService.findShopOrderByUUID(outTradeNo);
			
			String returnCode = wxPayOrderNotifyResult.getReturnCode();//返回结果
			
			return_msg = "";
			
			if ("SUCCESS".equals(returnCode.toUpperCase())){//成功时处理
				
				logger.info("wxPayNotify >>> deal with success notify, order={}, result={}", order, wxPayOrderNotifyResult);
				
				//更新订单状态，并且记录支付时间
				orderService.updateOrderStatusToWaitingSend(order.getCustomerId(), order.getId(), OnlinePayType.wxpay.ordinal());
				
				//更新微信支付表数据
				wxPayService.saveOrderSuccessNotifyResult(wxPayOrderNotifyResult, order.getId());
				
				return_msg = WxPayOrderNotifyResponse.success("OK");
			}else {//失败时处理
				
				logger.error("wxPayNotify >>> deal with fail notify, order={}, result={}", order, wxPayOrderNotifyResult);
				
				wxPayService.saveOrderFailNotifyResult(wxPayOrderNotifyResult, order.getId());
				
				return_msg = WxPayOrderNotifyResponse.fail("OK");
			}
			
			logger.info("return_msg = {}", return_msg);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return return_msg;
	}
	
}
