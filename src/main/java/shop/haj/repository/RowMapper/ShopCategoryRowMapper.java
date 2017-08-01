package shop.haj.repository.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import shop.haj.entity.ShopCategory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>Title: shop.ha.repository.RowMapper</p>
 * <p/>
 * <p>
 * Description: ShopCategoryRowMapper
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：4/11/17
 */
public class ShopCategoryRowMapper implements RowMapper<ShopCategory> {
	
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
	public ShopCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ShopCategory shopCategory = new ShopCategory();
		
		shopCategory.setId(rs.getString("id"));
		shopCategory.setName(rs.getString("name"));
		shopCategory.setPid(rs.getString("pid"));
		shopCategory.setCreate_time(rs.getString("create_time"));
		shopCategory.setUpdate_time(rs.getString("update_time"));
		
		return shopCategory;
	}
}
