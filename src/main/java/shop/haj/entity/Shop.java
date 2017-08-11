package shop.haj.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: shop.ha.Entity</p>
 * <p/>
 * <p>
 * Description: Shop
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/1/17
 */
@Document(collection="wx_shop")
public class Shop implements Serializable {

	@Id
	private String id;//序列ID
	
	private String name;//店铺名称

	private String avatar;//

	private String sellerId;	//卖家ID
	
	private String categoryId;//店铺分类
	
	private String categoryName;//分类名称
	
	private String describe;//店铺描述
	
	private String address;//店铺地址
	
	private String phone;//联系电话
	
	private String createTime;
	
	private String updateTime;
	
	private List<Image> images;
	
	public Shop() {
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	

	public String getDescribe() {
		return describe;
	}
	
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public List<Image> getImages() {
		return images;
	}
	
	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	@Override
	public String toString() {
		return "Shop{" +
				"id=" + id +
				", name='" + name + '\'' +
				", category_id=" + categoryId +
				", category_name='" + categoryName + '\'' +
				", describe='" + describe + '\'' +
				", address='" + address + '\'' +
				", phone='" + phone + '\'' +
				", create_time='" + createTime + '\'' +
				", update_time='" + updateTime + '\'' +
				", images=" + images +
				'}';
	}
}
