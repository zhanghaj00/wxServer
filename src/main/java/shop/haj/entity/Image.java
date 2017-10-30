package shop.haj.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Random;

/**
 * <p>图片实体</p>
 * <p/>
 * <p>
 * Description: Image
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/11/17
 */
@Document(collection="wx_image")
public class Image implements Serializable{

	@Id
	private String id = System.currentTimeMillis()+ "" + new Random().nextInt(10000);

	private String shopId;

	private String goodsId;

	private String url;
	
	private String createTime;
	
	public Image() {
	}
	
	public Image(String id, String url, String createTime) {
		this.id = id;
		this.url = url;
		this.createTime = createTime;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	@Override
	public String toString() {
		return "Image{" +
				"id=" + id +
				", url='" + url + '\'' +
				", createTime='" + createTime + '\'' +
				'}';
	}
}
