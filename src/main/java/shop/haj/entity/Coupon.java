package shop.haj.entity;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: shop.ha.entity</p>
 * <p/>
 * <p>
 * Description: 优惠券信息实体类
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/22/17
 */
public class Coupon implements Serializable {
	
	private int id;
	private double price;//优惠券面额
	private double limit_price;//最低消费多少时可使用
	private int shop_id;
	private String begin_time;//优惠券可领取时间
	private String due_time;//优惠券失效时间
	private int stock;//库存
	private int per_limit;//每人限制领取个数
	private int suit_limit;//使用限制：0：全店适用，1：部分商品适用
	private String create_time;
	private List<Integer> goodsIdList;//当为部分商品适用时，保存所有商品的ID
	
	public Coupon() {
	}
	
	public Coupon(int id, double price, double limit_price, int shop_id, String begin_time, String due_time, int stock, int per_limit, int suit_limit, String create_time, List<Integer> goodsIdList) {
		this.id = id;
		this.price = price;
		this.limit_price = limit_price;
		this.shop_id = shop_id;
		this.begin_time = begin_time;
		this.due_time = due_time;
		this.stock = stock;
		this.per_limit = per_limit;
		this.suit_limit = suit_limit;
		this.create_time = create_time;
		this.goodsIdList = goodsIdList;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getLimit_price() {
		return limit_price;
	}
	
	public void setLimit_price(double limit_price) {
		this.limit_price = limit_price;
	}
	
	public int getShop_id() {
		return shop_id;
	}
	
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	
	public String getBegin_time() {
		return begin_time;
	}
	
	public void setBegin_time(String begin_time) {
		this.begin_time = begin_time;
	}
	
	public String getDue_time() {
		return due_time;
	}
	
	public void setDue_time(String due_time) {
		this.due_time = due_time;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public int getPer_limit() {
		return per_limit;
	}
	
	public void setPer_limit(int per_limit) {
		this.per_limit = per_limit;
	}
	
	public int getSuit_limit() {
		return suit_limit;
	}
	
	public void setSuit_limit(int suit_limit) {
		this.suit_limit = suit_limit;
	}
	
	public String getCreate_time() {
		return create_time;
	}
	
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
	public List<Integer> getGoodsIdList() {
		return goodsIdList;
	}
	
	public void setGoodsIdList(List<Integer> goodsIdList) {
		this.goodsIdList = goodsIdList;
	}
	
	@Override
	public String toString() {
		return "Coupon{" +
				"id=" + id +
				", price=" + price +
				", limit_price=" + limit_price +
				", shop_id=" + shop_id +
				", begin_time='" + begin_time + '\'' +
				", due_time='" + due_time + '\'' +
				", stock=" + stock +
				", per_limit=" + per_limit +
				", suit_limit=" + suit_limit +
				", create_time='" + create_time + '\'' +
				", goodsIdList=" + goodsIdList +
				'}';
	}
}
