package shop.haj.entity;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: shop.ha.entity</p>
 * <p/>
 * <p>
 * Description: 订单列表
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/13/17
 */
public class OrderListInfo implements Serializable {
	
	private int order_id;
	private int shop_id;
	private String order_time;//下单时间
	private int status;
	private double final_price;//最终成交价
	private String shop_name;
	private List<OrderGoodsInfo> orderGoodsInfos;
	
	public OrderListInfo() {
	}
	
	public OrderListInfo(int order_id, int shop_id, String order_time, int status, double final_price, String shop_name, List<OrderGoodsInfo> orderGoodsInfos) {
		this.order_id = order_id;
		this.shop_id = shop_id;
		this.order_time = order_time;
		this.status = status;
		this.final_price = final_price;
		this.shop_name = shop_name;
		this.orderGoodsInfos = orderGoodsInfos;
	}
	
	public int getOrder_id() {
		return order_id;
	}
	
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	
	public int getShop_id() {
		return shop_id;
	}
	
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	
	public String getOrder_time() {
		return order_time;
	}
	
	public void setOrder_time(String order_time) {
		this.order_time = order_time;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public double getFinal_price() {
		return final_price;
	}
	
	public void setFinal_price(double final_price) {
		this.final_price = final_price;
	}
	
	public String getShop_name() {
		return shop_name;
	}
	
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	
	public List<OrderGoodsInfo> getOrderGoodsInfos() {
		return orderGoodsInfos;
	}
	
	public void setOrderGoodsInfos(List<OrderGoodsInfo> orderGoodsInfos) {
		this.orderGoodsInfos = orderGoodsInfos;
	}
	
	@Override
	public String toString() {
		return "OrderListInfo{" +
				"order_id=" + order_id +
				", shop_id=" + shop_id +
				", order_time='" + order_time + '\'' +
				", status=" + status +
				", final_price=" + final_price +
				", shop_name='" + shop_name + '\'' +
				", orderGoodsInfos=" + orderGoodsInfos +
				'}';
	}
}
