package shop.haj.service.impl;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.haj.entity.Goods;
import shop.haj.entity.GoodsSkuInfo;
import shop.haj.entity.Image;
import shop.haj.entity.Pagination;
import shop.haj.manage.CacheManage;
import shop.haj.mongo_repository.MongoGoodsRepository;
import shop.haj.mongo_repository.MongoImageRepository;
import shop.haj.repository.GoodsDetailRepository;
import shop.haj.repository.GoodsSkuRepository;
import shop.haj.service.GoodsService;

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
	private MongoGoodsRepository mongoGoodsRepository;
	
	//@Autowired
	//private GoodsSkuRepository goodsSkuRepository;
	
	@Autowired
	private MongoImageRepository mongoImageRepository;
	
	//@Autowired
	//private GoodsDetailRepository goodsDetailRepository;
	
	@Autowired
	private CacheManage cacheManage;
	
	@Override
	@Transactional
//	@Cacheable(value = "goodsPages", key = "{#shop_id, #page.getOrderByLimitString()}")
	public List<Goods> findAll(String shop_id, Pagination page) {

		Goods condition = new Goods();
		condition.setShopId(shop_id);
		Example<Goods> example = Example.of(condition);
		PageRequest request = new PageRequest(0,10,page.getSort());
		Page<Goods> goodsList = mongoGoodsRepository.findAll(example,request);

		if(goodsList.getContent() == null && goodsList.getContent().size() <= 0) return goodsList.getContent();

		//cacheManage.addGoodsPageKeysMapData(shop_id, page.getOrderByLimitString());
		
		return goodsList.getContent();
	}
	
	@Override
//	@Cacheable(value = "goods", key = "#goods_id")
	public Goods findGoodsByID(String shop_id, String goods_id) {
		Goods goods = mongoGoodsRepository.findByIdAndShopId(shop_id, goods_id);
		
		if(goods == null) return null;

		return goods;
	}
	
	/*private GoodsSkuInfo findSkuInfo(int goods_id){
		GoodsSkuInfo goodsSkuInfo = goodsSkuRepository.findInfo(goods_id);
		
		if(goodsSkuInfo != null){
			goodsSkuInfo.setGoodsSkuDetails(goodsSkuRepository.findDetail(goods_id));
			
		}
		
		return goodsSkuInfo;
	}*/
	
	@Override
	public Goods findGoodsByUUID(String shop_id, String uuid) {
		
		Goods goods = mongoGoodsRepository.findByShopIdAndUuid(shop_id, uuid);
		
		return goods;
	}
	
	@Override
	public Goods findGoodsByName(String shop_id, String name) {
		Goods goods = null;//mongoGoodsRepository.findGoodsByName(shop_id, name);
		//goods.setImages(imageRepository.findGoodsImagesByGoodsID(goods.getId(), DefaultPagination.getImageGoods()));
		/*goods.setGoodsDetails(goodsDetailRepository.findGoodsDetailByGoodsID(goods.getId(), DefaultPagination.getGoodsDetail()));
		goods.setGoodsSkuInfo(findSkuInfo(goods.getId()));*/
		
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
		goods = mongoGoodsRepository.insert(goods);
		
		//当商品基本信息新增成功时，加入商品规格信息
		/*if(goods.getId() != null && goods.getGoodsSkuInfo() != null){
			goodsSkuRepository.addInfo(goods.getGoodsSkuInfo(), goods.getId());
			
			if(goods.getGoodsSkuInfo().getGoodsSkuDetails() != null &&
					goods.getGoodsSkuInfo().getGoodsSkuDetails() != null){
				goodsSkuRepository.addDetail(goods.getGoodsSkuInfo().getGoodsSkuDetails(), goods.getId());
			}
		}*/
		List<Image> goodsImage = goods.getImages();
		for(Image image:goodsImage){
			image.setGoodsId(goods.getId());
			image.setShopId(goods.getShopId());
		}
		//在新增商品成功后，保存图片信息
		mongoImageRepository.insert(goodsImage);

		//重置分页缓存
		clearShopPageCache(goods.getShopId());
		
		return goods;
	}
	
	@Override
	@Transactional
	public Goods updateGoods(Goods goods) {
		
		/*GoodsSkuInfo skuInfo = goods.getGoodsSkuInfo();
		if(skuInfo == null){//不包含商品规格信息，则需要删除

			mongoGoodsRepository.deleteDetail(goods.getId());
			mongoGoodsRepository.deleteInfo(goods.getId());
		}else {//包含商品规格信息
			//更新商品规格基本信息
			mongoGoodsRepository.deleteInfo(goods.getId());
			mongoGoodsRepository.addInfo(goods.getGoodsSkuInfo(), goods.getId());
			
			//更新明细
			goodsSkuRepository.deleteDetail(goods.getId());
			goodsSkuRepository.addDetail(goods.getGoodsSkuInfo().getGoodsSkuDetails(), goods.getId());
		}
		
		int result = mongoGoodsRepository.updateGoods(goods);
		
		//清除缓存
		clearShopPageCache(goods.getShop_id());
		clearGoodsCache(goods.getId());*/
		
		return goods;
	}
	
	@Override
	@Transactional
	public int deleteGoods(String shop_id, String goods_id) {
		
		//删除关联的图片,目前商品是假删除，暂时注释
//		goodsRepository.deleteImageByGoodsID(goods_id);
//		goodsSkuRepository.deleteInfo(goods_id);
		//int result = mongoGoodsRepository.deleteGoods(shop_id, goods_id);

		//清除缓存
		//clearShopPageCache(shop_id);
		//(goods_id);
		
		return 0;
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
	public int addGoodsImage(String shop_id, Image image, String goods_id) {

		if(null == image) return 0;
		image.setShopId(shop_id);
		image.setGoodsId(goods_id);
		mongoImageRepository.insert(Lists.newArrayList(image));
		
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
	public int deleteGoodsImage(String shop_id, String image_id, String goods_id) {
		//int result = mongoGoodsRepository.deleteSingleGoodsImage(image_id, goods_id);
		
		//清除缓存
		//clearShopPageCache(shop_id);
		//clearGoodsCache(goods_id);
		
		return 1;
	}
	
	/**
	 * 清除商品集合缓存
	 * @param shop_id
	 */
	private void clearShopPageCache(String shop_id){
		
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
	private void clearGoodsCache(String goods_id){
		
		cacheManage.clearGoodsCache(goods_id);
	}
}
