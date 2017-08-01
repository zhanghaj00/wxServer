package shop.haj.service;

import shop.haj.entity.Customer;
import shop.haj.entity.Pagination;

import java.util.List;

/**
 * <p>Title: shop.ha.service</p>
 * <p/>
 * <p>
 * Description: 买家操作Service
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/1/17
 */
public interface CustomerService {
	
	/**
	 * 查找所有买家信息
	 * @return
	 */
	List<Customer> findAll(Pagination page);
	
	/**
	 * 根据ID查找买家信息
	 * @param id
	 * @return
	 */
	Customer findById(String id);
	
	/**
	 * 根据open_id查找用户
	 * @param open_id
	 * @return
	 */
	Customer findByOpenID(String open_id);
	
	/**
	 * 根据ID和OpenID查找用户信息
	 * @param id
	 * @param open_id
	 * @return
	 */
	Customer findByIdAndOpenId(String id, String open_id);
	
	/**
	 * 新增买家信息
	 * @param customer
	 * @return
	 */
	Customer add(Customer customer);
	
	/**
	 * 更新买家信息
	 * @param customer
	 */
	Customer update(Customer customer);
	
	/**
	 * 删除买家信息
	 * @param id
	 */
	int delete(String id);
	
}
