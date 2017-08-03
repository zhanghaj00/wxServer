package shop.haj.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.haj.entity.GoodsDetail;
import shop.haj.entity.Pagination;
import shop.haj.service.GoodsDetailService;

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
@Service
@Transactional
public class GoodsDetailServiceImpl implements GoodsDetailService{
	@Override
	public List<GoodsDetail> findGoodsDetailByGoodsID(int goods_id, Pagination page) {
		return null;
	}

	@Override
	public List<GoodsDetail> addGoodsDetailList(List<GoodsDetail> goodsDetails, int goods_id) {
		return null;
	}

	@Override
	public int updateGoodsDetail(int goods_id, List<GoodsDetail> goodsDetails) {
		return 0;
	}

	@Override
	public int deleteGoodsDetail(int goods_id, int goodsdetail_id) {
		return 0;
	}
	
	/*private Logger logger = LogManager.getLogger(GoodsDetailServiceImpl.class);
	
	@Autowired
	private MongoGoodsDetailRepository mongoGoodsDetailRepository;
	
	@Autowired
	private CacheManage cacheManage;
	
	*//**
	 * 根据商品ID查找全部的商品详情
	 *
	 * @param goods_id
	 * @return
	 *//*
	@Override
	public List<GoodsDetail> findGoodsDetailByGoodsID(String goods_id, Pagination page) {
		return mongoGoodsDetailRepository.findByGoodsId(goods_id, page).getContent();
	}
	
	*//**
	 * 批量新增商品详情
	 *
	 * @param goodsDetails
	 * @return
	 *//*
	@Override
	public List<GoodsDetail> addGoodsDetailList(List<GoodsDetail> goodsDetails, String goods_id) {
		
		clearGoodsCache(goods_id);

		return mongoGoodsDetailRepository.addGoodsDetailList(goodsDetails, goods_id);
	}
	
	*//**
	 * 更新商品详情
	 *
	 * @param goodsDetails
	 * @return
	 *//*
	@Override
	public int updateGoodsDetail(String goods_id, List<GoodsDetail> goodsDetails) {
		
		clearGoodsCache(goods_id);
		
		return mongoGoodsDetailRepository.updateGoodsDetail(goodsDetails);
	}
	
	*//**
	 * 删除商品详情
	 *
	 * @param goodsdetail_id
	 * @return
	 *//*
	@Override
	public int deleteGoodsDetail(String goods_id, String goodsdetail_id) {
		
		clearGoodsCache(goods_id);
		
		return mongoGoodsDetailRepository.deleteGoodsDetail(goodsdetail_id);
	}
	
	*//**
	 * 清除商品缓存
	 * @param goods_id
	 *//*
	private void clearGoodsCache(String goods_id){
		
		cacheManage.clearGoodsCache(String.valueOf(goods_id));
	}*/
}
