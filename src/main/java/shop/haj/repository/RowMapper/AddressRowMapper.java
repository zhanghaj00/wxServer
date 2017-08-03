package shop.haj.repository.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import shop.haj.entity.Address;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressRowMapper implements RowMapper<Address>{
	@Override
	public Address mapRow(ResultSet resultSet, int i) throws SQLException {
		return null;
	}

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
	 *//*
	@Override
	public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Address address = new Address();
		address.setId(rs.getInt("id"));
		address.setProvince(rs.getString("province"));
		address.setCity(rs.getString("city"));
		address.setCountry(rs.getString("country"));
		address.setTown(rs.getString("town"));
		address.setDetail(rs.getString("detail"));
		address.setIs_default(rs.getInt("is_default"));
		address.setCustomer_id(rs.getInt("customer_id"));
		address.setName(rs.getString("name"));
		address.setPhone(rs.getString("phone"));
		
		return address;
	}*/
}
