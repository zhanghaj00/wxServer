/**
 * Create time
 */
package shop.haj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import shop.haj.entity.Pagination;
import shop.haj.entity.Supplier;
import shop.haj.mongo_repository.MongoSupplierRepository;
import shop.haj.service.SupplierService;

import java.util.List;

/**
 * Created by Hao on .
 * description。
 * @since 1.9.0
 */
@Service
public class SupplierServiceImpl implements SupplierService {


    @Autowired
    private MongoSupplierRepository mongoSupplierRepository;

    @Override
    public List<Supplier> pageSupplier(Supplier supplier, Pagination pagination) {

        Example<Supplier> conditon = Example.of(supplier);
        return mongoSupplierRepository.findAll(conditon,pagination.getRequest()).getContent();
    }

    @Override
    public List<Supplier> findAll(Supplier supplier) {
        Example<Supplier> condition = Example.of(supplier);
        return mongoSupplierRepository.findAll(condition);
    }

    @Override
    public Supplier insert(Supplier supplier) {
        return mongoSupplierRepository.insert(supplier);
    }

    @Override
    public void delete(String id) {
        mongoSupplierRepository.delete(id);
    }
}
