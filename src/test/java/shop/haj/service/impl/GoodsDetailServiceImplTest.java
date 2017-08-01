package shop.haj.service.impl;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop.haj.entity.GoodsDetail;
import shop.haj.service.GoodsDetailService;
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
 *         CreateTime：3/21/17
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class GoodsDetailServiceImplTest {
	
	@Autowired
	private GoodsDetailService goodsDetailService;
	
	@Test
	public void findGoodsDetailByGoodsID() throws Exception {
		List<GoodsDetail> goodsDetails = goodsDetailService.findGoodsDetailByGoodsID(3, DefaultPagination.getGoodsDetail());
		System.out.println(goodsDetails);
	}
	
	@Test
	public void addGoodsDetailList() throws Exception {
		
		List<GoodsDetail> goodsDetails = Lists.newArrayList();
		
		GoodsDetail goodsDetail = new GoodsDetail();
		goodsDetail.setSequence(1);
		goodsDetail.setContent("烤鸭中的战斗鸭");
		goodsDetail.setType(1);
		
		GoodsDetail goodsDetail2 = new GoodsDetail();
		goodsDetail2.setSequence(2);
		goodsDetail2.setContent("http://ha.shop/img/aa.jpg");
		goodsDetail2.setType(2);
		
		GoodsDetail goodsDetail3 = new GoodsDetail();
		goodsDetail3.setSequence(3);
		goodsDetail3.setContent("http://www.qq.com");
		goodsDetail3.setType(3);
		
		goodsDetails.add(goodsDetail);
		goodsDetails.add(goodsDetail2);
		goodsDetails.add(goodsDetail3);
		
		goodsDetailService.addGoodsDetailList(goodsDetails, 5);
		
		System.out.println(goodsDetails);
	}
	
	@Test
	public void updateGoodsDetail() throws Exception {
		
		GoodsDetail goodsDetail = new GoodsDetail();
		goodsDetail.setId(4);
		goodsDetail.setSequence(11);
		goodsDetail.setContent("烤鸭中的超级战斗鸭");
		goodsDetail.setType(2);
		
		List<GoodsDetail> goodsDetails = Lists.newArrayList();
		goodsDetails.add(goodsDetail);
		
		int goods_id = 100;
		
		int result = goodsDetailService.updateGoodsDetail(goods_id, goodsDetails);
		System.out.println(result);
	}
	
	@Test
	public void deleteGoodsDetail() throws Exception {
		
		int goods_id = 100;
		
		int result = goodsDetailService.deleteGoodsDetail(goods_id, 3);
		System.out.println(result);
	}
	
}