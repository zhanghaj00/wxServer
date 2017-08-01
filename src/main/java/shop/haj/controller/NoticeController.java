package shop.haj.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.haj.entity.Notice;
import shop.haj.entity.Pagination;
import shop.haj.service.NoticeService;
import shop.haj.utils.ResultUtil;

import java.util.List;

/**
 * <p>Title: shop.ha.controller</p>
 * <p/>
 * <p>
 * Description: 公告管理接口
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/4/17
 */
@RestController
@RequestMapping(value = "/v1")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	/**
	 * 卖家查找店内所有公告
	 * @param shop_id
	 * @param from
	 * @param to
	 * @param by
	 * @param sort
	 * @return
	 */
	@ApiOperation(value = "查找店内所有公告", notes = "卖家查找店内所有公告")
	@GetMapping(value = "/seller/notices")
	public List<Notice> findAllNotice(@RequestHeader("shop_id") int shop_id,
	                                  @RequestParam(value = "from", defaultValue = "0") int from,
	                                  @RequestParam(value = "limit", defaultValue = "20") int to,
	                                  @RequestParam(value = "by", defaultValue = "id") String by,
	                                  @RequestParam(value = "sort", defaultValue = "asc") String sort){
		
		Pagination page = new Pagination();
		/*page.setFrom(from);
		page.setLimit(to);
		page.setBy(by);
		page.setSort(sort);*/
		
		return noticeService.findAllNotice(shop_id, page);
	}
	
	/**
	 * 卖家查找所有店内需要显示的公告
	 * @param shop_id
	 * @param from
	 * @param to
	 * @param by
	 * @param sort
	 * @return
	 */
	@ApiOperation(value = "查找店内所有需要显示的公告", notes = "卖家查找店内所有需要显示的公告")
	@GetMapping(value = {"/seller/notices/shows", "/customer/notices/shows"})
	public List<Notice> findAllShowedNotice(@RequestHeader("shop_id") int shop_id,
	                                        @RequestParam(value = "from", defaultValue = "0") int from,
	                                        @RequestParam(value = "limit", defaultValue = "20") int to,
	                                        @RequestParam(value = "by", defaultValue = "id") String by,
	                                        @RequestParam(value = "sort", defaultValue = "asc") String sort){
		
		Pagination page = new Pagination();
		/*page.setFrom(from);
		page.setLimit(to);
		page.setBy(by);
		page.setSort(sort);*/
		
		return noticeService.findAllShowedNotice(shop_id, page);
	}
	
	/**
	 * 卖家新增公告
	 * @param shop_id
	 * @param notice
	 * @return
	 */
	@ApiOperation(value = "卖家新增公告", notes = "卖家新增公告")
	@PostMapping(value = {"/seller/notices"})
	public Notice addNotice(@RequestHeader("shop_id") int shop_id,
	                        @RequestBody Notice notice){
		notice.setShop_id(shop_id);
		return noticeService.addNotice(notice);
	}
	
	/**
	 * 卖家删除公告
	 * @param shop_id
	 * @param notice_id
	 * @return
	 */
	@ApiOperation(value = "卖家删除公告", notes = "卖家删除公告")
	@DeleteMapping(value = {"/seller/notices/{notice_id}"})
	public String deleteNotice(@RequestHeader("shop_id") int shop_id,
	                        @PathVariable("notice_id") int notice_id){
		int result = noticeService.deleteNotice(shop_id, notice_id);
		return ResultUtil.getJson(result);
	}
}
