package shop.haj.entity;

import java.io.Serializable;

/**
 * <p>Title: shop.ha.entity</p>
 * <p/>
 * <p>
 * Description: 规格组合的基本信息
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/22/17
 */
public class GoodsSkuDetailBase implements Serializable{
	
	private int stock;
	private double price;
	private String imageUrl;
	
	public GoodsSkuDetailBase() {
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	@Override
	public String toString() {
		return "GoodsSkuDetailBase{" +
				"stock=" + stock +
				", price=" + price +
				", imageUrl='" + imageUrl + '\'' +
				'}';
	}
}
