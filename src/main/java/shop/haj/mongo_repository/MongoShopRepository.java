/**
 * Create time
 */
package shop.haj.mongo_repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shop.haj.entity.Shop;

import java.util.List;

/**
 * Created by Hao on .
 * description。
 * @since 1.9.0
 */
public interface MongoShopRepository extends MongoRepository<Shop, String> {

    Shop findShopById(String id);

    List<Shop> findBySellerId(String sellerId);
}
