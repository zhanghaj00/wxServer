package shop.haj.service;

import shop.haj.entity.GoodsDetail;
import shop.haj.entity.Pagination;

import java.util.List;

/**
 * <p>Title: shop.ha.service</p>
 * <p/>
 * <p>
 * Description: 订单详情
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/21/17
 */
public interface GoodsDetailService {
	
	/**
	 * 根据商品ID查找全部的商品详情
	 * @param goods_id
	 * @return
	 */
	List<GoodsDetail> findGoodsDetailByGoodsID(int goods_id, Pagination page);
	
	/**
	 * 批量新增商品详情
	 * @param goodsDetails
	 * @return
	 */
	List<GoodsDetail> addGoodsDetailList(List<GoodsDetail> goodsDetails, int goods_id);
	
	/**
	 * 更新商品详情
	 * @param goodsDetails
	 * @return
	 */
	int updateGoodsDetail(int goods_id, List<GoodsDetail> goodsDetails);
	
	/**
	 * 删除商品详情
	 * @param goodsdetail_id
	 * @return
	 */
	int deleteGoodsDetail(int goods_id, int goodsdetail_id);
}
