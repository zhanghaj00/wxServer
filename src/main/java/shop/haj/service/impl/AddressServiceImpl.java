package shop.haj.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.haj.entity.Address;
import shop.haj.entity.Pagination;
import shop.haj.mongo_repository.MongoAddressRepository;
import shop.haj.repository.AddressRepository;
import shop.haj.service.AddressService;

import java.util.List;

/**
 * <p>Title: shop.ha.service.impl</p>
 * <p/>
 * <p>
 * Description: AddressServiceImpl
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/21/17
 */
@Service
@Transactional
public class AddressServiceImpl implements AddressService {
	
	private Logger logger = LogManager.getLogger(AddressServiceImpl.class);
	
	@Autowired
	private MongoAddressRepository mongoAddressRepository;
	
	/**
	 * 新增地址
	 *
	 * @param address
	 * @return
	 */
	@Override
	public Address addAddress(Address address) {

		address = mongoAddressRepository.insert(address);
		
		if(address.getIsDefault() == 1){
			this.setUndefaultAddress(address.getCustomerId(), address.getId());
		}
		
		return address;
	}
	
	/**
	 * 查找买家的全部地址列表
	 *
	 * @param customer_id
	 * @return
	 */
	@Override
	public List<Address> findAddressListByCustomerID(String customer_id, Pagination page) {
		
		return mongoAddressRepository.findByCustomerId(customer_id, page);
	}
	
	/**
	 * 根据地址ID查找
	 *
	 * @param address_id
	 * @return
	 */
	@Override
	public Address findAddressByID(String address_id) {
		
		return mongoAddressRepository.findOne(address_id);
	}
	
	/**
	 * 查找用户默认地址
	 *
	 * @param customer_id
	 * @return
	 */
	@Override
	public Address findDefaultAddress(String customer_id) {
		
		Address address = mongoAddressRepository.findByCustomerIdAndIsDefault(customer_id,1);
		
		logger.info("findDefaultAddress service >>> custoemr_id={} result=({})", customer_id, address);
		
		return address;
	}
	
	/**
	 * 更新地址信息
	 *
	 * @param address
	 * @return
	 */
	@Override
	public Address updateAddress(Address address) {
		
		return mongoAddressRepository.save(address);
	}
	
	/**
	 * 设置默认地址
	 *      1. 设置当前地址为默认地址
	 *      2. 设置其他地址为非默认地址
	 * @param customer_id
	 * @param address_id
	 * @return
	 */
	@Override
	public int setDefaultAddress(String customer_id, String address_id) {
		
		logger.info("setDefaultAddress service >>> customer_id={}, address_id={}", customer_id, address_id);

		this.setDefaultAddress(address_id);
		
		return this.setUndefaultAddress(customer_id, address_id);
	}
	
	/**
	 * 删除某个地址信息
	 *
	 * @param address_id
	 * @return
	 */
	@Override
	public int deleteAddress(String address_id) {
		mongoAddressRepository.delete(address_id);
		return 1;

	}


	private void setDefaultAddress(String address_id){
		if (StringUtils.isEmpty(address_id)) return;
		Address address = new Address();
		address.setId(address_id);
		address.setIsDefault(1);
		mongoAddressRepository.save(address);

	}

	private int setUndefaultAddress(String customer_id,String address_id){
		if(StringUtils.isEmpty(customer_id)) return 0;
		Address address = new Address();
		//TODO
		return 1;
	}
	
}
