package shop.haj.repository.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import shop.haj.entity.Goods;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GoodsRowMapper implements RowMapper<Goods>{
	
	
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
	public Goods mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Goods goods = new Goods();
		goods.setId(rs.getInt("id"));
		goods.setUuid(rs.getString("uuid"));
		goods.setName(rs.getString("name"));
		goods.setShop_id(rs.getInt("shop_id"));
		goods.setStatus(rs.getInt("status"));
		goods.setOriginal_price(rs.getDouble("original_price"));
		goods.setSell_price(rs.getDouble("sell_price"));
		goods.setStock(rs.getInt("stock"));
		goods.setSales_volume(rs.getInt("sales_volume"));
		goods.setInner_cid(rs.getInt("inner_cid"));
		goods.setGlobal_cid(rs.getInt("global_cid"));
		goods.setIs_recommend(rs.getInt("is_recommend"));
		goods.setCreate_time(rs.getString("create_time"));
		goods.setUpdate_time(rs.getString("update_time"));
		
		return goods;
	}
}