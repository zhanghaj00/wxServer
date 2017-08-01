package shop.haj.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import shop.haj.entity.GoodsDetail;
import shop.haj.entity.Pagination;
import shop.haj.repository.RowMapper.GoodsDetailRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
 *         CreateTime：3/21/17
 */
@Repository
public class GoodsDetailRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 根据商品ID查找全部的商品详情
	 * @param goods_id
	 * @return
	 */
	public List<GoodsDetail> findGoodsDetailByGoodsID(int goods_id, Pagination page){
		String sql = "select * from goods_detail where goods_id=? ";// + page.getOrderByLimitString();
		return jdbcTemplate.query(sql, new Object[]{goods_id},
				new GoodsDetailRowMapper());
	}
	
	/**
	 * 新增商品详情
	 * @param goodsDetail
	 * @Param goods_id
	 * @return
	 */
	private GoodsDetail addGoodsDetail(GoodsDetail goodsDetail, int goods_id){
		
		String sql = "insert into goods_detail(goods_id, sequence, content, `type`, create_time, update_time)\n" +
				"values(?, ?, ?, ?, now(), now())";
		
		KeyHolder holder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				/*ps.setInt(1, goods_id);
				ps.setInt(2, goodsDetail.getSequence());
				ps.setString(3, goodsDetail.getContent());
				ps.setInt(4, goodsDetail.getType());*/
				return ps;
			}
		}, holder);
		
		goodsDetail.setId(holder.getKey().intValue());
		
		return goodsDetail;
	}
	
	/**
	 * 批量新增商品详情
	 * @param goodsDetails
	 * @param goods_id
	 * @return
	 */
	public List<GoodsDetail> addGoodsDetailList(List<GoodsDetail> goodsDetails, int goods_id){
		
		for (GoodsDetail goodsDetail : goodsDetails) {
			this.addGoodsDetail(goodsDetail, goods_id);
		}
		
		return goodsDetails;
	}
	
	/**
	 * 更新商品详情
	 * @param goodsDetails
	 * @return
	 */
	public int updateGoodsDetail(List<GoodsDetail> goodsDetails){
		
		String sql = "update goods_detail set sequence=?, content=?, `type`=?, update_time=now() where id=?";
		
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				
				GoodsDetail goodsDetail = goodsDetails.get(i);
				
				ps.setInt(1, goodsDetail.getSequence());
				ps.setString(2, goodsDetail.getContent());
				ps.setInt(3, goodsDetail.getType());
				ps.setInt(4, goodsDetail.getId());
				
			}
			
			@Override
			public int getBatchSize() {
				return goodsDetails.size();
			}
		});
		
		return 1;
	}
	
	/**
	 * 删除商品详情
	 * @param goodsdetail_id
	 * @return
	 */
	public int deleteGoodsDetail(int goodsdetail_id){
		
		String sql = "delete from goods_detail where id=?";
		int result = jdbcTemplate.update(sql, new Object[]{goodsdetail_id});
		if(result <= 0) return 0;
		return 1;
	}
	
}