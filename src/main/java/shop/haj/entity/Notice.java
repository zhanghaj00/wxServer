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
	
	private String shop_id;
	
	private String content;
	
	private int is_show;
	
	private String create_time;
	
	private String update_time;
	
	public Notice() {
	}

	public Notice(String id, String shop_id, String content, int is_show, String create_time, String update_time) {
		this.id = id;
		this.shop_id = shop_id;
		this.content = content;
		this.is_show = is_show;
		this.create_time = create_time;
		this.update_time = update_time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShop_id() {
		return shop_id;
	}

	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getIs_show() {
		return is_show;
	}

	public void setIs_show(int is_show) {
		this.is_show = is_show;
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
}
