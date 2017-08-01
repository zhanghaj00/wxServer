package shop.haj.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.haj.entity.Coupon;
import shop.haj.entity.CouponCustomerInfo;
import shop.haj.entity.Pagination;
import shop.haj.service.CouponService;

import java.util.List;

/**
 * <p>Title: shop.ha.controller</p>
 * <p/>
 * <p>
 * Description: 优惠券相关控制器处理类
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/22/17
 */
@RestController
@RequestMapping(value = "/v1")
public class CouponController {
	
	@Autowired
	private CouponService couponService;
	
	/**
	 * 卖家新增优惠券
	 * @param shop_id
	 * @param coupon
	 * @return
	 */
	@ApiOperation(value = "新增优惠券", notes = "卖家新增优惠券")
	@PostMapping({"/seller/coupons"})
	public Coupon addCoupon(@RequestHeader("shop_id") int shop_id,
	                        @RequestBody Coupon coupon){
		
		coupon.setShop_id(shop_id);
		
		return couponService.addCoupon(coupon);
	}
	
	/**
	 * 卖家查询优惠券
	 * @param shop_id
	 * @return
	 */
	@ApiOperation(value = "卖家查找店铺优惠券", notes = "卖家查找优惠券列表")
	@GetMapping(value = {"/seller/coupons"})
	public List<Coupon> findCouponByShopID(@RequestHeader("shop_id") int shop_id,
	                                       @RequestParam(value = "from", defaultValue = "0") int from,
	                                       @RequestParam(value = "limit", defaultValue = "20") int to,
	                                       @RequestParam(value = "by", defaultValue = "id") String by,
	                                       @RequestParam(value = "sort", defaultValue = "asc") String sort){
		
		Pagination page = new Pagination();
		/*page.setFrom(from);
		page.setLimit(to);
		page.setBy(by);
		page.setSort(sort);*/
		
		return couponService.findCouponByShopID(shop_id, page);
	}
	
	/**
	 * 卖家删除优惠券
	 * @return
	 */
	@ApiOperation(value = "删除优惠券", notes = "卖家删除指定优惠券")
	@DeleteMapping(value = {"/seller/coupons/{coupon_id}"})
	public String deleteCouponByID(@RequestHeader("shop_id") int shop_id,
	                               @PathVariable("coupon_id") int coupon_id){
		
		return couponService.deleteCouponByID(shop_id, coupon_id);
	}
	
	/**
	 * 买家查询已领取的优惠券
	 * @param customer_id
	 * @return
	 */
	@ApiOperation(value = "买家查找优惠券", notes = "买家查找拥有的优惠券信息")
	@GetMapping(value = {"/customer/coupons"})
	public List<CouponCustomerInfo> findCustomerCouponInfo(@RequestAttribute(value = "customer_id", required = false) int customer_id,
	                                                       @RequestParam(value = "from", defaultValue = "0") int from,
	                                                       @RequestParam(value = "limit", defaultValue = "20") int to,
	                                                       @RequestParam(value = "by", defaultValue = "id") String by,
	                                                       @RequestParam(value = "sort", defaultValue = "asc") String sort){
		
		Pagination page = new Pagination();
		/*page.setFrom(from);
		page.setLimit(to);
		page.setBy(by);
		page.setSort(sort);*/
		
		return couponService.findCustomerCouponInfo(customer_id, page);
	}
}
