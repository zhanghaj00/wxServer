package shop.haj.manage;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

/**
 * <p>Title: shop.ha.manage</p>
 * <p/>
 * <p>
 * Description: Spring 缓存操作类
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：4/14/17
 */
@Component
public class CacheManage {
	
	private Logger logger = LogManager.getLogger(CacheManage.class);
	
	//商品分页缓存的KEY存放，当新增、修改、删除商品信息时，需要清空分页缓存信息
	private static final ConcurrentMap<String, List<String>> goodsPageCacheKeyMap = Maps.newConcurrentMap();
	
	//订单缓存
	private static final ConcurrentMap<String, List<String>> orderPageCacheKeyMap = Maps.newConcurrentMap();
	
	//独立店铺购物车缓存
	private static final ConcurrentMap<String, List<String>> shopCartPageCacheKeyMap = Maps.newConcurrentMap();
	
	private static final String GOODS_LIST_KEY_REFIX = "GOODSLIST_";
	
	private static final String ORDER_LIST_KEY_PREFIX = "ORDERLIST_";
	
	private static final String SHOPCART_LIST_KEY_PREFIX = "SHOPCARTLIST_";
	
	/**
	 * 存储缓存过的商家商品列表
	 * @param shop_id
	 * @param value
	 */
	public void addGoodsPageKeysMapData(int shop_id, String value){
		
		String key = GOODS_LIST_KEY_REFIX + shop_id;
		
		if(goodsPageCacheKeyMap.get(key) == null){
			List<String> pagesKeys = Lists.newArrayList();
			goodsPageCacheKeyMap.put(key, pagesKeys);
		}
		goodsPageCacheKeyMap.get(key).add(value);
		
		logger.debug("goodsPageCacheKeyMap is {}", goodsPageCacheKeyMap);
	}
	
	/**
	 * 存储缓存过的买家订单列表
	 * @param customer_id
	 * @param value
	 */
	public void addOrderPageKeysMapData(int customer_id, String value){
		
		String key = ORDER_LIST_KEY_PREFIX + customer_id;
		
		if(orderPageCacheKeyMap.get(key) == null){
			List<String> pagesKeys = Lists.newArrayList();
			orderPageCacheKeyMap.put(key, pagesKeys);
		}
		orderPageCacheKeyMap.get(key).add(value);
		
		logger.debug("orderPageCacheKeyMap is {}", orderPageCacheKeyMap);
	}
	
	/**
	 * 存储缓存过的商家商品列表
	 * @param customer_id
	 * @param shop_id
	 * @param value
	 */
	public void addShopCartPageKeysMapData(int customer_id, int shop_id, String value){
		
		String key = SHOPCART_LIST_KEY_PREFIX + customer_id + shop_id;
		
		if(shopCartPageCacheKeyMap.get(key) == null){
			List<String> pagesKeys = Lists.newArrayList();
			shopCartPageCacheKeyMap.put(key, pagesKeys);
		}
		shopCartPageCacheKeyMap.get(key).add(value);
		
		logger.debug("goodsPageCacheKeyMap is {}", shopCartPageCacheKeyMap);
	}
	
	/**
	 * 获取店铺的缓存
	 * @param shop_id
	 */
	public List<String> getShopPageCacheKeys(String shop_id){
		
		String key = GOODS_LIST_KEY_REFIX + shop_id;
		
		return  goodsPageCacheKeyMap.get(key);
	}
	
	public List<String> getOrderPageCacheKeys(int customer_id){
		
		String key = ORDER_LIST_KEY_PREFIX + customer_id;
		
		return  orderPageCacheKeyMap.get(key);
	}
	
	public List<String> getShopCartPageCacheKeys(int customer_id, int shop_id){
		
		String key = SHOPCART_LIST_KEY_PREFIX + customer_id + shop_id;
		
		return shopCartPageCacheKeyMap.get(key);
	}
	
	@CacheEvict(value = {"goodsPages"}, key = "{#shop_id, #pageInfo}")
	public void clearGoodsPageCache(String shop_id,String pageInfo){
		
		logger.info("clearGoodsPageCache >>> Cache Evict : value=goodsPages, key={{}, {}}, pageMap={}", shop_id, pageInfo, goodsPageCacheKeyMap);
	}
	
	@CacheEvict(value = "goods", key = "#goods_id")
	public void clearGoodsCache(String goods_id){
		logger.info("clearGoodsCache >>> Cache Evict : value=goods, key={{}}", goods_id);
	}
	
	@CacheEvict(value = {"goodsPages"}, allEntries = true)
	public void clearAllGoodsPagesCache(){
		
		goodsPageCacheKeyMap.clear();
		
		logger.info("clearAllGoodsPagesCache >>> clear all Goods Cache, pageMap={}", goodsPageCacheKeyMap);
	}
	
	@CacheEvict(value = {"orderPages"}, key = "{#customer_id, #pageInfo}")
	public void clearOrderPageCache(int customer_id, String pageInfo){
		
		logger.info("clearOrderPageCache >>> Cache Evict : value=goodsPages, key={{}, {}}, pageMap={}", customer_id, pageInfo, orderPageCacheKeyMap);
	}
	
	@CacheEvict(value = {"orderPages"}, allEntries = true)
	public void clearAllOrderPagesCache(){
		
		orderPageCacheKeyMap.clear();
		
		logger.info("clearAllOrderPagesCache >>> clear all Order Cache, pageMap={}", orderPageCacheKeyMap);
	}
	
	@CacheEvict(value = "{shopCartPages}", key = "{#customer_id, #shop_id, #pageInfo}")
	public void clearShopCartPageCache(int customer_id, int shop_id, String pageInfo){
		logger.info("clearShopCartPageCache >>> Cache Evict : value=shopCartPages, key={{}, {}, {}}, pageMap={}",
				customer_id, shop_id, pageInfo, shopCartPageCacheKeyMap);
	}
	
	@CacheEvict(value = {"shopCartPages"}, allEntries = true)
	public void clearAllShopCartPagesCache(){
		
		shopCartPageCacheKeyMap.clear();
		
		logger.info("clearAllShopCartPagesCache >>> clear all shopCart Cache, pageMap={}", shopCartPageCacheKeyMap);
	}
	
	@CacheEvict(value = "shopCart", key = "#cart_id")
	public void clearShopCartCache(int cart_id){
		logger.info("clearShopCartCache >>> Cache Evict : value=shopCart, key={{}}", cart_id);
	}
}
