package shop.haj.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.haj.entity.Pagination;
import shop.haj.entity.Shop;
import shop.haj.entity.VisitShop;
import shop.haj.mongo_repository.MongoVisitLogRepository;
import shop.haj.service.VisitLogService;
import shop.haj.utils.TimeUtil;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
@Transactional(rollbackFor = RuntimeException.class )
public class VisitLogServiceImpl implements VisitLogService{


	@Resource
	private MongoVisitLogRepository mongoVisitLogRepository;


	@Override
	public List<VisitShop> findVisitShopsByShopId(String id) {
		return mongoVisitLogRepository.findByShopId(id);
	}

	@Override
	public int addVisitShopLog(String customer_id, String shop_id) {
		VisitShop visitShop = new VisitShop();
		visitShop.setShopId(shop_id);
		visitShop.setCustomerId(customer_id);
		visitShop.setVisitTime(TimeUtil.getTimeStamp());
		mongoVisitLogRepository.insert(visitShop);
		return 1;
	}

	@Override
	public Integer countShopVisit(String countType ,String shopId) {

		if(StringUtils.isEmpty(countType)) {return 0;}
		List<VisitShop> log = findVisitShopsByShopId(shopId);
		Set<String> visitMember = new HashSet<>();


		switch (countType){
			case "TODAY":
				log.forEach(x-> {
					if (TimeUtil.compareToday(x.getVisitTime())) {
						visitMember.add(x.getCustomerId());
					}
					}
				);
				break;
			case "MONTH":
				log.forEach(x->visitMember.add(x.getCustomerId()));
				break;
			default:
				log.forEach(x->visitMember.add(x.getCustomerId()));
		}
		return visitMember.size();
	}
}
