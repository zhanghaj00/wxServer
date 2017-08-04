package shop.haj.mongo_repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import shop.haj.entity.GoodsDetail;

/**
 * Created by Hao on ${date}.
 * description。
 *
 * @since 1.9.0
 */
public interface MongoGoodsDetailRepository {//extends MongoRepository<GoodsDetail,String> {
   // Page<GoodsDetail> findByGoodsId(String goodId,Pageable page);
}
