/**
 * Create time
 */
package shop.haj.mongo_repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shop.haj.entity.Image;

import java.util.List;

/**
 * Created by Hao on .
 * description。
 * @since 1.9.0
 */
public interface MongoImageRepository extends MongoRepository<Image, String> {
    List<Image> findByShopId(String shopId);

    int deleteByIdAndShopId(String id,String shopId);

}
