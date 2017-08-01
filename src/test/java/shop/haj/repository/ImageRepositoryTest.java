package shop.haj.repository;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Title: shop.ha.repository</p>
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
public class ImageRepositoryTest {
	
	/*@Autowired
	private ImageRepository imageRepository;
	
	@Test
	public void addImage() throws Exception {
		
		Image image = new Image();
		image.setUrl("http://115.28.93.210/img/cc.jpg");
		
		imageRepository.addImage(image);
		
		System.out.println(image);
	}
	
	@Test
	public void findImageByID() throws Exception {
		Image image = imageRepository.findImageByID(9);
		System.out.println(image);
	}
	
	@Test
	public void deleteImage(){
		Assert.assertEquals(1, imageRepository.deleteImage(9));
	}
	
	@Test
	public void findGoodsImagesByGoodsID() throws Exception {
		List<Image> imageList = imageRepository.findGoodsImagesByGoodsID(100, DefaultPagination.getImageGoods());
		
		for (Image image : imageList) {
			System.out.println(image);
		}
	}
	
	@Test
	public void addGoodsImage() throws Exception {
		
		Image image = new Image();
		image.setId(10);
		image.setUrl("http://115.28.93.210/img/cc.jpg");
		
		imageRepository.addGoodsImage(image, 100);
		
		System.out.println(image);
	}
	
	@Test
	public void deleteSingleGoodsImageByID() throws Exception {
		Assert.assertEquals(1, imageRepository.deleteSingleGoodsImageByID(10, 100));
	}
	
	@Test
	public void deleteAllGoodsImage() throws Exception {
		Assert.assertEquals(1, imageRepository.deleteAllGoodsImage(100));
	}
	
	@Test
	public void findShopImage() throws Exception {
		List<Image> images = imageRepository.findShopImage(2);
		
		for (Image image : images) {
			System.out.println(image);
		}
	}
	
	@Test
	public void addShopImage() throws Exception {
		Image image = new Image();
		image.setId(10);
		image.setUrl("http://115.28.93.210/img/cc.jpg");
		
		List<Image> images = Lists.newArrayList();
		images.add(image);
		
		imageRepository.addShopImage(images, 2);
		
		System.out.println(image);
	}
	
	@Test
	public void deleteSingleShopImage() throws Exception {
		Assert.assertEquals(1, imageRepository.deleteSingleShopImage(10, 2));
	}
	
	@Test
	public void deleteAllShopImage() throws Exception {
		Assert.assertEquals(1, imageRepository.deleteAllShopImage(2));
	}
	*/
}