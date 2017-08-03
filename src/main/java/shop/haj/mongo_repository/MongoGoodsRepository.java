/**
 * Create time
 */
package shop.haj.mongo_repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import shop.haj.entity.Goods;

/**
 * Created by Hao on .
 * description。
 * @since 1.9.0
 */
public interface MongoGoodsRepository extends PagingAndSortingRepository<Goods,String>,MongoRepository<Goods,String> {

    Goods findByIdAndShopId(String shop_id, String goods_id);

    Goods findByShopIdAndUuid(String shop_id, String uuid);
}
