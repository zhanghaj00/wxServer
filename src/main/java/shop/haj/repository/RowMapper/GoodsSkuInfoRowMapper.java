package shop.haj.repository.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import shop.haj.entity.GoodsSkuInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>Title: shop.ha.repository.RowMapper</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/23/17
 */
public class GoodsSkuInfoRowMapper implements RowMapper<GoodsSkuInfo>{
	
	
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
	public GoodsSkuInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		GoodsSkuInfo goodsSkuInfo = new GoodsSkuInfo();
		
		goodsSkuInfo.setProp1(rs.getString("prop1"));
		goodsSkuInfo.setValue1(rs.getString("value1"));
		goodsSkuInfo.setProp2(rs.getString("prop2"));
		goodsSkuInfo.setValue2(rs.getString("value2"));
		goodsSkuInfo.setProp3(rs.getString("prop3"));
		goodsSkuInfo.setValue3(rs.getString("value3"));
		
		return goodsSkuInfo;
	}
}
