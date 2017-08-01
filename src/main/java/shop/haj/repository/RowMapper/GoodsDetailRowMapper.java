package shop.haj.repository.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import shop.haj.entity.GoodsDetail;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GoodsDetailRowMapper implements RowMapper<GoodsDetail>{
	
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
	public GoodsDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		GoodsDetail goodsDetail = new GoodsDetail();
		
		goodsDetail.setId(rs.getInt("id"));
		goodsDetail.setContent(rs.getString("content"));
		goodsDetail.setSequence(rs.getInt("sequence"));
		goodsDetail.setType(rs.getInt("type"));
		
		return goodsDetail;
	}
}
