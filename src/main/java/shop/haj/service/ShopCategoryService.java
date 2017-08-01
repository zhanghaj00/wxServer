package shop.haj.service;

import shop.haj.entity.ShopCategory;

import java.util.List;

/**
 * <p>Title: shop.ha.service</p>
 * <p/>
 * <p>
 * Description: 商品分类服务接口
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：4/11/17
 */
public interface ShopCategoryService {
	
	/**
	 * 查找所有一级分类
	 * @return
	 */
	List<ShopCategory> findParentShopCategory();
	
	/**
	 * 查找某分类下的全部子类别
	 * @param pid
	 * @return
	 */
	List<ShopCategory> findParentShopCategoryByID(String pid);
	
	/**
	 * 根据分类ID查找分类信息
	 * @param category_id
	 * @return
	 */
	ShopCategory findShopCategoryByID(String category_id);
	
	/**
	 * 新增商店分类信息
	 * @param shopCategory
	 * @return
	 */
	ShopCategory addShopCategory(ShopCategory shopCategory);
	
	/**
	 * 更新商品分类信息
	 * @param shopCategory
	 * @return
	 */
	ShopCategory updateShopCategory(ShopCategory shopCategory);
	
	/**
	 * 删除商品分类
	 * 注意：如果删除的是父分类，需要删除子分类
	 * @param category_id
	 * @return
	 */
	int deleteShopCategory(String category_id);
}
