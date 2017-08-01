package shop.haj.utils;

/**
 * <p>Title: shop.ha.utils</p>
 * <p/>
 * <p>
 * Description: 订单状态枚举类
 *
 *  待付款
 *      取消订单 --> 已取消
 *  已付款
 *      申请退款 --> 等待卖家确认 --> 退款中 --> 已退款 --> 退款结束 --> 已关闭
 *                  取消退款 --> 退款关闭
 *  待发货
 *      申请退款 --> 等待卖家确认 --> 退款中 --> 已退款 --> 退款结束 --> 已关闭
 *  已发货
 *  确认收货
 *  待评价
 *  申请售后
 *      申请退货 --> 等待卖家确认 --> 退货中 --> 已退款 --> 已关闭
 *                  取消退货申请 --> 售后关闭
 *      申请换货 --> 等待卖家确认 --> 换货中 --> 已完成
 *                  取消换货申请 --> 售后关闭
 *  已完成
 *
 * </p>
 *
 * <p>
 *      买家视角：待付款、待发货、待收货、待评价、退款/售后、已完成、已取消
 *      卖家视角：待付款、待发货、待收货、退款/售后、已完成、已关闭
 * </p>
 *
 * <p/>
 *
 * @author hao
 *         CreateTime：4/8/17
 */
public enum OrderStatus {
	
	BOTH,//0:全部订单
	WAITING_PAYMENT,//1:待付款
	WAITING_SEND,//2:待发货
	WAITING_RECEIVE,//3:待收货
	WAITING_COMMENT,//4:待评论
	REFUND,//5:退款/售后
	FINISHED,//6:已完成
	CLOSED,//7:已关闭
}