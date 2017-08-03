package shop.haj.service;

import shop.haj.entity.FavoriteGoods;
import shop.haj.entity.Pagination;

import java.util.List;

/**
 * <p>Title: shop.ha.service</p>
 * <p/>
 * <p>
 * Description: 收藏商品服务
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/24/17
 */
public interface FavoriteGoodsService {
	
	/**
	 * 查找卖家全部收藏的商品
	 * @param customer_id
	 * @return
	 */
	List<FavoriteGoods> findFavoriteGoodsByCustomer(String customer_id, Pagination page);
	
	/**
	 * 查找卖家在该店铺收藏的商品
	 * @param customer_id
	 * @param shop_id
	 * @return
	 */
	List<FavoriteGoods> findFavoriteGoodsByCustomerShop(String customer_id, String shop_id, Pagination page);
	
	/**
	 * 商品加入收藏
	 * @param favoriteGoods
	 * @return
	 */
	FavoriteGoods addFavoriteGoods(FavoriteGoods favoriteGoods);
	
	/**
	 * 移除收藏
	 * @param customer_id
	 * @param goods_id
	 * @return
	 */
	int removeFavoriteGoods(String customer_id, String goods_id);
}
