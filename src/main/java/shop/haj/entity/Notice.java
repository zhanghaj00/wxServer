package shop.haj.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * <p>Title: shop.ha.entity</p>
 * <p/>
 * <p>
 * Description: 公告
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/4/17
 */
@Document(collection = "wx_notice")
public class Notice implements Serializable {

	@Id
	private String id;
	
	private String shopId;
	
	private String content;
	
	private int isShow;
	
	private String createTime;
	
	private String updateTime;
	
	public Notice() {
	}

	public Notice(String id, String shopId, String content, int isShow, String createTime, String updateTime) {
		this.id = id;
		this.shopId = shopId;
		this.content = content;
		this.isShow = isShow;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getIsShow() {
		return isShow;
	}

	public void setIsShow(int isShow) {
		this.isShow = isShow;
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
}
