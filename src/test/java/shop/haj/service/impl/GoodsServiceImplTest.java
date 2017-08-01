package shop.haj.service.impl;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop.haj.entity.*;
import shop.haj.service.GoodsService;

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
 *         CreateTime：3/19/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsServiceImplTest {
	
	@Autowired
	private GoodsService goodsService;
	
	@Test
	public void findAll() throws Exception {
		
		Pagination page = new Pagination();
		/*page.setFrom(0);
		page.setLimit(10);
		page.setBy("id");
		page.setSort("desc");*/
		
		List<Goods> goodsList = goodsService.findAll(3, page);
		
		System.out.println(goodsList);
	}
	
	@Test
	public void findGoodsByID(){
		
		Goods goods = goodsService.findGoodsByID(3, 276552);
		System.out.println(goods);
	}
	
	@Test
	public void addGoods(){
		
		int shop_id = 3;
		
		for (int i = 100; i < 101; i++) {
			Goods goods = new Goods();
			
			goods.setName("测试商品" + i);
			goods.setStatus(1);
			goods.setOriginal_price(99.99);
			goods.setSell_price(69.99);
			goods.setStock(99);
			goods.setSales_volume(1);
			goods.setInner_cid(2);
			goods.setGlobal_cid(2);
			goods.setIs_recommend(0);
			
			List<Image> images = Lists.newArrayList();
			
			Image image1 = new Image();
			image1.setId("");
			image1.setUrl("/img/aa.jpg");
			
			Image image2 = new Image();
			image2.setId("");
			image2.setUrl("/img/bb.jpg");
			
			Image image3 = new Image();
			image3.setId("");
			image3.setUrl("/img/cc.jpg");
			
			images.add(image1);
			images.add(image2);
			images.add(image3);
			
			goods.setImages(images);
			
			goods.setShop_id(shop_id);
			
			GoodsSkuDetailBase skuDetailBase = new GoodsSkuDetailBase();
			skuDetailBase.setPrice(100.11);
			skuDetailBase.setStock(1000000);
			
			List<GoodsSkuDetail> skuDetails = Lists.newArrayList();
			
			GoodsSkuDetail skuDetail = new GoodsSkuDetail();
			skuDetail.setSku("A1");
			skuDetail.setGoodsSkuDetailBase(skuDetailBase);
			
			GoodsSkuDetail skuDetail2 = new GoodsSkuDetail();
			skuDetail2.setSku("A2");
			skuDetail2.setGoodsSkuDetailBase(skuDetailBase);
			
			GoodsSkuDetail skuDetail3 = new GoodsSkuDetail();
			skuDetail3.setSku("A3");
			skuDetail3.setGoodsSkuDetailBase(skuDetailBase);
			
			skuDetails.add(skuDetail);
			skuDetails.add(skuDetail2);
			skuDetails.add(skuDetail3);
			
			GoodsSkuInfo skuInfo = new GoodsSkuInfo();
			skuInfo.setProp1("A");
			skuInfo.setValue1("A1,A2,A3");
			skuInfo.setGoodsSkuDetails(skuDetails);
			
			goods.setGoodsSkuInfo(skuInfo);
			
			goodsService.addGoods(goods);
		}
	}
	
	private Goods getGoods(int i, int shop_id){
		Goods goods = new Goods();
		
		goods.setName("测试商品" + i);
		goods.setStatus(1);
		goods.setOriginal_price(99.99);
		goods.setSell_price(69.99);
		goods.setStock(99);
		goods.setSales_volume(1);
		goods.setInner_cid(2);
		goods.setGlobal_cid(2);
		goods.setIs_recommend(0);
		
		List<Image> images = Lists.newArrayList();
		
		Image image1 = new Image();
		image1.setId("");
		image1.setUrl("/img/aa.jpg");
		
		Image image2 = new Image();
		image2.setId("");
		image2.setUrl("/img/bb.jpg");
		
		Image image3 = new Image();
		image3.setId("");
		image3.setUrl("/img/cc.jpg");
		
		images.add(image1);
		images.add(image2);
		images.add(image3);
		
		goods.setImages(images);
		
		goods.setShop_id(shop_id);
		
		GoodsSkuDetailBase skuDetailBase = new GoodsSkuDetailBase();
		skuDetailBase.setPrice(100.11);
		skuDetailBase.setStock(1000000);
		
		List<GoodsSkuDetail> skuDetails = Lists.newArrayList();
		
		GoodsSkuDetail skuDetail = new GoodsSkuDetail();
		skuDetail.setSku("A1");
		skuDetail.setGoodsSkuDetailBase(skuDetailBase);
		
		GoodsSkuDetail skuDetail2 = new GoodsSkuDetail();
		skuDetail2.setSku("A2");
		skuDetail2.setGoodsSkuDetailBase(skuDetailBase);
		
		GoodsSkuDetail skuDetail3 = new GoodsSkuDetail();
		skuDetail3.setSku("A3");
		skuDetail3.setGoodsSkuDetailBase(skuDetailBase);
		
		skuDetails.add(skuDetail);
		skuDetails.add(skuDetail2);
		skuDetails.add(skuDetail3);
		
		GoodsSkuInfo skuInfo = new GoodsSkuInfo();
		skuInfo.setProp1("A");
		skuInfo.setValue1("A1,A2,A3");
		skuInfo.setGoodsSkuDetails(skuDetails);
		
		goods.setGoodsSkuInfo(skuInfo);
		
		return goods;
	}
	
	@Test
	public void updateGoods(){
		Goods goods = getGoods(1000, 3);
		goods.setId(276552);
		goodsService.updateGoods(goods);
		
	}
	
	@Test
	public void deleteGoods(){
		
		goodsService.deleteGoods(3, 276552);
	}
}