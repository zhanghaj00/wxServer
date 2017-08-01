package shop.haj.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import shop.haj.entity.FavoriteGoods;
import shop.haj.entity.Pagination;
import shop.haj.repository.RowMapper.FavoriteGoodsRowMapper;

import java.util.List;

/**
 * <p>Title: shop.ha.repository</p>
 * <p/>
 * <p>
 * Description: FavoriteGoodsRepository
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/24/17
 */
@Repository
public class FavoriteGoodsRepository {
	
	private Logger logger = LogManager.getLogger(FavoriteGoodsRepository.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查找卖家全部收藏的商品
	 * @param customer_id
	 * @return
	 */
	public List<FavoriteGoods> findFavoriteGoodsByCustomer(int customer_id, Pagination page){
		
		String sql = "select customer_id, shop_id, goods_id, \n" +
				"date_format(create_time, '%Y-%m-%d %H:%i:%s') create_time \n" +
				"from favorite_goods\n" +
				"where customer_id=?" ;//+ page.getOrderByLimitString();
		
		try {
			List<FavoriteGoods> favoriteGoodss = jdbcTemplate.query(sql, new Object[]{customer_id}, new FavoriteGoodsRowMapper());
			logger.info("findFavoriteGoodsByCustomer >>> sql={}, args=({}), result size={}",
					sql, customer_id, favoriteGoodss.size());
			return favoriteGoodss;
		} catch (DataAccessException e) {
			logger.error(e.toString() + sql, e);
			return null;
		}
	}
	
	/**
	 * 查找卖家在该店铺收藏的商品
	 * @param customer_id
	 * @param shop_id
	 * @return
	 */
	public List<FavoriteGoods> findFavoriteGoodsByCustomerShop(int customer_id, int shop_id, Pagination page){
		
		String sql = "select customer_id, shop_id, goods_id, \n" +
				"date_format(create_time, '%Y-%m-%d %H:%i:%s') create_time \n" +
				"from favorite_goods\n" +
				"where customer_id=? and shop_id=?" ;//+ page.getOrderByLimitString();
		
		try {
			List<FavoriteGoods> favoriteGoodss = jdbcTemplate.query(sql, new Object[]{customer_id, shop_id}, new FavoriteGoodsRowMapper());
			logger.info("findFavoriteGoodsByCustomerShop >>> sql={}, args=({}), result size={}",
					sql, customer_id, favoriteGoodss.size());
			return favoriteGoodss;
		} catch (DataAccessException e) {
			logger.error(e.toString() + sql, e);
			return null;
		}
		
	}
	
	/**
	 * 商品加入收藏
	 * @param favoriteGoods
	 * @return
	 */
	public int addFavoriteGoods(FavoriteGoods favoriteGoods){
		
		String sql = "insert into favorite_goods(customer_id, shop_id, goods_id, create_time)\n" +
				"values(?, ?, ?, now())";
		
		try {
			int result = jdbcTemplate.update(sql, favoriteGoods.getCustomer_id(), favoriteGoods.getShop_id(), favoriteGoods.getGoods_id());
			
			logger.info("addFavoriteGoods >>> sql={}, args=({}), result={}",
					sql, favoriteGoods.toString(), result);
			return result;
		} catch (DataAccessException e) {
			logger.error(e.toString() + sql, e);
			return 0;
		}
	}
	
	/**
	 * 移除收藏
	 * @param customer_id
	 * @param goods_id
	 * @return
	 */
	public int removeFavoriteGoods(int customer_id, int goods_id){
		
		String sql = "delete from favorite_goods where customer_id=? and goods_id=?";
		
		try {
			int result = jdbcTemplate.update(sql, customer_id, goods_id);
			logger.info("removeFavoriteGoods >>> sql={}, args=({}, {}), result={}",
					sql, customer_id, goods_id, result);
			return 1;
		} catch (DataAccessException e) {
			logger.error(e.toString() + sql , e);
			return 0;
		}
	}
}
