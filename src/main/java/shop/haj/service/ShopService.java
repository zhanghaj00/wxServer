package shop.haj.service;

import shop.haj.entity.Image;
import shop.haj.entity.Pagination;
import shop.haj.entity.Shop;

import java.util.List;

/**
 * <p>Title: shop.ha.service</p>
 * <p/>
 * <p>
 * Description: Shop Service
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/1/17
 */
public interface ShopService {
	
	/**
	 * 查找所有店铺
	 * @param page
	 * @return
	 */
	List<Shop> findAll(Pagination page);
	
	/**
	 * 根据店铺ID查找店铺
	 * @param id
	 * @return
	 */
	Shop findById(String id);
	
	/**
	 * 开店
	 * @param shop
	 * @param seller_id
	 * @return
	 */
	Shop addShop(Shop shop, String seller_id);
	
	/**
	 * 更新店铺信息
	 * @param shop
	 * @return
	 */
	Shop updateShop(Shop shop);
	
	/**
	 * 新增店铺图片
	 * @param images
	 * @param shop_id
	 * @return
	 */
	List<Image> addShopImage(List<Image> images, String shop_id);
	
	/**
	 * 删除店铺图片
	 * @param image_id
	 * @param shop_id
	 * @return
	 */
	int deleteShopImage(String image_id, String shop_id);

	List<Shop> findBySellerId(String seller_id);
}
