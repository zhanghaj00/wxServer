package shop.haj.mongo_repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import shop.haj.entity.FavoriteGoods;

/**
 * Created by 1 on 2017/8/3.
 */
public interface MongoFavoriteGoodsRepository extends MongoRepository<FavoriteGoods,String> {
    Page<FavoriteGoods> findByCustomerId(String customerId,Pageable page);
    Page<FavoriteGoods> findByCustomerIdAndShopId(String customerId,String shopId,Pageable page);
    void deleteByCustomerIdAndGoodsId(String customerId,String shopId);
}
