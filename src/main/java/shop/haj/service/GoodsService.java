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
	List<Goods> findAll(String shop_id, Pagination page);
	
	/**
	 * 根据店铺ID和商品ID查找商品信息
	 * @param shop_id
	 * @param goods_id
	 * @return
	 */
	Goods findGoodsByID(String shop_id, String goods_id);
	
	/**
	 * 根据店铺ID和商品UUID查找商品信息
	 * @param shop_id
	 * @param uuid
	 * @return
	 */
	Goods findGoodsByUUID(String shop_id, String uuid);
	
	/**
	 * 根据店铺ID和名称查找商品信息
	 * @param shop_id
	 * @param name
	 * @return
	 */
	Goods findGoodsByName(String shop_id, String name);
	
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
	Goods updateGoods(Goods goods);
	
	/**
	 * 删除商品信息
	 * @param shop_id
	 * @param goods_id
	 * @return
	 */
	int deleteGoods(String shop_id, String goods_id);
	
	/**
	 * 新增商品图片
	 * @param image
	 * @Param goods_id
	 * @return
	 */
	int addGoodsImage(String shop_id, Image image, String goods_id);
	
	/**
	 * 删除某个商品的图片
	 * @param image_id
	 * @param goods_id
	 * @return
	 */
	int deleteGoodsImage(String shop_id, String image_id, String goods_id);
	
}
