/**
 * Create time
 */
package shop.haj.service;

import shop.haj.entity.Comment;
import shop.haj.entity.Pagination;

import java.util.List;

/**
 * Created by Hao on .
 * description。
 * @since 1.9.0
 */
public interface CommentService {
    public List<Comment> pageComment(Comment comment,Pagination pagination);
    public Comment insertComment(Comment comment);

    public List<Comment> countComment(Comment comment);
}
