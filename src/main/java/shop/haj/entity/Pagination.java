package shop.haj.entity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * <p>Title: shop.ha.entity</p>
 * <p/>
 * <p>
 * Description: 分页信息实体，用以存储分页信息
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/19/17
 */
public class Pagination implements Serializable ,Pageable {

	/**
	 * mongodb分页
	 */
	private static final long serialVersionUID = 1L;
	// 当前页
	private Integer pagenumber = 1;
	// 当前页面条数
	private Integer pagesize = 10;

	@Override
	public int getPageNumber() {
		return 1;
	}

	@Override
	public int getPageSize() {
		return 10;
	}

	@Override
	public int getOffset() {
		return 10;
	}

	@Override
	public Pageable next() {
		return null;
	}

	@Override
	public Pageable previousOrFirst() {
		return null;
	}

	@Override
	public Pageable first() {
		return null;
	}

	@Override
	public boolean hasPrevious() {
		return false;
	}

	public Integer getPagenumber() {
		return pagenumber;
	}

	public void setPagenumber(Integer pagenumber) {
		this.pagenumber = pagenumber;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	@Override
	public Sort getSort() {
		return new Sort(Sort.Direction.ASC,"id");
	}
}
