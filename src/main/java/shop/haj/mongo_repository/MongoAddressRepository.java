package shop.haj.mongo_repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shop.haj.entity.Address;
import shop.haj.entity.Pagination;

import java.util.List;

/**
 * Created by 1 on 2017/8/3.
 */
public interface MongoAddressRepository extends MongoRepository<Address,String> {

    List<Address> findByCustomerId(String customer_id,Pagination page);

    Address findByCustomerIdAndIsDefault(String customer_id,int is_default);


}
