package shop.haj.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

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
	private String id;

	private String shopId;

	private String goodsId;

	private String url;
	
	private String create_time;
	
	public Image() {
	}
	
	public Image(String id, String url, String create_time) {
		this.id = id;
		this.url = url;
		this.create_time = create_time;
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
	
	public String getCreate_time() {
		return create_time;
	}
	
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
	@Override
	public String toString() {
		return "Image{" +
				"id=" + id +
				", url='" + url + '\'' +
				", create_time='" + create_time + '\'' +
				'}';
	}
}
