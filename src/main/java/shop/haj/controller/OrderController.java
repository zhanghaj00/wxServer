package shop.haj.controller;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.haj.entity.*;
import shop.haj.entity.wxPay.WxPayConfig;
import shop.haj.entity.wxPay.request.WxPayRefundRequest;
import shop.haj.entity.wxPay.result.WxPayOrderCloseResult;
import shop.haj.entity.wxPay.result.WxPayRefundResult;
import shop.haj.service.OrderService;
import shop.haj.service.ShopService;
import shop.haj.service.WxPayService;
import shop.haj.utils.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: shop.ha.controller</p>
 * <p/>
 * <p>
 * Description: 订单管理接口
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/4/17
 */
@RestController
@RequestMapping(value = "/v1")
public class OrderController extends BaseController {

	private Logger logger = LogManager.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	@Autowired
	private ShopService shopService;

	@Autowired
	private WxPayService wxPayService;

	/**
	 * 根据买家ID来进行查询买家的全部订单
	 *
	 * @param customer_id 买家ID
	 * @return 返回订单简要信息列表，满足【订单】页面的全部显示字段
	 */
	@ApiOperation(value = "查找买家订单列表", notes = "根据买家ID来进行查询买家的全部订单")
	@GetMapping(value = "/manager/orders")
	public List<OrderListInfo> findOrderListByCustomerID(@RequestAttribute(value = "customer_id", required = false) int customer_id,
														 @RequestParam(value = "status", defaultValue = "0") int status,
														 @RequestParam(value = "from", defaultValue = "0") int from,
														 @RequestParam(value = "limit", defaultValue = "20") int to,
														 @RequestParam(value = "by", defaultValue = "order_id") String by,
														 @RequestParam(value = "sort", defaultValue = "desc") String sort) {
		Pagination page = new Pagination();
		/*page.setFrom(from);
		page.setLimit(to);
		page.setBy(by);
		page.setSort(sort);*/

		return orderService.findOrderListByCustomerID(customer_id, status, page);
	}

	/**
	 * 根据买家ID和店铺ID返回订单列表
	 * 查询买家在某店铺的全部订单
	 *
	 * @param shop_id
	 * @param customer_id
	 * @return
	 */
	@ApiOperation(value = "查找买家在某店的订单列表", notes = "根据买家ID和店铺ID返回订单列表")
	@GetMapping(value = "/customer/orders")
	public List<OrderListInfo> findOrderListByShopAndCustomerID(@RequestAttribute(value = "customer_id", required = false) int customer_id,
																@RequestHeader("shop_id") int shop_id,
																@RequestParam(value = "status", defaultValue = "0") int status,
																@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
																@RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
																@RequestParam(value = "by", defaultValue = "order_id") String by,
																@RequestParam(value = "sort", defaultValue = "desc") String sort) {

		Pagination page = new Pagination();
		page.setFrom(pageNum);
		page.setLimit(pageSize);
		page.setBy(by);
		page.setSort(sort);

		return orderService.findOrderListByShopAndCustomerID(shop_id, customer_id, status, page);
	}

	/**
	 * 查找订单详细信息
	 *
	 * @param order_id
	 * @return
	 */
	@ApiOperation(value = "查找订单详细信息", notes = "查找订单详细信息")
	@GetMapping(value = "/customer/orders/{order_id}")
	public Order findShopOrderByID(@PathVariable("order_id") int order_id) {

		return orderService.findShopOrderByID(order_id, DefaultPagination.getOrder());
	}

	/**
	 * 创建订单
	 *
	 * @param shop_id
	 * @param customer_id
	 * @param order
	 * @return
	 */
	@ApiOperation(value = "创建订单", notes = "创建订单")
	@PostMapping("/customer/orders")
	public Map<String,Object> addOrder(@RequestHeader("shop_id") String shop_id,
						  @RequestAttribute(value = "customer_id", required = false) String customer_id,
						  @RequestBody Order order) {

		order.setShopId(shop_id);
		order.setCustomerId(customer_id);

		//查询shop name
		Shop shop = shopService.findById(shop_id);
		if(null != shop){
			order.setShopName(shop.getName());
		}

		return rtnParam(0,orderService.addOrder(order));
	}

	@ApiOperation(value = "更新订单状态为待收货", notes = "当卖家进行发货处理时，订单状态变更为待收货.")
	@PatchMapping("/seller/orders/{order_id}/status/receive")
	public String updateOrderStatusToWaitingReceive(@PathVariable("order_id") int order_id,
													@RequestAttribute(value = "customer_id", required = false) int customer_id) {
		int result = orderService.updateOrderStatusToWaitingReceive(customer_id, order_id);
		return ResultUtil.getJson(result);
	}

	@ApiOperation(value = "更新订单状态为待评价", notes = "更新订单状态为待评价")
	@PatchMapping("/customer/orders/{order_id}/status/comments")
	public String updateOrderStatusToWaitingComments(@PathVariable("order_id") int order_id,
													 @RequestAttribute(value = "customer_id", required = false) int customer_id) {
		int result = orderService.updateOrderStatusToWaitingComments(customer_id, order_id);
		return ResultUtil.getJson(result);
	}

	@ApiOperation(value = "买家提交退款申请", notes = "买家提交退款申请，修改状态为退款中")
	@PutMapping("/customer/orders/{order_id}/status/refund")
	public String updateOrderStatusToRefund(@PathVariable("order_id") int order_id,
											@RequestAttribute(value = "customer_id", required = false) int customer_id,
											@RequestBody OrderRefund orderRefund) {

		logger.info("updateOrderStatusToRefund controller >>> ");

		orderRefund.setOrder_id(order_id);

		Order order = orderService.findShopOrderByID(order_id, DefaultPagination.getOrder());

		//订单状态校验, 允许订单状态为已付款或者待发货时允许退款
		if (orderRefund.getType() == OrderRefundType.REFUND_MONEY.ordinal() &&
				(order.getStatus() != OrderStatus.WAITING_SEND.ordinal())) {
			return ResultUtil.getJson(0);
		} else if (orderRefund.getType() == OrderRefundType.REFUND_GOODS.ordinal() &&
				(order.getStatus() != OrderStatus.WAITING_RECEIVE.ordinal() ||
						order.getStatus() != OrderStatus.WAITING_COMMENT.ordinal() ||
						order.getStatus() != OrderStatus.FINISHED.ordinal())) {
			return ResultUtil.getJson(0);
		}

		//判断是否已经存在退款记录
		if (orderRefund.getType() == OrderRefundType.REFUND_MONEY.ordinal()) {
			//1. 判断如果是退款，则唯一是： order_id
			OrderRefund exist_orderRefund = orderService.findOrderRefund(order_id);
			if (exist_orderRefund != null && exist_orderRefund.getOrder_id() > 0) {
				return ResultUtil.getJson(0);
			}
		} else if (orderRefund.getType() == OrderRefundType.REFUND_GOODS.ordinal()) {
			//2. 如果是退货，则唯一是：order_id+goods_id
			OrderRefund exist_orderRefund = orderService.findOrderRefund(order_id, orderRefund.getGoods_id());
			if (exist_orderRefund != null && exist_orderRefund.getOrder_id() > 0) {
				return ResultUtil.getJson(0);
			}
		}

		orderRefund.setOrder_uuid(order.getUuid());

		//生成订单号, 毫秒时间戳(17位) + 7位随机数 + 3位随机字母, 总共27位
		DateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String outRefundNo = format1.format(System.currentTimeMillis()) + "" + RandomUtils.nextInt(1000000, 9999999) + RandomStringUtils.randomAlphabetic(5).toUpperCase();

		orderRefund.setRefund_uuid(outRefundNo);
		orderRefund.setOrder_status(order.getStatus());
		orderRefund.setStatus(OrderRefundStatus.WAITING_AGREE.ordinal());

		//设置退款/退货的价格
		if (OrderRefundType.REFUND_MONEY.ordinal() == orderRefund.getType()) {//退款
			orderRefund.setRefund_price(order.getFinalPrice());
		} else if (OrderRefundType.REFUND_GOODS.ordinal() == orderRefund.getType()) {//退货
			//退款指定商品的价格
			List<OrderGoodsInfo> orderGoodsInfos = order.getOrderGoodsInfos();
			for (OrderGoodsInfo orderGoodsInfo : orderGoodsInfos) {
				/*if (orderGoodsInfo.getGoodsId() == orderRefund.getGoods_id()) {
					orderRefund.setRefund_price(orderGoodsInfo.getGoodsPrice());
				}*/
			}
		}

		//将订单状态更新为退款/售后
		int status = orderService.updateOrderStatusToRefund(orderRefund, customer_id);

		return ResultUtil.getJson(status);
	}

	/**
	 * 取消退款申请, 当用户进行退款处理后，在卖家未处理之前，可手工撤销退款申请
	 * <p>
	 * 1. 服务端需要校验：
	 * a. 退单表否存在退款记录
	 * b. 退单表状态是否为*等待卖家处理*，如果卖家已处理则不允许取消退单
	 * 2. 服务端需要将该退款记录状态变更为*关闭*
	 *
	 * @param order_id
	 * @param customer_id
	 * @return
	 */
	@ApiOperation(value = "取消退款申请", notes = "当用户进行退款处理后，在卖家未处理之前，可手工撤销退款申请")
	@PutMapping("/customer/orders/{order_id}/status/cancelRefundMoney")
	public String cancelRefundMoneyApply(@PathVariable("order_id") int order_id,
										 @RequestAttribute(value = "customer_id", required = false) int customer_id,
										 @RequestBody OrderRefund orderRefund) {

		logger.info("cancelRefundMoneyApply controller >>> order_id={}, customer_id={}", order_id, customer_id);

		//退单表否存在退款记录
		OrderRefund exist_orderRefund = orderService.findOrderRefund(order_id);
		if (exist_orderRefund == null) {
			logger.error("cancelRefundMoneyApply controller >>> customer_id={}, order_id={} 退单表未发现记录，用户无法进行取消退款/售后申请操作.", customer_id, order_id);
			return ResultUtil.getJson(0);
		}

		//检查状态，确认该退
		if (exist_orderRefund.getStatus() == OrderRefundStatus.WAITING_AGREE.ordinal()) {
			logger.error("cancelRefundMoneyApply controller >>> customer_id={}, order_id={} 退单表状态异常，用户无法进行取消退款/售后申请操作.", customer_id, order_id);
			return ResultUtil.getJson(0);
		}

		//退单表状态是否为*等待卖家处理*，如果卖家已处理则不允许取消退单
		if (exist_orderRefund.getIs_agree() > 0) {
			logger.error("cancelRefundMoneyApply controller >>> customer_id={}, order_id={} 卖家已经处理，不允许撤销退单。", customer_id, order_id);
			return ResultUtil.getJson(0);
		}

		int result = orderService.updateOrderRefundStatusToClose(orderRefund.getRefund_uuid());

		return ResultUtil.getJson(result);
	}

	@ApiOperation(value = "取消退货申请", notes = "当用户进行退货处理后，在卖家未处理之前，可手工撤销退货申请")
	@PutMapping("/customer/orders/{order_id}/status/cancelRefundGoods")
	public String cancelRefundGoodsApply(@PathVariable("order_id") int order_id,
										 @RequestAttribute(value = "customer_id", required = false) int customer_id,
										 @RequestBody OrderRefund orderRefund) {

		logger.info("cancelRefundGoodsApply controller >>> order_id={}, customer_id={}", order_id, customer_id);

		//退单表否存在退货记录
		OrderRefund exist_orderRefund = orderService.findOrderRefund(order_id, orderRefund.getGoods_id());
		if (exist_orderRefund == null) {
			logger.error("cancelRefundGoodsApply controller >>> customer_id={}, order_id={} 退单表未发现记录，用户无法进行取消退款/售后申请操作.", customer_id, order_id);
			return ResultUtil.getJson(0);
		}

		//退单表状态是否为*等待卖家处理*，如果卖家已处理则不允许取消退单
		if (exist_orderRefund.getIs_agree() > 0) {
			logger.error("cancelRefundGoodsApply controller >>> customer_id={}, order_id={} 卖家已经处理，不允许撤销退单。", customer_id, order_id);
			return ResultUtil.getJson(0);
		}

		int result = orderService.updateOrderRefundStatusToClose(orderRefund.getRefund_uuid());

		return ResultUtil.getJson(result);
	}

	@ApiOperation(value = "卖家确认退款", notes = "当卖家同意退款请求时，系统将正式开始进行退款流程处理")
	@PatchMapping("/seller/orders/{order_id}/status/refund_money")
	public String replyForRefundMoney(@PathVariable("order_id") int order_id) {

//		//校验支付渠道，根据不同的订单支付渠道采取不同的退款方式
		Order order = orderService.findShopOrderByID(order_id, DefaultPagination.getOrder());

		//查找退单信息
		OrderRefund orderRefund = orderService.findOrderRefund(order_id);


		//申请退款，并确认退款
		WxPayConfig config = null;// wxPayService.getWxConfig(order.getShopId());

		//需要证书
		if (config.getSslContext() == null) {
			try {
				config.initSSLContext();
			} catch (Exception e) {
				logger.error(e.toString(), e);
				return "";
			}
		}

		try {
			WxPayRefundRequest request = WxPayRefundRequest.newBuilder()
					.outRefundNo(orderRefund.getRefund_uuid())
					.outTradeNo(order.getUuid())
					.totalFee((int) (order.getFinalPrice() * 100))
					.refundFee((int) (order.getFinalPrice() * 100))
					.build();

			request.checkAndSign(config);

			WxPayRefundResult result = wxPayService.refund(request, config.getSslContext(), config.getMchKey());

			logger.info("refund response >>> {}", result.toString());

		} catch (WxErrorException e) {
			logger.error(e.toString(), e);
		}

		//退款接口调用成功后，更新状态
		int status =  1;//orderService.updateOrderStatusToFinish(order.getCustomerId(), order_id);

		return ResultUtil.getJson(status);
	}

	@ApiOperation(value = "买家主动关闭未支付订单", notes = "买家提交订单但不想购买后，可主动取消订单")
	@PatchMapping("/customer/orders/{order_id}/status/close")
	public String updateOrderStatusToCloseByCustomer(@PathVariable("order_id") int order_id,
													 @RequestAttribute(value = "customer_id", required = false) int customer_id) {

		Order order = orderService.findShopOrderByID(order_id, DefaultPagination.getOrder());

		WxPayConfig config = null; //wxPayService.getWxConfig(order.getShopId());

		//已经提交未支付的订单,则需要关闭微信端订单
		if (wxPayService.isPrepay(order_id)) {
			try {
				WxPayOrderCloseResult result = wxPayService.closeOrder(order.getUuid(), config.getMchKey());

				logger.info("updateOrderStatusToCloseByCustomer >>> result={}", result.toString());

				if ("SUCCESS".equals(result.getResultCode())) {//当关闭成功时，更新订单数据
					int status = orderService.updateOrderStatusToClose(customer_id, order_id);
					return ResultUtil.getJson(status);
				}

			} catch (WxErrorException e) {
				logger.error(e.toString(), e);
			}
		} else {
			int status = orderService.updateOrderStatusToClose(customer_id, order_id);
			return ResultUtil.getJson(status);
		}

		return ResultUtil.getJson(0);
	}


	@ApiOperation(value = "卖家订单统计", notes = "卖家的订单状态统计")
	@GetMapping("/seller/count/order")
	public Map<String, Object> countOrderNumByStatus(@RequestHeader("shop_id") String shop_id) {

		if (StringUtils.isEmpty(shop_id)) return rtnParam(0, null);
		//orderService.findOrderListByCustomerID();


		return rtnParam(0, new ArrayList<>());

	}

	@ApiOperation(value = "卖家日均统计", notes = "卖家的日均订单状态统计")
	@GetMapping("/seller/count/{count_type}")
	public Map<String, Object> countOrderNumByTime(@RequestHeader("shop_id") String shop_id,
												   @PathVariable(value = "count_type") String count_type) {

		if (StringUtils.isEmpty(shop_id)) return rtnParam(0, null);
		switch (count_type) {
			case "TODAY":
				ImmutableMap result = new ImmutableMap.Builder<>().put("income",10).build();
				return rtnParam(0,result);
			case "MONTH":
				ImmutableMap result1 = new ImmutableMap.Builder<>().put("income",20).build();
				return rtnParam(0,result1);
			default:
				ImmutableMap result3 = new ImmutableMap.Builder<>().put("income",4).build();
				return rtnParam(0,result3);

		}

	}
}