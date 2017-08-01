package shop.haj.entity;

import java.io.Serializable;

/**
 * <p>Title: shop.ha.entity</p>
 * <p/>
 * <p>
 * Description: GoodsSkuDetail
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/22/17
 */
public class GoodsSkuDetail implements Serializable{
	
	private String sku;
	private GoodsSkuDetailBase goodsSkuDetailBase;
	
	public GoodsSkuDetail() {
	}
	 
	public String getSku() {
		return sku;
	}
	
	public void setSku(String sku) {
		this.sku = sku;
	}
	
	public GoodsSkuDetailBase getGoodsSkuDetailBase() {
		return goodsSkuDetailBase;
	}
	
	public void setGoodsSkuDetailBase(GoodsSkuDetailBase goodsSkuDetailBase) {
		this.goodsSkuDetailBase = goodsSkuDetailBase;
	}
	
	@Override
	public String toString() {
		return "GoodsSkuDetail{" +
				"sku='" + sku + '\'' +
				", goodsSkuDetailBase=" + goodsSkuDetailBase +
				'}';
	}
}
