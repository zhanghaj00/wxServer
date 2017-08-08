package shop.haj.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.haj.entity.Pagination;
import shop.haj.entity.Shop;
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


	@Override
	public List<Shop> findVisitShopsByCusomterID(int id, Pagination page) {
		return null;
	}

	@Override
	public int addVisitShopLog(int customer_id, int shop_id) {
		return 0;
	}
}
