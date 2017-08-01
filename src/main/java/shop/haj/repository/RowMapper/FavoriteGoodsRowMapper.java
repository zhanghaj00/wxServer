package shop.haj.repository.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import shop.haj.entity.FavoriteGoods;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>Title: shop.ha.repository.RowMapper</p>
 * <p/>
 * <p>
 * Description: FavoriteGoodsRowMapper
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/24/17
 */
public class FavoriteGoodsRowMapper implements RowMapper<FavoriteGoods>{
	
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
	public FavoriteGoods mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		FavoriteGoods favoriteGoods = new FavoriteGoods();
		favoriteGoods.setCustomer_id(rs.getInt("customer_id"));
		favoriteGoods.setShop_id(rs.getInt("shop_id"));
		favoriteGoods.setGoods_id(rs.getInt("goods_id"));
		favoriteGoods.setCreate_time(rs.getString("create_time"));
		
		return favoriteGoods;
	}
}
