package shop.haj.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.haj.entity.Cart;
import shop.haj.entity.Pagination;
import shop.haj.manage.CacheManage;
import shop.haj.mongo_repository.MongoCartRepository;
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
	private MongoCartRepository mongoCartRepository;
	
	@Autowired
	private CacheManage cacheManage;
	
	/**
	 * 查找用户在某店铺的购物车信息(独立店家)
	 * @Cacheable(value = "shopCartPages", key = "{#customer_id, #shop_id, #page.getOrderByLimitString()}")
	 * @return
	 */
	@Override
	public List<Cart> findShopCarts(Cart cart, Pagination page) {
		
		logger.debug("findShopCarts >>> customer_id={}, shop_id={}",cart.getCustomerId()
						, cart.getShopId());

		Example<Cart> example = Example.of(cart);

		return mongoCartRepository.findAll(example,page.getRequest()).getContent();

		//List<Cart> carts = mongoCartRepository.findShopCarts(customer_id, shop_id, page);
		
		//logger.info("findCarts >>> result size = {}", carts == null ? 0 : carts.size());
		
		//添加分页缓存信息
		//cacheManage.addShopCartPageKeysMapData(customer_id, shop_id, page.getOrderByLimitString());
		
	}
	
	/**
	 * 根据商品ID及规格查找购物车信息
	 *
	 * @param goods_id
	 * @param sku
	 * @return
	 */
	@Override
	public Cart findShopCart(String customer_id, String shop_id,
							 String goods_id, String sku) {
		logger.debug("findShopCart >>> customer_id={}, shop_id={}, goods_id={}, sku={}",
				customer_id, shop_id, goods_id, sku);
		/*Cart cart = mongoCartRepository.findShopCart(customer_id, shop_id, goods_id, sku);
		logger.info("findShopCart >>> result = {}", cart);
		
		return cart;*/
		return null;
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

		mongoCartRepository.insert(cart);
		
		/*if(cart.getCart_id() > 0){//当cart_id大于0，说明新增成功
			logger.info("addCart >>> add cart success:{}", cart);
			return 1;
		}*/
		
		this.clearShopCartCache(cart.getId());
		this.clearShopCartPageCache(cart.getCustomerId(), cart.getShopId());
		
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
	public int updateCartNum(String customer_id, String shop_id,
							 String cart_id, int num) {
		
		logger.debug("updateCartNum >>> cart_id={}, num={}", cart_id, num);


		Cart cart = mongoCartRepository.findOne(cart_id);
		cart.setGoodsNum(num);
		Cart cart1 =  mongoCartRepository.save(cart);
		
		if(cart1 != null){
			logger.info("updateCartNum >>> update success: cart_id={}, num={}",
					cart_id, num);
		}
		
		this.clearShopCartCache(cart_id);
		this.clearShopCartPageCache(customer_id, shop_id);
		
		return 1;
	}
	
	/**
	 * 在购物车中删除某件商品
	 *
	 * @param cart_id
	 * @return
	 */
	@Override
	@CacheEvict(value = "shopCart", key = "{#customer_id, #shop_id}")
	public int removeGoodsFromCart(String customer_id, String shop_id, String cart_id) {
		
		logger.debug("removeGoodsFromCart >>> cart_id={}", cart_id);
		
		 mongoCartRepository.delete(cart_id);
		
		/*if(result > 0){
			logger.info("removeGoodsFromCart >>> success: cart_id={}", cart_id);
		}
		
		this.clearShopCartCache(cart_id);
		this.clearShopCartPageCache(customer_id, shop_id);
		
		return result;*/
		return  0;
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
	public int clearShopCart(String customer_id, String shop_id) {
		
		logger.debug("clearShopCart >>> customer_id={}, shop_id={}",
				customer_id, shop_id);

		List<Cart> carts = mongoCartRepository.findByCustomerIdAndShopId(customer_id,shop_id);

		for (Cart c:carts){
			mongoCartRepository.delete(c.getId());
		}
		return carts.size();
	}
	
	/**
	 * 清除商品集合缓存
	 * @param customer_id
	 * @param shop_id
	 */
	private void clearShopCartPageCache(String customer_id, String shop_id){
		
		List<String> keys = null;//cacheManage.getShopCartPageCacheKeys(customer_id, shop_id);
		
		if(keys == null || keys.size() == 0) {
			cacheManage.clearAllShopCartPagesCache();
		}
		
		if(keys != null && keys.size() > 0){
			for (String pageKey : keys) {
				//cacheManage.clearShopCartPageCache(customer_id, shop_id, pageKey);
			}
			keys.clear();
		}
	}
	
	/**
	 * 清除商品缓存
	 * @param cart_id
	 */
	private void clearShopCartCache(String cart_id){
		
		//cacheManage.clearShopCartCache(cart_id);
	}
}
