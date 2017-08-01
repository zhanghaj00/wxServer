package shop.haj.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import shop.haj.entity.GoodsSkuDetail;
import shop.haj.entity.GoodsSkuInfo;
import shop.haj.repository.RowMapper.GoodsSkuDetailRowMapper;
import shop.haj.repository.RowMapper.GoodsSkuInfoRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * <p>Title: shop.ha.repository</p>
 * <p/>
 * <p>
 * Description: 商品规格数据库操作类
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/22/17
 */
@Repository
public class GoodsSkuRepository {
	
	private Logger logger = LogManager.getLogger(GoodsSkuRepository.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查找商品规格信息
	 * @param goods_id
	 * @return
	 */
	public GoodsSkuInfo findInfo(int goods_id){
		
		String sql = "select prop1, value1, prop2, value2, prop3, value3 from goods_sku_info where goods_id=?";
		
		GoodsSkuInfo goodsSkuInfo = null;
		
		try {
			goodsSkuInfo = jdbcTemplate.queryForObject(sql, new Object[]{goods_id}, new GoodsSkuInfoRowMapper());
		} catch (DataAccessException e) {
		}
		
		logger.info("findInfo >>> sql={}, args=({}), result={}", sql, goods_id, goodsSkuInfo);
		
		return goodsSkuInfo;
		
	}
	
	/**
	 * 查找商品规格明细
	 * @param goods_id
	 * @return
	 */
	public List<GoodsSkuDetail> findDetail(int goods_id){
		
		String sql = "select sku, stock, price, image_url from goods_sku_detail where goods_id=?";
		
		List<GoodsSkuDetail> goodsSkuDetails = null;
		
		try {
			goodsSkuDetails = jdbcTemplate.query(sql, new Object[]{goods_id}, new GoodsSkuDetailRowMapper());
		} catch (DataAccessException e) {
			logger.error(e.toString() + sql, e);
		}
		
		logger.info("findDetail >>> sql={}, args=({}), result size={}", sql, goods_id, goodsSkuDetails.size());
		
		return goodsSkuDetails;
	}
	
	/**
	 * 新增商品规格信息
	 * @param info
	 * @return
	 */
	public int addInfo(GoodsSkuInfo info, int goods_id){
		
		String sql = "insert into goods_sku_info(goods_id, prop1, value1, prop2, value2, prop3, value3, create_time)\n" +
				"values(?, ?, ?, ?, ?, ?, ?, now())";
		
		int result = 0;
		
		try {
			result = jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, goods_id);
					ps.setString(2, info.getProp1());
					ps.setString(3, info.getValue1());
					ps.setString(4, info.getProp2());
					ps.setString(5, info.getValue2());
					ps.setString(6, info.getProp3());
					ps.setString(7, info.getValue3());
					
					return ps;
				}
			});
		} catch (DataAccessException e) {
			logger.error(e.toString() + sql, e);
			return 0;
		}
		
		logger.info("addInfo >>> sql={}, args=({}), result={}", sql, info, result);
		return 1;
	}
	
	/**
	 * 新增商品规格明细
	 * @param details
	 * @return
	 */
	public int addDetail(List<GoodsSkuDetail> details, int goods_id){
		
		String sql = "insert into goods_sku_detail(goods_id, sku, stock, price, image_url, create_time)\n" +
				"values(?, ?, ?, ?, ?, now())";
		
		try {
			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					GoodsSkuDetail detail = details.get(i);
					ps.setInt(1, goods_id);
					ps.setString(2, detail.getSku());
					ps.setInt(3, detail.getGoodsSkuDetailBase().getStock());
					ps.setDouble(4, detail.getGoodsSkuDetailBase().getPrice());
					ps.setString(5, detail.getGoodsSkuDetailBase().getImageUrl());
				}
				
				@Override
				public int getBatchSize() {
					return details.size();
				}
			});
		} catch (DataAccessException e) {
			logger.error(e.toString() + sql, e);
			return 0;
		}
		
		return 1;
	}
	
	/**
	 * 更新商品规格信息
	 * @param info
	 * @return
	 */
	public int updateInfo(GoodsSkuInfo info, int goods_id){
		
		String sql = "update goods_sku_info set prop1=?, value1=?, \n" +
				"prop2=?, value2=?, prop3=?, value3=?\n" +
				"where goods_id=?";
		
		try {
			jdbcTemplate.update(sql, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, info.getProp1());
					ps.setString(2, info.getValue1());
					ps.setString(3, info.getProp2());
					ps.setString(4, info.getValue2());
					ps.setString(5, info.getProp3());
					ps.setString(6, info.getValue3());
					ps.setInt(7, goods_id);
				}
			});
		} catch (DataAccessException e) {
			logger.error(e.toString() + sql, e);
			return 0;
		}
		
		logger.info("updateInfo >>> sql={}, args={}", sql, info);
		
		return 1;
	}
	
	
	/**
	 * 删除商品规格
	 * @param goods_id
	 * @return
	 */
	public int deleteInfo(int goods_id){
		
		String sql = "delete from goods_sku_info where goods_id=?";
		
		try {
			jdbcTemplate.update(sql, goods_id);
		} catch (DataAccessException e) {
			logger.error(e.toString() + sql, e);
			return 0;
		}
		
		logger.info("deleteInfo >>> sql={}, args=({})", sql, goods_id);
		
		return 1;
	}
	
	/**
	 * 删除商品规格明细
	 * @return
	 */
	public int deleteDetail(int goods_id){
		
		String sql = "delete from goods_sku_detail where goods_id=?";
		
		try {
			jdbcTemplate.update(sql, goods_id);
		} catch (DataAccessException e) {
			logger.error(e.toString() + sql, e);
			return 0;
		}
		
		logger.info("deleteDetail >>> sql={}, args=({})", sql, goods_id);
		
		return 1;
	}
}
