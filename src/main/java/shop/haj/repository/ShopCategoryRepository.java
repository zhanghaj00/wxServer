package shop.haj.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import shop.haj.entity.ShopCategory;
import shop.haj.repository.RowMapper.ShopCategoryRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * <p>Title: shop.ha.repository</p>
 * <p/>
 * <p>
 * Description: ShopCategoryRepository
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：4/11/17
 */
@Repository
public class ShopCategoryRepository {
	
	private Logger logger = LogManager.getLogger();
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查找某分类下的全部子类别
	 *
	 * @param pid
	 * @return
	 */
	public List<ShopCategory> findParentShopCategoryByID(int pid) {
		
		String sql = "select \n" +
				"\tid,\n" +
				"    name,\n" +
				"    pid,\n" +
				"    date_format(create_time, '%Y-%m-%d %H:%i:%s') create_time, \n" +
				"    date_format(update_time, '%Y-%m-%d %H:%i:%s') update_time\n" +
				"from \n" +
				"\tshop_category\n" +
				"where pid=?";
		
		List<ShopCategory> shopCategories = jdbcTemplate.query(sql, new Object[]{pid}, new ShopCategoryRowMapper());
		
		logger.info("findParentShopCategoryByID >>> sql={}, pid={}, size={}",sql, pid, shopCategories.size());
		
		return shopCategories;
		
	}
	
	/**
	 * 根据分类ID查找分类信息
	 *
	 * @param category_id
	 * @return
	 */
	public ShopCategory findShopCategoryByID(int category_id) {
		
		String sql = "select \n" +
				"\tid,\n" +
				"    name,\n" +
				"    pid,\n" +
				"    date_format(create_time, '%Y-%m-%d %H:%i:%s') create_time, \n" +
				"    date_format(update_time, '%Y-%m-%d %H:%i:%s') update_time\n" +
				"from \n" +
				"\tshop_category\n" +
				"where id=?";
		
		ShopCategory shopCategory = jdbcTemplate.queryForObject(sql, new Object[]{category_id}, new ShopCategoryRowMapper());
		
		logger.info("findShopCategoryByID >>> sql={}, id={}, result={}", sql, category_id, shopCategory.toString());
		
		return shopCategory;
	}
	
	/**
	 * 新增商店分类信息
	 *
	 * @param shopCategory
	 * @return
	 */
	public ShopCategory addShopCategory(ShopCategory shopCategory) {
		
		String sql = "insert into shop_category(name, pid, create_time, update_time)\n" +
				"values(?, ?, now(), now())";
		
		KeyHolder holder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, shopCategory.getName());
				ps.setString(2, shopCategory.getPid());
				
				return ps;
			}
		}, holder);
		
		//新生成的序列号
		int id = holder.getKey().intValue();
		
		if(id <= 0){
			logger.error("addShopCategory failure >>> sql={}, param={} ", sql, shopCategory.toString());
			return null;
		}
		
		//赋值新序列号
		shopCategory.setId(String.valueOf(id));
		logger.info("addShopCategory success >>> sql={}, param={} ", sql, shopCategory.toString());
		
		return shopCategory;
	}
	
	/**
	 * 更新商品分类信息
	 *
	 * @param shopCategory
	 * @return
	 */
	public int updateShopCategory(ShopCategory shopCategory) {
		
		String sql = "update shop_category set name=?, pid=?, update_time=now() where id=?";
		
		int result = jdbcTemplate.update(sql, new Object[]{shopCategory.getName(), shopCategory.getPid(), shopCategory.getId()});
		
		if (result <= 0){
			
			logger.error("updateShopCategory failure >>> sql={}, param={}", sql, shopCategory.toString());
			return 0;
		}
		
		logger.info("updateShopCategory success >>> sql={}, param={}", sql, shopCategory.toString());
		
		return 1;
	}
	
	/**
	 * 删除商品分类
	 * 注意：删除仅能以id为条件，不能以pid为条件删除
	 *
	 * @param category_id
	 * @return
	 */
	public int deleteShopCategory(int category_id) {
		
		String sql = "delete from shop_category where id=?";
		
		int result = jdbcTemplate.update(sql, new Object[]{category_id});
		
		if(result <= 0){
			logger.error("deleteShopCategory failure >>> sql={}, category_id={}", sql, category_id);
			return 0;
		}
		logger.info("deleteShopCategory success >>> sql={}, category_id={}", sql, category_id);
		return 1;
	}
	
}
