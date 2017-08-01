package shop.haj.entity;

import java.io.Serializable;

/**
 * <p>Title: shop.ha.entity</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/21/17
 */
public class Address implements Serializable{
	
	private int id;
	private String province;//省份
	private String city;//城市
	private String country;//县城
	private String town;//城镇
	private String detail;//详细地址
	private int is_default;//是否默认地址
	private int customer_id;
	private String name;
	private String phone;
	private String create_time;
	private String update_time;
	
	/**
	 * 返回完整地址列表
	 * @return
	 */
	public String getFullAddress(){
		return province + city + country + town + detail;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getProvince() {
		return province;
	}
	
	public void setProvince(String province) {
		this.province = province;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getTown() {
		
		if(town == null || town.toLowerCase().equals("null")) return "";
		
		return town;
	}
	
	public void setTown(String town) {
		this.town = town;
	}
	
	public String getDetail() {
		return detail;
	}
	
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public int getIs_default() {
		return is_default;
	}
	
	public void setIs_default(int is_default) {
		this.is_default = is_default;
	}
	
	public int getCustomer_id() {
		return customer_id;
	}
	
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
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
		return "Address{" +
				"id=" + id +
				", province='" + province + '\'' +
				", city='" + city + '\'' +
				", country='" + country + '\'' +
				", town='" + town + '\'' +
				", detail='" + detail + '\'' +
				", is_default=" + is_default +
				", customer_id=" + customer_id +
				", name='" + name + '\'' +
				", phone='" + phone + '\'' +
				", create_time='" + create_time + '\'' +
				", update_time='" + update_time + '\'' +
				'}';
	}
}
