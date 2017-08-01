package shop.haj.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.haj.entity.Pagination;
import shop.haj.entity.Seller;
import shop.haj.repository.RowMapper.SellerRowMapper;

import java.sql.*;
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
 *         CreateTime：3/14/17
 */
@Repository
public class SellerRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查找所有买家信息
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Seller> findAll(Pagination page){
		String sql = "select * from seller " ;//+ page.getOrderByLimitString();
		return jdbcTemplate.query(sql, new SellerRowMapper());
	}
	
	/**
	 * 根据ID查找买家信息
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Seller findByID(int id){
		String sql = "select * from seller where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new SellerRowMapper());
	}
	
	/**
	 * 新增买家信息
	 * @param seller
	 * @return
	 */
	public Seller add(final Seller seller){
		final String sql = "insert into seller(wechat_id, `name`, avatar, phone, note, create_time, update_time)" +
				" values(?, ?, ?, ?, ?, now(), now())";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				/*ps.setString(1, seller.getWechat_id());
				ps.setString(2, seller.getName());
				ps.setString(3, seller.getAvatar());
				ps.setString(4, seller.getPhone());
				ps.setString(5, seller.getNote());*/
				
				return ps;
			}
		}, holder);
		
		//设置主键ID
		seller.setId(holder.getKey().intValue());
		return seller;
	}
	
	/**
	 * 更新全量买家信息
	 * @param seller
	 */
	@Transactional
	public int update(Seller seller){
		String sql = "update seller set wechat_id=?, name=?, avatar=?, phone=?, note=?, update_time=now() where id=?";
		int result = jdbcTemplate.update(sql,
				new Object[]{seller.getWechat_id(), seller.getName(),
						seller.getAvatar(), seller.getPhone(), seller.getNote(), seller.getId()});
		
		if(result <= 0) return 0;
		return 1;
	}
	
	/**
	 * 删除指定买家
	 * @param id 买家ID
	 */
	public int delete(int id){
		String sql = "delete from seller where id=?";
		int result = jdbcTemplate.update(sql, new Object[]{id}, new int[]{Types.INTEGER});
		if(result <= 0) return 0;
		return 1;
	}
	
}