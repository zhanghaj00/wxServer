package shop.haj.entity;

import java.io.Serializable;

/**
 * <p>Title: shop.ha.entity</p>
 * <p/>
 * <p>
 * Description: 订单基本信息：订单详情、数量, 冗余表，查看订单基本信息
 *  给order_goods_info表查询使用
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/12/17
 */
public class OrderListSingleInfo implements Serializable {
	
	private int order_id;
	
	private String order_time;
	
	private int status;
	
	private double final_price;//本次订单总价格
	
	private int goods_id;
	
	private String image_url;
	
	private String goods_name;
	
	private String goods_sku;//商品规格
	
	private double goods_price;//商品成交单价
	
	private int count;//商品个数
	
	private int shop_id;
	
	private String shop_name;
	
	private int customer_id;
	
	public OrderListSingleInfo() {
	}
	
	public int getOrder_id() {
		return order_id;
	}
	
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
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
	
	public int getGoods_id() {
		return goods_id;
	}
	
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	
	public String getImage_url() {
		return image_url;
	}
	
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	
	public String getGoods_name() {
		return goods_name;
	}
	
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
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
	
	public int getCustomer_id() {
		return customer_id;
	}
	
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	
	@Override
	public String toString() {
		return "OrderListSingleInfo{" +
				"order_id=" + order_id +
				", order_time='" + order_time + '\'' +
				", status=" + status +
				", final_price=" + final_price +
				", goods_id=" + goods_id +
				", image_url='" + image_url + '\'' +
				", goods_name='" + goods_name + '\'' +
				", goods_sku='" + goods_sku + '\'' +
				", goods_price=" + goods_price +
				", count=" + count +
				", shop_id=" + shop_id +
				", shop_name='" + shop_name + '\'' +
				", customer_id=" + customer_id +
				'}';
	}
}
