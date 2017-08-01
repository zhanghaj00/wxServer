package shop.haj.entity;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: shop.ha.entity</p>
 * <p/>
 * <p>
 * Description: Goods
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/4/17
 */
public class Goods implements Serializable{
	
	private int id;
	
	private String uuid;
	
	private String name;
	
	private int shop_id;
	
	private int status;
	
	private double original_price;
	
	private double sell_price;
	
	private int stock;
	
	private int sales_volume;
	
	private int inner_cid;
	
	private int global_cid;
	
	private int is_recommend;
	
	private int is_deleted;
	
	private String create_time;
	
	private String update_time;
	
	private List<Image> images;
	
	private List<GoodsDetail> goodsDetails;//商品详情
	
	private GoodsSkuInfo goodsSkuInfo;
	
	public Goods() {
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getShop_id() {
		return shop_id;
	}
	
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public double getOriginal_price() {
		return original_price;
	}
	
	public void setOriginal_price(double original_price) {
		this.original_price = original_price;
	}
	
	public double getSell_price() {
		return sell_price;
	}
	
	public void setSell_price(double sell_price) {
		this.sell_price = sell_price;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public int getSales_volume() {
		return sales_volume;
	}
	
	public void setSales_volume(int sales_volume) {
		this.sales_volume = sales_volume;
	}
	
	public int getInner_cid() {
		return inner_cid;
	}
	
	public void setInner_cid(int inner_cid) {
		this.inner_cid = inner_cid;
	}
	
	public int getGlobal_cid() {
		return global_cid;
	}
	
	public void setGlobal_cid(int global_cid) {
		this.global_cid = global_cid;
	}
	
	public int getIs_recommend() {
		return is_recommend;
	}
	
	public void setIs_recommend(int is_recommend) {
		this.is_recommend = is_recommend;
	}
	
	public int getIs_deleted() {
		return is_deleted;
	}
	
	public void setIs_deleted(int is_deleted) {
		this.is_deleted = is_deleted;
	}
	
	public String getCreate_time() {
		return create_time;
	}
	
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
	public String getUpdate_time() {
		return update_time;
	}
	
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	
	public List<Image> getImages() {
		return images;
	}
	
	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	public List<GoodsDetail> getGoodsDetails() {
		return goodsDetails;
	}
	
	public void setGoodsDetails(List<GoodsDetail> goodsDetails) {
		this.goodsDetails = goodsDetails;
	}
	
	public GoodsSkuInfo getGoodsSkuInfo() {
		return goodsSkuInfo;
	}
	
	public void setGoodsSkuInfo(GoodsSkuInfo goodsSkuInfo) {
		this.goodsSkuInfo = goodsSkuInfo;
	}
	
	@Override
	public String toString() {
		return "Goods{" +
				"id=" + id +
				", uuid='" + uuid + '\'' +
				", name='" + name + '\'' +
				", shop_id=" + shop_id +
				", status=" + status +
				", original_price=" + original_price +
				", sell_price=" + sell_price +
				", stock=" + stock +
				", sales_volume=" + sales_volume +
				", inner_cid=" + inner_cid +
				", global_cid=" + global_cid +
				", is_recommend=" + is_recommend +
				", is_deleted=" + is_deleted +
				", create_time='" + create_time + '\'' +
				", update_time='" + update_time + '\'' +
				", images=" + images +
				", goodsDetails=" + goodsDetails +
				", goodsSkuInfo=" + goodsSkuInfo +
				'}';
	}
}
