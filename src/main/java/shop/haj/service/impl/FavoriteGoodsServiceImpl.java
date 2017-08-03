package shop.haj.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.haj.entity.FavoriteGoods;
import shop.haj.entity.Pagination;
import shop.haj.mongo_repository.MongoFavoriteGoodsRepository;
import shop.haj.repository.FavoriteGoodsRepository;
import shop.haj.service.FavoriteGoodsService;

import java.util.List;

/**
 * <p>Title: shop.ha.service.impl</p>
 * <p/>
 * <p>
 * 收藏商品服务具体实现类,包含功能如下：
 *  1. 查找卖家全部收藏的商品
 *  2. 查找卖家在该店铺收藏的商品
 *  3. 商品加入收藏
 *  4. 商品移除收藏
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/24/17
 */
@Service
public class FavoriteGoodsServiceImpl implements FavoriteGoodsService{
	
	private Logger logger = LogManager.getLogger(FavoriteGoodsServiceImpl.class);
	
	@Autowired
	private MongoFavoriteGoodsRepository mongoFavoriteGoodsRepository;
	
	/**
	 * 查找卖家全部收藏的商品
	 *
	 * @param customer_id
	 * @return
	 */
	@Override
	public List<FavoriteGoods> findFavoriteGoodsByCustomer(String customer_id, Pagination page) {
		logger.info("findFavoriteGoodsByCustomer >>> customer_id={}", customer_id);
		return mongoFavoriteGoodsRepository.findByCustomerId(customer_id, page.getRequest()).getContent();
	}
	
	/**
	 * 查找卖家在该店铺收藏的商品
	 *
	 * @param customer_id
	 * @param shop_id
	 * @return
	 */
	@Override
	public List<FavoriteGoods> findFavoriteGoodsByCustomerShop(String customer_id, String shop_id, Pagination page) {
		logger.info("findFavoriteGoodsByCustomerShop >>> customer_id={}," +
				" shop_id={}", customer_id, shop_id);
		return mongoFavoriteGoodsRepository.findByCustomerIdAndShopId(customer_id, shop_id, page.getRequest()).getContent();
	}
	
	/**
	 * 商品加入收藏
	 *
	 * @param favoriteGoods
	 * @return
	 */
	@Override
	public FavoriteGoods addFavoriteGoods(FavoriteGoods favoriteGoods) {
		logger.info("addFavoriteGoods >>> favoriteGoods={}", favoriteGoods);
		return mongoFavoriteGoodsRepository.insert(favoriteGoods);
	}
	
	/**
	 * 移除收藏
	 *
	 * @param customer_id
	 * @param goods_id
	 * @return
	 */
	@Override
	public int removeFavoriteGoods(String customer_id, String goods_id) {
		logger.info("removeFavoriteGoods >>> customer_id={}, goods_id={}",
				customer_id, goods_id);
		mongoFavoriteGoodsRepository.deleteByCustomerIdAndGoodsId(customer_id, goods_id);
		return 1;

	}
}
