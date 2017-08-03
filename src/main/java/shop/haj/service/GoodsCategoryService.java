package shop.haj.service;

import shop.haj.entity.GoodsCategory;

import java.util.List;

/**
 * Created by 1 on 2017/8/3.
 */
public interface GoodsCategoryService {

    public List<GoodsCategory> findByShopId(String shopId);

    public GoodsCategory addGoodsCategory(GoodsCategory goodsCategory);
}
