package shop.haj.repository.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import shop.haj.entity.Seller;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SellerRowMapper implements RowMapper<Seller> {
	
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
	public Seller mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Seller seller = new Seller();
		seller.setId(rs.getInt("id"));
		seller.setWechat_id(rs.getString("wechat_id"));
		seller.setName(rs.getString("name"));
		seller.setAvatar(rs.getString("avatar"));
		seller.setPhone(rs.getString("phone"));
		seller.setNote(rs.getString("note"));
		
		return seller;
	}
}
