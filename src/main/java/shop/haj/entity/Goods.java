package shop.haj.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document(collection = "wx_goods")
public class Goods implements Serializable{

	@Id
	private String id;
	
	private String uuid;
	
	private String name;
	
	private String shopId;
	
	private Integer status;
	
	private Double originalPrice;
	
	private Double sellPrice;
	
	private Integer stock;
	
	private Integer salesVolume;
	
	private Integer innerCid;
	
	private Integer globalCid;
	
	private Integer isRecommend;
	
	private Integer isDeleted;
	
	private String createTime;
	
	private String updateTime;
	
	private List<Image> images;
	
	private List<GoodsDetail> goodsDetails;//商品详情
	
	private GoodsSkuInfo goodsSkuInfo;
	
	public Goods() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}

	public Integer getInnerCid() {
		return innerCid;
	}

	public void setInnerCid(Integer innerCid) {
		this.innerCid = innerCid;
	}

	public Integer getGlobalCid() {
		return globalCid;
	}

	public void setGlobalCid(Integer globalCid) {
		this.globalCid = globalCid;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
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
}
