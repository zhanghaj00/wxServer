package shop.haj.mongo_repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shop.haj.entity.Order;

/**
 * Created by Hao on ${date}.
 * description。
 *
 * @since 1.9.0
 */
public interface MongoOrderRepository extends MongoRepository<Order,String> {

}
