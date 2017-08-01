package shop.haj.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.haj.entity.Coupon;
import shop.haj.entity.CouponCustomerInfo;
import shop.haj.entity.Pagination;
import shop.haj.service.CouponService;

import java.util.List;

/**
 * <p>Title: shop.ha.service.impl</p>
 * <p/>
 * <p>
 * Description: CouponServiceImpl
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/23/17
 */
@Service
@Transactional
public class CouponServiceImpl implements CouponService{
	
	
	/**
	 * 卖家新增优惠券
	 *
	 * @param coupon
	 * @return
	 */
	@Override
	public Coupon addCoupon(Coupon coupon) {
		
		//新增Coupon表信息
		
		//判断是否优惠券适用范围为部分商品，如果是则新增coupon_goods_rel表信息
		
		return null;
	}
	
	/**
	 * 卖家查询优惠券
	 *
	 * @param shop_id
	 * @return
	 */
	@Override
	public List<Coupon> findCouponByShopID(int shop_id, Pagination page) {
		
		//查询该店铺全部的优惠券信息
		
		//检查每个优惠券，如果有适用部分商品的情况，查询商品ID集合，然后更新到优惠券实体
		
		return null;
	}
	
	/**
	 * 卖家删除优惠券
	 *
	 * @param shop_id
	 * @param coupon_id
	 * @return
	 */
	@Override
	public String deleteCouponByID(int shop_id, int coupon_id) {
		
		//先判断是否部分商品适用，如果有则先删除coupon_goods_rel表记录
		
		//删除coupon表记录
		
		return null;
	}
	
	/**
	 * 买家查询已领取的优惠券
	 *
	 * @param customer_id
	 * @return
	 */
	@Override
	public List<CouponCustomerInfo> findCustomerCouponInfo(int customer_id, Pagination page) {
		
		//查询coupon_customer_info表信息
		
		return null;
	}
}
