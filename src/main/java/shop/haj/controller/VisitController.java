package shop.haj.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.haj.entity.Pagination;
import shop.haj.entity.Shop;
import shop.haj.service.VisitLogService;
import shop.haj.utils.ResultUtil;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: shop.ha.controller</p>
 * <p/>
 * <p>
 * Description: 访问记录管理接口
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/4/17
 */
@RestController
@RequestMapping(value = "/v1")
public class VisitController {
	
	@Autowired
	private VisitLogService visitLogService;
	
	@ApiOperation(value = "查询买家访问店铺记录", notes = "查询买家访问店铺记录")
	@GetMapping(value = "/customer/visit_shops")
	public List<Shop> findVisitShopsByCusomterID(@RequestAttribute(value = "customer_id", required = false) int customer_id,
	                                             @RequestParam(value = "from", defaultValue = "0") int from,
	                                             @RequestParam(value = "limit", defaultValue = "20") int to,
	                                             @RequestParam(value = "by", defaultValue = "visit_time") String by,
	                                             @RequestParam(value = "sort", defaultValue = "asc") String sort){
		
		Pagination page = new Pagination();
		/*page.setFrom(from);
		page.setLimit(to);
		page.setBy(by);
		page.setSort(sort);*/
		
		return visitLogService.findVisitShopsByCusomterID(customer_id, page);
	}
	
	@ApiOperation(value = "新增买家访问店铺记录", notes = "新增买家访问店铺记录")
	@PostMapping(value = "/customer/visit_shops")
	public String addVisitShopLog(@RequestAttribute(value = "customer_id", required = false) int customer_id, @RequestBody Map<String, Object> data){
		int result = visitLogService.addVisitShopLog(customer_id, (int)data.get("shop_id"));
		return ResultUtil.getJson(result);
	}
}
