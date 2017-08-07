package shop.haj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.haj.entity.Image;
import shop.haj.entity.Pagination;
import shop.haj.entity.Shop;
import shop.haj.mongo_repository.MongoImageRepository;
import shop.haj.mongo_repository.MongoShopRepository;
import shop.haj.service.ShopService;

import java.util.List;

/**
 * <p>Title: shop.ha.service.impl</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/1/17
 */
@Service
@Transactional
public class ShopServiceImpl implements ShopService{
	
	@Autowired
	private MongoImageRepository mongoImageRepository;

	@Autowired
	private MongoShopRepository mongoShopRepository;
	
	//@Autowired
	//private ShopCategoryRepository shopCategoryRepository;
	
	@Override
	@Transactional
	public List<Shop> findAll(Pagination page) {
		Page<Shop> shops = mongoShopRepository.findAll(page.getRequest());
		
		
		for (Shop shop : shops.getContent()) {
			
			//新增图片信息
			shop.setImages(mongoImageRepository.findByShopId(shop.getId()));
		}
		
		return shops.getContent();
	}
	
	@Override
	@Transactional
	public Shop findById(String id) {
		Shop shop = mongoShopRepository.findShopById(id);
		if(null != shop){
			shop.setImages(mongoImageRepository.findByShopId(shop.getId()));
		}
		return shop;
	}
	
	@Override
	@Transactional
	public Shop addShop(Shop shop, String seller_id) {

		shop.setSellerId(seller_id);
		//新增店铺
		shop = mongoShopRepository.insert(shop);
		
		if(shop.getImages() == null || shop.getImages().size() == 0) return shop;
		
		//新增图片
		addShopImage(shop.getImages(), shop.getId());
		
		return shop;
	}
	
	@Override
	@Transactional
	public Shop updateShop(Shop shop) {
		shop =  mongoShopRepository.save(shop);
		return shop;
	}
	
	/**
	 * 新增店铺图片
	 *
	 * @param images
	 * @param shop_id
	 * @return
	 */
	@Override
	public List<Image> addShopImage(List<Image> images, String shop_id) {

		for(Image image:images){
			image.setShopId(shop_id);
		}

		return mongoImageRepository.insert(images);
	}
	
	/**
	 * 删除店铺图片
	 *
	 * @param image_id
	 * @param shop_id
	 * @return
	 */
	@Override
	public int deleteShopImage(String image_id, String shop_id) {
		return mongoImageRepository.deleteByIdAndShopId(image_id, shop_id);
	}

	@Override
	public List<Shop> findBySellerId(String seller_id) {
		return mongoShopRepository.findBySellerId(seller_id);
	}
}
