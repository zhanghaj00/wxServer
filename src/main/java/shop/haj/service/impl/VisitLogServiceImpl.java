package shop.haj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.haj.entity.Pagination;
import shop.haj.entity.Shop;
import shop.haj.repository.VisitLogRepository;
import shop.haj.service.VisitLogService;

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
 *         CreateTime：3/4/17
 */
@Service
@Transactional
public class VisitLogServiceImpl implements VisitLogService{
	
	@Autowired
	private VisitLogRepository visitLogRepository;
	
	/**
	 * 查找买家的店铺访问记录
	 *
	 * @param id
	 * @param page
	 * @return
	 */
	@Override
	public List<Shop> findVisitShopsByCusomterID(int id, Pagination page) {
		return visitLogRepository.findVisitShopsByCusomterID(id, page);
	}
	
	@Override
	public int addVisitShopLog(int customer_id, int shop_id) {
		return visitLogRepository.addVisitShopLog(customer_id, shop_id);
	}
}
