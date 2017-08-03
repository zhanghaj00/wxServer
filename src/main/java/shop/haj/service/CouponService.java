package shop.haj.service;

import shop.haj.entity.Coupon;
import shop.haj.entity.CouponCustomerInfo;
import shop.haj.entity.Pagination;

import java.util.List;

/**
 * <p>Title: shop.ha.service</p>
 * <p/>
 * <p>
 * Description: 优惠券处理信息Service接口
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/23/17
 */
public interface CouponService {
	
	/**
	 * 卖家新增优惠券
	 * @param coupon
	 * @return
	 */
	Coupon addCoupon(Coupon coupon);
	
	/**
	 * 卖家查询优惠券
	 * @param shop_id
	 * @return
	 */
	List<Coupon> findCouponByShopID(String shop_id, Pagination page);
	
	/**
	 * 卖家删除优惠券
	 * @return
	 */
	String deleteCouponByID(String shop_id, String coupon_id);
	
	/**
	 * 买家查询已领取的优惠券
	 * @param customer_id
	 * @return
	 */
	List<CouponCustomerInfo> findCustomerCouponInfo(String customer_id, Pagination page);
	
}
