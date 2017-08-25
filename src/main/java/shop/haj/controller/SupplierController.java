/**
 * Create time
 */
package shop.haj.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.haj.entity.Supplier;
import shop.haj.service.SupplierService;

import java.util.Map;

/**
 * Created by Hao on .
 * description。
 * @since 1.9.0
 */
@RestController
@RequestMapping("v1")
public class SupplierController extends BaseController {

    @Autowired
    private SupplierService supplierService;


    @ApiOperation(value = "供应商查询",notes = "供应商查询")
    @RequestMapping(value = {"seller/supplier","customer/supplier"})
    public Map<String,Object> findAllSupplier(@RequestHeader("shop_id")String shopId,
                                              Supplier supplier){

        supplier.setShopId(shopId);
        return  rtnParam(0,supplierService.findAll(supplier));
    }

    @ApiOperation(value = "插入供应商",notes = "插入供应商")
    @PostMapping(value = {"seller/supplier"})
    public Map<String,Object> insertSupplier(@RequestHeader("shop_id")String shopId,
                                             @RequestBody Supplier supplier){

        supplier.setShopId(shopId);
        return  rtnParam(0,supplierService.insert(supplier));
    }
}
