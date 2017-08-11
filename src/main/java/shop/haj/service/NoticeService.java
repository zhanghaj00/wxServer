package shop.haj.service;

import shop.haj.entity.Notice;
import shop.haj.entity.Pagination;

import java.util.List;

/**
 * <p>Title: shop.ha.service</p>
 * <p/>
 * <p>
 * Description: 公告服务
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/4/17
 */
public interface NoticeService {
	
	/**
	 * 查找店铺的全部公告信息
	 * @param shop_id
	 * @param page
	 * @return
	 */
	List<Notice> findAllNotice(String shop_id,Integer isShowd, Pagination page);
	
	/**
	 * 查找店铺所有需要显示的公告
	 * @param shop_id
	 * @param page
	 * @return
	 */
	List<Notice> findAllShowedNotice(String shop_id, Pagination page);
	
	/**
	 * 新增公告信息
	 * @param notice
	 * @return
	 */
	Notice addNotice(Notice notice);
	
	/**
	 * 删除店铺信息
	 * @param shop_id
	 * @param notice_id
	 * @return
	 */
	int deleteNotice(String shop_id, String notice_id);
	
}
