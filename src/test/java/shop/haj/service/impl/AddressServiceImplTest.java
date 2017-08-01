package shop.haj.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop.haj.entity.Address;
import shop.haj.service.AddressService;
import shop.haj.utils.DefaultPagination;

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
 *         CreateTime：3/22/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceImplTest {
	
	@Autowired
	private AddressService addressService;
	
	@Test
	public void addAddress() throws Exception {
		
	}
	
	@Test
	public void findAddressListByCustomerID() throws Exception {
		
		List<Address> addressList = addressService.findAddressListByCustomerID(1, DefaultPagination.getAddress());
		
		System.out.println(addressList);
	}
	
	@Test
	public void findAddressByID() throws Exception {
		
		Address address = addressService.findAddressByID(2);
		
		System.out.println(address);
	}
	
	@Test
	public void updateAddress() throws Exception {
		
	}
	
	@Test
	public void deleteAddress() throws Exception {
		
		addressService.deleteAddress(2);
		
	}
	
}