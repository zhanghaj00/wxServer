package shop.haj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.haj.entity.Notice;
import shop.haj.entity.Pagination;
import shop.haj.repository.NoticeRepository;
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
	private NoticeRepository noticeRepository;
	
	@Override
	public List<Notice> findAllNotice(int shop_id, Pagination page) {
		return noticeRepository.findAllNotice(shop_id, page);
	}
	
	@Override
	public List<Notice> findAllShowedNotice(int shop_id, Pagination page) {
		return noticeRepository.findAllShowedNotice(shop_id, page);
	}
	
	@Override
	public Notice addNotice(Notice notice) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		notice.setCreate_time(format.format(System.currentTimeMillis()));
		notice.setUpdate_time(format.format(System.currentTimeMillis()));
		
		return noticeRepository.addNotice(notice);
	}
	
	@Override
	public int deleteNotice(int shop_id, int notice_id) {
		return noticeRepository.deleteNotice(shop_id, notice_id);
	}
}
