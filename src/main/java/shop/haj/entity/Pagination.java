package shop.haj.entity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.concurrent.locks.Condition;

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
	private Integer from = 1;
	// 当前页面条数
	private Integer limit = 10;

	private Sort sort =  new Sort(Sort.Direction.ASC,"id");

	private String sortStr = "asc";

	private String by = "id";

	@Override
	public int getPageNumber() {
		return this.from;
	}

	@Override
	public int getPageSize() {
		return this.limit;
	}

	@Override
	public int getOffset() {
		return (this.from - 1) * this.limit;
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


	@Override
	public Sort getSort() {
		Sort sort = null;
		if(this.sort.equals("asc")){
			sort =  new Sort(Sort.Direction.ASC,this.by);
		}else{
			sort =  new Sort(Sort.Direction.ASC,this.by);
		}
		return sort;
	}


	public void setFrom(int from){
		this.from = from;
	}

	public void setLimit(int limit){
		this.limit = limit;
	}

	public void setBy(String by){
		this.by =by;
	}
	public void setSort(String sortStr){
		this.sortStr = sortStr;
	}
}
