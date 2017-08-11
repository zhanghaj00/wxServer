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
		if( address.getIsDefault() == 1){
			if (StringUtils.isEmpty(address.getCustomerId())) return null;
			Address address_old = mongoAddressRepository.findByCustomerIdAndIsDefault(address.getCustomerId(),1);
			address_old.setIsDefault(0);
			mongoAddressRepository.save(address_old);
			address = mongoAddressRepository.insert(address);
		}else{
			address = mongoAddressRepository.insert(address);
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
		//Address address1 = mongoAddressRepository.findOne(address.getId());
		//address1.setCity(address.getCity());

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

		if (StringUtils.isEmpty(customer_id)) return 0;
		Address address = mongoAddressRepository.findByCustomerIdAndIsDefault(customer_id,1);
		address.setIsDefault(0);
		mongoAddressRepository.save(address);

		Address address1 = mongoAddressRepository.findOne(address_id);
		address1.setIsDefault(1);
		mongoAddressRepository.save(address1);
		
		return 1;
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




	private int setUndefaultAddress(String customer_id,String address_id){
		if(StringUtils.isEmpty(customer_id)) return 0;
		Address address = new Address();
		//TODO
		return 1;
	}
	
}
