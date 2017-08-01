package shop.haj.entity;

import java.io.Serializable;

/**
 * <p>Title: shop.ha.entity</p>
 * <p/>
 * <p>
 * Description: 订单中的商品信息
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/17/17
 */
public class OrderGoodsInfo implements Serializable {
	
	private int goods_id;
	
	private String image_url;//商品图片地址
	
	private String goods_name;//商品名称
	
	private String goods_sku;//商品规格信息
	
	private double goods_price;//商品成交价格
	
	private int count;//购买商品数量
	
	public OrderGoodsInfo() {
	}
	
	public int getGoods_id() {
		return goods_id;
	}
	
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	
	public String getGoods_name() {
		return goods_name;
	}
	
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	
	public String getImage_url() {
		return image_url;
	}
	
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	
	public String getGoods_sku() {
		return goods_sku;
	}
	
	public void setGoods_sku(String goods_sku) {
		this.goods_sku = goods_sku;
	}
	
	public double getGoods_price() {
		return goods_price;
	}
	
	public void setGoods_price(double goods_price) {
		this.goods_price = goods_price;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public String toString() {
		return "OrderGoodsInfo{" +
				"goods_id=" + goods_id +
				", image_url='" + image_url + '\'' +
				", goods_name='" + goods_name + '\'' +
				", goods_sku='" + goods_sku + '\'' +
				", goods_price=" + goods_price +
				", count=" + count +
				'}';
	}
}
