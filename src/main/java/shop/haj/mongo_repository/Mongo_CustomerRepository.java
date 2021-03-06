/**
 * Create time
 */
package shop.haj.mongo_repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import shop.haj.entity.Customer;

/**
 * Created by Hao on .
 * description。
 * @since 1.9.0
 */
@Repository
public interface Mongo_CustomerRepository extends MongoRepository<Customer, String> {

    Customer findByIdAndOpenId(String id, String open_id);

    Customer findByOpenId(String open_id);


}
