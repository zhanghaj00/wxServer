/**
 * Create time
 */
package shop.haj.controller;

import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.haj.entity.Comment;
import shop.haj.entity.CommentCount;
import shop.haj.entity.Pagination;
import shop.haj.service.CommentService;

import java.util.List;
import java.util.Map;

/**
 * Created by Hao on .
 * description。
 * @since 1.9.0
 */
@RestController
@RequestMapping("/v1")
public class CommentController extends BaseController {

    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "添加订单评价",notes = "添加订单评价")
    @PostMapping("/customer/comments")
    public Map<String,Object> comment(@RequestHeader("shop_id") String shopId,
                                      @RequestAttribute("customer_id")String customerId,
                                      @RequestBody Comment comment){

        if(null == comment || StringUtils.isEmpty(comment.getComment())){
            rtnParam(50021,"没有有效的留言");
        }

        comment.setShopId(shopId);
        comment.setCustomerId(customerId);
        return rtnParam(0,commentService.insertComment(comment));
    }


    @ApiOperation(notes = "分页查询订单评价",value = "查询订单评价")
    @GetMapping(value = {"/customer/comments","/seller/comments"})
    public Map<String,Object> pageComment(@RequestHeader("shop_id") String shopId,
                                          @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                                          @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                                          @RequestParam(value = "by", defaultValue = "id") String by,
                                          @RequestParam(value = "sort", defaultValue = "asc") String sort,
                                          Comment comment){

        Pagination page = new Pagination();
        page.setFrom(pageNum);
        page.setLimit(pageSize);
        page.setBy(by);
        page.setSort(sort);
        comment.setShopId(shopId);
        return rtnParam(0,commentService.pageComment(comment,page));
    }

    @ApiOperation(value = "查询评价列表统计",notes = "评价列表统计")
    @GetMapping(value = {"/customer/comments/count","/seller/comments/count"})
    public Map<String,Object> countComment(@RequestHeader("shop_id") String shopId,
                                           @RequestParam("goods_id") String goodsId){
        Comment comment = new Comment();
        comment.setShopId(shopId);
        comment.setGoodsId(goodsId);
        List<Comment> result = commentService.countComment(comment);
        Integer good = 0;
        Integer normal = 0;
        Integer bad = 0;
        for(Comment c:result){
            if(null !=c.getStar() && 4<=c.getStar()){
                good++;
                continue;
            }
            if(null !=c.getStar() && 3==c.getStar()){
                normal++;
                continue;
            }
            if(null !=c.getStar() && 2>=c.getStar()){
                bad++;
                continue;
            }
        }
        Integer all = good+normal+bad;

        return rtnParam(0,new CommentCount(good,normal,bad,all));

    }
}
