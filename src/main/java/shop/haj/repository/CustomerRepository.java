package shop.haj.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import shop.haj.entity.Customer;
import shop.haj.entity.Pagination;
import shop.haj.repository.RowMapper.CustomerRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

/**
 * <p>Title: shop.ha.repository</p>
 * <p/>
 * <p>
 * Description:  买家数据库操作类
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/2/17
 */
@Repository
public class CustomerRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查找所有买家信息
	 * @return
	 */
	public List<Customer> findAll(Pagination page){
		String sql = "select id, nick_name, open_id, union_id, avatar_url, phone, note,\n" +
				"gender, language, city, province, country, date_format(create_time,'%Y-%m-%d %H:%i:%s') create_time,\n" +
				"date_format(update_time,'%Y-%m-%d %H:%i:%s') update_time from customer " ;
		return jdbcTemplate.query(sql, new CustomerRowMapper());
	}
	
	/**
	 * 根据ID查找买家信息
	 * @param id
	 * @return
	 */
	public Customer findByID(int id){
		String sql = "select id, nick_name, open_id, union_id, avatar_url, phone, note,\n" +
				"gender, language, city, province, country, date_format(create_time,'%Y-%m-%d %H:%i:%s') create_time,\n" +
				"date_format(update_time,'%Y-%m-%d %H:%i:%s') update_time from customer where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new CustomerRowMapper());
	}
	
	/**
	 * 根据open_id查找用户数据
	 * @param open_id
	 * @return
	 */
	public Customer findByOpenID(String open_id){
		String sql = "select id, nick_name, open_id, union_id, avatar_url, phone, note,\n" +
				"gender, language, city, province, country, date_format(create_time,'%Y-%m-%d %H:%i:%s') create_time,\n" +
				"date_format(update_time,'%Y-%m-%d %H:%i:%s') update_time from customer where open_id=?";
		
		Object object = null;
		try {
			object = jdbcTemplate.queryForObject(sql, new Object[]{open_id}, new CustomerRowMapper());
		} catch (DataAccessException e) {
		}
		
		if(object == null) return null;
		
		return (Customer)object;
	}
	
	public Customer findByIdAndOpenId(int id, String open_id){
		
		String sql = "select id, nick_name, open_id, union_id, avatar_url, phone, note,\n" +
				"gender, language, city, province, country, date_format(create_time,'%Y-%m-%d %H:%i:%s') create_time,\n" +
				"date_format(update_time,'%Y-%m-%d %H:%i:%s') update_time from customer where id=? and open_id=?";
		
		return jdbcTemplate.queryForObject(sql, new Object[]{id, open_id}, new CustomerRowMapper());
	}
	
	/**
	 * 新增买家信息
	 * @param customer
	 * @return
	 */
	public Customer add(final Customer customer){
		final String sql = "insert into customer(nick_name, open_id, union_id, avatar_url, phone, note, gender, \n" +
				"`language`, city, province, country, create_time, update_time)\n" +
				"values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), now())";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				/*ps.setString(1, customer.getNickName());
				ps.setString(2, customer.getOpenId());
				ps.setString(3, customer.getUnionId());
				ps.setString(4, customer.getAvatarUrl());
				ps.setString(5, customer.getPhone());
				ps.setString(6, customer.getNote());
				ps.setString(7, customer.getGender());
				ps.setString(8, customer.getLanguage());
				ps.setString(9, customer.getCity());
				ps.setString(10, customer.getProvince());
				ps.setString(11, customer.getCountry());*/
				
				return ps;
			}
		}, holder);
		
		//设置主键ID
		customer.setId(holder.getKey().toString());
		return customer;
	}
	
	/**
	 * 更新全量买家信息
	 * @param customer
	 */
	public int update(Customer customer){
		String sql = "update customer set nick_name=?, open_id=?, union_id=?, avatar_url=?,\n" +
				"phone=?, note=?, gender=?, `language`=?, city=?, province=?, \n" +
				"country=?, update_time=now() where id=?";
		int result = jdbcTemplate.update(sql,
				new Object[]{customer.getNickName(), customer.getOpenId(), customer.getUnionId(),
						customer.getAvatarUrl(), customer.getPhone(), customer.getNote(), customer.getGender(),
						customer.getLanguage(), customer.getCity(), customer.getProvince(),
						customer.getCountry(), customer.getId()});
		
		if(result <= 0) return 0;
		return 1;
	}
	
	/**
	 * 删除指定买家
	 * @param id 买家ID
	 */
	public int delete(int id){
		String sql = "delete from customer where id=?";
		int result = jdbcTemplate.update(sql, new Object[]{id}, new int[]{Types.INTEGER});
		if(result <= 0) return 0;
		return 1;
	}
	
}
