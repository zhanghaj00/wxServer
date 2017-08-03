package shop.haj.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.haj.entity.*;
import shop.haj.manage.CacheManage;
import shop.haj.mongo_repository.MongoGoodsRepository;
import shop.haj.mongo_repository.MongoOrderListRepository;
import shop.haj.mongo_repository.MongoOrderRepository;
import shop.haj.repository.OrderRefundRepository;
import shop.haj.service.OrderService;

import java.util.List;

/**
 * <p>Title: shop.ha.service.impl</p>
 * <p/>
 * <p>
 * Description: OrderServiceImpl
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/4/17
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	
	private Logger logger = LogManager.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private MongoOrderRepository mongoOrderRepository;
	
	@Autowired
	private MongoOrderListRepository mongoOrderListRepository;
	
	@Autowired
	private MongoGoodsRepository mongoGoodsRepository;
	
	@Autowired
	private CacheManage cacheManage;
	
	@Autowired
	private OrderRefundRepository orderRefundRepository;
	
	/**
	 * 查找某买家在某店铺的全部订单列表
	 * @param shop_id
	 * @param customer_id
	 * @return
	 */
	@Override
	public List<OrderListInfo> findOrderListByShopAndCustomerID(int shop_id, int customer_id, int status, Pagination page) {

		/*List<OrderListSingleInfo> orderListSingleInfoList = mongoOrderListRepository.findByShopIdAndCustomerIdAndStatus(shop_id, customer_id, status, page).getContent();

		//处理订单数据
		if(orderListSingleInfoList == null || orderListSingleInfoList.size() == 0) return Lists.newArrayList();

		return this.groupByOrderListInfo(orderListSingleInfoList);*/
		return null;
	}
	
	/**
	 * 查找某买家全部订单列表
	 *
	 * @param customer_id
	 * @return
	 */
	@Override
//	@Cacheable(value = "orderPages", key = "{#customer_id, #status + '_' + #page.orderByLimitString}")
	public List<OrderListInfo> findOrderListByCustomerID(int customer_id, int status, Pagination page) {
		/*List<OrderListSingleInfo> orderListSingleInfoList = orderListRepository.findOrderListSingleByCustomerID(customer_id, status, page);

		//处理订单数据
		if(orderListSingleInfoList == null || orderListSingleInfoList.size() == 0) return Lists.newArrayList();

		cacheManage.addOrderPageKeysMapData(customer_id, status + "_") ;//+ page.getOrderByLimitString());

		return this.groupByOrderListInfo(orderListSingleInfoList);*/
		return null;
	}
	
	/**
	 * 关联订单和商品信息
	 * @param orderListSingleInfoList
	 * @return
	 */
	private List<OrderListInfo> groupByOrderListInfo(List<OrderListSingleInfo> orderListSingleInfoList){
		
		/*//订单号集合
		List<Integer> orderIDList = Lists.newArrayList();
		int temp_order_id = 0;
		
		//计算订单个数
		for (OrderListSingleInfo singleInfo : orderListSingleInfoList) {
			//当前订单号与临时订单号不一致，则算作新订单
			if(singleInfo.getOrder_id() != temp_order_id){
				orderIDList.add(singleInfo.getOrder_id());
				temp_order_id = singleInfo.getOrder_id();
			}
		}
		
		//Key为order_id，value为该order_id的所有商品信息
		List<OrderListInfo> orderListInfos = Lists.newArrayList();
		
		//按订单ID个数进行遍历
		for (Integer order_id : orderIDList) {
			
			OrderListInfo orderListInfo = new OrderListInfo();
			List<OrderGoodsInfo> orderGoodsInfos = Lists.newArrayList();
			
			for (OrderListSingleInfo singleInfo : orderListSingleInfoList) {
				if(order_id != singleInfo.getOrder_id()) continue;
				//订单信息判断
				orderListInfo.setOrder_id(order_id);
				orderListInfo.setOrder_time(singleInfo.getOrder_time());
				orderListInfo.setStatus(singleInfo.getStatus());
				orderListInfo.setFinal_price(singleInfo.getFinal_price());
				orderListInfo.setShop_id(singleInfo.getShop_id());
				orderListInfo.setShop_name(singleInfo.getShop_name());
				
				OrderGoodsInfo orderGoodsInfo = new OrderGoodsInfo();
				orderGoodsInfo.setGoods_id(singleInfo.getGoods_id());
				orderGoodsInfo.setGoods_name(singleInfo.getGoods_name());
				orderGoodsInfo.setImage_url(singleInfo.getImage_url());
				orderGoodsInfo.setGoods_price(singleInfo.getGoods_price());
				orderGoodsInfo.setCount(singleInfo.getCount());
				orderGoodsInfo.setGoods_sku(singleInfo.getGoods_sku());
				
				orderGoodsInfos.add(orderGoodsInfo);
			}
			orderListInfo.setOrderGoodsInfos(orderGoodsInfos);
			orderListInfos.add(orderListInfo);
		}
		
		return orderListInfos;*/
		return null;
	}
	
	
	@Override
//	@Cacheable(value = "order", key = "#order_id")
	public Order findShopOrderByID(int order_id, Pagination page) {
		/*Order order = orderRepository.findShopOrderByID(order_id);
		
		fillOrderGoodsInfo(order);
		
		return order;*/
		return null;
	}
	
	/**
	 * 根据订单号查询订单信息
	 *
	 * @param uuid
	 * @return
	 */
	@Override
//	@Cacheable(value = "order", key = "#uuid")
	public Order findShopOrderByUUID(String uuid) {
		
		/*Order order = orderRepository.findShopOrderByUUID(uuid);
		
		fillOrderGoodsInfo(order);
		
		return order;*/
		return null;
	}
	
	/**
	 * 补充订单中的商品信息
	 * @param order
	 */
	private void fillOrderGoodsInfo(Order order){
		
		/*List<OrderListSingleInfo> orderListSingleInfos = orderListRepository.findOrderGoodsInfoListByOrderID(order.getOrder_id(), DefaultPagination.getOrder());
		
		List<OrderGoodsInfo> orderGoodsInfos = Lists.newArrayList();
		
		for (OrderListSingleInfo orderListSingleInfo : orderListSingleInfos) {
			
			OrderGoodsInfo orderGoodsInfo = new OrderGoodsInfo();
			
			orderGoodsInfo.setGoods_id(orderListSingleInfo.getGoods_id());
			orderGoodsInfo.setGoods_name(orderListSingleInfo.getGoods_name());
			orderGoodsInfo.setImage_url(orderListSingleInfo.getImage_url());
			orderGoodsInfo.setGoods_price(orderListSingleInfo.getGoods_price());
			orderGoodsInfo.setCount(orderListSingleInfo.getCount());
			orderGoodsInfo.setGoods_sku(orderListSingleInfo.getGoods_sku());
			
			orderGoodsInfos.add(orderGoodsInfo);
		}
		//设置订单的商品信息
		order.setOrderGoodsInfos(orderGoodsInfos);
		
		order.setShop_name(orderListSingleInfos.get(0).getShop_name());*/
	}
	
	@Override
	public Order addOrder(Order order) {
		
		/*//当支付方式为线下支付时，初始支付状态为待发货， 否则初始状态为待支付
		if(order.getPayment_type() == OrderPayType.OFFLINEPAY.ordinal()){
			order.setStatus(OrderStatus.WAITING_SEND.ordinal());
		}else {
			order.setStatus(OrderStatus.WAITING_PAYMENT.ordinal());
		}
		
		
		DateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		
		//生成订单号, 毫秒时间戳(17位) + 7位随机数 + 3位随机字母, 总共27位
		String uuid = format1.format(System.currentTimeMillis()) + "" + RandomUtils.nextInt(1000000, 9999999) + RandomStringUtils.randomAlphabetic(5).toUpperCase();
		
		order.setUuid(uuid);
		
		//生成订单时间
		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		order.setOrder_time(format2.format(System.currentTimeMillis()));
		order.setUpdate_time(format2.format(System.currentTimeMillis()));
		
		//计算订单价格
		double final_price = 0;
		for (OrderGoodsInfo orderGoodsInfo : order.getOrderGoodsInfos()) {
			Goods goods = goodsRepository.findGoodsByID(order.getShop_id(), orderGoodsInfo.getGoods_id());
			
			//增加商品销量
			goods.setSales_volume(goods.getSales_volume()+1);
			goodsRepository.updateGoods(goods);
			
			//减少库存
			
			//清除缓存
			clearShopPageCache(goods.getShopId());
			clearGoodsCache(goods.getId());
			
			orderGoodsInfo.setGoods_price(goods.getSell_price());
			orderGoodsInfo.setGoods_name(goods.getName());
			
			List<Image> images = goodsRepository.findImagesByGoodsID(orderGoodsInfo.getGoods_id());
			
			if(images != null && images.size() > 0) {
				orderGoodsInfo.setImage_url(images.get(0).getUrl());//选择首张图片作为默认
			}
			
			final_price += goods.getSell_price() * orderGoodsInfo.getCount();
			logger.info("订单统计当前价格：店铺:{}, 买家:{}, 商品:{}，单价:{}, 个数:{}，当前总价:{}",
					order.getShop_id(), order.getCustomer_id(), goods.getId(), goods.getSell_price(),
					orderGoodsInfo.getCount(), final_price);
		}
		logger.info("订单统计总价：店铺:{}, 买家:{}, 最终价格:{}",
				order.getShop_id(), order.getCustomer_id(), final_price);
		
		order.setFinal_price(final_price);
		
		
		if(order.getCoupon_used_id() > 0){
			//计算优惠券优惠后价格
			
		}
		
		orderRepository.addOrder(order);
		
		orderListRepository.addOrderListGoodsInfo(order);
		
		logger.info("addOrder >>> {}", order);
		
		//清空该买家订单缓存
		clearOrderPagesCache(order.getCustomer_id());
		
		return order;*/
		return null;
	}
	
	/**
	 * 当买家付款，将订单状态修改为待发货
	 *
	 * @param order_id
	 * @return
	 */
	@Override
	@Transactional
	@CacheEvict(value = "order", key = "#order_id")
	public int updateOrderStatusToWaitingSend(String customer_id, String order_id, int onlinePayType) {
		
		/*logger.info("updateOrderStatusToWaitingSend >>> customer_id={}, order_id={}", customer_id, order_id);
		
		orderRepository.updateOrderStatusToWaitingSend(order_id, onlinePayType);
		
		//修改订单商品关系表状态
		int result = orderRepository.updateOrderGoodsInfoStatus(order_id, OrderStatus.WAITING_SEND.ordinal());
		clearOrderPagesCache(customer_id);
		
		return result;*/
		return 0;
	}
	
	/**
	 * 将订单状态修改为待收货
	 *
	 * @param customer_id
	 * @param order_id
	 * @return
	 */
	@Override
	@CacheEvict(value = "order", key = "#order_id")
	public int updateOrderStatusToWaitingReceive(int customer_id, int order_id) {
		
		/*logger.info("updateOrderStatusToWaitingReceive >>> customer_id={}, order_id={}", customer_id, order_id);
		
		orderRepository.updateOrderStatusToWaitingReceive(order_id);
		
		//修改订单商品关系表状态
		int result = orderRepository.updateOrderGoodsInfoStatus(order_id, OrderStatus.WAITING_RECEIVE.ordinal());
		clearOrderPagesCache(customer_id);
		
		return result;*/
		return  0;
	}
	
	/**
	 * 将订单状态修改为确认收货，当买家确认收货，或者超时自动确认收货后，调用此接口，订单状态由**已发货**变更为**待评价**
	 *
	 * @param customer_id
	 * @param order_id
	 * @return
	 */
	@Override
	@CacheEvict(value = "order", key = "#order_id")
	public int updateOrderStatusToWaitingComments(int customer_id, int order_id) {
		
		/*logger.info("updateOrderStatusToComments >>> customer_id={}, order_id={}", customer_id, order_id);
		
		orderRepository.updateOrderStatusToWaitingComments(order_id);
		
		//修改订单商品关系表状态
		int result = orderRepository.updateOrderGoodsInfoStatus(order_id, OrderStatus.WAITING_COMMENT.ordinal());
		clearOrderPagesCache(customer_id);
		
		return result;*/
		return 0;
	}
	
	/**
	 * 将订单状态修改为退款中
	 *
	 * @param orderRefund
	 * @return
	 */
	@Override
	@CacheEvict(value = "order", key = "#orderRefund.getOrder_id()")
	public int updateOrderStatusToRefund(OrderRefund orderRefund, int customer_id) {
		
		/*logger.info("updateOrderStatusToRefund >>> OrderRefund={}", orderRefund);
		
		//新增退款记录
		orderRefundRepository.addOrderRefund(orderRefund);
		
		orderRepository.updateOrderStatusToRefund(orderRefund.getOrder_id());
		
		//修改订单商品关系表状态
		int result = orderRepository.updateOrderGoodsInfoStatus(orderRefund.getOrder_id(), OrderStatus.REFUND.ordinal());
		clearOrderPagesCache(customer_id);
		
		return result;*/
		return 0;
	}
	
	/**
	 * 将订单状态修改为已完成
	 *
	 * @param customer_id
	 * @param order_id
	 * @return
	 */
	@Override
	public int updateOrderStatusToFinish(int customer_id, int order_id) {
		
		/*logger.info("updateOrderStatusToFinish >>> customer_id={}, order_id={}", customer_id, order_id);
		
		orderRepository.updateOrderStatusToFinish(order_id);
		
		//修改订单商品关系表状态
		int result = orderRepository.updateOrderGoodsInfoStatus(order_id, OrderStatus.FINISHED.ordinal());
		clearOrderPagesCache(customer_id);
		
		return result;*/
		return 0;
	}
	
	/**
	 * 将订单状态修改为已关闭
	 *
	 * @param customer_id
	 * @param order_id
	 * @return
	 */
	@Override
	@CacheEvict(value = "order", key = "#order_id")
	public int updateOrderStatusToClose(int customer_id, int order_id) {
		
		/*logger.info("updateOrderStatusToClose >>> customer_id={}, order_id={}", customer_id, order_id);
		
		orderRepository.updateOrderStatusToClose(order_id);
		
		//修改订单商品关系表状态
		int result = orderRepository.updateOrderGoodsInfoStatus(order_id, OrderStatus.CLOSED.ordinal());
		clearOrderPagesCache(customer_id);
		*/
		return 0;
	}
	
	/**
	 * 买家查看退款记录,有且仅有一条记录
	 *
	 * @param order_id
	 * @return
	 */
	@Override
	public OrderRefund findOrderRefund(int order_id) {
		
		OrderRefund orderRefunds = orderRefundRepository.findOrderRefund(order_id);
		
		logger.info("findOrderRefund service >>> order_id={}, result=(orderRefunds={})", orderRefunds);
		
		return orderRefunds;
	}
	
	
	/**
	 * 买家查看订单中某件商品的退货记录
	 *
	 * @param order_id
	 * @param goods_id
	 * @return
	 */
	@Override
	public OrderRefund findOrderRefund(int order_id, int goods_id) {
		
		OrderRefund orderRefund = orderRefundRepository.findOrderRefund(order_id, goods_id);
		
		logger.info("findOrderRefund service >>> order_id={}, result=(orderRefunds={})", orderRefund);
		
		return orderRefund;
	}
	
	/**
	 * 当买家提交退款/售后申请时，当卖家未确认前，买家可以主动关闭退单
	 *
	 * @param refund_uuid
	 * @return
	 */
	@Override
	public int updateOrderRefundStatusToClose(String refund_uuid) {
		
		int result = orderRefundRepository.updateStatusToClose(refund_uuid);
		
		logger.info("updateOrderRefundStatusToClose service >>> refund_uuid={}, result={}", refund_uuid, result);
		
		if(result <= 0) return 0;
		return 1;
	}
	
	/**
	 * 清除订单集合缓存
	 * @param customer_id
	 */
	private void clearOrderPagesCache(int customer_id){
		
		/*List<String> keys = cacheManage.getOrderPageCacheKeys(customer_id);
		
		if(keys == null || keys.size() == 0) {
			cacheManage.clearAllOrderPagesCache();
		}
		
		if(keys != null && keys.size() > 0){
			for (String pageKey : keys) {
				cacheManage.clearOrderPageCache(customer_id, pageKey);
			}
			keys.clear();
		}*/
		
	}
	
	/**
	 * 清除商品集合缓存
	 * @param shop_id
	 */
	private void clearShopPageCache(String shop_id){
		
		List<String> keys = cacheManage.getShopPageCacheKeys(shop_id);
		
		if(keys == null || keys.size() == 0) {
			cacheManage.clearAllGoodsPagesCache();
		}
		
		if(keys != null && keys.size() > 0){
			for (String pageKey : keys) {
				cacheManage.clearGoodsPageCache(shop_id, pageKey);
			}
			keys.clear();
		}
	}
	
	/**
	 * 清除商品缓存
	 * @param goods_id
	 */
	private void clearGoodsCache(String goods_id){
		
		cacheManage.clearGoodsCache(goods_id);
	}
}
