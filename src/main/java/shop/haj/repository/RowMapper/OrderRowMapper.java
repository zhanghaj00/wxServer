package shop.haj.repository.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import shop.haj.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {
	
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
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Order order = new Order();
		/*order.setOrder_id(rs.getInt("id"));
		order.setUuid(rs.getString("uuid"));
		order.setCustomer_id(rs.getInt("customer_id"));
		order.setStatus(rs.getInt("status"));
		order.setDeal_price(rs.getDouble("deal_price"));
		order.setFinal_price(rs.getDouble("final_price"));
		order.setCoupon_used_id(rs.getInt("coupon_used_id"));
		order.setAddress(rs.getString("address"));
		order.setReceiveName(rs.getString("receive_name"));
		order.setReceivePhone(rs.getString("receive_phone"));
		order.setShop_id(rs.getInt("shop_id"));
		order.setPayment_type(rs.getInt("payment_type"));
		order.setOrder_time(rs.getString("order_time"));
		order.setPayment_time(rs.getString("payment_time"));
		order.setSended_time(rs.getString("sended_time"));
		order.setClose_time(rs.getString("close_time"));
		order.setUpdate_time(rs.getString("update_time"));*/
		
		//addShop by lingq 2017/04/02 新增买家留言字段
		order.setMessage(rs.getString("message"));
		
		return order;
	}
}