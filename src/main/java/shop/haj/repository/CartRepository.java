package shop.haj.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import shop.haj.entity.Cart;
import shop.haj.entity.Pagination;
import shop.haj.repository.RowMapper.CartRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * <p>Title: shop.ha.repository</p>
 * <p/>
 * <p>
 * Description: 购物车数据库操作类
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/21/17
 */
@Repository
public class CartRepository {
	
	private Logger logger = LogManager.getLogger(CartRepository.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 用户在某家店的全部购物车信息(独立店模式)
	 * @param customer_id
	 * @param shop_id
	 * @return
	 */
	public List<Cart> findShopCarts(int customer_id, int shop_id, Pagination page){
		
		String sql = "select id,\n" +
				"      customer_id,\n" +
				"      shop_id,\n" +
				"      shop_name,\n" +
				"      goods_id,\n" +
				"      goods_name,\n" +
				"      goods_price,\n" +
				"      goods_num,\n" +
				"      goods_image,\n" +
				"      goods_sku,\n" +
				"      note,\n" +
				"      date_format(create_time, '%Y-%m-%d %H:%i:%s') create_time,\n" +
				"      date_format(update_time, '%Y-%m-%d %H:%i:%s') update_time\n" +
				"from cart\n" +
				"where customer_id=? and shop_id=?" ;
		
		logger.info("findCarts >>> sql={}, args=({}, {})", sql, customer_id, shop_id);
		
		return jdbcTemplate.query(sql, new Object[]{customer_id, shop_id}, new CartRowMapper());
	}
	
	public Cart findShopCart(int customer_id, int shop_id,
	                         int goods_id, String sku){
		
		String sql = "select id,\n" +
				"      customer_id,\n" +
				"      shop_id,\n" +
				"      shop_name,\n" +
				"      goods_id,\n" +
				"      goods_name,\n" +
				"      goods_price,\n" +
				"      goods_num,\n" +
				"      goods_image,\n" +
				"      goods_sku,\n" +
				"      note,\n" +
				"      date_format(create_time, '%Y-%m-%d %H:%i:%s') create_time,\n" +
				"      date_format(update_time, '%Y-%m-%d %H:%i:%s') update_time\n" +
				"from cart\n" +
				"where customer_id=? and shop_id=? and goods_id=?";
		
		Object[] args = new Object[]{customer_id, shop_id, goods_id};
		
		if(null != sku && sku.trim().length() > 0) {
			sql += " and goods_sku=?";
			args = new Object[]{customer_id, shop_id, goods_id, sku};
		}
		
		try {
			Cart cart = jdbcTemplate.queryForObject(sql, args, new CartRowMapper());
			
			logger.info("findCarts >>> sql={}, args=({}, {}, {}, {}), result={}",
					sql, customer_id, shop_id, goods_id, sku, cart);
			
			return cart;
		} catch (DataAccessException e) {
			logger.error(e.toString() + sql, e);
			return null;
		}
	}
	
	/**
	 * 新增至购物车
	 * @param cart
	 * @return
	 */
	public Cart addCart(Cart cart){
		
		String sql = "insert into cart(\n" +
				"      customer_id,\n" +
				"      shop_id,\n" +
				"      shop_name,\n" +
				"      goods_id,\n" +
				"      goods_name,\n" +
				"      goods_price,\n" +
				"      goods_num,\n" +
				"      goods_image,\n" +
				"      goods_sku,\n" +
				"      note,\n" +
				"      create_time,\n" +
				"      update_time)\n" +
				"values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), now())";
		
		logger.info("addCart >>> sql={}, args=({})", sql, cart);
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

				ps.setInt(1, cart.getCustomer_id());
				ps.setInt(2, cart.getShop_id());
				ps.setString(3, cart.getShop_name());
				ps.setInt(4, cart.getGoods_id());
				ps.setString(5, cart.getGoods_name());
				ps.setDouble(6, cart.getGoods_price());
				ps.setInt(7, cart.getGoods_num());
				ps.setString(8, cart.getGoods_image());
				ps.setString(9, cart.getGoods_sku());
				ps.setString(10, cart.getNote());

				return ps;
			}
		}, keyHolder);

		cart.setCart_id(keyHolder.getKey().intValue());
		
		return cart;
	}
	
	/**
	 * 更新商品数量
	 * @param cart_id
	 * @param num
	 * @return
	 */
	public int updateNum(int cart_id, int num){
		
		String sql = "update cart set goods_num=? where id=?";
		
		logger.info("updateNum >>> sql={}, args=({}, {})", num, cart_id);
		
		return jdbcTemplate.update(sql, num, cart_id);
	}
	
	/**
	 * 在购物车中删除某件商品
	 *
	 * @param cart_id
	 * @return
	 */
	public int removeGoodsFromCart(int cart_id) {
		
		String sql = "delete from cart where id=?";
		
		logger.info("removeGoodsFromCart >>> sql={}, args=({})", sql, cart_id);
		
		return jdbcTemplate.update(sql, cart_id);
	}
	
	public int batchRemoveGoodsFromCart(List<Cart> carts){
		
		String sql = "delete from cart where id=?";
		
		logger.info("batchRemoveGoodsFromCart >>> sql={}, cart size=({})", sql, carts.size());
		
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				
			}
			
			@Override
			public int getBatchSize() {
				return 0;
			}
		});
		
		return 0;
	}
	
	/**
	 * 清空用户在某家店的购物车信息
	 *
	 * @param customer_id
	 * @param shop_id
	 * @return
	 */
	public int clearShopCart(int customer_id, int shop_id) {
		
		String sql = "delete from cart where customer_id=? and shop_id=?";
		
		logger.info("clearShopCart >>> sql={}, args=({}, {})", sql, customer_id, shop_id);
		
		return jdbcTemplate.update(sql, customer_id, shop_id);
	}
	
}
