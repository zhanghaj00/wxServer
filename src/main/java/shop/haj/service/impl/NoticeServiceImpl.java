package shop.haj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.haj.entity.Notice;
import shop.haj.entity.Pagination;
import shop.haj.mongo_repository.MongoNoticeRepository;
import shop.haj.service.NoticeService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private MongoNoticeRepository mongoNoticeRepository;
	
	@Override
	public List<Notice> findAllNotice(String shop_id, Pagination page) {
		return mongoNoticeRepository.findByShopId(shop_id, page.getRequest()).getContent();
	}
	
	@Override
	public List<Notice> findAllShowedNotice(String shop_id, Pagination page) {
		return mongoNoticeRepository.findByShopIdAndIsShow(shop_id,1, page.getRequest()).getContent();
	}
	
	@Override
	public Notice addNotice(Notice notice) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		notice.setCreateTime(format.format(System.currentTimeMillis()));
		notice.setUpdateTime(format.format(System.currentTimeMillis()));
		
		return mongoNoticeRepository.insert(notice);
	}
	
	@Override
	public int deleteNotice(String shop_id, String notice_id) {
		mongoNoticeRepository.delete(notice_id);return 1;
	}
}
