package shop.haj.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document(collection = "wx_coupon")
public class Coupon implements Serializable {

	@Id
	private String id;
	private double price;//优惠券面额
	private double limitPrice;//最低消费多少时可使用
	private String shopId;
	private String beginTime;//优惠券可领取时间
	private String dueTime;//优惠券失效时间
	private int stock;//库存
	private int perLimit;//每人限制领取个数
	private int suitLimit;//使用限制：0：全店适用，1：部分商品适用
	private String createTime;
	private List<String> goodsIdList;//当为部分商品适用时，保存所有商品的ID
	
	public Coupon() {
	}


	public Coupon(String id, double price, double limitPrice, String shopId, String beginTime, String dueTime, int stock, int perLimit, int suitLimit, String createTime, List<String> goodsIdList) {

		this.id = id;
		this.price = price;
		this.limitPrice = limitPrice;
		this.shopId = shopId;
		this.beginTime = beginTime;
		this.dueTime = dueTime;
		this.stock = stock;
		this.perLimit = perLimit;
		this.suitLimit = suitLimit;
		this.createTime = createTime;
		this.goodsIdList = goodsIdList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getLimitPrice() {
		return limitPrice;
	}

	public void setLimitPrice(double limitPrice) {
		this.limitPrice = limitPrice;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getDueTime() {
		return dueTime;
	}

	public void setDueTime(String dueTime) {
		this.dueTime = dueTime;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getPerLimit() {
		return perLimit;
	}

	public void setPerLimit(int perLimit) {
		this.perLimit = perLimit;
	}

	public int getSuitLimit() {
		return suitLimit;
	}

	public void setSuitLimit(int suitLimit) {
		this.suitLimit = suitLimit;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public List<String> getGoodsIdList() {
		return goodsIdList;
	}

	public void setGoodsIdList(List<String> goodsIdList) {
		this.goodsIdList = goodsIdList;
	}
}
