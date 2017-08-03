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
	private String couponId;
	private String customerId;
	private String acceptTime;
	private String usedTime;
	private int status;//优惠券状态，未使用、已使用、已过期
	
	public CouponCustomerInfo() {
	}

	public CouponCustomerInfo(String id, String couponId, String customerId, String acceptTime, String usedTime, int status) {
		this.id = id;
		this.couponId = couponId;
		this.customerId = customerId;
		this.acceptTime = acceptTime;
		this.usedTime = usedTime;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
	}

	public String getUsedTime() {
		return usedTime;
	}

	public void setUsedTime(String usedTime) {
		this.usedTime = usedTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
