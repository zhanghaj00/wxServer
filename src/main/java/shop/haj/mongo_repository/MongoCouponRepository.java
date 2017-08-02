package shop.haj.mongo_repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shop.haj.entity.Coupon;

/**
 * Created by 1 on 2017/8/3.
 */
public interface MongoCouponRepository extends MongoRepository<Coupon,String> {
}
