package shop.haj.entity;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: shop.ha.entity</p>
 * <p/>
 * <p>
 * Description: 订单
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/4/17
 */
public class Order implements Serializable {
	
	private int order_id;
	
	private String uuid;
	
	private int customer_id;
	
	private int status;
	
	private double deal_price;
	
	private double final_price;
	
	private int coupon_used_id;
	
	private String message;//买家留言
	
	private String address;//the full address
	
	private String receiveName;
	
	private String receivePhone;
	
	private int shop_id;
	
	private String shop_name;
	
	private int payment_type;
	
	private String order_time;
	
	private String payment_time;
	
	private String sended_time;
	
	private String close_time;
	
	private String update_time;
	
	private List<OrderGoodsInfo> orderGoodsInfos;
	
	public int getOrder_id() {
		return order_id;
	}
	
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public int getCustomer_id() {
		return customer_id;
	}
	
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public double getDeal_price() {
		return deal_price;
	}
	
	public void setDeal_price(double deal_price) {
		this.deal_price = deal_price;
	}
	
	public double getFinal_price() {
		return final_price;
	}
	
	public void setFinal_price(double final_price) {
		this.final_price = final_price;
	}
	
	public int getCoupon_used_id() {
		return coupon_used_id;
	}
	
	public void setCoupon_used_id(int coupon_used_id) {
		this.coupon_used_id = coupon_used_id;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getReceiveName() {
		return receiveName;
	}
	
	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}
	
	public String getReceivePhone() {
		return receivePhone;
	}
	
	public void setReceivePhone(String receivePhone) {
		this.receivePhone = receivePhone;
	}
	
	public int getShop_id() {
		return shop_id;
	}
	
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	
	public String getShop_name() {
		return shop_name;
	}
	
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	
	public int getPayment_type() {
		return payment_type;
	}
	
	public void setPayment_type(int payment_type) {
		this.payment_type = payment_type;
	}
	
	public String getOrder_time() {
		return order_time;
	}
	
	public void setOrder_time(String order_time) {
		this.order_time = order_time;
	}
	
	public String getPayment_time() {
		return payment_time;
	}
	
	public void setPayment_time(String payment_time) {
		this.payment_time = payment_time;
	}
	
	public String getSended_time() {
		return sended_time;
	}
	
	public void setSended_time(String sended_time) {
		this.sended_time = sended_time;
	}
	
	public String getClose_time() {
		return close_time;
	}
	
	public void setClose_time(String close_time) {
		this.close_time = close_time;
	}
	
	public String getUpdate_time() {
		return update_time;
	}
	
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	
	public List<OrderGoodsInfo> getOrderGoodsInfos() {
		return orderGoodsInfos;
	}
	
	public void setOrderGoodsInfos(List<OrderGoodsInfo> orderGoodsInfos) {
		this.orderGoodsInfos = orderGoodsInfos;
	}
	
	@Override
	public String toString() {
		return "Order{" +
				"order_id=" + order_id +
				", uuid='" + uuid + '\'' +
				", customer_id=" + customer_id +
				", status=" + status +
				", deal_price=" + deal_price +
				", final_price=" + final_price +
				", coupon_used_id=" + coupon_used_id +
				", message='" + message + '\'' +
				", address='" + address + '\'' +
				", receiveName='" + receiveName + '\'' +
				", receivePhone='" + receivePhone + '\'' +
				", shop_id=" + shop_id +
				", shop_name='" + shop_name + '\'' +
				", payment_type=" + payment_type +
				", order_time='" + order_time + '\'' +
				", payment_time='" + payment_time + '\'' +
				", sended_time='" + sended_time + '\'' +
				", close_time='" + close_time + '\'' +
				", update_time='" + update_time + '\'' +
				", orderGoodsInfos=" + orderGoodsInfos +
				'}';
	}
}
