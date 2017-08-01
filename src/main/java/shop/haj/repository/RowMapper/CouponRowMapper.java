package shop.haj.repository.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import shop.haj.entity.Coupon;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 映射Coupon表的实体对应字段，不包含goodsIdList集合
 */
public class CouponRowMapper implements RowMapper<Coupon> {
	
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
	public Coupon mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Coupon coupon = new Coupon();
		
		coupon.setId(rs.getInt("id"));
		coupon.setPrice(rs.getDouble("price"));
		coupon.setLimit_price(rs.getDouble("limit_price"));
		coupon.setBegin_time(rs.getString("begin_time"));
		coupon.setDue_time(rs.getString("due_time"));
		coupon.setStock(rs.getInt("stock"));
		coupon.setPer_limit(rs.getInt("per_limit"));
		coupon.setSuit_limit(rs.getInt("suit_limit"));
		
		return coupon;
	}
}
