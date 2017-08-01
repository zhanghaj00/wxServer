package shop.haj.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.haj.entity.Pagination;
import shop.haj.entity.Seller;
import shop.haj.service.SellerService;
import shop.haj.utils.ResultUtil;

import java.util.List;

/**
 * <p>Title: shop.ha.controller</p>
 * <p/>
 * <p>
 * Description: 卖家管理接口
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/14/17
 */
@RestController
@RequestMapping(value = "/v1/manager/sellers")
public class SellerController {
	
	@Autowired
	private SellerService sellerService;
	
	@ApiOperation(value = "查找所有卖家信息", notes = "查找所有卖家信息")
	@GetMapping
	public List<Seller> findAll(@RequestParam(value = "from", defaultValue = "0") int from,
	                            @RequestParam(value = "limit", defaultValue = "20") int to,
	                            @RequestParam(value = "by", defaultValue = "id") String by,
	                            @RequestParam(value = "sort", defaultValue = "asc") String sort){
		
		Pagination page = new Pagination();
		/*page.setFrom(from);
		page.setLimit(to);
		page.setBy(by);
		page.setSort(sort);*/
		
		return sellerService.findAll(page);
	}
	
	@ApiOperation(value = "根据ID查找卖家信息", notes = "根据ID查找卖家信息")
	@GetMapping(value = "/{id}")
	public Seller findSellerByID(@PathVariable("id") int id){
		return sellerService.findById(id);
	}
	
	@ApiOperation(value = "新增卖家信息", notes = "新增卖家信息")
	@PostMapping
	public Seller addSeller(@RequestBody Seller seller){
		return sellerService.add(seller);
	}
	
	@ApiOperation(value = "修改卖家信息", notes = "修改卖家信息")
	@PutMapping(value = "/{id}")
	public String updateSeller(@PathVariable("id") int id, @RequestBody Seller seller){
		seller.setId(id);
		int result = sellerService.update(seller);
		return ResultUtil.getJson(result);
	}
	
	@ApiOperation(value = "删除卖家信息", notes = "删除卖家信息")
	@DeleteMapping(value = "/{id}")
	public String delete(@PathVariable("id") int id){
		int result = sellerService.delete(id);
		return ResultUtil.getJson(result);
	}
}
