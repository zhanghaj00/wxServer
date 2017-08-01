package shop.haj.repository.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import shop.haj.entity.GoodsSkuDetail;
import shop.haj.entity.GoodsSkuDetailBase;

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
public class GoodsSkuDetailRowMapper implements RowMapper<GoodsSkuDetail> {
	
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
	public GoodsSkuDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		GoodsSkuDetail detail = new GoodsSkuDetail();
		detail.setSku(rs.getString("sku"));
		
		GoodsSkuDetailBase detailBase = new GoodsSkuDetailBase();
		detailBase.setStock(rs.getInt("stock"));
		detailBase.setPrice(rs.getDouble("price"));
		detailBase.setImageUrl(rs.getString("image_url"));
		
		detail.setGoodsSkuDetailBase(detailBase);
		
		return detail;
	}
}
