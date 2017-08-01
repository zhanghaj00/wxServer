/*
package shop.ha.repository.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import shop.ha.entity.Image;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageRowMapper implements RowMapper<Image>{
	
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
	public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Image image = new Image();
		image.setId(rs.getInt("id"));
		image.setUrl(rs.getString("url"));
		image.setCreate_time(rs.getString("create_time"));
		
		return image;
	}
}
*/
