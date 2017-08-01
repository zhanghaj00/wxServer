/*
package shop.ha.repository.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import shop.ha.entity.Shop;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShopRowMapper implements RowMapper<Shop>{
	
	*/
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
	 *//*

	@Override
	public Shop mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Shop shop = new Shop();
		
		shop.setId(rs.getInt("id"));
		shop.setName(rs.getString("name"));
		shop.setDescribe(rs.getString("describe"));
		shop.setAddress(rs.getString("address"));
		shop.setPhone(rs.getString("phone"));
		shop.setCategory_id(rs.getInt("category_id"));
		shop.setCreate_time(rs.getString("create_time"));
		shop.setUpdate_time(rs.getString("update_time"));
		
		return shop;
	}
}
*/
