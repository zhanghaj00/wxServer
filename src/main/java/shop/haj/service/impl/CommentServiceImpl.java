/**
 * Create time
 */
package shop.haj.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import shop.haj.entity.Comment;
import shop.haj.entity.Pagination;
import shop.haj.mongo_repository.MongoCommentRpository;
import shop.haj.service.CommentService;
import shop.haj.service.CustomerService;

import java.util.List;

/**
 * Created by Hao on .
 * description。
 * @since 1.9.0
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private MongoCommentRpository mongoCommentRpository;

    @Autowired
    private CustomerService customerService;

    @Override
    public List<Comment> pageComment(Comment comment,Pagination pagination){
        Example<Comment> condition = Example.of(comment);
        List<Comment> result = mongoCommentRpository.findAll(condition, pagination.getRequest()).getContent();

        for(Comment c:result){
            if(!StringUtils.isEmpty(c.getCustomerId())){
                c.setCustomer(customerService.findById(c.getCustomerId()));
            }
        }
        return result;
    }

    @Override
    public Comment insertComment(Comment comment){
        if(comment!= null && comment.getStar() == 3){
            comment.setStarMark("NORMAL");
        }else if(comment!= null && comment.getStar() <= 2 ){
            comment.setStarMark("BAD");
        }else{
            comment.setStarMark("GOOD");
        }
        return mongoCommentRpository.insert(comment);
    }

    @Override
    public List<Comment> countComment(Comment comment) {
        Example<Comment> condtion = Example.of(comment);
        return mongoCommentRpository.findAll(condtion);
    }


}
