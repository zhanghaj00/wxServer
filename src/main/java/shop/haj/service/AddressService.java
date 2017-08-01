package shop.haj.service;

import shop.haj.entity.Address;
import shop.haj.entity.Pagination;

import java.util.List;

/**
 * <p>Title: shop.ha.service</p>
 * <p/>
 * <p>
 * Description: AddressService
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/21/17
 */
public interface AddressService {
	
	/**
	 * 新增地址
	 * @param address
	 * @return
	 */
	Address addAddress(Address address);
	
	/**
	 * 查找买家的全部地址列表
	 * @param customer_id
	 * @return
	 */
	List<Address> findAddressListByCustomerID(int customer_id, Pagination page);
	
	/**
	 * 根据地址ID查找
	 * @param address_id
	 * @return
	 */
	Address findAddressByID(int address_id);
	
	/**
	 * 查找用户默认地址
	 * @param customer_id
	 * @return
	 */
	Address findDefaultAddress(int customer_id);
	
	/**
	 * 更新地址信息
	 * @param address
	 * @return
	 */
	int updateAddress(Address address);
	
	/**
	 * 设置默认地址
	 *      1. 设置当前地址为默认地址
	 *      2. 设置其他地址为非默认地址
	 *
	 * @param customer_id
	 * @param address_id
	 * @return
	 */
	int setDefaultAddress(int customer_id, int address_id);
	
	/**
	 * 删除某个地址信息
	 * @param address_id
	 * @return
	 */
	int deleteAddress(int address_id);
	
}
