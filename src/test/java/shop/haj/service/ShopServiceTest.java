package shop.haj.service;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Title: shop.ha.service</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：4/11/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopServiceTest {
	
	/*@Autowired
	private ShopService shopService;
	
	
	
	@Test
	public void findAll() throws Exception {
		
		List<Shop> shops = shopService.findAll(DefaultPagination.getShop());
		for (Shop shop : shops) {
			System.out.println(shop);
		}
	}
	
	@Test
	public void findById() throws Exception {
		Shop shop = shopService.findById(6);
		System.out.println(shop);
	}
	
	@Test
	public void addShop() throws Exception {
		Shop shop = new Shop();
		shop.setName("王老师的花店");
		shop.setCategory_id(2);
		shop.setDescribe("朵朵鲜，日日艳!");
		shop.setAddress("乌鲁木齐大户尔汗路");
		shop.setPhone("77777777");
		
		shopService.addShop(shop, 1);
		
		System.out.println(shop);
	}
	
	@Test
	public void updateShop() throws Exception {
		
		Shop shop = new Shop();
		shop.setName("王老师的花店");
		shop.setCategory_id(2);
		shop.setDescribe("朵朵艳，日日鲜!");
		shop.setAddress("乌鲁木齐大户尔汗路");
		shop.setPhone("77777777");
		
		shopService.updateShop(shop);
	}
	
	@Test
	public void addShopImage() throws Exception {
		Image image = new Image();
		image.setId(10);
		image.setUrl("http://115.28.93.210/img/cc.jpg");
		
		List<Image> images = Lists.newArrayList();
		images.add(image);
		
		shopService.addShopImage(images, 6);
	}
	
	@Test
	public void deleteShopImage() throws Exception {
		shopService.deleteShopImage(10, 6);
	}
	*/
}