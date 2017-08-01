package shop.haj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.haj.entity.Customer;
import shop.haj.entity.Pagination;
import shop.haj.mongo_repository.Mongo_CustomerRepository;
import shop.haj.repository.CustomerRepository;
import shop.haj.service.CustomerService;

import java.util.List;

/**
 * <p>Title: shop.ha.service.impl</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/3/17
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private Mongo_CustomerRepository mongp_customerRepository;

	@Override
	public List<Customer> findAll(Pagination page) {
		return customerRepository.findAll(page);
	}
	
	@Override
	public Customer findById(String id) {
		return mongp_customerRepository.findOne(id);
	}
	
	/**
	 * 根据open_id查找用户
	 *
	 * @param open_id
	 * @return
	 */
	@Override
	public Customer findByOpenID(String open_id) {
		return mongp_customerRepository.findByOpenId(open_id);
	}
	
	/**
	 * 根据ID和OpenID查找用户信息
	 *
	 * @param id
	 * @param open_id
	 * @return
	 */
	@Override
	public Customer findByIdAndOpenId(String id, String open_id) {
		return mongp_customerRepository.findByIdAndOpenId(id, open_id);
	}
	
	@Override
	public Customer add(Customer customer) {
		return mongp_customerRepository.save(customer);
	}
	
	@Override
	public Customer update(Customer customer) {
		return mongp_customerRepository.insert(customer);
	}
	
	@Override
	public int delete(String id) {
		mongp_customerRepository.delete(id);
		return 1;
	}
}
