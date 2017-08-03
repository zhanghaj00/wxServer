package shop.haj.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.haj.entity.GoodsDetail;
import shop.haj.entity.Pagination;
import shop.haj.manage.CacheManage;
import shop.haj.repository.GoodsDetailRepository;
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
	
	private Logger logger = LogManager.getLogger(GoodsDetailServiceImpl.class);
	
	@Autowired
	private GoodsDetailRepository goodsDetailRepository;
	
	@Autowired
	private CacheManage cacheManage;
	
	/**
	 * 根据商品ID查找全部的商品详情
	 *
	 * @param goods_id
	 * @return
	 */
	@Override
	public List<GoodsDetail> findGoodsDetailByGoodsID(int goods_id, Pagination page) {
		return goodsDetailRepository.findGoodsDetailByGoodsID(goods_id, page);
	}
	
	/**
	 * 批量新增商品详情
	 *
	 * @param goodsDetails
	 * @return
	 */
	@Override
	public List<GoodsDetail> addGoodsDetailList(List<GoodsDetail> goodsDetails, int goods_id) {
		
		clearGoodsCache(goods_id);
		
		return goodsDetailRepository.addGoodsDetailList(goodsDetails, goods_id);
	}
	
	/**
	 * 更新商品详情
	 *
	 * @param goodsDetails
	 * @return
	 */
	@Override
	public int updateGoodsDetail(int goods_id, List<GoodsDetail> goodsDetails) {
		
		clearGoodsCache(goods_id);
		
		return goodsDetailRepository.updateGoodsDetail(goodsDetails);
	}
	
	/**
	 * 删除商品详情
	 *
	 * @param goodsdetail_id
	 * @return
	 */
	@Override
	public int deleteGoodsDetail(int goods_id, int goodsdetail_id) {
		
		clearGoodsCache(goods_id);
		
		return goodsDetailRepository.deleteGoodsDetail(goodsdetail_id);
	}
	
	/**
	 * 清除商品缓存
	 * @param goods_id
	 */
	private void clearGoodsCache(int goods_id){
		
		cacheManage.clearGoodsCache(String.valueOf(goods_id));
	}
}
