package shop.haj.service;

import shop.haj.entity.Pagination;
import shop.haj.entity.Shop;

import java.util.List;

/**
 * <p>Title: shop.ha.service</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/4/17
 */
public interface VisitLogService {
	
	/**
	 * 查找买家的店铺访问记录
	 * @param id
	 * @param page
	 * @return
	 */
	List<Shop> findVisitShopsByCusomterID(int id, Pagination page);
	
	/**
	 * 新增访问记录
	 * @param customer_id
	 * @param shop_id
	 * @return
	 */
	int addVisitShopLog(int customer_id, int shop_id);
	
}
