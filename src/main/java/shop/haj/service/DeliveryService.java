/**
 * Create time
 */
package shop.haj.service;

import shop.haj.entity.Delivery;
import shop.haj.entity.Pagination;

import java.util.List;

/**
 * Created by Hao on .
 * description。
 * @since 1.9.0
 */
public interface DeliveryService {

     Delivery insert(Delivery delivery);

     List<Delivery> pageDelivery(Delivery delivery,Pagination pagination);

     List<Delivery> findAll(Delivery delivery);

     List<Delivery> findBySupplierId(String supplierId);

}
