/**
 * Create time
 */
package shop.haj.mongo_repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shop.haj.entity.Comment;

/**
 * Created by Hao on .
 * description。
 * @since 1.9.0
 */
public interface MongoCommentRpository extends MongoRepository<Comment,String> {
}
