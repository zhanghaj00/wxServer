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

    public Delivery insert(Delivery delivery);

    public List<Delivery> pageDelivery(Delivery delivery,Pagination pagination);

    public List<Delivery> findAll(Delivery delivery);
}
