package shop.haj.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.haj.entity.ShopCategory;
import shop.haj.mongo_repository.MongoShopCategoryRepository;
import shop.haj.service.ShopCategoryService;

import java.util.List;

/**
 * <p>Title: shop.ha.service.impl</p>
 * <p/>
 * <p>
 * Description: ShopCategoryServiceImpl
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：4/11/17
 */
@Service
@Transactional
public class ShopCategoryServiceImpl implements ShopCategoryService{
	
	private Logger logger = LogManager.getLogger();
	
	@Autowired
	private MongoShopCategoryRepository mongoShopCategoryRepository;
	
	/**
	 * 查找所有一级分类
	 *
	 * @return
	 */
	@Override
	public List<ShopCategory> findParentShopCategory() {
		
		return mongoShopCategoryRepository.findByPid("0");
	}
	
	/**
	 * 查找某分类下的全部子类别
	 *
	 * @param pid
	 * @return
	 */
	@Override
	public List<ShopCategory> findParentShopCategoryByID(String pid) {
		
		return mongoShopCategoryRepository.findByPid(pid);
		
	}
	
	/**
	 * 根据分类ID查找分类信息
	 *
	 * @param category_id
	 * @return
	 */
	@Override
	public ShopCategory findShopCategoryByID(String category_id) {
		
		return mongoShopCategoryRepository.findOne(category_id);
	}
	
	/**
	 * 新增商店分类信息
	 *
	 * @param shopCategory
	 * @return
	 */
	@Override
	public ShopCategory addShopCategory(ShopCategory shopCategory) {
		
		return mongoShopCategoryRepository.insert(shopCategory);
	}
	
	/**
	 * 更新商品分类信息
	 *
	 * @param shopCategory
	 * @return
	 */
	@Override
	public ShopCategory updateShopCategory(ShopCategory shopCategory) {
		return mongoShopCategoryRepository.save(shopCategory);
	}
	
	/**
	 * 删除商品分类
	 * 注意：如果删除的是父分类，需要删除子分类
	 *
	 * @param category_id
	 * @return
	 */
	@Override
	public int deleteShopCategory(String category_id) {
		mongoShopCategoryRepository.delete(category_id);
		return 0;
	}
}
