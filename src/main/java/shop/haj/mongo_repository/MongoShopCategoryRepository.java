package shop.haj.mongo_repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shop.haj.entity.ShopCategory;

import java.util.List;

/**
 * Created by 1 on 2017/8/1.
 */
public interface MongoShopCategoryRepository  extends MongoRepository<ShopCategory,String>{

    List<ShopCategory> findByPid(String pid);
}
