package shop.haj.entity;

import java.io.Serializable;

/**
 * <p>Title: shop.ha.entity</p>
 * <p/>
 * <p>
 * Description: 买家实体
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/2/17
 */
public class Seller implements Serializable {
	
	private int id;
	
	private String wechat_id;//微信编号
	
	private String name;//姓名
	
	private String avatar;//头像地址
	
	private String phone;//电话号码
	
	private String note;//备注
	
	private String create_time;
	
	private String update_time;
	
	public Seller() {
	}
	
	public Seller(int id, String wechat_id, String name, String avatar, String phone, String note, String create_time, String update_time) {
		this.id = id;
		this.wechat_id = wechat_id;
		this.name = name;
		this.avatar = avatar;
		this.phone = phone;
		this.note = note;
		this.create_time = create_time;
		this.update_time = update_time;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getWechat_id() {
		return wechat_id;
	}
	
	public void setWechat_id(String wechat_id) {
		this.wechat_id = wechat_id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAvatar() {
		return avatar;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	@Override
	public String toString() {
		return "Seller{" +
				"id=" + id +
				", wechat_id='" + wechat_id + '\'' +
				", name='" + name + '\'' +
				", avatar='" + avatar + '\'' +
				", phone='" + phone + '\'' +
				", note='" + note + '\'' +
				", create_time='" + create_time + '\'' +
				", update_time='" + update_time + '\'' +
				'}';
	}
}
