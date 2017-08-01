package shop.haj.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * <p>Title: shop.ha.entity</p>
 * <p/>
 * <p>
 * Description: ShopCategory
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：4/11/17
 */
@Document(collection = "wx_shopcategory")
public class ShopCategory implements Serializable {

	@Id
	private String id;
	private String name;
	private String pid;
	private String create_time;
	private String update_time;
	
	public ShopCategory() {
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
	
	public String getPid() {
		return pid;
	}
	
	public void setPid(String pid) {
		this.pid = pid;
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
	
	@Override
	public String toString() {
		return "ShopCategory{" +
				"id=" + id +
				", name='" + name + '\'' +
				", pid=" + pid +
				", create_time='" + create_time + '\'' +
				", update_time='" + update_time + '\'' +
				'}';
	}
}
