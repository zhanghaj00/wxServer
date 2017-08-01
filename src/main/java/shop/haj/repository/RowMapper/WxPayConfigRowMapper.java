package shop.haj.repository.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import shop.haj.entity.wxPay.WxPayConfig;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>Title: shop.ha.repository.RowMapper</p>
 * <p/>
 * <p>
 * Description: WxPayConfigRowMapper
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/16/17
 */
public class WxPayConfigRowMapper implements RowMapper<WxPayConfig> {
	
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
	public WxPayConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		WxPayConfig config = new WxPayConfig();
		config.setAppId(rs.getString("app_id"));
		config.setMchId(rs.getString("mch_id"));
		config.setMchKey(rs.getString("mchKey"));
		config.setKeyPath(rs.getString("keyPath"));
		
		return config;
	}
}
