package shop.haj.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document(collection = "wx_coupon_customer_info")
public class CouponCustomerInfo implements Serializable {

	@Id
	private String id;
	private String coupon_id;
	private String customer_id;
	private String accept_time;
	private String used_time;
	private int status;//优惠券状态，未使用、已使用、已过期
	
	public CouponCustomerInfo() {
	}

	public CouponCustomerInfo(String id, String coupon_id, String customer_id, String accept_time, String used_time, int status) {
		this.id = id;
		this.coupon_id = coupon_id;
		this.customer_id = customer_id;
		this.accept_time = accept_time;
		this.used_time = used_time;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(String coupon_id) {
		this.coupon_id = coupon_id;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
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
}
