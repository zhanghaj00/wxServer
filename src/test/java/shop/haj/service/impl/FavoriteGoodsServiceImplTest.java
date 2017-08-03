package shop.haj.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop.haj.entity.FavoriteGoods;
import shop.haj.service.FavoriteGoodsService;
import shop.haj.utils.DefaultPagination;

import java.util.List;

/**
 * <p>Title: shop.ha.service.impl</p>
 * <p/>
 * <p>
 * Description: 商品收藏测试用例
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/24/17
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FavoriteGoodsServiceImplTest {
	
	@Autowired
	private FavoriteGoodsService service;
	
	@Test
	public void findFavoriteGoodsByCustomer() throws Exception {
		String customer_id = "10";
		List<FavoriteGoods> favoriteGoodss = service.findFavoriteGoodsByCustomer(customer_id, DefaultPagination.getFavoriteGoods());
		
		for (FavoriteGoods favoriteGoods : favoriteGoodss) {
			System.out.println(favoriteGoods);
		}
	}
	
	@Test
	public void findFavoriteGoodsByCustomerShop() throws Exception {
		String customer_id = "";
		String shop_id = "";
		List<FavoriteGoods> favoriteGoodss = service.findFavoriteGoodsByCustomerShop(customer_id, shop_id, DefaultPagination.getFavoriteGoods());
		
		for (FavoriteGoods favoriteGoods : favoriteGoodss) {
			System.out.println(favoriteGoods);
		}
	}
	
	@Test
	public void addFavoriteGoods() throws Exception {
		
		FavoriteGoods favoriteGoods = new FavoriteGoods();
		favoriteGoods.setCustomerId("10");
		favoriteGoods.setShopId("10");
		favoriteGoods.setGoodsId("10");
		
		service.addFavoriteGoods(favoriteGoods);
	}
	
	@Test
	public void removeFavoriteGoods() throws Exception {
		service.removeFavoriteGoods("","");
	}
	
}