package shop.haj.mongo_repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shop.haj.entity.Notice;

/**
 * Created by 1 on 2017/8/3.
 */
public interface MongoNoticeRepository extends MongoRepository<Notice,String> {
}
