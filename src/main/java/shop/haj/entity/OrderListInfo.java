package shop.haj.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: shop.ha.entity</p>
 * <p/>
 * <p>
 * Description: 订单列表
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/13/17
 */
@Document(collection = "wx_order_listinfo")
public class OrderListInfo implements Serializable {

	@Id
	private String id;
	private String orderId;
	private String shopId;
	private String order_time;//下单时间
	private int status;
	private double final_price;//最终成交价
	private String shopName;
	private List<OrderGoodsInfo> orderGoodsInfos;
	
	public OrderListInfo() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getOrder_time() {
		return order_time;
	}

	public void setOrder_time(String order_time) {
		this.order_time = order_time;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getFinal_price() {
		return final_price;
	}

	public void setFinal_price(double final_price) {
		this.final_price = final_price;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public List<OrderGoodsInfo> getOrderGoodsInfos() {
		return orderGoodsInfos;
	}

	public void setOrderGoodsInfos(List<OrderGoodsInfo> orderGoodsInfos) {
		this.orderGoodsInfos = orderGoodsInfos;
	}
}
