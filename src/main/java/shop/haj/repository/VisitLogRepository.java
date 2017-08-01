package shop.haj.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.haj.entity.Pagination;
import shop.haj.entity.Shop;

import java.util.List;

/**
 * <p>Title: shop.ha.repository</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/4/17
 */
@Repository
public class VisitLogRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 根据访问者ID获取该访问者浏览过的店铺信息
	 * @param customer_id
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Shop> findVisitShopsByCusomterID(int customer_id, Pagination page){
		String sql = "select b.* from visit_shop_log a, shop b\n" +
				"where a.shop_id = b.id\n" +
				"and a.customer_id = ? " ;
		return null;//jdbcTemplate.query(sql, new Object[]{customer_id}, new ShopRowMapper());
	}
	
	/**
	 * addShop a new visit shop log
	 * @param customer_id
	 * @param shop_id
	 * @return
	 */
	@Transactional
	public int addVisitShopLog(int customer_id, int shop_id){
		
		String insertSql = "insert into visit_shop_log(customer_id, shop_id, visit_time) " +
				"values(?, ?, now())";
		
		String updateSql = "update visit_shop_log set visit_time=now() where customer_id=? and shop_id=?";
		
		int result = jdbcTemplate.update(updateSql, new Object[]{customer_id, shop_id});
		if(result <= 0){
			jdbcTemplate.update(insertSql, new Object[]{customer_id, shop_id});
		}
		return 1;
	}
}
