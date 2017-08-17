package shop.haj.mongo_repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import shop.haj.entity.Order;

import java.util.List;

/**
 * Created by Hao on ${date}.
 * description。
 *
 * @since 1.9.0
 */
public interface MongoOrderRepository extends MongoRepository<Order,String> {
    Order findByUuid(String uuid);
    List<Order> findByShopId(String shopId);
    Page<Order> findByShopId(String shopId,Pageable page);
}
