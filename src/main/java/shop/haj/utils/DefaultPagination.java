package shop.haj.utils;

import shop.haj.entity.Pagination;

/**
 * <p>Title: shop.ha.utils</p>
 * <p/>
 * <p>
 * Description: 各个实体默认的分页信息
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：4/9/17
 */
public class DefaultPagination {
	
	public static Pagination getShop(){
		Pagination page = new Pagination();
		/*page.setBy("id");
		page.setSort("asc");
		page.setFrom(0);
		page.setLimit(20);*/
		
		return page;
	}
	
	public static Pagination getOrder(){
		Pagination page = new Pagination();
		/*page.setBy("order_id");
		page.setSort("desc");
		page.setFrom(0);
		page.setLimit(20);*/
		
		return page;
	}
	
	public static Pagination getAddress(){
		Pagination page = new Pagination();
		/*page.setBy("id");
		page.setSort("asc");
		page.setFrom(0);
		page.setLimit(20);*/
		
		return page;
	}
	
	public static Pagination getImageGoods(){
		
		Pagination page = new Pagination();
		/*page.setBy("image_id");
		page.setSort("asc");
		page.setFrom(0);
		page.setLimit(20);*/
		
		return page;
	}
	
	public static Pagination getGoodsDetail(){
		
		Pagination page = new Pagination();
		/*page.setBy("sequence");
		page.setSort("asc");
		page.setFrom(0);
		page.setLimit(20);*/
		
		return page;
	}
	
	public static Pagination getCart(){
		
		Pagination page = new Pagination();
		/*page.setBy("create_time");
		page.setSort("desc");
		page.setFrom(0);
		page.setLimit(20);*/
		
		return page;
	}
	
	public static Pagination getFavoriteGoods(){
		
		Pagination page = new Pagination();
		/*page.setBy("create_time");
		page.setSort("desc");
		page.setFrom(0);
		page.setLimit(20);*/
		
		return page;
	}
}
