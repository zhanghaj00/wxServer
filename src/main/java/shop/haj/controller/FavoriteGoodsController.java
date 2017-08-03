package shop.haj.controller;

import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.haj.entity.FavoriteGoods;
import shop.haj.entity.Pagination;
import shop.haj.service.FavoriteGoodsService;
import shop.haj.utils.ResultUtil;

import java.util.List;

/**
 * <p>Title: shop.ha.controller</p>
 * <p/>
 * <p>
 * Description: 收藏商品服务
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/24/17
 */
@RestController
@RequestMapping(value = "/v1")
public class FavoriteGoodsController {
	
	private Logger logger = LogManager.getLogger(FavoriteGoodsController.class);
	
	@Autowired
	private FavoriteGoodsService favoriteGoodsService;
	
	/**
	 * 查找卖家全部收藏的商品
	 * @param customer_id
	 * @return
	 */
	@ApiOperation(value = "查找用户全部收藏的商品", notes = "查找用户全部收藏的商品")
	@GetMapping(value = "/customer/favorite_goods")
	public List<FavoriteGoods> findFavoriteGoodsByCustomer(@RequestAttribute(value = "customer_id", required = false) String customer_id,
	                                                       @RequestParam(value = "from", defaultValue = "0") int from,
	                                                       @RequestParam(value = "limit", defaultValue = "20") int to,
	                                                       @RequestParam(value = "by", defaultValue = "create_time") String by,
	                                                       @RequestParam(value = "sort", defaultValue = "desc") String sort){
		Pagination page = new Pagination();
		/*page.setFrom(from);
		page.setLimit(to);
		page.setBy(by);
		page.setSort(sort);*/
		
		return favoriteGoodsService.findFavoriteGoodsByCustomer(customer_id, page);
	}
	
	/**
	 * 查找卖家在该店铺收藏的商品
	 * @param customer_id
	 * @param shop_id
	 * @return
	 */
	@ApiOperation(value = "查找用户在某家店收藏的商品", notes = "查找用户在某家店收藏的商品")
	@GetMapping(value = "/customer/shops/favorite_goods")
	public List<FavoriteGoods> findFavoriteGoodsByCustomerShop(
			@RequestAttribute(value = "customer_id", required = false) String customer_id,
			@RequestHeader("shop_id") String shop_id,
			@RequestParam(value = "from", defaultValue = "0") int from,
			@RequestParam(value = "limit", defaultValue = "20") int to,
			@RequestParam(value = "by", defaultValue = "create_time") String by,
			@RequestParam(value = "sort", defaultValue = "desc") String sort){
		
		Pagination page = new Pagination();
		/*page.setFrom(from);
		page.setLimit(to);
		page.setBy(by);
		page.setSort(sort);*/
		
		return favoriteGoodsService.findFavoriteGoodsByCustomerShop(customer_id, shop_id, page);
	}
	
	/**
	 * 商品加入收藏
	 * @param favoriteGoods
	 * @return
	 */
	@ApiOperation(value = "新增收藏", notes = "新增收藏商品, POST参数需要传${goods_id}")
	@PostMapping(value = "/customer/favorite_goods")
	public String addFavoriteGoods(@RequestAttribute(value = "customer_id", required = false) String customer_id,
	                            @RequestHeader("shop_id") String shop_id,
	                            @RequestBody FavoriteGoods favoriteGoods){
		favoriteGoods.setCustomerId(customer_id);
		favoriteGoods.setShopId(shop_id);

		favoriteGoods = favoriteGoodsService.addFavoriteGoods(favoriteGoods);
		
		return favoriteGoods.toString();
	}
	
	/**
	 * 移除收藏
	 * @param customer_id
	 * @return
	 */
	@ApiOperation(value = "移除收藏", notes = "移除收藏, POST参数需要传${goods_id}")
	@DeleteMapping(value = "/customer/favorite_goods")
	public String removeFavoriteGoods(@RequestAttribute(value = "customer_id", required = false) String customer_id,
	                                  @RequestBody FavoriteGoods favoriteGoods){
		int result = 0;//favoriteGoodsService.removeFavoriteGoods(customer_id, favoriteGoods.getGoods_id());
		
		return ResultUtil.getJson(result);
	}
}
