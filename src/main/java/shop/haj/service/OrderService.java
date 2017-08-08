package shop.haj.service;

import shop.haj.entity.*;

import java.util.List;

/**
 * <p>Title: shop.ha.service</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/4/17
 */
public interface OrderService {
	
	/**
	 * 查找某买家在某店铺的全部订单列表
	 * @return
	 */
	List<OrderListInfo> findOrderListInfo(OrderListSingleInfo order , Pagination page);
	
	/**
	 * 查找某买家全部订单列表
	 * @param customer_id
	 * @return
	 */
	List<OrderListInfo> findOrderListByCustomerID(String customer_id, int status, Pagination page);

	/**
	 * 查找某买家全部订单列表
	 * @return
	 */
	List<Order> findOrderListShopId(String shopId);

	/**
	 * 根据订单ID查找某个订单详细信息
	 * @param order_id
	 * @param page
	 * @return
	 */
	Order findShopOrderByID(String order_id, Pagination page);
	
	/**
	 * 根据订单号查询订单信息
	 * @param uuid
	 * @return
	 */
	Order findShopOrderByUUID(String uuid);
	
	/**
	 * 新增订单
	 * @param order
	 * @return
	 */
	Order addOrder(Order order);
	
	/**
	 * 买家付款后，将订单状态修改为待发货
	 * @param customer_id
	 * @param order_id
	 * @return
	 */
	int updateOrderStatusToWaitingSend(String customer_id, String order_id, int onlinePayType);
	
	/**
	 * 将订单状态修改为待收货
	 * @param customer_id
	 * @param order_id
	 * @return
	 */
	int updateOrderStatusToWaitingReceive(String customer_id, String order_id);
	
	/**
	 * 将订单状态修改为待评价
	 * @param customer_id
	 * @param order_id
	 * @return
	 */
	int updateOrderStatusToWaitingComments(String customer_id, String order_id);
	
	/**
	 * 将订单状态修改为退款/售后
	 * @param orderRefund
	 * @return
	 */
	int updateOrderStatusToRefund(OrderRefund orderRefund, String customer_id);
	
	/**
	 * 将订单状态修改为已完成
	 * @param order_id
	 * @return
	 */
	int updateOrderStatusToFinish(String customer_id, String order_id);
	
	/**
	 * 将订单状态修改为已关闭
	 * @param order_id
	 * @return
	 */
	int updateOrderStatusToClose(String customer_id, String order_id);
	
	/**
	 * 买家查看退款记录,有且仅有一条记录
	 * @param order_id
	 * @return
	 */
	OrderRefund findOrderRefund(String order_id);
	
	/**
	 * 买家查看订单中某件商品的退货记录
	 * @param order_id
	 * @param goods_id
	 * @return
	 */
	OrderRefund findOrderRefund(String order_id, String goods_id);
	
	/**
	 * 当买家提交退款/售后申请时，当卖家未确认前，买家可以主动关闭退单
	 * @param refund_uuid
	 * @return
	 */
	int updateOrderRefundStatusToClose(String refund_uuid);
}
