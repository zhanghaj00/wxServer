package shop.haj.controller;

import com.google.common.collect.Maps;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.haj.entity.Cart;
import shop.haj.entity.Goods;
import shop.haj.entity.Pagination;
import shop.haj.entity.Shop;
import shop.haj.service.GoodsService;
import shop.haj.service.ShopCartService;
import shop.haj.service.ShopService;
import shop.haj.utils.ResultUtil;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: shop.ha.controller</p>
 * <p/>
 * <p>
 * Description: 购物车接口
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/20/17
 */
@RestController
@RequestMapping(value = "/v1")
public class CartController {
	
	private Logger logger = LogManager.getLogger(CartController.class);
	
	@Autowired
	private ShopCartService shopCartService;
	
	@Autowired
	private ShopService shopService;
	
	@Autowired
	private GoodsService goodsService;
	
	private Map<String, String> shopCartUsedMap = Maps.newConcurrentMap();//记录当前用户占用的购物车情况，key：customer_id+shop_id，单个时间仅允许一个请求进行购物车数量更新。
	
	
	@ApiOperation(value = "查找店铺购物车列表", notes = "该用户在该店铺的购物车列表")
	@GetMapping("/customer/carts")
	public List<Cart> findShopCarts(@RequestAttribute(value = "customer_id", required = false) String customer_id,
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
		
		return shopCartService.findShopCarts(customer_id, shop_id, page);
	}
	
	@ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车")
	@PostMapping("/customer/carts")
	public String addCart(@RequestAttribute(value = "customer_id", required = false) String customer_id,
	                    @RequestHeader("shop_id") String shop_id,
	                    @RequestBody Cart cart){
		
		String key = customer_id + "_" + shop_id;
		
		//防止购物车过于频繁的请求导致购物车数量新增出错
		synchronized (key) {
			if(shopCartUsedMap.get(key) != null){
				return ResultUtil.getJson(0);
			}
			shopCartUsedMap.put(key, cart.toString());
		}
		
		int result;
		try {
			Cart checkCart = shopCartService.findShopCart(customer_id, "1",
					cart.getGoodsId(), cart.getGoodsSku());
			
			//如果该商品规格在购物车中不存在，则新增，否则数量+1
			if(null == checkCart) {
				
				Shop shop = shopService.findById(shop_id);
				
				cart.setCustomerId(customer_id);
				cart.setShopId("1");
				cart.setShopName(shop.getName());
				
				Goods goods = goodsService.findGoodsByID("1", String.valueOf(cart.getGoodsId()));
				cart.setGoodsName(goods.getName());
				cart.setGoodsPrice(goods.getSellPrice());
				cart.setGoodsImage(goods.getImages().get(0).getUrl());
				
				result = shopCartService.addCart(cart);
				
			} else {
				int increaseNum = cart.getGoodsNum() == 0 ? 1 : cart.getGoodsNum();
				result = shopCartService.updateCartNum(customer_id, "1", checkCart.getId(), (checkCart.getGoodsNum() + increaseNum));
			}
		} finally {
			
			shopCartUsedMap.remove(key);
		}
		
		
		return ResultUtil.getJson(result);
	}
	
	@ApiOperation(value = "更新购物车数量", notes = "更新购物车数量")
	@PutMapping("/customer/carts/{cart_id}")
	public String updateCartNum(@RequestAttribute(value = "customer_id", required = false) String customer_id,
	                            @RequestHeader("shop_id") String shop_id,
	                            @PathVariable("cart_id") String cart_id,
	                            @RequestBody Cart cart){
		
		String key = customer_id + "_" + shop_id;
		
		//防止购物车过于频繁的请求导致购物车数量新增出错
		synchronized (key) {
			if(shopCartUsedMap.get(key) != null){
				return ResultUtil.getJson(0);
			}
			shopCartUsedMap.put(key, cart.toString());
		}
		
		int result;
		try {
			result = shopCartService.updateCartNum(customer_id, shop_id, cart_id, cart.getGoodsNum());
		} finally {
			shopCartUsedMap.remove(key);
		}
		
		return ResultUtil.getJson(result);
	}
	
	@ApiOperation(value = "从购物车中删除商品", notes = "从购物车中删除商品")
	@DeleteMapping("/customer/carts/{cart_id}")
	public String removeGoodsFromCart(@RequestAttribute(value = "customer_id", required = false) String customer_id,
	                                  @RequestHeader("shop_id") String shop_id,
	                                  @PathVariable("cart_id") String cart_id){
		
		int result = shopCartService.removeGoodsFromCart(customer_id, shop_id, cart_id);
		return ResultUtil.getJson(result);
	}
	
	@ApiOperation(value = "清空购物车", notes = "清空该用户在该店铺的购物车信息")
	@DeleteMapping("/customer/carts")
	public String clearShopCart(@RequestAttribute(value = "customer_id", required = false) String customer_id,
	                            @RequestHeader("shop_id") String shop_id){
		
		int result = shopCartService.clearShopCart(customer_id, shop_id);
		return ResultUtil.getJson(result);
	}
}
