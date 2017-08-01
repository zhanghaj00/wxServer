package shop.haj.service.impl;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.haj.entity.Goods;
import shop.haj.entity.GoodsSkuInfo;
import shop.haj.entity.Image;
import shop.haj.entity.Pagination;
import shop.haj.manage.CacheManage;
import shop.haj.mongo_repository.MongoImageRepository;
import shop.haj.repository.GoodsDetailRepository;
import shop.haj.repository.GoodsRepository;
import shop.haj.repository.GoodsSkuRepository;
import shop.haj.service.GoodsService;
import shop.haj.utils.DefaultPagination;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

/**
 * <p>Title: shop.ha.service.impl</p>
 * <p/>
 * <p>
 * Description: GoodsServiceImpl
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/4/17
 */
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService{
	
	private Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);
	
	@Autowired
	private GoodsRepository goodsRepository;
	
	@Autowired
	private GoodsSkuRepository goodsSkuRepository;
	
	@Autowired
	private MongoImageRepository mongoImageRepository;
	
	@Autowired
	private GoodsDetailRepository goodsDetailRepository;
	
	@Autowired
	private CacheManage cacheManage;
	
	@Override
	@Transactional
//	@Cacheable(value = "goodsPages", key = "{#shop_id, #page.getOrderByLimitString()}")
	public List<Goods> findAll(int shop_id, Pagination page) {
		
		List<Goods> goodsList = goodsRepository.findAll(shop_id, page);

		if(goodsList.size() == 0) return goodsList;

		for (Goods goods : goodsList) {
			//goods.setImages(imageRepository.findGoodsImagesByGoodsID(goods.getId(), DefaultPagination.getImageGoods()));
			goods.setGoodsDetails(goodsDetailRepository.findGoodsDetailByGoodsID(goods.getId(), DefaultPagination.getGoodsDetail()));
			goods.setGoodsSkuInfo(findSkuInfo(goods.getId()));
		}

		//cacheManage.addGoodsPageKeysMapData(shop_id, page.getOrderByLimitString());
		
		return goodsList;
	}
	
	@Override
//	@Cacheable(value = "goods", key = "#goods_id")
	public Goods findGoodsByID(int shop_id, int goods_id) {
		Goods goods = goodsRepository.findGoodsByID(shop_id, goods_id);
		
		if(goods == null) return null;
		
		//goods.setImages(imageRepository.findGoodsImagesByGoodsID(goods.getId(), DefaultPagination.getImageGoods()));
		goods.setGoodsDetails(goodsDetailRepository.findGoodsDetailByGoodsID(goods.getId(), DefaultPagination.getGoodsDetail()));
		goods.setGoodsSkuInfo(findSkuInfo(goods_id));
		
		return goods;
	}
	
	private GoodsSkuInfo findSkuInfo(int goods_id){
		GoodsSkuInfo goodsSkuInfo = goodsSkuRepository.findInfo(goods_id);
		
		if(goodsSkuInfo != null){
			goodsSkuInfo.setGoodsSkuDetails(goodsSkuRepository.findDetail(goods_id));
			
		}
		
		return goodsSkuInfo;
	}
	
	@Override
	public Goods findGoodsByUUID(int shop_id, String uuid) {
		
		Goods goods = goodsRepository.findGoodsByUUID(shop_id, uuid);
		//goods.setImages(imageRepository.findGoodsImagesByGoodsID(goods.getId(), DefaultPagination.getImageGoods()));
		goods.setGoodsDetails(goodsDetailRepository.findGoodsDetailByGoodsID(goods.getId(), DefaultPagination.getGoodsDetail()));
		goods.setGoodsSkuInfo(findSkuInfo(goods.getId()));
		
		return goods;
	}
	
	@Override
	public Goods findGoodsByName(int shop_id, String name) {
		Goods goods = goodsRepository.findGoodsByName(shop_id, name);
		//goods.setImages(imageRepository.findGoodsImagesByGoodsID(goods.getId(), DefaultPagination.getImageGoods()));
		goods.setGoodsDetails(goodsDetailRepository.findGoodsDetailByGoodsID(goods.getId(), DefaultPagination.getGoodsDetail()));
		goods.setGoodsSkuInfo(findSkuInfo(goods.getId()));
		
		return goods;
	}
	
	@Override
	@Transactional
	public Goods addGoods(Goods goods) {
		String uuid = UUID.randomUUID().toString();
		goods.setUuid(uuid);
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		goods.setCreate_time(format.format(System.currentTimeMillis()));
		goods.setUpdate_time(format.format(System.currentTimeMillis()));
		
		//首先添加商品基本信息，如果成功，goods的id会被设置
		goodsRepository.addGoods(goods);
		
		//当商品基本信息新增成功时，加入商品规格信息
		if(goods.getId() > 0 && goods.getGoodsSkuInfo() != null){
			goodsSkuRepository.addInfo(goods.getGoodsSkuInfo(), goods.getId());
			
			if(goods.getGoodsSkuInfo().getGoodsSkuDetails() != null &&
					goods.getGoodsSkuInfo().getGoodsSkuDetails() != null){
				goodsSkuRepository.addDetail(goods.getGoodsSkuInfo().getGoodsSkuDetails(), goods.getId());
			}
		}
		
		//在新增商品成功后，保存图片信息
		goodsRepository.addImage(goods.getImages(), goods.getId());
		
		//重置分页缓存
		clearShopPageCache(goods.getShop_id());
		
		return goods;
	}
	
	@Override
	@Transactional
	public int updateGoods(Goods goods) {
		
		GoodsSkuInfo skuInfo = goods.getGoodsSkuInfo();
		if(skuInfo == null){//不包含商品规格信息，则需要删除
			
			goodsSkuRepository.deleteDetail(goods.getId());
			goodsSkuRepository.deleteInfo(goods.getId());
		}else {//包含商品规格信息
			//更新商品规格基本信息
			goodsSkuRepository.deleteInfo(goods.getId());
			goodsSkuRepository.addInfo(goods.getGoodsSkuInfo(), goods.getId());
			
			//更新明细
			goodsSkuRepository.deleteDetail(goods.getId());
			goodsSkuRepository.addDetail(goods.getGoodsSkuInfo().getGoodsSkuDetails(), goods.getId());
		}
		
		int result = goodsRepository.updateGoods(goods);
		
		//清除缓存
		clearShopPageCache(goods.getShop_id());
		clearGoodsCache(goods.getId());
		
		return result;
	}
	
	@Override
	@Transactional
	public int deleteGoods(int shop_id, int goods_id) {
		
		//删除关联的图片,目前商品是假删除，暂时注释
//		goodsRepository.deleteImageByGoodsID(goods_id);
//		goodsSkuRepository.deleteInfo(goods_id);
		int result = goodsRepository.deleteGoods(shop_id, goods_id);

		//清除缓存
		clearShopPageCache(shop_id);
		clearGoodsCache(goods_id);
		
		return result;
	}
	
	/**
	 * 新增商品图片
	 *
	 * @param image
	 * @param goods_id
	 * @return
	 * @Param goods_id
	 */
	@Override
	@Transactional
	public int addGoodsImage(int shop_id, Image image, int goods_id) {
		
		goodsRepository.addImage(Lists.newArrayList(image), goods_id);
		
		//清除缓存
		clearShopPageCache(shop_id);
		clearGoodsCache(goods_id);
		
		return 1;
	}
	
	/**
	 * 删除某个商品的图片
	 *
	 * @param image_id
	 * @param goods_id
	 * @return
	 */
	@Override
	@Transactional
	public int deleteGoodsImage(int shop_id, int image_id, int goods_id) {
		int result = goodsRepository.deleteSingleGoodsImage(image_id, goods_id);
		
		//清除缓存
		clearShopPageCache(shop_id);
		clearGoodsCache(goods_id);
		
		return result;
	}
	
	/**
	 * 清除商品集合缓存
	 * @param shop_id
	 */
	private void clearShopPageCache(int shop_id){
		
		List<String> keys = cacheManage.getShopPageCacheKeys(shop_id);
		
		if(keys == null || keys.size() == 0) {
			cacheManage.clearAllGoodsPagesCache();
		}
		
		if(keys != null && keys.size() > 0){
			for (String pageKey : keys) {
				cacheManage.clearGoodsPageCache(shop_id, pageKey);
			}
			keys.clear();
		}
	}
	
	/**
	 * 清除商品缓存
	 * @param goods_id
	 */
	private void clearGoodsCache(int goods_id){
		
		cacheManage.clearGoodsCache(goods_id);
	}
}
