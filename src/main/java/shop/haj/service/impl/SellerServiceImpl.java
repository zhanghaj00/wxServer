package shop.haj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.haj.entity.Pagination;
import shop.haj.entity.Seller;
import shop.haj.repository.SellerRepository;
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
	private SellerRepository sellerRepository;
	
	@Override
	public List<Seller> findAll(Pagination page) {
		return sellerRepository.findAll(page);
	}
	
	@Override
	public Seller findById(int id) {
		return sellerRepository.findByID(id);
	}
	
	@Override
	public Seller add(Seller seller) {
		return sellerRepository.add(seller);
	}
	
	@Override
	public int update(Seller seller) {
		return sellerRepository.update(seller);
	}
	
	@Override
	public int delete(int id) {
		return sellerRepository.delete(id);
	}
}
