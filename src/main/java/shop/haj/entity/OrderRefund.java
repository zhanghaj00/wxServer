package shop.haj.entity;

/**
 * <p>Title: shop.ha.entity</p>
 * <p/>
 * <p>
 * Description: 订单退款处理实体
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/30/17
 */
public class OrderRefund {
	
	private int order_id;//订单序列号
	private String order_uuid;//订单编号
	private String refund_uuid;//退单编号
	private int type;//0:退款; 1:售后
	private int order_status;//退款/售后发起时的订单状态
	private int status;//0: 等待卖家确认; 1: 处理中; 2: 主动关闭(买家撤销、卖家驳回); 3: 退款/售后结束
	private int goods_id;//商品序列号
	private String cause;//退货原因
	private String contact_name;//联系人
	private String contact_phone;//联系电话
	private double refund_price;//退款价格
	private String create_time;//创建时间
	private String update_time;//更新时间
	private String close_time;//关闭时间
	private String finish_time;//完成时间
	private String seller_dealtime;//卖家回复时间
	private int is_agree;//卖家是否同意
	private String disagree_cause;//当卖家不同意时需要填写的内容
	
	public int getOrder_id() {
		return order_id;
	}
	
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	
	public String getOrder_uuid() {
		return order_uuid;
	}
	
	public void setOrder_uuid(String order_uuid) {
		this.order_uuid = order_uuid;
	}
	
	public String getRefund_uuid() {
		return refund_uuid;
	}
	
	public void setRefund_uuid(String refund_uuid) {
		this.refund_uuid = refund_uuid;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getOrder_status() {
		return order_status;
	}
	
	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getGoods_id() {
		return goods_id;
	}
	
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	
	public String getCause() {
		return cause;
	}
	
	public void setCause(String cause) {
		this.cause = cause;
	}
	
	public String getContact_name() {
		return contact_name;
	}
	
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	
	public String getContact_phone() {
		return contact_phone;
	}
	
	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}
	
	public double getRefund_price() {
		return refund_price;
	}
	
	public void setRefund_price(double refund_price) {
		this.refund_price = refund_price;
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
	
	public String getClose_time() {
		return close_time;
	}
	
	public void setClose_time(String close_time) {
		this.close_time = close_time;
	}
	
	public String getFinish_time() {
		return finish_time;
	}
	
	public void setFinish_time(String finish_time) {
		this.finish_time = finish_time;
	}
	
	public String getSeller_dealtime() {
		return seller_dealtime;
	}
	
	public void setSeller_dealtime(String seller_dealtime) {
		this.seller_dealtime = seller_dealtime;
	}
	
	public int getIs_agree() {
		return is_agree;
	}
	
	public void setIs_agree(int is_agree) {
		this.is_agree = is_agree;
	}
	
	public String getDisagree_cause() {
		return disagree_cause;
	}
	
	public void setDisagree_cause(String disagree_cause) {
		this.disagree_cause = disagree_cause;
	}
	
	@Override
	public String toString() {
		return "OrderRefund{" +
				"order_id=" + order_id +
				", order_uuid='" + order_uuid + '\'' +
				", refund_uuid='" + refund_uuid + '\'' +
				", type=" + type +
				", order_status=" + order_status +
				", status=" + status +
				", goods_id=" + goods_id +
				", cause='" + cause + '\'' +
				", contact_name='" + contact_name + '\'' +
				", contact_phone='" + contact_phone + '\'' +
				", refund_price=" + refund_price +
				", create_time='" + create_time + '\'' +
				", update_time='" + update_time + '\'' +
				", close_time='" + close_time + '\'' +
				", finish_time='" + finish_time + '\'' +
				", seller_dealtime='" + seller_dealtime + '\'' +
				", is_agree=" + is_agree +
				", disagree_cause='" + disagree_cause + '\'' +
				'}';
	}
}
