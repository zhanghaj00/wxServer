package shop.haj.mongo_repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import shop.haj.entity.OrderListSingleInfo;

/**
 * Created by Hao on ${date}.
 * description。
 *
 * @since 1.9.0
 */
public interface MongoOrderListRepository extends MongoRepository<OrderListSingleInfo,String> {
    Page<OrderListSingleInfo> findByShopIdAndCustomerIdAndStatus(String shopId,String customerId,int status ,Pageable pagination);
}
