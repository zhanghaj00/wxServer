package shop.haj.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.haj.entity.Goods;
import shop.haj.entity.Image;
import shop.haj.entity.Pagination;
import shop.haj.service.GoodsService;
import shop.haj.utils.ResultUtil;

import java.util.Map;

/**
 * <p>Title: shop.ha.controller</p>
 * <p/>
 * <p>
 * Description: GoodsController
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/4/17
 */
@RestController
@RequestMapping(value = "/v1")
public class GoodsController extends  BaseController{
	
	@Autowired
	private GoodsService goodsService;
	
	/**
	 * 查找某个店铺的全部商品信息
	 * @param shop_id
	 * @return
	 */
	@ApiOperation(value = "查找店铺内的全部商品信息", notes = "查找某个店铺的全部商品信息，买家和卖家共用接口")
	@GetMapping(value = {"/customer/goods", "/seller/goods"})
	public Map<String,Object> findAll(@RequestHeader("shop_id") String shop_id,
	                           @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
	                           @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
	                           @RequestParam(value = "by", defaultValue = "id") String by,
	                           @RequestParam(value = "sort", defaultValue = "asc") String sort){
		
		Pagination page = new Pagination();
		page.setFrom(pageNum);
		page.setLimit(pageSize);
		page.setBy(by);
		page.setSort(sort);
		
		return rtnParam(0, goodsService.findAll(shop_id, page));
	}
	
	/**
	 * 根据商品ID查找商品
	 * @param shop_id
	 * @param goods_id
	 * @return
	 */
	
	@ApiOperation(value = "查找店铺内的单个商品信息", notes = "查找店铺内的单个商品信息，买家和卖家共用接口")
	@GetMapping(value = {"/customer/goods/{goods_id}",
						"/seller/goods/{goods_id}"})
	public Map<String,Object>  findGoodsByID(@RequestHeader("shop_id") String shop_id,
	                           @PathVariable("goods_id") String goods_id){
		return rtnParam(0, goodsService.findGoodsByID(shop_id, goods_id));
	}
	
	/**
	 * 在店铺内新增商品
	 * @param shop_id
	 * @param goods
	 * @return
	 */
	@ApiOperation(value = "卖家新增商品", notes = "卖家新增店铺内的单个商品")
	@PostMapping(value = "/seller/goods")
	public Map<String,Object>  addGoods(@RequestHeader("shop_id") String shop_id,
	                      @RequestBody Goods goods){
		goods.setShopId(shop_id);
		return rtnParam(0, goodsService.addGoods(goods));
	}
	
	/**
	 * 更新商品信息
	 * @param shop_id
	 * @param goods_id
	 * @param goods
	 * @return
	 */
	@ApiOperation(value = "卖家修改商品", notes = "卖家修改商品信息")
	@PutMapping(value = "/seller/goods/{goods_id}")
	public Map<String,Object>  updateGoods(@RequestHeader("shop_id") String shop_id,
	                          @PathVariable("goods_id") String goods_id,
	                          @RequestBody Goods goods){
		
		goods.setShopId(shop_id);
		goods.setId(goods_id);
		goods = goodsService.updateGoods(goods);
		return rtnParam(0,goods);
	}

	/**
	 * 删除商品信息
	 * @param shop_id
	 * @param goods_id
	 * @return
	 */
	@ApiOperation(value = "卖家删除商品", notes = "卖家删除商品信息")
	@DeleteMapping(value = "/seller/goods/{goods_id}")
	public Map<String,Object>  deleteGoods(@RequestHeader("shop_id") String shop_id,
	                          @PathVariable("goods_id") String goods_id){
		int result = goodsService.deleteGoods(shop_id, goods_id);
		return rtnParam(0, ResultUtil.getJson(result));
	}
	
	/**
	 * 卖家新增商品图片
	 * @param shop_id
	 * @param goods_id
	 * @param image
	 * @return
	 */
	@ApiOperation(value = "卖家新增商品图片", notes = "卖家新增商品图片")
	@PostMapping(value = "/seller/goods/{goods_id}/images")
	public Map<String,Object>  addGoodsImage(@RequestHeader("shop_id") String shop_id,
	                            @PathVariable("goods_id") String goods_id,
	                            @RequestBody Image image){
		int state = goodsService.addGoodsImage(shop_id, image, goods_id);
		return rtnParam(0, ResultUtil.getJson(state));
	}
	
	/**
	 * 卖家删除商品图片
	 * @param shop_id
	 * @param goods_id
	 * @param image_id
	 * @return
	 */
	@ApiOperation(value = "卖家删除商品图片", notes = "卖家删除商品图片")
	@DeleteMapping(value = "/seller/goods/{goods_id}/images/{image_id}")
	public Map<String,Object>  deleteGoodsImage(@RequestHeader("shop_id") String shop_id,
	                               @PathVariable("goods_id") String goods_id,
	                               @PathVariable("image_id") String image_id){
		int state = goodsService.deleteGoodsImage(shop_id, image_id, goods_id);
		return rtnParam(0,ResultUtil.getJson(state));
	}
	
}
