package shop.haj.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.haj.entity.Notice;
import shop.haj.entity.Pagination;
import shop.haj.service.NoticeService;
import shop.haj.utils.ResultUtil;

import java.util.List;
import java.util.Map;

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
public class NoticeController  extends BaseController{
	
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
	public Map<String,Object> findAllNotice(@RequestHeader("shop_id") String shop_id,
	                                  @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
									  @RequestParam(value = "status", defaultValue = "0") String status,
	                                  @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
	                                  @RequestParam(value = "by", defaultValue = "id") String by,
	                                  @RequestParam(value = "sort", defaultValue = "asc") String sort){
		
		Pagination page = new Pagination();
		page.setFrom(pageNum);
		page.setLimit(pageSize);
		page.setBy(by);
		page.setSort(sort);
		int showd = 0;
		if("SHOW".equals(status)){
			showd = 1;
		}else{
			showd = 2;
		}
		return rtnParam(0, noticeService.findAllNotice(shop_id,showd, page));
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
	public Map<String,Object> findAllShowedNotice(@RequestHeader("shop_id") String shop_id,
	                                        @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
	                                        @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
	                                        @RequestParam(value = "by", defaultValue = "id") String by,
	                                        @RequestParam(value = "sort", defaultValue = "asc") String sort){
		
		Pagination page = new Pagination();
		page.setFrom(pageNum);
		page.setLimit(pageSize);
		page.setBy(by);
		page.setSort(sort);
		
		return rtnParam(0, noticeService.findAllShowedNotice(shop_id, page));
	}
	
	/**
	 * 卖家新增公告
	 * @param shop_id
	 * @param notice
	 * @return
	 */
	@ApiOperation(value = "卖家新增公告", notes = "卖家新增公告")
	@PostMapping(value = {"/seller/notices"})
	public Map<String,Object> addNotice(@RequestHeader("shop_id") String shop_id,
	                        @RequestBody Notice notice){
		notice.setShopId(shop_id);
		return rtnParam(0, noticeService.addNotice(notice));
	}
	
	/**
	 * 卖家删除公告
	 * @param shop_id
	 * @param notice_id
	 * @return
	 */
	@ApiOperation(value = "卖家删除公告", notes = "卖家删除公告")
	@DeleteMapping(value = {"/seller/notices/{notice_id}"})
	public Map<String,Object> deleteNotice(@RequestHeader("shop_id") String shop_id,
	                        @PathVariable("notice_id") String notice_id){
		int result = noticeService.deleteNotice(shop_id, notice_id);
		return rtnParam(0, result);
	}
}
