package shop.haj.utils;

/**
 * <p>Title: shop.ha.utils</p>
 * <p/>
 * <p>
 * 退款/售后 订单状态: 等待卖家确认、处理中、退款/售后关闭、退款/售后结束
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/30/17
 */
public enum OrderRefundStatus {
	
	WAITING_AGREE,//0:等待卖家确认
	PROCESS,//1:处理中
	CLOSE,//2:退款/售后关闭
	FINISH//3:退款/售后结束
}
