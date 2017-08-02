package shop.haj.mongo_repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shop.haj.entity.FavoriteGoods;

/**
 * Created by 1 on 2017/8/3.
 */
public interface MongoFavoriteGoodsRepository extends MongoRepository<FavoriteGoods,String> {
}
