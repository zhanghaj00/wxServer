package shop.haj.service;

import shop.haj.entity.Pagination;
import shop.haj.entity.Seller;

import java.util.List;

/**
 * <p>Title: shop.ha.service</p>
 * <p/>
 * <p>
 * Description: 卖家操作Service
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/1/17
 */
public interface SellerService {
	
	/**
	 * 查找所有卖家信息
	 * @return
	 */
	List<Seller> findAll(Pagination page);
	
	/**
	 * 根据ID查找卖家信息
	 * @param id
	 * @return
	 */
	Seller findById(int id);
	
	/**
	 * 新增卖家信息
	 * @param seller
	 * @return
	 */
	Seller add(Seller seller);
	
	/**
	 * 更新卖家信息
	 * @param seller
	 */
	int update(Seller seller);
	
	/**
	 * 删除卖家信息
	 * @param id
	 */
	int delete(int id);
	
}
