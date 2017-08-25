package shop.haj.controller;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import shop.haj.entity.*;
import shop.haj.entity.wxPay.WxPayConfig;
import shop.haj.entity.wxPay.request.WxPayRefundRequest;
import shop.haj.entity.wxPay.result.WxPayRefundResult;
import shop.haj.service.*;
import shop.haj.utils.*;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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

	@Autowired
	private CustomerService customerService;

	@Autowired
	private DeliveryService deliveryService;

	/**
	 * 根据卖家ID来进行查询买家的全部订单
	 *
	 * @param shop_id 卖家Id
	 * @return 返回订单简要信息列表，满足【订单】页面的全部显示字段
	 */
	@ApiOperation(value = "查找买家订单列表", notes = "根据买家ID来进行查询买家的全部订单")
	@GetMapping(value = "/seller/orders")
	public Map<String,Object> findOrderListByShopId(@RequestHeader(value = "shop_id", required = false) String shop_id,
														@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
														@RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
														@RequestParam(value = "by", defaultValue = "order_id") String by,
														@RequestParam(value = "sort", defaultValue = "desc") String sort,
														Order order) {
		Pagination page = new Pagination();
		page.setFrom(pageNum);
		page.setLimit(pageSize);
		page.setBy(by);
		page.setSort(sort);
		order.setShopId(shop_id);
		if(order.getStatus()!=null && 0 == order.getStatus()){
			order.setStatus(null);
		}
		return rtnParam(0, orderService.findOrderListByPage(order, page));
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
	public Map<String,Object> findOrderListByShopAndCustomerID(@RequestAttribute(value = "customer_id", required = false) String customer_id,
																@RequestHeader("shop_id") String shop_id,
																@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
																@RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
																@RequestParam(value = "by", defaultValue = "order_id") String by,
																@RequestParam(value = "sort", defaultValue = "desc") String sort,
															   Order order) {

		Pagination page = new Pagination();
		page.setFrom(pageNum);
		page.setLimit(pageSize);
		page.setBy(by);
		page.setSort(sort);
		order.setShopId(shop_id);
		order.setCustomerId(customer_id);
		if(order.getStatus()!=null && 0 == order.getStatus()){
			order.setStatus(null);
		}
		return rtnParam(0, orderService.findOrderListByPage(order, page));
	}

	/**
	 * 查找订单详细信息
	 *
	 * @return
	 */
	@ApiOperation(value = "查找订单运费信息", notes = "查找订单运费信息")
	@PostMapping(value = "/customer/orders/delivery")
	public Map<String,Object> findShopOrderByID(@RequestHeader("shop_id")String shop_id,@RequestAttribute("customer_id")String customerId
												,@RequestBody Map<String,Object> requestBody) {


		Set<String> innerCid = new HashSet<>();
		Map<String,List<Delivery>> delilveryList = new HashMap<>();
		if(!CollectionUtils.isEmpty(requestBody)){
			Object goodList = requestBody.get("goodsList");
			if(goodList instanceof List){
				for(Object c:(List)goodList){

					innerCid.add(((Map) c).get("innerCid").toString());
				}
			}
		}
		//List<Delivery> delivers = Lists.newArrayList();

		for(String cid:innerCid){
			Delivery condition = new Delivery();
			condition.setInnerCid(cid);
			List<Delivery> result = deliveryService.findAll(condition);

			delilveryList.put(result.get(0).getInnerCidName(),result);

		}
		//delivers.add(new Delivery("1","顺丰","顺丰",true,10.0));
		/*delivers.add(new Deliver("2","天天","天天",false,12.66));
		delivers.add(new Deliver("3","神通","神通",false,125.6));
		delivers.add(new Deliver("4","光通","光通",false,123.6));
		delivers.add(new Deliver("5","快递沙发","快递沙发",false,121.6));*/

		ImmutableMap<String,Object> result = new ImmutableMap.Builder<String,Object>().put("delivery",true).put("delilveryList", delilveryList).build();
		return rtnParam(0,result);
	}

	/**
	 * 查找查找邮运方式
	 *
	 * @param order_id
	 * @return
	 */
	@ApiOperation(value = "查找订单详细信息", notes = "查找订单详细信息")
	@GetMapping(value = {"/customer/orders/{order_id}","/seller/orders/{order_id}"})
	public Map<String,Object> findShopOrderByID(@PathVariable("order_id") String order_id) {

		return rtnParam(0, orderService.findShopOrderByID(order_id, DefaultPagination.getOrder()));
	}

	@ApiOperation(value = "修改订单价格",notes = "修改定单价格")
	@PostMapping(value = "/seller/orders/{order_id}/modify_money")
	public Map<String ,Object> modifyOrder(@PathVariable("order_id") String order_id, @RequestBody ModifyPrice modifyPrice){

		Order order = orderService.findShopOrderByID(order_id, DefaultPagination.getOrder());

		if(null != modifyPrice){
			if(null != modifyPrice.getModifyPrice()){
				order.setFinalPrice(modifyPrice.getModifyPrice());
			}
			if(null != modifyPrice.getModifyPost()){
				order.setPostFee(modifyPrice.getModifyPost());
			}

		}
		return  rtnParam(0,orderService.updateOrder(order));
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
	public Map<String,Object> updateOrderStatusToWaitingReceive(@PathVariable("order_id") String order_id,
													@RequestAttribute(value = "customer_id", required = false) String customer_id) {
		return rtnParam(0,orderService.updateOrderStatusToWaitingReceive(customer_id, order_id));
	}

	@ApiOperation(value = "更新订单状态为待评价", notes = "更新订单状态为待评价")
	@PatchMapping("/customer/orders/{order_id}/status/comments")
	public Map<String,Object> updateOrderStatusToWaitingComments(@PathVariable("order_id") String order_id,
													 @RequestAttribute(value = "customer_id", required = false) String customer_id) {
		return rtnParam(0,orderService.updateOrderStatusToWaitingComments(customer_id, order_id));
	}

	@ApiOperation(value = "买家提交退款申请", notes = "买家提交退款申请，修改状态为退款中")
	@PutMapping("/customer/orders/{order_id}/status/refund")
	public Map<String,Object> updateOrderStatusToRefund(@PathVariable("order_id") String order_id,
											@RequestAttribute(value = "customer_id", required = false) String customer_id,
											@RequestBody OrderRefund orderRefund) {

		logger.info("updateOrderStatusToRefund controller >>> ");

		//orderRefund.setOrder_id(order_id);

		Order order = orderService.findShopOrderByID(order_id, DefaultPagination.getOrder());

		//订单状态校验, 允许订单状态为已付款或者待发货时允许退款
		if (orderRefund.getType() == OrderRefundType.REFUND_MONEY.ordinal() &&
				(order.getStatus() != OrderStatus.WAITING_SEND.ordinal())) {
			return rtnParam(53001,"订单状态不允许退款");
		} else if (orderRefund.getType() == OrderRefundType.REFUND_GOODS.ordinal() &&
				(order.getStatus() != OrderStatus.WAITING_RECEIVE.ordinal() ||
						order.getStatus() != OrderStatus.WAITING_COMMENT.ordinal() ||
						order.getStatus() != OrderStatus.FINISHED.ordinal())) {
			return rtnParam(53001, "订单状态不允许退款");
		}

		//判断是否已经存在退款记录
		if (orderRefund.getType() == OrderRefundType.REFUND_MONEY.ordinal()) {
			//1. 判断如果是退款，则唯一是： order_id
			OrderRefund exist_orderRefund = orderService.findOrderRefund(order_id);
			if (exist_orderRefund != null && exist_orderRefund.getOrder_id() > 0) {
				return rtnParam(53001, "已经退款中");
			}
		} else if (orderRefund.getType() == OrderRefundType.REFUND_GOODS.ordinal()) {
			//2. 如果是退货，则唯一是：order_id+goods_id
			OrderRefund exist_orderRefund = null ; //orderService.findOrderRefund(order_id, orderRefund.getGoods_id());
			if (exist_orderRefund != null && exist_orderRefund.getOrder_id() > 0) {
				return rtnParam(53001, "退款状态不一样");
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

		return rtnParam(0, orderService.updateOrderStatusToRefund(orderRefund, customer_id));
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
	public Map<String,Object> cancelRefundMoneyApply(@PathVariable("order_id") String order_id,
										 @RequestAttribute(value = "customer_id", required = false) String customer_id,
										 @RequestBody OrderRefund orderRefund) {

		logger.info("cancelRefundMoneyApply controller >>> order_id={}, customer_id={}", order_id, customer_id);

		//退单表否存在退款记录
		OrderRefund exist_orderRefund = orderService.findOrderRefund(order_id);
		if (exist_orderRefund == null) {
			logger.error("cancelRefundMoneyApply controller >>> customer_id={}, order_id={} 退单表未发现记录，用户无法进行取消退款/售后申请操作.", customer_id, order_id);
			return rtnParam(53001, "不存在退款");
		}

		//检查状态，确认该退
		if (exist_orderRefund.getStatus() == OrderRefundStatus.WAITING_AGREE.ordinal()) {
			logger.error("cancelRefundMoneyApply controller >>> customer_id={}, order_id={} 退单表状态异常，用户无法进行取消退款/售后申请操作.", customer_id, order_id);
			return rtnParam(53001, "退单状态异常");
		}

		//退单表状态是否为*等待卖家处理*，如果卖家已处理则不允许取消退单
		if (exist_orderRefund.getIs_agree() > 0) {
			logger.error("cancelRefundMoneyApply controller >>> customer_id={}, order_id={} 卖家已经处理，不允许撤销退单。", customer_id, order_id);
			return rtnParam(53001, "卖家已经处理");
		}


		return rtnParam(0, orderService.updateOrderRefundStatusToClose(orderRefund.getRefund_uuid()));
	}

	@ApiOperation(value = "取消退货申请", notes = "当用户进行退货处理后，在卖家未处理之前，可手工撤销退货申请")
	@PutMapping("/customer/orders/{order_id}/status/cancelRefundGoods")
	public Map<String,Object> cancelRefundGoodsApply(@PathVariable("order_id") String order_id,
										 @RequestAttribute(value = "customer_id", required = false) String customer_id,
										 @RequestBody OrderRefund orderRefund) {

		logger.info("cancelRefundGoodsApply controller >>> order_id={}, customer_id={}", order_id, customer_id);

		//退单表否存在退货记录
		OrderRefund exist_orderRefund = null;//orderService.findOrderRefund(order_id, orderRefund.getGoods_id());
		if (exist_orderRefund == null) {
			logger.error("cancelRefundGoodsApply controller >>> customer_id={}, order_id={} 退单表未发现记录，用户无法进行取消退款/售后申请操作.", customer_id, order_id);
			return rtnParam(53001, "没有退单状态");
		}

		//退单表状态是否为*等待卖家处理*，如果卖家已处理则不允许取消退单
		if (exist_orderRefund.getIs_agree() > 0) {
			logger.error("cancelRefundGoodsApply controller >>> customer_id={}, order_id={} 卖家已经处理，不允许撤销退单。", customer_id, order_id);
			return rtnParam(53001, "卖家已经处理");
		}

		return rtnParam(0, orderService.updateOrderRefundStatusToClose(orderRefund.getRefund_uuid()));
	}

	@ApiOperation(value = "卖家确认退款", notes = "当卖家同意退款请求时，系统将正式开始进行退款流程处理")
	@PatchMapping("/seller/orders/{order_id}/status/refund_money")
	public Map<String,Object> replyForRefundMoney(@PathVariable("order_id") String order_id) {

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
				return rtnParam(53001, e.getMessage());
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

		return rtnParam(0, orderService.updateOrderStatusToFinish(order.getCustomerId(), order_id));
	}

	@ApiOperation(value = "买家主动关闭未支付订单", notes = "买家提交订单但不想购买后，可主动取消订单")
	@PatchMapping("/customer/orders/{order_id}/status/close")
	public Map<String,Object> updateOrderStatusToCloseByCustomer(@PathVariable("order_id") String order_id,
													 @RequestAttribute(value = "customer_id", required = false) String customer_id) {

		Order order = orderService.findShopOrderByID(order_id, DefaultPagination.getOrder());

		WxPayConfig config = null; //wxPayService.getWxConfig(order.getShopId());

		//已经提交未支付的订单,则需要关闭微信端订单
		/*if (wxPayService.isPrepay(order_id)) {
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
		}*/

		return rtnParam(53001, "");
	}


	@ApiOperation(value = "卖家订单统计", notes = "卖家的订单状态统计")
	@GetMapping("/seller/count/order")
	public Map<String, Object> countOrderNumByStatus(@RequestHeader("shop_id") String shop_id) {

		if (StringUtils.isEmpty(shop_id)) return rtnParam(0, null);
		//orderService.findOrderListByCustomerID();
		return rtnParam(0, orderService.findOrderListShopId(shop_id));

	}

	@ApiOperation(value = "卖家日均统计", notes = "卖家的日均订单状态统计")
	@GetMapping("/seller/count/{count_type}")
	public Map<String, Object> countOrderNumByTime(@RequestHeader("shop_id") String shop_id,
												   @PathVariable(value = "count_type") String count_type) {

		if (StringUtils.isEmpty(shop_id)) return rtnParam(0, null);
		switch (count_type) {
			case "TODAY":
				return countIncome(orderService.findOrderListShopId(shop_id), count_type);
			case "MONTH":
				return countIncome(orderService.findOrderListShopId(shop_id), count_type);
			default:
				return countIncome(orderService.findOrderListShopId(shop_id), count_type);

		}

	}


	/**
	 * 卖家端统计卖家信息
	 * @param shop_id
	 * @return
	 */
	@ApiOperation(value = "统计买家", notes = "统计买家信息")
	@GetMapping(value = "/seller/count/customer/time")
	public Map<String,Object> countTime(@RequestHeader("shop_id") String shop_id,
										@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
										@RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
										@RequestParam(value = "by", defaultValue = "order_id") String by,
										@RequestParam(value = "sort", defaultValue = "desc") String sort){
		List<CustomerCount> orders = orderService.countTimeGroupByCustomerId(shop_id);
		return rtnParam(0, orders);
	}
	/**
	 * 卖家端统计卖家信息
	 * @param shop_id
	 * @return
	 */
	@ApiOperation(value = "统计买家", notes = "统计买家信息")
	@GetMapping(value = "/seller/count/customer/times")
	public Map<String,Object> countTimes(@RequestHeader("shop_id") String shop_id,
										 @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
										 @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
										 @RequestParam(value = "by", defaultValue = "order_id") String by,
										 @RequestParam(value = "sort", defaultValue = "desc") String sort){
		List<CustomerCount> orders = orderService.countTimesGroupByCustomerId(shop_id);
		return rtnParam(0, orders);
	}
	/**
	 * 卖家端统计卖家信息
	 * @param shop_id
	 * @return
	 */
	@ApiOperation(value = "统计买家", notes = "统计买家信息")
	@GetMapping(value = "/seller/count/customer/price")
	public Map<String,Object> countPrice(@RequestHeader("shop_id") String shop_id,
										 @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
										 @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
										 @RequestParam(value = "by", defaultValue = "order_id") String by,
										 @RequestParam(value = "sort", defaultValue = "desc") String sort){
		List<CustomerCount> orders = orderService.countPriceGroupByCustomerId(shop_id);
		return rtnParam(0,orders);
	}

	private Map<String, Object> countIncome(List<Order> orders,String count_type){
		Double dayIncome = 0d;
		Double montyIncome = 0d;
		Integer dayPayOrder = 0;
		Integer monthPayOrder = 0;
		Integer paymentGoods = 0;

		for(Order o:orders){
			if(TimeUtil.compareToday(o.getOrderTime())){
				dayIncome +=o.getFinalPrice();
				dayPayOrder++;
				if(o.getStatus()!= null && o.getStatus()==3){
					paymentGoods++;
				}
			}
			montyIncome+=o.getFinalPrice();
			monthPayOrder++;

		}
		switch (count_type) {
			case "TODAY":
				ImmutableMap result = new ImmutableMap.Builder<>().put("income",dayIncome).put("paymentGoods",paymentGoods).put("visitShopLog",99).put("paymentOrder",dayPayOrder).build();
				return rtnParam(0,result);
			case "MONTH":
				ImmutableMap result1 = new ImmutableMap.Builder<>().put("income",montyIncome).put("visitShopLog",99).put("paymentOrder", monthPayOrder).build();
				return rtnParam(0,result1);
			default:
				ImmutableMap result3 = new ImmutableMap.Builder<>().put("income",dayIncome).put("visitShopLog", 99).put("paymentOrder", dayPayOrder).build();
				return rtnParam(0,result3);

		}
	}

	static class  ModifyPrice implements Serializable{

		private Double modifyPrice;

		private Double modifyPost;


		public Double getModifyPost() {
			return modifyPost;
		}

		public void setModifyPost(Double modifyPost) {
			this.modifyPost = modifyPost;
		}

		public Double getModifyPrice() {
			return modifyPrice;
		}

		public void setModifyPrice(Double modifyPrice) {
			this.modifyPrice = modifyPrice;
		}
	}
}