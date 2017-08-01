package shop.haj.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop.haj.entity.Cart;
import shop.haj.service.ShopCartService;
import shop.haj.utils.DefaultPagination;

import java.util.List;

/**
 * <p>Title: shop.ha.service.impl</p>
 * <p/>
 * <p>
 * Description: 购物车Service测试类
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/21/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopCartServiceImplTest {
	
	@Autowired
	private ShopCartService shopCartService;
	
	@Test
	public void findShopCarts() throws Exception {
		List<Cart> carts = shopCartService.findShopCarts(1, 4, DefaultPagination.getCart());
		for (Cart cart : carts) {
			System.out.println(cart);
		}
	}
	
	@Test
	public void addCart() throws Exception {
		Cart cart = new Cart();
		cart.setCustomer_id(1);
		cart.setShop_id(3);
		cart.setShop_name("hello");
		cart.setGoods_id(1);
		cart.setGoods_name("test");
		cart.setGoods_price(12.22);
		cart.setGoods_num(12);
		cart.setGoods_image("http");
		cart.setGoods_sku("http");
		cart.setNote("test");
		
		int result = shopCartService.addCart(cart);
		System.out.println("result = " + result);
	}
	
	@Test
	public void updateCartNum() throws Exception {
		Assert.assertEquals(1, shopCartService.updateCartNum(1, 3,
				4, 100));
	}
	
	@Test
	public void removeGoodsFromCart() throws Exception {
		Assert.assertEquals(1, shopCartService.removeGoodsFromCart(1, 3,4));
	}
	
	@Test
	public void clearShopCart() throws Exception {
		Assert.assertEquals(1, shopCartService.clearShopCart(1, 3));
	}
	
}