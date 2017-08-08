package shop.haj.entity;

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
public class OrderListInfo implements Serializable {

	private String id;
	private String orderId;
	private String shopId;
	private String orderTime;//下单时间
	private Integer status;
	private Double finalPrice;//最终成交价
	private Double postFee;//运费
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

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public Double getPostFee() {
		return postFee;
	}

	public void setPostFee(Double postFee) {
		this.postFee = postFee;
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
