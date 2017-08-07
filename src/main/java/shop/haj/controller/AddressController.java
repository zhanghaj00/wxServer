package shop.haj.controller;

import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.haj.entity.Address;
import shop.haj.entity.Pagination;
import shop.haj.service.AddressService;
import shop.haj.utils.ResultUtil;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: shop.ha.controller</p>
 * <p/>
 * <p>
 * Description: 地址管理控制器，进行买家地址的增删改查
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/22/17
 */
@RestController
@RequestMapping(value = "/v1")
public class AddressController extends BaseController{
	
	private Logger logger = LogManager.getLogger(AddressController.class);
	
	@Autowired
	private AddressService addressService;
	
	/**
	 * 新增地址
	 * @param address
	 * @return
	 */
	@ApiOperation(value = "新增地址", notes = "新增买家地址信息")
	@PostMapping(value = "/customer/addresses")
	public Map<String,Object> addAddress(@RequestAttribute(value = "customer_id", required = false) String customer_id,
	                          @RequestBody Address address){
		logger.info("PostMapping request addAddress , address is {}, customer_id is {}", address, customer_id);
		
		address.setCustomerId(customer_id);
		
		return rtnParam(0,addressService.addAddress(address));
	}
	
	/**
	 * 查找买家的全部地址列表
	 * @param customer_id
	 * @return
	 */
	@ApiOperation(value = "查找买家地址列表", notes = "根据买家ID查找买家地址列表")
	@GetMapping(value = "/customer/addresses")
	public Map<String,Object> findAddressListByCustomerID(@RequestAttribute(value = "customer_id", required = false) String customer_id,
														  @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
														  @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
														  @RequestParam(value = "by", defaultValue = "is_default desc, id") String by,
														  @RequestParam(value = "sort", defaultValue = "desc") String sort){
		
		Pagination page = new Pagination();
		page.setFrom(pageNum);
		page.setLimit(pageSize);
		page.setBy(by);
		page.setSort(sort);
		
		logger.info("GetMapping request findAddressListByCustomerID , customer_id is {}", customer_id);
		
		return rtnParam(0,addressService.findAddressListByCustomerID(customer_id, page));
	}
	
	/**
	 * 根据地址ID查找
	 * @param address_id
	 * @return
	 */
	@ApiOperation(value = "查找买家地址", notes = "根据地址ID查找买家地址")
	@GetMapping(value = "/customer/addresses/{address_id}")
	public Address findAddressByID(@RequestAttribute(value = "customer_id", required = false) String customer_id,
	                               @PathVariable("address_id") String address_id){
		logger.info("GetMapping request findAddressByID , customer_id is {}, address_id is {}",
				customer_id, address_id);
		return addressService.findAddressByID(address_id);
	}
	
	@ApiOperation(value = "查找买家默认地址", notes = "查找买家默认地址")
	@GetMapping(value = "/customer/addresses/default")
	public Address findDefaultAddress(@RequestAttribute(value = "customer_id", required = false) String customer_id){
		
		logger.info("findDefaultAddress controller >>> customer_id={}", customer_id);
		
		return addressService.findDefaultAddress(customer_id);
	}
	
	/**
	 * 更新地址信息
	 * @param address
	 * @return
	 */
	@ApiOperation(value = "修改地址", notes = "根据地址ID修改买家地址")
	@PutMapping(value = "/customer/addresses/{address_id}")
	public String updateAddress(@RequestAttribute(value = "customer_id", required = false) String customer_id,
	                            @PathVariable("address_id") String address_id,
	                            @RequestBody Address address){
		
		logger.info("PutMapping request updateAddress , customer_id is {}, address_id is {}, address is {}",
				customer_id, address_id, address);
		
		address.setCustomerId(customer_id);
		address.setId(address_id);
		
		//int state = addressService.updateAddress(address);
		
		return ResultUtil.getJson(9);
	}
	
	@ApiOperation(value = "设置默认地址", notes = "将某个地址设置为默认地址")
	@PatchMapping(value = "/customer/addresses/{address_id}/default")
	public String setDefaultAddress(@RequestAttribute(value = "customer_id", required = false) String customer_id,
	                         @PathVariable("address_id") String address_id){
		logger.info("setDefaultAddress controller >>> customer_id={}, address_id={}",
				customer_id, address_id);
		
		int result = addressService.setDefaultAddress(customer_id, address_id);
		
		return ResultUtil.getJson(result);
	}
	
	/**
	 * 删除某个地址信息
	 * @param address_id
	 * @return
	 */
	@ApiOperation(value = "删除地址", notes = "根据地址ID删除买家地址")
	@DeleteMapping(value = "/customer/addresses/{address_id}")
	public String deleteAddress(@RequestAttribute(value = "customer_id", required = false) String customer_id,
	                            @PathVariable("address_id") String address_id){
		
		logger.info("DeleteMapping request deleteAddress , customer_id is {}, address_id is {}",
						customer_id, address_id);
		
		int state = addressService.deleteAddress(address_id);
		
		return ResultUtil.getJson(state);
	}
	
}
