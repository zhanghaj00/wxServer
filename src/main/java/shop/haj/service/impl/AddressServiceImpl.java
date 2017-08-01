package shop.haj.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.haj.entity.Address;
import shop.haj.entity.Pagination;
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
	private AddressRepository addressRepository;
	
	/**
	 * 新增地址
	 *
	 * @param address
	 * @return
	 */
	@Override
	public Address addAddress(Address address) {
		
		addressRepository.addAddress(address);
		
		if(address.getIs_default() == 1){
			addressRepository.setUndefaultAddress(address.getCustomer_id(), address.getId());
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
	public List<Address> findAddressListByCustomerID(int customer_id, Pagination page) {
		
		return addressRepository.findAddressListByCustomerID(customer_id, page);
	}
	
	/**
	 * 根据地址ID查找
	 *
	 * @param address_id
	 * @return
	 */
	@Override
	public Address findAddressByID(int address_id) {
		
		return addressRepository.findAddressByID(address_id);
	}
	
	/**
	 * 查找用户默认地址
	 *
	 * @param customer_id
	 * @return
	 */
	@Override
	public Address findDefaultAddress(int customer_id) {
		
		Address address = addressRepository.findDefaultAddress(customer_id);
		
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
	public int updateAddress(Address address) {
		
		return addressRepository.updateAddress(address);
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
	public int setDefaultAddress(int customer_id, int address_id) {
		
		logger.info("setDefaultAddress service >>> customer_id={}, address_id={}", customer_id, address_id);
		
		addressRepository.setDefaultAddress(address_id);
		
		return addressRepository.setUndefaultAddress(customer_id, address_id);
	}
	
	/**
	 * 删除某个地址信息
	 *
	 * @param address_id
	 * @return
	 */
	@Override
	public int deleteAddress(int address_id) {
		
		return addressRepository.deleteAddress(address_id);
	}
	
	
}
