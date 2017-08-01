/*
package shop.ha.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.ha.entity.Image;
import shop.ha.entity.Pagination;
import shop.ha.entity.Shop;
import shop.ha.repository.RowMapper.ShopRowMapper;

import java.sql.*;
import java.util.List;

*/
/**
 * <p>Title: shop.ha.repository</p>
 * <p/>
 * <p>
 * Description: 店铺数据操作类
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/1/17
 *//*

@Repository
public class ShopRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	*/
/**
	 * query all shop information
	 * @return
	 *//*

	@Transactional(readOnly = true)
	public List<Shop> findAllShops(Pagination page){
		String sql = "select \n" +
				"\tid,\n" +
				"    `name`,\n" +
				"    category_id,\n" +
				"    `describe`,\n" +
				"    address,\n" +
				"    phone,\n" +
				"    date_format(create_time, '%Y-%m-%d %H:%i:%s') create_time,\n" +
				"    date_format(update_time, '%Y-%m-%d %H:%i:%s') update_time\n" +
				"from shop " + page.getOrderByLimitString();
		return jdbcTemplate.query(sql, new ShopRowMapper());
	}
	
	*/
/**
	 * find shop by id
	 * @param id
	 * @return
	 *//*

	@Transactional(readOnly = true)
	public Shop findShopById(String id){
		String sql = "select * from shop where id=?";
		return jdbcTemplate.queryForObject(sql,
							new Object[]{id},
							new ShopRowMapper());
	}
	
	*/
/**
	 * addShop a new shop
	 * @param shop
	 * @return
	 *//*

	@Transactional
	public Shop addShop(final Shop shop, String seller_id){
		
		final String sql = "insert into shop(`name`, `describe`, address, phone, category_id, create_time, update_time) " +
				" values(?, ?, ?, ?, ?, now(), now())";
		
		KeyHolder holder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				*/
/*ps.setString(1, shop.getName());
				ps.setString(2, shop.getDescribe());
				ps.setString(3, shop.getAddress());
				ps.setString(4, shop.getPhone());
				ps.setInt(5, shop.getCategory_id());*//*

				
				return ps;
			}
		}, holder);

		String shop_id = holder.getKey().toString();
		if(shop_id !=null){
			shop.setId(shop_id);
			//新增关系数据
			this.addSellerShopRel(seller_id, shop_id);
		}
		
		return shop;
	}
	
	*/
/**
	 * 新增卖家和店铺的关系表
	 * @param seller_id
	 * @param shop_id
	 * @return
	 *//*

	public int addSellerShopRel(final String seller_id, final String shop_id){
		
		String sql = "insert into seller_shop_rel(seller_id, shop_id, ctime) values(?, ?, now())";
		
		int result = jdbcTemplate.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				*/
/*ps.setInt(1, seller_id);
				ps.setInt(2, shop_id);*//*

			}
		});
		
		if(result <= 0) return 0;
		return 1;
	}
	
	*/
/**
	 * updateShop a exist shop
	 * @param shop
	 *//*

	@Transactional
	public int updateShop(final Shop shop){
		String sql = "update shop set `name`=?, `describe`=?, address=?, phone=?, category_id=?, update_time=now() where id=?";
		int result = jdbcTemplate.update(sql, new Object[]{shop.getName(), shop.getDescribe(),
							shop.getAddress(), shop.getPhone(), shop.getCategory_id(), shop.getId()});
		if(result <= 0) return 0;
		return 1;
	}
	
	*/
/**
	 * deleteShop a Shop by Id
	 * @param shop_id
	 * @param seller_id
	 * @return
	 *//*

	@Transactional
	public int deleteShopById(int shop_id, int seller_id){
		String sql = "delete from shop where id=?";
		int result1 = this.deleteSellerShopRel(shop_id, seller_id);
		int result2 = jdbcTemplate.update(sql, new Object[]{shop_id}, new int[]{Types.INTEGER});
		if(result1 <= 0 || result2 <= 0) return 0;
		return 1;
	}
	
	*/
/**
	 * 删除卖家店铺关系表数据
	 * @param shop_id
	 * @param seller_id
	 * @return
	 *//*

	private int deleteSellerShopRel(int shop_id, int seller_id){
		String sql = "delete from seller_shop_rel where shop_id=? and seller_id=?";
		return jdbcTemplate.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, shop_id);
				ps.setInt(2, seller_id);
			}
		});
	}
}*/
