package shop.haj.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.haj.entity.Cart;
import shop.haj.entity.Pagination;
import shop.haj.manage.CacheManage;
import shop.haj.repository.CartRepository;
import shop.haj.service.ShopCartService;

import java.util.List;

/**
 * <p>Title: shop.ha.service.impl</p>
 * <p/>
 * <p>
 * Description: 购物车服务接口实现
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/21/17
 */
@Service
@Transactional
public class ShopCartServiceImpl implements ShopCartService {
	
	private Logger logger = LogManager.getLogger(ShopCartServiceImpl.class);
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CacheManage cacheManage;
	
	/**
	 * 查找用户在某店铺的购物车信息(独立店家)
	 * @Cacheable(value = "shopCartPages", key = "{#customer_id, #shop_id, #page.getOrderByLimitString()}")
	 * @return
	 */
	@Override
	public List<Cart> findShopCarts(int customer_id, int shop_id, Pagination page) {
		
		logger.debug("findShopCarts >>> customer_id={}, shop_id={}",
						customer_id, shop_id);
		
		List<Cart> carts = cartRepository.findShopCarts(customer_id, shop_id, page);
		
		logger.info("findCarts >>> result size = {}", carts == null ? 0 : carts.size());
		
		//添加分页缓存信息
		//cacheManage.addShopCartPageKeysMapData(customer_id, shop_id, page.getOrderByLimitString());
		
		return carts;
	}
	
	/**
	 * 根据商品ID及规格查找购物车信息
	 *
	 * @param goods_id
	 * @param sku
	 * @return
	 */
	@Override
	public Cart findShopCart(int customer_id, int shop_id,
	                         int goods_id, String sku) {
		logger.debug("findShopCart >>> customer_id={}, shop_id={}, goods_id={}, sku={}",
				customer_id, shop_id, goods_id, sku);
		Cart cart = cartRepository.findShopCart(customer_id, shop_id, goods_id, sku);
		logger.info("findShopCart >>> result = {}", cart);
		
		return cart;
	}
	
	/**
	 * 添加商品到购物车
	 *
	 * @param cart
	 * @return
	 */
	@Override
	public int addCart(Cart cart) {
		
		logger.debug("addCart >>> {}", cart);
		
		cartRepository.addCart(cart);
		
		if(cart.getCart_id() > 0){//当cart_id大于0，说明新增成功
			logger.info("addCart >>> add cart success:{}", cart);
			return 1;
		}
		
		this.clearShopCartCache(cart.getCart_id());
		this.clearShopCartPageCache(cart.getCustomer_id(), cart.getShop_id());
		
		return 0;
	}
	
	/**
	 * 更新购物车数量
	 *
	 * @param cart_id
	 * @param num
	 * @return
	 */
	@Override
	public int updateCartNum(int customer_id, int shop_id,
	                         int cart_id, int num) {
		
		logger.debug("updateCartNum >>> cart_id={}, num={}", cart_id, num);
		
		int result = cartRepository.updateNum(cart_id, num);
		
		if(result > 0){
			logger.info("updateCartNum >>> update success: cart_id={}, num={}",
					cart_id, num);
		}
		
		this.clearShopCartCache(cart_id);
		this.clearShopCartPageCache(customer_id, shop_id);
		
		return result;
	}
	
	/**
	 * 在购物车中删除某件商品
	 *
	 * @param cart_id
	 * @return
	 */
	@Override
	@CacheEvict(value = "shopCart", key = "{#customer_id, #shop_id}")
	public int removeGoodsFromCart(int customer_id, int shop_id, int cart_id) {
		
		logger.debug("removeGoodsFromCart >>> cart_id={}", cart_id);
		
		int result = cartRepository.removeGoodsFromCart(cart_id);
		
		if(result > 0){
			logger.info("removeGoodsFromCart >>> success: cart_id={}", cart_id);
		}
		
		this.clearShopCartCache(cart_id);
		this.clearShopCartPageCache(customer_id, shop_id);
		
		return result;
	}
	
	/**
	 * 清空用户在某家店的购物车信息
	 *
	 * @param customer_id
	 * @param shop_id
	 * @return
	 */
	@Override
	@CacheEvict(value = "shopCart", key = "{#customer_id, #shop_id}")
	public int clearShopCart(int customer_id, int shop_id) {
		
		logger.debug("clearShopCart >>> customer_id={}, shop_id={}",
				customer_id, shop_id);
		
		int result = cartRepository.clearShopCart(customer_id, shop_id);
		
		if(result > 0){
			logger.info("clearCart >>> update success: customer_id={}", customer_id);
		}
		
		this.clearShopCartPageCache(customer_id, shop_id);
		
		return result;
	}
	
	/**
	 * 清除商品集合缓存
	 * @param customer_id
	 * @param shop_id
	 */
	private void clearShopCartPageCache(int customer_id, int shop_id){
		
		List<String> keys = cacheManage.getShopCartPageCacheKeys(customer_id, shop_id);
		
		if(keys == null || keys.size() == 0) {
			cacheManage.clearAllShopCartPagesCache();
		}
		
		if(keys != null && keys.size() > 0){
			for (String pageKey : keys) {
				cacheManage.clearShopCartPageCache(customer_id, shop_id, pageKey);
			}
			keys.clear();
		}
	}
	
	/**
	 * 清除商品缓存
	 * @param cart_id
	 */
	private void clearShopCartCache(int cart_id){
		
		cacheManage.clearShopCartCache(cart_id);
	}
}
