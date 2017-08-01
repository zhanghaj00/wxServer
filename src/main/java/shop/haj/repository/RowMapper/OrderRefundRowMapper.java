package shop.haj.repository.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import shop.haj.entity.OrderRefund;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>Title: shop.ha.repository.RowMapper</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/30/17
 */
public class OrderRefundRowMapper implements RowMapper<OrderRefund> {
	
	/**
	 * Implementations must implement this method to map each row of data
	 * in the ResultSet. This method should not call {@code next()} on
	 * the ResultSet; it is only supposed to map values of the current row.
	 *
	 * @param rs     the ResultSet to map (pre-initialized for the current row)
	 * @param rowNum the number of the current row
	 * @return the result object for the current row
	 * @throws SQLException if a SQLException is encountered getting
	 *                      column values (that is, there's no need to catch SQLException)
	 */
	@Override
	public OrderRefund mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		OrderRefund orderRefund = new OrderRefund();
		orderRefund.setOrder_id(rs.getInt("order_id"));
		orderRefund.setOrder_uuid(rs.getString("order_uuid"));
		orderRefund.setRefund_uuid(rs.getString("refund_uuid"));
		orderRefund.setType(rs.getInt("type"));
		orderRefund.setOrder_status(rs.getInt("order_status"));
		orderRefund.setStatus(rs.getInt("status"));
		orderRefund.setGoods_id(rs.getInt("goods_id"));
		orderRefund.setCause(rs.getString("cause"));
		orderRefund.setContact_name(rs.getString("contact_name"));
		orderRefund.setContact_phone(rs.getString("contact_phone"));
		orderRefund.setRefund_price(rs.getDouble("refund_price"));
		orderRefund.setCreate_time(rs.getString("create_time"));
		orderRefund.setUpdate_time(rs.getString("update_time"));
		orderRefund.setClose_time(rs.getString("close_time"));
		orderRefund.setFinish_time(rs.getString("finish_time"));
		orderRefund.setSeller_dealtime(rs.getString("seller_dealtime"));
		orderRefund.setIs_agree(rs.getInt("is_agree"));
		orderRefund.setDisagree_cause(rs.getString("disagree_cause"));
		
		return orderRefund;
	}
}
