package shop.haj.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import shop.haj.entity.Order;
import shop.haj.entity.OrderGoodsInfo;
import shop.haj.entity.OrderListSingleInfo;
import shop.haj.entity.Pagination;
import shop.haj.repository.RowMapper.OrderListSingleInfoRowMapper;
import shop.haj.utils.OrderStatus;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * <p>Title: shop.ha.repository</p>
 * <p/>
 * <p>
 * Description: OrderListRepository
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/14/17
 */
@Repository
public class OrderListRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 根据买家ID来进行查询买家的全部订单信息
	 * @param customer_id 买家ID
	 * @param status 订单状态
	 * @param page 订单分页信息
	 * @return
	 */
	public List<OrderListSingleInfo> findOrderListSingleByCustomerID(int customer_id, int status, Pagination page){
		
		String sql = "SELECT \n" +
				"    order_id,\n" +
				"    date_format(order_time, '%Y-%m-%d %H:%i:%s') order_time,\n" +
				"    `status`,\n" +
				"    final_price,\n" +
				"    goods_id,\n" +
				"    image_url,\n" +
				"    goods_name,\n" +
				"    goods_sku,\n" +
				"    goods_price,\n" +
				"    `count`,\n" +
				"    shop_id,\n" +
				"    shop_name,\n" +
				"    customer_id\n" +
				" FROM\n" +
				"    order_goods_info\n" +
				" WHERE customer_id=? " +
				(status == OrderStatus.BOTH.ordinal() ? "" : " and `status`=?") ;
		
		Object[] args = new Object[]{customer_id, status};
		
		if(status == OrderStatus.BOTH.ordinal()){
			args = new Object[]{customer_id};
		}
		
		return jdbcTemplate.query(sql, args, new OrderListSingleInfoRowMapper());
	}
	
	/**
	 * 根据买家ID和店铺ID返回订单列表
	 * 查询买家在某店铺的全部订单
	 * @param shop_id
	 * @param customer_id
	 * @return
	 */
	public List<OrderListSingleInfo> findOrderListSingleByShopAndCustomerID(int shop_id, int customer_id, int status, Pagination page){
		
		String sql = "SELECT \n" +
				"    order_id,\n" +
				"    date_format(order_time, '%Y-%m-%d %H:%i:%s') order_time,\n" +
				"    `status`,\n" +
				"    final_price,\n" +
				"    goods_id,\n" +
				"    image_url,\n" +
				"    goods_name,\n" +
				"    goods_sku,\n" +
				"    goods_price,\n" +
				"    `count`,\n" +
				"    shop_id,\n" +
				"    shop_name,\n" +
				"    customer_id\n" +
				"FROM\n" +
				"    order_goods_info\n" +
				"WHERE shop_id=?\n" +
				"and customer_id=?\n" +
				(status == OrderStatus.BOTH.ordinal() ? "" : " and `status`=? ") ;
		
		Object[] args = new Object[]{shop_id, customer_id, status};
		
		if(status == OrderStatus.BOTH.ordinal()){
			args = new Object[]{shop_id, customer_id};
		}
			
		
		return jdbcTemplate.query(sql, args, new OrderListSingleInfoRowMapper());
	}
	
	
	
	/**
	 * 查找订单对应的商品信息
	 * @param order_id
	 * @return
	 */
	public List<OrderListSingleInfo> findOrderGoodsInfoListByOrderID(int order_id, Pagination page){
		
		String sql = "select \n" +
				"    order_id,\n" +
				"    order_time,\n" +
				"    `status`,\n" +
				"    final_price,\n" +
				"    goods_id,\n" +
				"    image_url,\n" +
				"    goods_name,\n" +
				"    goods_sku,\n" +
				"    goods_price,\n" +
				"    count,\n" +
				"    shop_id,\n" +
				"    shop_name,\n" +
				"    customer_id\n" +
				" from order_goods_info " +
				" where order_id=? ";
		
		return jdbcTemplate.query(sql, new Object[]{order_id},
				new OrderListSingleInfoRowMapper());
	}
	
	/**
	 * 新增订单商品关联表信息
	 * @param order
	 */
	public void addOrderListGoodsInfo(Order order){
		
		String sql = "insert into order_goods_info(order_id, goods_id, shop_id, customer_id,\n" +
				"order_time, `status`, final_price, image_url, shop_name, goods_name, " +
				"goods_sku, goods_price, `count`, create_time)\n" +
				" values(?, ?, ?, ?, date_format(?,'%Y-%m-%d %H:%i:%s'),\n" +
				"?, ?, ?, ?, ?, ?, ?, ?, now())";
		
		List<OrderGoodsInfo> orderGoodsInfoList = order.getOrderGoodsInfos();
		
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				OrderGoodsInfo orderGoodsInfo = orderGoodsInfoList.get(i);
				ps.setInt(1, order.getOrder_id());
				ps.setInt(2, orderGoodsInfo.getGoods_id());
				ps.setInt(3, order.getShop_id());
				ps.setInt(4, order.getCustomer_id());
				ps.setString(5, order.getOrder_time());
				ps.setInt(6, order.getStatus());
				ps.setDouble(7, order.getFinal_price());
				ps.setString(8, orderGoodsInfo.getImage_url());
				ps.setString(9, order.getShop_name());
				ps.setString(10, orderGoodsInfo.getGoods_name());
				ps.setString(11, orderGoodsInfo.getGoods_sku() == null ? "null" : orderGoodsInfo.getGoods_sku());
				ps.setDouble(12, orderGoodsInfo.getGoods_price());
				ps.setInt(13, orderGoodsInfo.getCount());
			}
			
			@Override
			public int getBatchSize() {
				return orderGoodsInfoList.size();
			}
		});
		
	}
	
}