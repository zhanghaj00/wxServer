package shop.haj.repository.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import shop.haj.entity.Notice;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NoticeRowMapper implements RowMapper<Notice>{
	
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
	public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Notice notice = new Notice();
		notice.setId(rs.getInt("id"));
		notice.setShop_id(rs.getInt("shop_id"));
		notice.setContent(rs.getString("content"));
		notice.setIs_show(rs.getInt("is_show"));
		
		return notice;
	}
}
