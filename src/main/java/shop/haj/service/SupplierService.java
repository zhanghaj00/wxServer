/**
 * Create time
 */
package shop.haj.service;

import shop.haj.entity.Pagination;
import shop.haj.entity.Supplier;

import java.util.List;

/**
 * Created by Hao on .
 * description。
 * @since 1.9.0
 */
public interface SupplierService {

    List<Supplier> pageSupplier(Supplier supplier,Pagination pagination);

    List<Supplier> findAll(Supplier supplier);

    Supplier insert(Supplier supplier);

    void delete(String id);
 }
