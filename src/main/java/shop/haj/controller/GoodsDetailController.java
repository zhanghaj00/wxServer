package shop.haj.controller;

import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.haj.entity.GoodsDetail;
import shop.haj.entity.Pagination;
import shop.haj.service.GoodsDetailService;
import shop.haj.utils.ResultUtil;

import java.util.List;

/**
 * <p>Title: shop.ha.controller</p>
 * <p/>
 * <p>
 * Description: 商品详情控制器处理
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/21/17
 */
@RestController
@RequestMapping(value = "/v1")
public class GoodsDetailController {
	
	private Logger logger = LogManager.getLogger(GoodsDetailController.class);
	
	@Autowired
	private GoodsDetailService goodsDetailService;
	
	/**
	 * 根据商品ID查找全部的商品详情
	 * @param goods_id
	 * @return
	 */
	@ApiOperation(value = "查找商品详细列表", notes = "返回商品详细的内容")
	@GetMapping(value = {"/customer/goods/{goods_id}/details",
						 "/seller/goods/{goods_id}/details"})
	public List<GoodsDetail> findGoodsDetailByGoodsID(@RequestHeader("shop_id") int shop_id,
	                                                  @PathVariable("goods_id") int goods_id,
	                                                  @RequestParam(value = "from", defaultValue = "0") int from,
	                                                  @RequestParam(value = "limit", defaultValue = "20") int to,
	                                                  @RequestParam(value = "by", defaultValue = "sequence") String by,
	                                                  @RequestParam(value = "sort", defaultValue = "asc") String sort){
		
		Pagination page = new Pagination();
		/*page.setFrom(from);
		page.setLimit(to);
		page.setBy(by);
		page.setSort(sort);*/
		
		return goodsDetailService.findGoodsDetailByGoodsID(goods_id, page);
	}
	
	/**
	 * 新增商品详情
	 * @param goodsDetails
	 * @return
	 */
	@ApiOperation(value = "新增商品详细内容", notes = "卖家新增商品详细内容，可传入多个商品详情信息")
	@PostMapping(value = {"/seller/goods/{goods_id}/details"})
	public List<GoodsDetail> addGoodsDetailList(@RequestHeader("shop_id") int shop_id,
	                                            @PathVariable("goods_id") int goods_id,
	                                            @RequestBody List<GoodsDetail> goodsDetails){
		return goodsDetailService.addGoodsDetailList(goodsDetails, goods_id);
	}
	
	/**
	 * 更新商品详情
	 * @param goodsDetails
	 * @return
	 */
	@ApiOperation(value = "更新商品详细内容", notes = "卖家更新商品详细内容")
	@PutMapping(value = "/seller/goods/{goods_id}/details")
	public String updateGoodsDetail(@RequestHeader("shop_id") int shop_id,
	                                @PathVariable("goods_id") int goods_id,
	                                @RequestBody List<GoodsDetail> goodsDetails){
		
		int result = goodsDetailService.updateGoodsDetail(goods_id, goodsDetails);
		return ResultUtil.getJson(result);
	}
	
	/**
	 * 删除商品详情
	 * @param detail_id
	 * @return
	 */
	@ApiOperation(value = "删除商品详细内容", notes = "卖家删除某个商品详细内容")
	@DeleteMapping(value = "/seller/goods/{goods_id}/details/{detail_id}")
	public String deleteGoodsDetail(@RequestHeader("shop_id") int shop_id,
	                                @PathVariable("goods_id") int goods_id,
	                                @PathVariable("detail_id") int detail_id){
		
		int result = goodsDetailService.deleteGoodsDetail(goods_id, detail_id);
		return ResultUtil.getJson(result);
	}
}
