package shop.haj.entity;

import org.springframework.data.mongodb.core.mapping.Document;

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
@Document(collection = "wx_coupon_goods_rel")
public class CouponGoodsRel implements Serializable {

	private String id;
	private String couponId;
	private String goodsId;
	private String create_time;
	
	public CouponGoodsRel() {
	}

	public CouponGoodsRel(String id, String couponId, String goodsId, String create_time) {
		this.id = id;
		this.couponId = couponId;
		this.goodsId = goodsId;
		this.create_time = create_time;
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

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
}
