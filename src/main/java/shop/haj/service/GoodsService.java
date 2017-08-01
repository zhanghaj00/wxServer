package shop.haj.service;

import shop.haj.entity.Goods;
import shop.haj.entity.Image;
import shop.haj.entity.Pagination;

import java.util.List;

/**
 * <p>Title: shop.ha.service</p>
 * <p/>
 * <p>
 * Description: 商品服务
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/4/17
 */
public interface GoodsService {
	
	/**
	 * 根据店铺ID查找全部商品
	 * @param shop_id
	 * @param page 分页信息
	 * @return
	 */
	List<Goods> findAll(int shop_id, Pagination page);
	
	/**
	 * 根据店铺ID和商品ID查找商品信息
	 * @param shop_id
	 * @param goods_id
	 * @return
	 */
	Goods findGoodsByID(int shop_id, int goods_id);
	
	/**
	 * 根据店铺ID和商品UUID查找商品信息
	 * @param shop_id
	 * @param uuid
	 * @return
	 */
	Goods findGoodsByUUID(int shop_id, String uuid);
	
	/**
	 * 根据店铺ID和名称查找商品信息
	 * @param shop_id
	 * @param name
	 * @return
	 */
	Goods findGoodsByName(int shop_id, String name);
	
	/**
	 * 新增商品信息
	 * @param goods
	 * @return
	 */
	Goods addGoods(Goods goods);
	
	/**
	 * 更新商品信息
	 * @param goods
	 * @return
	 */
	int updateGoods(Goods goods);
	
	/**
	 * 删除商品信息
	 * @param shop_id
	 * @param goods_id
	 * @return
	 */
	int deleteGoods(int shop_id, int goods_id);
	
	/**
	 * 新增商品图片
	 * @param image
	 * @Param goods_id
	 * @return
	 */
	int addGoodsImage(int shop_id, Image image, int goods_id);
	
	/**
	 * 删除某个商品的图片
	 * @param image_id
	 * @param goods_id
	 * @return
	 */
	int deleteGoodsImage(int shop_id, int image_id, int goods_id);
	
}
