/**
 * Create time
 */
package shop.haj.mongo_repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import shop.haj.entity.VisitShop;

import java.util.List;

/**
 * Created by Hao on .
 * description。
 * @since 1.9.0
 */
@Repository
public interface MongoVisitLogRepository extends MongoRepository<VisitShop,String> {

    List<VisitShop> findByShopId(String shopId);
}
