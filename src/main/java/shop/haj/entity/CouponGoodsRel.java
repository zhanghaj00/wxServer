package shop.haj.entity;

import java.io.Serializable;

/**
 * <p>Title: shop.ha.entity</p>
 * <p/>
 * <p>
 * Description: CouponGoodsRel
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/24/17
 */
public class CouponGoodsRel implements Serializable {
	
	private int coupon_id;
	private int goods_id;
	private String create_time;
	
	public CouponGoodsRel() {
	}
	
	public CouponGoodsRel(int coupon_id, int goods_id, String create_time) {
		this.coupon_id = coupon_id;
		this.goods_id = goods_id;
		this.create_time = create_time;
	}
	
	public int getCoupon_id() {
		return coupon_id;
	}
	
	public void setCoupon_id(int coupon_id) {
		this.coupon_id = coupon_id;
	}
	
	public int getGoods_id() {
		return goods_id;
	}
	
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	
	public String getCreate_time() {
		return create_time;
	}
	
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
	@Override
	public String toString() {
		return "CouponGoodsRel{" +
				"coupon_id=" + coupon_id +
				", goods_id=" + goods_id +
				", create_time='" + create_time + '\'' +
				'}';
	}
}
