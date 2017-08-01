package shop.haj.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import shop.haj.entity.Address;
import shop.haj.entity.Pagination;
import shop.haj.repository.RowMapper.AddressRowMapper;

import java.sql.*;
import java.util.List;

/**
 * <p>Title: shop.ha.repository</p>
 * <p/>
 * <p>
 * Description: 买家地址数据操作类
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/21/17
 */
@Repository
public class AddressRepository {
	
	private Logger logger = LogManager.getLogger(AddressRepository.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 新增地址
	 * @param address
	 * @return
	 */
	public Address addAddress(Address address){
		String sql = "insert into address(province, city, country, town, detail, is_default, customer_id,\n" +
				"`name`, phone, create_time, update_time) values(?, ?, ?, ?, ?, ?, ?, ?, ?, now(), now())";
		
		KeyHolder holder = new GeneratedKeyHolder();
		
		jdbcTemplate.update( (con) -> {
			
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				/*ps.setString(1, address.getProvince());
				ps.setString(2, address.getCity());
				ps.setString(3, address.getCountry());
				ps.setString(4, address.getTown());
				ps.setString(5, address.getDetail());
				ps.setInt(6, address.getIs_default());
				ps.setInt(7, address.getCustomer_id());
				ps.setString(8, address.getName());
				ps.setString(9, address.getPhone());*/
				
				return ps;
				
		}, holder);
		
		address.setId(holder.getKey().intValue());
		
		return address;
	}
	
	/**
	 * 查找买家的全部地址列表
	 * @param customer_id
	 * @return
	 */
	public List<Address> findAddressListByCustomerID(int customer_id, Pagination page){
		String sql = "select\n" +
				"\tid,\n" +
				"\tprovince, \n" +
				"    city, \n" +
				"    country, \n" +
				"    town, \n" +
				"    detail, \n" +
				"    is_default, \n" +
				"    customer_id, \n" +
				"    date_format(create_time, '%Y-%m-%d %H:%i:%s') create_time,\n" +
				"    date_format(update_time, '%Y-%m-%d %H:%i:%s') update_time,\n" +
				"    `name`,\n" +
				"    phone\n" +
				"from address\n" +
				"where customer_id=? " ;
		return jdbcTemplate.query(sql, new Object[]{customer_id}, new AddressRowMapper());
	}
	
	/**
	 * 根据地址ID查找
	 * @param address_id
	 * @return
	 */
	public Address findAddressByID(int address_id){
		String sql = "select\n" +
				"\tid,\n" +
				"\tprovince, \n" +
				"    city, \n" +
				"    country, \n" +
				"    town, \n" +
				"    detail, \n" +
				"    is_default, \n" +
				"    customer_id, \n" +
				"    date_format(create_time, '%Y-%m-%d %H:%i:%s') create_time,\n" +
				"    date_format(update_time, '%Y-%m-%d %H:%i:%s') update_time,\n" +
				"    `name`,\n" +
				"    phone\n" +
				"from address\n" +
				"where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{address_id},
						new AddressRowMapper());
	}
	
	/**
	 * 查找用户默认地址
	 * @param customer_id
	 * @return
	 */
	public Address findDefaultAddress(int customer_id){
		String sql = "select\n" +
				"\tid,\n" +
				"\tprovince, \n" +
				"    city, \n" +
				"    country, \n" +
				"    town, \n" +
				"    detail, \n" +
				"    is_default, \n" +
				"    customer_id, \n" +
				"    date_format(create_time, '%Y-%m-%d %H:%i:%s') create_time,\n" +
				"    date_format(update_time, '%Y-%m-%d %H:%i:%s') update_time,\n" +
				"    `name`,\n" +
				"    phone\n" +
				"from address\n" +
				"where customer_id=? and is_default=1";
		
		logger.info("findDefaultAddress repo >>> sql={}, args=(customer_id={})", sql, customer_id);
		
		try {
			return jdbcTemplate.queryForObject(sql, new Object[]{customer_id},
					new AddressRowMapper());
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	/**
	 * 更新地址信息
	 * @param address
	 * @return
	 */
	public int updateAddress(Address address){
		String sql = "update address set province=?, city=?, country=?, town=?, detail=?,\n" +
				"is_default=?, `name`=?, phone=?, update_time=now() where id=?";
		
		int result = jdbcTemplate.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, address.getProvince());
				ps.setString(2, address.getCity());
				ps.setString(3, address.getCountry());
				ps.setString(4, address.getTown());
				ps.setString(5, address.getDetail());
				ps.setInt(6, address.getIs_default());
				ps.setString(7, address.getName());
				ps.setString(8, address.getPhone());
				ps.setInt(9, address.getId());
			}
		});
		if(result <= 0) return 0;
		return 1;
	}
	
	/**
	 * 删除某个地址信息
	 * @param address_id
	 * @return
	 */
	public int deleteAddress(int address_id){
		
		String sql = "delete from address where id=?";
		
		int result = jdbcTemplate.update(sql, new Object[]{address_id});
		
		if(result <= 0) return 0;
		return 1;
	}
	
	/**
	 * 设置默认地址
	 * @param address_id
	 * @return
	 */
	public int setDefaultAddress(int address_id){
		
		String sql = "update address set is_default=1 where id=?";
		
		logger.info("setDefaultAddress repo >>> sql={}, args=(id={})", sql, address_id);
		
		return jdbcTemplate.update(sql, address_id);
	}
	
	/**
	 * 将该用户的其他地址设置为非默认
	 * @param customer_id
	 * @param address_id
	 * @return
	 */
	public int setUndefaultAddress(int customer_id, int address_id){
		
		String sql = "update address set is_default=0 where customer_id=? and id!=?";
		
		logger.info("setUndefaultAddress repo >>> sql={}, args=(customer_id={}, address_id={})", sql, customer_id, address_id);
		
		return jdbcTemplate.update(sql, customer_id, address_id);
	}
}
