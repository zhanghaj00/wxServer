package shop.haj.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.haj.service.VisitLogService;

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
public class VisitController extends BaseController{
	
	@Autowired
	private VisitLogService visitLogService;

	
	@ApiOperation(value = "新增买家访问店铺记录", notes = "新增买家访问店铺记录")
	@PostMapping(value = "/customer/visit_shops")
	public Map<String,Object> addVisitShopLog(@RequestAttribute(value = "customer_id", required = false) String customer_id, @RequestHeader("shop_id") String shop_id){
		int result = visitLogService.addVisitShopLog(customer_id, shop_id);
		return rtnParam(0,result);
	}


}
