package shop.haj.service;

import shop.haj.entity.Cart;
import shop.haj.entity.Pagination;

import java.util.List;

/**
 * <p>Title: shop.ha.service</p>
 * <p/>
 * <p>
 * 独立门店使用(平台购物车除外)
 *
 * 购物车Service，包含：
 *      1. 查找用户在某店铺的购物车信息(独立店家)
 *      2. 添加商品到购物车
 *      3. 更新购物车数量
 *      4. 在购物车中删除某件商品
 *      5. 删除用户在某店的全部购物车信息
 *
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/21/17
 */
public interface ShopCartService {
	
	/**
	 * 查找用户在某店铺的购物车信息(独立店家)
	 * @return
	 */
	List<Cart> findShopCarts(Cart cart, Pagination page);
	
	/**
	 * 根据商品ID及规格查找购物车信息
	 * @param goods_id
	 * @param sku
	 * @return
	 */
	Cart findShopCart(String customer_id, String shop_id,
					  String goods_id, String sku);
	
	/**
	 * 添加商品到购物车
	 * @param cart
	 * @return
	 */
	int addCart(Cart cart);
	
	/**
	 * 更新购物车数量
	 * @param cart_id
	 * @param num
	 * @return
	 */
	int updateCartNum(String customer_id, String shop_id, String cart_id, int num);
	
	/**
	 * 在购物车中删除某件商品
	 * @param cart_id
	 * @return
	 */
	int removeGoodsFromCart(String customer_id, String shop_id, String cart_id);
	
	/**
	 * 清空用户在某家店的购物车信息
	 * @param customer_id
	 * @param shop_id
	 * @return
	 */
	int clearShopCart(String customer_id, String shop_id);
}
