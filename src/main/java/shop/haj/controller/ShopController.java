package shop.haj.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.haj.entity.Image;
import shop.haj.entity.Pagination;
import shop.haj.entity.Shop;
import shop.haj.entity.ShopCategory;
import shop.haj.service.ShopCategoryService;
import shop.haj.service.ShopService;
import shop.haj.utils.ResultUtil;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: shop.ha.controller</p>
 * <p/>
 * <p>
 * Description: 店铺管理接口
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/1/17
 */
@RestController
@RequestMapping(value = "/v1")
public class ShopController extends BaseController{
	
	@Autowired
	private ShopService shopService;
	
	@Autowired
	private ShopCategoryService shopCategoryService;
	
	/**
	 * 查找所有店铺信息
	 * @return
	 */
	@ApiOperation(value = "查找所有店铺信息", notes = "查找所有店铺信息")
	@GetMapping(value = "/manager/shops")
	public List<Shop> findAll(@RequestParam(value = "from", defaultValue = "0") int from,
	                          @RequestParam(value = "limit", defaultValue = "20") int to,
	                          @RequestParam(value = "by", defaultValue = "id") String by,
	                          @RequestParam(value = "sort", defaultValue = "asc") String sort){
		
		Pagination page = new Pagination();
		page.setPagenumber(from);
		page.setPagesize(to);
		//page.setBy(by);
		//page.set(sort);
		
		List<Shop> shops = shopService.findAll(page);
		
		//补充商品名称
		for (Shop shop : shops) {
			ShopCategory shopCategory = shopCategoryService.findShopCategoryByID(shop.getCategoryId());
			shop.setCategoryName(shopCategory.getName());
		}
		
		return shops;
	}
	
	/**
	 * 根据店铺ID查找店铺信息
	 * @param shop_id
	 * @return
	 */
	@ApiOperation(value = "查找单个店铺信息", notes = "根据店铺ID查找店铺信息")
	@GetMapping(value = {"/seller/shops","/customer/shops"})
	public Map<String,Object> findShopByID(@RequestHeader("shop_id") String shop_id){
		Shop shop = shopService.findById(shop_id);

		ShopCategory shopCategory = shopCategoryService.findShopCategoryByID(shop.getCategoryId());
		shop.setCategoryName(shopCategory.getName());
		
		return rtnParam(0,shop);
	}

	/**
	 * 根据店铺ID查找店铺信息
	 * @param
	 * @return
	 */
	@ApiOperation(value = "查找所有店铺信息", notes = "根据店铺ID查找店铺信息")
	@GetMapping(value = {"/seller/shops_list"})
	public Map<String,Object> findShopBySellerId(@RequestHeader("login_code") String login_code){

		//根据login-code来查找相关点不
		List<Shop> shop = shopService.findBySellerId(login_code);

		//ShopCategory shopCategory = shopCategoryService.findShopCategoryByID(shop.getCategory_id());
		//shop.setCategory_name(shopCategory.getName());

		return rtnParam(0,shop);
	}

	/**
	 * 卖家创建店铺
	 * @param shop
	 * @return
	 */
	@ApiOperation(value = "新增店铺信息", notes = "新增店铺信息")
	@PostMapping(value = "/seller/sellers/{seller_id}/shops")
	public Shop addShop(@PathVariable("seller_id") String seller_id,
	                    @RequestBody Shop shop){
		return shopService.addShop(shop, seller_id);
	}
	
	/**
	 * 卖家更新店铺信息
	 * @param shop_id
	 * @param shop
	 * @return
	 */
	@ApiOperation(value = "修改店铺信息", notes = "卖家更新店铺信息")
	@PutMapping(value = {"/seller/shops"})
	public String updateShop(@RequestHeader("shop_id") String shop_id,
	                         @RequestBody Shop shop){
		shop.setId(shop_id);
		return "";//ResultUtil.getJson(shopService.updateShop(shop));
	}
	
	@ApiOperation(value = "新增店铺图片", notes = "卖家新增店铺图片")
	@PostMapping(value = "/seller/shops/images")
	public List<Image> addShopImage(@RequestHeader("shop_id") String shop_id,
	                           @RequestBody List<Image> images){
		
		return shopService.addShopImage(images, shop_id);
	}
	
	@ApiOperation(value = "删除店铺图片", notes = "卖家删除店铺图片")
	@DeleteMapping(value = "/seller/shops/images/{image_id}")
	public String deleteShopImage(@RequestHeader("shop_id") String shop_id,
	                              @PathVariable("image_id") String image_id){
		int result = shopService.deleteShopImage(image_id, shop_id);
		
		return ResultUtil.getJson(result);
	}
}
