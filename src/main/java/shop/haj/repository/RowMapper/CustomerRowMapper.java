package shop.haj.repository.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import shop.haj.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer>{
	
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
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Customer customer = new Customer();
		/*customer.setId(rs.getString("id"));
		customer.setNickName(rs.getString("nick_name"));
		customer.setOpenId(rs.getString("open_id"));
		customer.setUnionId(rs.getString("union_id"));
		customer.setAvatarUrl(rs.getString("avatar_url"));
		customer.setPhone(rs.getString("phone"));
		customer.setNote(rs.getString("note"));
		customer.setGender(rs.getString("gender"));
		customer.setLanguage(rs.getString("language"));
		customer.setCity(rs.getString("city"));
		customer.setProvince(rs.getString("province"));
		customer.setCountry(rs.getString("country"));
		customer.setCreate_time(rs.getString("create_time"));
		customer.setUpdate_time(rs.getString("update_time"));*/
		
		return customer;
	}
}