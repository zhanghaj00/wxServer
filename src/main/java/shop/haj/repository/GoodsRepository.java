/*
package shop.haj.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import shop.haj.entity.Goods;
import shop.haj.entity.Image;
import shop.haj.entity.Pagination;
import shop.haj.repository.RowMapper.GoodsRowMapper;

import java.sql.*;
import java.util.List;

*/
/**
 * <p>Title: shop.ha.repository</p>
 * <p/>
 * <p>
 * Description: GoodsRepository
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/4/17
 *//*

@Repository
public class GoodsRepository {
	
	private Logger logger = LogManager.getLogger(GoodsRepository.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	*/
/**
	 * 查找所有的商品
	 * @return
	 *//*

	public List<Goods> findAll(int shop_id, Pagination page){
		String sql = "SELECT \n" +
				"    id,\n" +
				"    uuid,\n" +
				"    `name`,\n" +
				"    shop_id,\n" +
				"    status,\n" +
				"    original_price,\n" +
				"    sell_price,\n" +
				"    stock,\n" +
				"    sales_volume,\n" +
				"    inner_cid,\n" +
				"    global_cid,\n" +
				"    is_recommend,\n" +
				"    DATE_FORMAT(create_time, '%Y-%m-%d %H:%i:%s') create_time,\n" +
				"    DATE_FORMAT(update_time, '%Y-%m-%d %H:%i:%s') update_time\n" +
				"FROM\n" +
				"    goods where shop_id=? and is_deleted=0 ";// + page.getOrderByLimitString();
		
		return jdbcTemplate.query(sql, new Object[]{shop_id}, new GoodsRowMapper());
	}
	
	*/
/**
	 * 根据商品id来查找商品
	 * @param goods_id
	 * @return
	 *//*

	public Goods findGoodsByID(int shop_id, int goods_id){
		String sql = "SELECT \n" +
				"    id,\n" +
				"    uuid,\n" +
				"    name,\n" +
				"    shop_id,\n" +
				"    status,\n" +
				"    original_price,\n" +
				"    sell_price,\n" +
				"    stock,\n" +
				"    sales_volume,\n" +
				"    inner_cid,\n" +
				"    global_cid,\n" +
				"    is_recommend,\n" +
				"    DATE_FORMAT(create_time, '%Y-%m-%d %H:%i:%s') create_time,\n" +
				"    DATE_FORMAT(update_time, '%Y-%m-%d %H:%i:%s') update_time\n" +
				"FROM\n" +
				"    goods\n" +
				"where shop_id=? and id=?  and is_deleted=0";
		
		Goods goods = null;
		try {
			goods = jdbcTemplate.queryForObject(sql, new Object[]{shop_id, goods_id}, new GoodsRowMapper());
		} catch (DataAccessException e) {
		}
		
		logger.info("findGoodsByID >>> sql={}, shop_id={}, goods_id={}, result={}", sql, shop_id, goods_id, goods.toString());
		
		return goods;
	}
	
	*/
/**
	 * 根据uuid来查找商品
	 * @param uuid
	 * @return
	 *//*

	public Goods findGoodsByUUID(int shop_id, String uuid){
		String sql = "SELECT \n" +
				"    id,\n" +
				"    uuid,\n" +
				"    name,\n" +
				"    shop_id,\n" +
				"    status,\n" +
				"    original_price,\n" +
				"    sell_price,\n" +
				"    stock,\n" +
				"    sales_volume,\n" +
				"    inner_cid,\n" +
				"    global_cid,\n" +
				"    is_recommend,\n" +
				"    DATE_FORMAT(create_time, '%Y-%m-%d %H:%i:%s') create_time,\n" +
				"    DATE_FORMAT(update_time, '%Y-%m-%d %H:%i:%s') update_time\n" +
				"FROM\n" +
				"    goods\n" +
				"where shop_id=? and uuid=?  and is_deleted=0";
		return jdbcTemplate.queryForObject(sql, new Object[]{shop_id, uuid}, new GoodsRowMapper());
	}
	
	*/
/**
	 * 根据名称查找商品
	 * @param name
	 * @return
	 *//*

	public Goods findGoodsByName(int shop_id, String name){
		String sql = "SELECT \n" +
				"    id,\n" +
				"    uuid,\n" +
				"    name,\n" +
				"    shop_id,\n" +
				"    status,\n" +
				"    original_price,\n" +
				"    sell_price,\n" +
				"    stock,\n" +
				"    sales_volume,\n" +
				"    inner_cid,\n" +
				"    global_cid,\n" +
				"    is_recommend,\n" +
				"    DATE_FORMAT(create_time, '%Y-%m-%d %H:%i:%s') create_time,\n" +
				"    DATE_FORMAT(update_time, '%Y-%m-%d %H:%i:%s') update_time \n" +
				"FROM\n" +
				"    goods\n" +
				"where shop_id=? and `name`=?  and is_deleted=0";
		return jdbcTemplate.queryForObject(sql, new Object[]{shop_id, name}, new GoodsRowMapper());
	}
	
	*/
/**
	 * 根据商品ID查找图片
	 * @param goods_id
	 * @return
	 *//*

	public List<Image> findImagesByGoodsID(int goods_id){
		String sql = "select image_id id, url, date_format(create_time, '%Y-%m-%d %H:%i:%s') create_time from image_goods where goods_id=?";
		return null;//jdbcTemplate.query(sql, new Object[]{goods_id}, new ImageRowMapper());
	}
	
	*/
/**
	 * 新增商品
	 * @param goods
	 * @return
	 *//*

	public Goods addGoods(Goods goods){
		
		String sql = "insert into goods(uuid, `name`, shop_id, status, original_price, sell_price, \n" +
				"stock, sales_volume, inner_cid, global_cid, is_recommend, create_time, update_time)\n" +
				"values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, date_format(?, '%Y-%m-%d %H:%i:%s'), date_format(?, '%Y-%m-%d %H:%i:%s'))";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				*/
/*ps.setString(1, goods.getUuid());
				ps.setString(2, goods.getName());
				ps.setInt(3, goods.getShop_id());
				ps.setInt(4, goods.getStatus());
				ps.setDouble(5, goods.getOriginal_price());
				ps.setDouble(6, goods.getSell_price());
				ps.setInt(7, goods.getStock());
				ps.setInt(8, goods.getSales_volume());
				ps.setInt(9, goods.getInner_cid());
				ps.setInt(10, goods.getGlobal_cid());
				ps.setInt(11, goods.getIs_recommend());
				ps.setString(12, goods.getCreate_time());
				ps.setString(13, goods.getUpdate_time());*//*

				return ps;
			}
		}, holder);
		int id = holder.getKey().intValue();
		
		goods.setId(String.valueOf(id));
		
		return goods;
	}
	
	*/
/**
	 * 批量新增图片
	 * @param images
	 * @param goods_id
	 * @return
	 *//*

	public void addImage(List<Image> images, int goods_id){
		
		if(images == null || images.size() == 0) return;
		
		String sql = "insert into image_goods(image_id, goods_id, url, create_time) values(?, ?, ?, now())";
		
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				*/
/*ps.setInt(1, images.get(i).getId());
				ps.setInt(2, goods_id);
				ps.setString(3, images.get(i).getUrl());*//*

			}
			
			@Override
			public int getBatchSize() {
				return images.size();
			}
		});
	}
	
	*/
/**
	 * 根据商品ID删除该商品所有图片信息
	 * @param goods_id
	 * @return
	 *//*

	public int deleteImageByGoodsID(int goods_id){
		String sql = "delete from image_goods where goods_id=?";
		int result = jdbcTemplate.update(sql, new int[]{Types.INTEGER});
		if(result <= 0) return 0;
		return 1;
	}
	
	*/
/**
	 * 删除单个商品照片
	 * @param image_id
	 * @param goods_id
	 * @return
	 *//*

	public int deleteSingleGoodsImage(int image_id, int goods_id){
		String sql = "delete from image_goods where image_id=? and goods_id=?";
		int result = jdbcTemplate.update(sql, new Object[]{image_id, goods_id});
		if(result <= 0) return 0;
		return 1;
	}
	
	*/
/**
	 * 新增商品快照
	 * @param goods
	 * @return
	 *//*

	*/
/*public Goods addGoodsSnapshot(Goods goods){
		String sql = "insert into goods_snapshot(uuid, `name`, shop_id, status, original_price, sell_price, \n" +
				"stock, sales_volume, inner_cid, global_cid, is_recommend, create_time, update_time)\n" +
				"values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, date_format(?, '%Y-%m-%d %H:%i:%s'), date_format(?, '%Y-%m-%d %H:%i:%s'))";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.updateShop(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, goods.getUuid());
				ps.setString(2, goods.getName());
				ps.setInt(3, goods.getShop_id());
				ps.setInt(4, goods.getStatus());
				ps.setDouble(5, goods.getOriginal_price());
				ps.setDouble(6, goods.getSell_price());
				ps.setInt(7, goods.getStock());
				ps.setInt(8, goods.getSales_volume());
				ps.setInt(9, goods.getInner_cid());
				ps.setInt(10, goods.getGlobal_cid());
				ps.setInt(11, goods.getIs_recommend());
				ps.setString(12, goods.getCreate_time());
				ps.setString(13, goods.getUpdate_time());
				return ps;
			}
		}, holder);
		goods.setId(holder.getKey().intValue());
		return goods;
	}*//*

	
	*/
/**
	 * 更新商品信息
	 * @param goods
	 * @return
	 *//*

	public int updateGoods(Goods goods){
		String sql = "update goods set `name`=?, status=?, original_price=?, sell_price=?,\n" +
				"stock=?, sales_volume=?, inner_cid=?, global_cid=?, is_recommend=?, update_time=now()\n" +
				"where shop_id=? and id=?";
		int result = jdbcTemplate.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, goods.getName());
				ps.setInt(2, goods.getStatus());
				ps.setDouble(3, goods.getOriginal_price());
				ps.setDouble(4, goods.getSell_price());
				ps.setInt(5, goods.getStock());
				ps.setInt(6, goods.getSales_volume());
				ps.setInt(7, goods.getInner_cid());
				ps.setInt(8, goods.getGlobal_cid());
				ps.setInt(9, goods.getIs_recommend());
				ps.setString(10, goods.getShopId());
				ps.setString(11, goods.getId());
			}
		});
		if(result <= 0){
			return 0;
		}
		return 1;
	}
	
	*/
/**
	 * 根据商品ID删除指定商品
	 * @param goods_id
	 * @return
	 *//*

	public int deleteGoods(int shop_id, int goods_id){
		
//		String sql = "deleteShop from goods where id=? and shop_id=?";
		String sql = "update goods set is_deleted=1 where id=? and shop_id=?";
		int result = jdbcTemplate.update(sql, new Object[]{goods_id, shop_id});
		
		if(result <= 0){
			return 0;
		}
		return 1;
		
	}
}*/
