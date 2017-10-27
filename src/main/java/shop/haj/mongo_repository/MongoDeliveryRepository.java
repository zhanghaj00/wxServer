/**
 * Create time
 */
package shop.haj.mongo_repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shop.haj.entity.Delivery;

import java.util.List;

/**
 * Created by Hao on .
 * description。
 * @since 1.9.0
 */
public interface MongoDeliveryRepository  extends MongoRepository<Delivery,String>{

    List<Delivery> findBySupplierId(String supplierId);
}
