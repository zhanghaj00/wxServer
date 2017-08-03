package shop.haj.mongo_repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shop.haj.entity.GoodsCategory;

import java.util.List;

/**
 * Created by 1 on 2017/8/3.
 */
public interface MongoGoodsCategoryRepository extends MongoRepository<GoodsCategory,String> {

    List<GoodsCategory> findByShopId(String shopId);
}
