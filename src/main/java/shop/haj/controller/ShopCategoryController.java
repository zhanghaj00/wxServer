package shop.haj.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.haj.entity.ShopCategory;
import shop.haj.service.ShopCategoryService;

import java.util.List;

/**
 * <p>Title: shop.ha.controller</p>
 * <p/>
 * <p>
 * Description: shop category controller
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：4/11/17
 */
@RestController
@RequestMapping(value = "/v1")
public class ShopCategoryController {
	
	@Autowired
	private ShopCategoryService shopCategoryService;
	
	/**
	 * 查找父类别对应的所有类别信息
	 * @param pid
	 * @return
	 */
	@ApiOperation(value = "查询店铺分类信息", notes = "查询店铺分类信息")
	@GetMapping(value = "/sellers/shop_parent_categories/{pid}")
	public List<ShopCategory> findParentShopCategory(@PathVariable("pid") String pid){
		return shopCategoryService.findParentShopCategoryByID(pid);
	}
	
}
