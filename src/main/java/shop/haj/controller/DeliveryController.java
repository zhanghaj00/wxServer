/**
 * Create time
 */
package shop.haj.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.haj.entity.Delivery;
import shop.haj.entity.Pagination;
import shop.haj.service.DeliveryService;

import java.util.Map;

/**
 * Created by Hao on .
 * description。
 * @since 1.9.0
 */
@RestController
@RequestMapping("/v1")
public class DeliveryController  extends  BaseController{

    @Autowired
    private DeliveryService deliveryService;


    @ApiOperation(value = "查询店铺的配送方式",notes = "后期添加根据地址来判断")
     @GetMapping(value = {"/seller/delivery","/buyer/delivery"})
     public Map<String,Object> pageDelivery(@RequestHeader("shop_id") String shopId,
                                           @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                                           @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                                           @RequestParam(value = "by", defaultValue = "createTime") String by,
                                           @RequestParam(value = "sort", defaultValue = "desc") String sort,
                                            Delivery delivery){
        Pagination page = new Pagination();
        page.setFrom(pageNum);
        page.setLimit(pageSize);
        page.setBy(by);
        page.setSort(sort);
        delivery.setShopId(shopId);
        return rtnParam(0,deliveryService.pageDelivery(delivery,page));
    }


    @ApiOperation(value = "添加店铺的配送方式",notes = "后期添加根据地址来判断")
    @PostMapping(value = {"/seller/delivery"})
    public Map<String,Object> addDelivery(@RequestHeader("shop_id") String shopId,
                                          @RequestBody Delivery delivery){
        if(null != delivery){
            delivery.setShopId(shopId);

        }
        return rtnParam(0,deliveryService.insert(delivery));
    }
}
