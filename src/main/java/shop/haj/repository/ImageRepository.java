/*
package shop.ha.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import shop.ha.entity.Image;
import shop.ha.entity.Pagination;
import shop.ha.repository.RowMapper.ImageRowMapper;

import java.sql.*;
import java.util.List;

*/
/**
 * <p>Title: shop.ha.repository</p>
 * <p/>
 * <p>
 * Description: Image数据库操作类
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/15/17
 *//*

@Repository
public class ImageRepository {
	
	private Logger logger = LogManager.getLogger(ImageRepository.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	*/
/**
	 * 新增图片
	 * @param image
	 * @return
	 *//*

	public Image addImage(final Image image){
		String sql = "insert into image(url, create_time) values(?, now())";
		KeyHolder holder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, image.getUrl());
				return ps;
			}
		}, holder);
		
		image.setId(holder.getKey().intValue());
		return image;
	}
	
	*/
/**
	 * 根据图片ID返回图片信息
	 * @param image_id
	 * @return
	 *//*

	public Image findImageByID(int image_id){
		String sql = "select \n" +
				"\tid, \n" +
				"    url, \n" +
				"    date_format(create_time, '%Y-%m-%d %H:%i:%s') create_time  \n" +
				"from image\n" +
				"where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{image_id}, new ImageRowMapper());
	}
	
	*/
/**
	 * 删除图片信息
	 * @param image_id
	 * @return
	 *//*

	public int deleteImage(int image_id){
		String sql = "delete from image where id=?";
		int result = jdbcTemplate.update(sql, image_id);
		
		if(result <= 0){
			logger.error("deleteImage >>> sql={}, image_id={}, result={}",
					sql, image_id, result);
			return 0;
		}
		logger.info("deleteImage >>> sql={}, image_id={}, result={}",
				sql, image_id, result);
		return 1;
	}
	
	*/
/**
	 * 根据商品ID返回商品图片信息
	 * @param goods_id
	 * @return
	 *//*

	public List<Image> findGoodsImagesByGoodsID(int goods_id, Pagination page){
		String sql = "select \n" +
				"\timage_id as id, \n" +
				"    url,\n" +
				"    date_format(create_time, '%Y-%m-%d %H:%i:%s') create_time\n" +
				"from \n" +
				"\timage_goods \n" +
				"where goods_id=? " + page.getOrderByLimitString();
		return jdbcTemplate.query(sql, new Object[]{goods_id}, new ImageRowMapper());
	}
	
	*/
/**
	 * 新增商品的图片
	 * @param image
	 * @para
	 *
	 * @return
	 *//*

	public Image addGoodsImage(Image image, int goods_id){
		
		String sql = "insert into image_goods(image_id, goods_id, url, create_time)\n" +
				"values(?, ?, ?, now())";
		
		int result = jdbcTemplate.update(sql, new Object[]{image.getId(), goods_id, image.getUrl()});
		
		if(result <= 0){
			logger.error("addGoodsImage failure >>> sql={}, goods_id={}, image={}", sql, goods_id, image);
			return null;
		}
		logger.info("addGoodsImage success>>> sql={}, goods_id={}, image={}", sql, goods_id, image);
		
		return image;
	}
	
	*/
/**
	 * 根据图片ID删除一张图片
	 * @param image_id
	 * @return
	 *//*

	public int deleteSingleGoodsImageByID(int image_id, int goods_id){
		String sql = "delete from image_goods where image_id=? and goods_id=?";
		int result = jdbcTemplate.update(sql, new Object[]{image_id, goods_id});
		
		if(result <= 0) return 0;
		return 1;
	}
	
	*/
/**
	 * 删除商品全部图片
	 * @param goods_id
	 * @return
	 *//*

	public int deleteAllGoodsImage(int goods_id){
		
		String sql = "delete from image_goods where goods_id=?";
		
		int result = jdbcTemplate.update(sql, new Object[]{goods_id});
		
		if(result <= 0){
			logger.error("deleteAllGoodsImage >>> sql={}, goods_id={}, result={}",
					sql, goods_id, result);
			return 0;
		}
		
		logger.info("deleteAllGoodsImage >>> sql={}, image_id={}, goods_id={}, result={}",
				sql, goods_id, result);
		
		return 1;
	}
	
	*/
/**
	 * 查找店铺的图片
	 * @param shop_id
	 * @return
	 *//*

	public List<Image> findShopImage(int shop_id){
		String sql = "select \n" +
				"    image_id as id, \n" +
				"    shop_id, \n" +
				"    url, \n" +
				"    date_format(create_time, '%Y-%m-%d %H:%i:%s') create_time \n" +
				"from \n" +
				"\timage_shop \n" +
				"where shop_id=?";
		
		List<Image> images = jdbcTemplate.query(sql, new Object[]{shop_id}, new ImageRowMapper());
		
		logger.info("findShopImage >>> sql={}, shop_id={}, result_size={}", sql, shop_id, images.size());
		
		return images;
	}
	
	*/
/**
	 * 新增店铺的图片
	 * @param images
	 * @param shop_id
	 * @return
	 *//*

	public List<Image> addShopImage(List<Image> images, int shop_id){
		
		String sql = "insert into image_shop(image_id, shop_id, url, create_time)\n" +
				"values(?, ?, ?, now())";
		
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Image image = images.get(i);
				
				ps.setInt(1, image.getId());
				ps.setInt(2, shop_id);
				ps.setString(3, image.getUrl());
			}
			
			@Override
			public int getBatchSize() {
				return images.size();
			}
		});
		
		
		return images;
	}
	
	*/
/**
	 * 删除单张图片
	 * @param image_id
	 * @return
	 *//*

	public int deleteSingleShopImage(int image_id, int shop_id){
		
		String sql = "delete from image_shop where image_id=? and shop_id=?";
		
		int result = jdbcTemplate.update(sql, new Object[]{image_id, shop_id});
		
		if(result <= 0){
			logger.error("deleteSingleShopImage >>> sql={}, image_id={}, shop_id={}, result={}",
					sql, image_id, shop_id, result);
			return 0;
		}
		
		logger.info("deleteSingleShopImage >>> sql={}, image_id={}, shop_id={}, result={}",
				sql, image_id, shop_id, result);
		
		return 1;
	}
	
	*/
/**
	 * 删除店铺全部图片
	 * @param shop_id
	 * @return
	 *//*

	public int deleteAllShopImage(int shop_id){
		
		String sql = "delete from image_shop where shop_id=?";
	
		int result = jdbcTemplate.update(sql, new Object[]{shop_id});
		
		if(result <= 0){
			logger.error("deleteAllShopImage >>> sql={}, shop_id={}, result={}",
					sql, shop_id, result);
			return 0;
		}
		
		logger.info("deleteAllShopImage >>> sql={}, image_id={}, shop_id={}, result={}",
				sql, shop_id, result);
		
		return 1;
	}
}
*/
