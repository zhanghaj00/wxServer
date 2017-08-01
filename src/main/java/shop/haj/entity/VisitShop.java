package shop.haj.entity;

import java.io.Serializable;

/**
 * <p>Title: shop.ha.entity</p>
 * <p/>
 * <p>
 * Description: 店铺历史访问记录
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/4/17
 */
public class VisitShop implements Serializable {
	
	private int customer_id;
	
	private int shop_id;
	
	private String visit_time;
	
	public VisitShop() {
	}
	
	public VisitShop(int customer_id, int shop_id, String visit_time) {
		this.customer_id = customer_id;
		this.shop_id = shop_id;
		this.visit_time = visit_time;
	}
	
	public int getCustomer_id() {
		return customer_id;
	}
	
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	
	public int getShop_id() {
		return shop_id;
	}
	
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	
	public String getVisit_time() {
		return visit_time;
	}
	
	public void setVisit_time(String visit_time) {
		this.visit_time = visit_time;
	}
	
	@Override
	public String toString() {
		return "VisitShop{" +
				"customer_id=" + customer_id +
				", shop_id=" + shop_id +
				", visit_time='" + visit_time + '\'' +
				'}';
	}
}
