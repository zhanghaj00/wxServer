package shop.haj.entity;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: shop.ha.entity</p>
 * <p/>
 * <p>
 * Description: 商品规格信息实体
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/22/17
 */
public class GoodsSkuInfo implements Serializable{
	
	private String prop1;
	private String value1;
	private String prop2;
	private String value2;
	private String prop3;
	private String value3;
	
	private List<GoodsSkuDetail> goodsSkuDetails;
	
	public GoodsSkuInfo() {
	}
	
	public String getProp1() {
		return prop1;
	}
	
	public void setProp1(String prop1) {
		this.prop1 = prop1;
	}
	
	public String getValue1() {
		return value1;
	}
	
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	
	public String getProp2() {
		return prop2;
	}
	
	public void setProp2(String prop2) {
		this.prop2 = prop2;
	}
	
	public String getValue2() {
		return value2;
	}
	
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	
	public String getProp3() {
		return prop3;
	}
	
	public void setProp3(String prop3) {
		this.prop3 = prop3;
	}
	
	public String getValue3() {
		return value3;
	}
	
	public void setValue3(String value3) {
		this.value3 = value3;
	}
	
	public List<GoodsSkuDetail> getGoodsSkuDetails() {
		return goodsSkuDetails;
	}
	
	public void setGoodsSkuDetails(List<GoodsSkuDetail> goodsSkuDetails) {
		this.goodsSkuDetails = goodsSkuDetails;
	}
	
	@Override
	public String toString() {
		return "GoodsSkuInfo{" +
				"prop1='" + prop1 + '\'' +
				", value1='" + value1 + '\'' +
				", prop2='" + prop2 + '\'' +
				", value2='" + value2 + '\'' +
				", prop3='" + prop3 + '\'' +
				", value3='" + value3 + '\'' +
				", goodsSkuDetails=" + goodsSkuDetails +
				'}';
	}
}
