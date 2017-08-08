package shop.haj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.haj.entity.Pagination;
import shop.haj.entity.Seller;
import shop.haj.mongo_repository.MongoSellerRpository;
import shop.haj.service.SellerService;

import java.util.List;

/**
 * <p>Title: shop.ha.service.impl</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/3/17
 */
@Service
@Transactional
public class SellerServiceImpl implements SellerService {
	
	@Autowired
	private MongoSellerRpository sellerRepository;
	
	@Override
	public List<Seller> findAll(Pagination page) {
		return sellerRepository.findAll(page.getRequest()).getContent();
	}
	
	@Override
	public Seller findById(int id) {
		return sellerRepository.findOne(id + "");
	}
	
	@Override
	public Seller add(Seller seller) {
		return sellerRepository.insert(seller);
	}
	
	@Override
	public int update(Seller seller) {
		sellerRepository.save(seller);
		return 0;
	}
	
	@Override
	public int delete(int id) {
		sellerRepository.delete(id + "");
		return 1;
	}
}
