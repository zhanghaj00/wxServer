package shop.haj.entity;

import java.io.Serializable;

/**
 * <p>Title: shop.ha.entity</p>
 * <p/>
 * <p>
 * Description: 收藏商品的实体
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/24/17
 */
public class FavoriteGoods implements Serializable{
	
	private int customer_id;
	private int shop_id;
	private int goods_id;
	private String create_time;
	
	public int getCustomer_id() {
		return customer_id;
	}
	
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	
	public int getShop_id() {
		return shop_id;
	}
	
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
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
		return "FavoriteGoods{" +
				"customer_id=" + customer_id +
				", shop_id=" + shop_id +
				", goods_id=" + goods_id +
				", create_time='" + create_time + '\'' +
				'}';
	}
}
