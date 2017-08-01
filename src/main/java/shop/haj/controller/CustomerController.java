package shop.haj.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.haj.entity.Customer;
import shop.haj.entity.Pagination;
import shop.haj.service.CustomerService;
import shop.haj.utils.ResultUtil;

import java.util.List;

/**
 * <p>Title: shop.ha.controller</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/3/17
 */
@RestController
@RequestMapping(value = "/v1/manager/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	/**
	 * 查询全部买家信息
	 * @param from
	 * @param to
	 * @param by
	 * @param sort
	 * @return
	 */
	@ApiOperation(value = "查询全部买家信息", notes = "查询全部买家信息")
	@GetMapping
	public List<Customer> findAll(@RequestParam(value = "from", defaultValue = "0") int from,
	                              @RequestParam(value = "limit", defaultValue = "20") int to,
	                              @RequestParam(value = "by", defaultValue = "id") String by,
	                              @RequestParam(value = "sort", defaultValue = "asc") String sort){
		
		Pagination page = new Pagination();
		/*page.setFrom(from);
		page.setLimit(to);
		page.setBy(by);
		page.setSort(sort);*/
		
		return customerService.findAll(page);
	}
	
	/**
	 * 查询单个买家信息
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询单个买家信息", notes = "根据买家ID查询单个买家信息")
	@GetMapping(value = "/{id}")
	public Customer findCustomerByID(@PathVariable("id") String id){
		return customerService.findById(id);
	}
	
	/**
	 * 新增买家信息
	 * @param customer
	 * @return
	 */
	@ApiOperation(value = "新增买家信息", notes = "新增买家信息")
	@PostMapping
	public Customer addCustomer(@RequestBody Customer customer){
		return customerService.add(customer);
	}
	
	/**
	 * 修改买家信息
	 * @param id
	 * @param customer
	 * @return
	 */
	@ApiOperation(value = "修改买家信息", notes = "根据买家ID修改买家信息")
	@PutMapping(value = "/{id}")
	public String updateCustomer(@PathVariable("id") String id, @RequestBody Customer customer){
		
		customer.setId(id);
		
		Customer result = customerService.update(customer);
		
		return ResultUtil.getJson("");
	}
	
	/**
	 * 删除买家信息
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "删除买家信息", notes = "根据买家ID删除买家信息")
	@DeleteMapping(value = "/{id}")
	public String delete(@PathVariable("id") String id){
		int result = customerService.delete(id);
		return ResultUtil.getJson(result);
	}
}
