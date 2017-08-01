package shop.haj.repository.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import shop.haj.entity.OrderListSingleInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 订单信息冗余表
 */
public class OrderListSingleInfoRowMapper implements RowMapper<OrderListSingleInfo>{
	
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
	public OrderListSingleInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		OrderListSingleInfo singleInfo = new OrderListSingleInfo();
		
		singleInfo.setOrder_id(rs.getInt("order_id"));
		singleInfo.setOrder_time(rs.getString("order_time"));
		singleInfo.setStatus(rs.getInt("status"));
		singleInfo.setFinal_price(rs.getDouble("final_price"));
		singleInfo.setGoods_id(rs.getInt("goods_id"));
		singleInfo.setImage_url(rs.getString("image_url"));
		singleInfo.setGoods_name(rs.getString("goods_name"));
		singleInfo.setGoods_sku(rs.getString("goods_sku").equals("null") ? null : rs.getString("goods_sku"));
		singleInfo.setGoods_price(rs.getDouble("goods_price"));
		singleInfo.setCount(rs.getInt("count"));
		singleInfo.setShop_id(rs.getInt("shop_id"));
		singleInfo.setCustomer_id(rs.getInt("customer_id"));
		singleInfo.setShop_name(rs.getString("shop_name"));
		
		return singleInfo;
	}
}
