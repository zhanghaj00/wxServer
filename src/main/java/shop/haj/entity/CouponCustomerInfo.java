package shop.haj.entity;

import java.io.Serializable;

/**
 * <p>Title: shop.ha.entity</p>
 * <p/>
 * <p>
 * Description: 买家拥有的优惠券信息实体
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/23/17
 */
public class CouponCustomerInfo implements Serializable {
	
	private int id;
	private int coupon_id;
	private int customer_id;
	private String accept_time;
	private String used_time;
	private int status;//优惠券状态，未使用、已使用、已过期
	
	public CouponCustomerInfo() {
	}
	
	public CouponCustomerInfo(int id, int coupon_id, int customer_id, String accept_time, String used_time, int status) {
		this.id = id;
		this.coupon_id = coupon_id;
		this.customer_id = customer_id;
		this.accept_time = accept_time;
		this.used_time = used_time;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getCoupon_id() {
		return coupon_id;
	}
	
	public void setCoupon_id(int coupon_id) {
		this.coupon_id = coupon_id;
	}
	
	public int getCustomer_id() {
		return customer_id;
	}
	
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	
	public String getAccept_time() {
		return accept_time;
	}
	
	public void setAccept_time(String accept_time) {
		this.accept_time = accept_time;
	}
	
	public String getUsed_time() {
		return used_time;
	}
	
	public void setUsed_time(String used_time) {
		this.used_time = used_time;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "CouponCustomerInfo{" +
				"id=" + id +
				", coupon_id=" + coupon_id +
				", customer_id=" + customer_id +
				", accept_time='" + accept_time + '\'' +
				", used_time='" + used_time + '\'' +
				", status=" + status +
				'}';
	}
}
