package shop.haj.repository.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import shop.haj.entity.Cart;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>Title: shop.ha.repository.RowMapper</p>
 * <p/>
 * <p>
 * Description: 购物车的数据库映射
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/21/17
 */
public class CartRowMapper implements RowMapper<Cart>{
	
	
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
	public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Cart cart = new Cart();
		cart.setCart_id(rs.getInt("id"));
		cart.setCustomer_id(rs.getInt("customer_id"));
		cart.setShop_id(rs.getInt("shop_id"));
		cart.setShop_name(rs.getString("shop_name"));
		cart.setGoods_id(rs.getInt("goods_id"));
		cart.setGoods_name(rs.getString("goods_name"));
		cart.setGoods_price(rs.getDouble("goods_price"));
		cart.setGoods_num(rs.getInt("goods_num"));
		cart.setGoods_image(rs.getString("goods_image"));
		cart.setGoods_sku(rs.getString("goods_sku"));
		cart.setNote(rs.getString("note"));
		cart.setCreate_time(rs.getString("create_time"));
		cart.setUpdate_time(rs.getString("update_time"));
		
		return cart;
	}
}
