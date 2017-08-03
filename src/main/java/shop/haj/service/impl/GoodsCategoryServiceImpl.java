package shop.haj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.haj.entity.GoodsCategory;
import shop.haj.mongo_repository.MongoGoodsCategoryRepository;
import shop.haj.service.GoodsCategoryService;

import java.util.List;

/**
 * Created by 1 on 2017/8/3.
 */

@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService{


    @Autowired
    private MongoGoodsCategoryRepository mongoGoodsCategoryRepository;


    @Override
    public List<GoodsCategory> findByShopId(String shopId) {
        return mongoGoodsCategoryRepository.findByShopId(shopId);
    }

    @Override
    public GoodsCategory addGoodsCategory(GoodsCategory goodsCategory) {
        return mongoGoodsCategoryRepository.insert(goodsCategory);
    }
}
