/**
 * Create time
 */
package shop.haj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import shop.haj.entity.Delivery;
import shop.haj.entity.Pagination;
import shop.haj.mongo_repository.MongoDeliveryRepository;
import shop.haj.service.DeliveryService;

import java.util.List;

/**
 * Created by Hao on .
 * description。
 * @since 1.9.0
 */
@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private MongoDeliveryRepository mongoDeliveryRepository;

    @Override
    public Delivery insert(Delivery delivery) {
        return mongoDeliveryRepository.insert(delivery);
    }

    @Override
    public List<Delivery> pageDelivery(Delivery delivery, Pagination pagination) {
        Example<Delivery> condition = Example.of(delivery);
        return mongoDeliveryRepository.findAll(condition,pagination.getRequest()).getContent();
    }

    @Override
    public List<Delivery> findAll(Delivery delivery) {
        Example<Delivery> condition = Example.of(delivery);
        return mongoDeliveryRepository.findAll(condition);
    }
}
