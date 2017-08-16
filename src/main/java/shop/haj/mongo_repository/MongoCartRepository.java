package shop.haj.mongo_repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shop.haj.entity.Cart;

import java.util.List;

/**
 * Created by 1 on 2017/8/3.
 */
public interface MongoCartRepository extends MongoRepository<Cart,String> {

    List<Cart> findByCustomerIdAndShopId(String customeId,String shopId);
}
