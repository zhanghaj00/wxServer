package shop.haj.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.haj.entity.Order;
import shop.haj.repository.RowMapper.OrderRowMapper;
import shop.haj.utils.OrderStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>Title: shop.ha.repository</p>
 * <p/>
 * <p>
 * Description: 订单
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/4/17
 */
@Repository
public class OrderRepository {
	
	private Logger logger = LogManager.getLogger(OrderRepository.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	/**
	 * 查询单个订单的信息
	 *
	 * @param order_id
	 * @return
	 */
	public Order findShopOrderByID(int order_id) {
		String sql = "select \n" +
				"    id, \n" +
				"    uuid, \n" +
				"    customer_id, \n" +
				"    `status`, \n" +
				"    deal_price, \n" +
				"    final_price, \n" +
				"    coupon_used_id, \n" +
				"    message, \n" +
				"    address, \n" +
				"    receive_name, \n" +
				"    receive_phone, \n" +
				"    shop_id, \n" +
				"    payment_type, \n" +
				"    DATE_FORMAT(order_time, '%Y-%m-%d %H:%i:%s') order_time,\n" +
				"    DATE_FORMAT(payment_time, '%Y-%m-%d %H:%i:%s') payment_time,\n" +
				"    DATE_FORMAT(sended_time, '%Y-%m-%d %H:%i:%s') sended_time,\n" +
				"    DATE_FORMAT(close_time, '%Y-%m-%d %H:%i:%s') close_time,\n" +
				"    DATE_FORMAT(update_time, '%Y-%m-%d %H:%i:%s') update_time\n" +
				"from \n" +
				" `order`\n" +
				"where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{order_id}, new OrderRowMapper());
	}
	
	/**
	 * 根据订单号查询订单信息
	 * @param uuid
	 * @return
	 */
	public Order findShopOrderByUUID(String uuid){
		
		String sql = "select \n" +
				"    id, \n" +
				"    uuid, \n" +
				"    customer_id, \n" +
				"    `status`, \n" +
				"    deal_price, \n" +
				"    final_price, \n" +
				"    coupon_used_id, \n" +
				"    message, \n" +
				"    address, \n" +
				"    receive_name, \n" +
				"    receive_phone, \n" +
				"    shop_id, \n" +
				"    payment_type, \n" +
				"    DATE_FORMAT(order_time, '%Y-%m-%d %H:%i:%s') order_time,\n" +
				"    DATE_FORMAT(payment_time, '%Y-%m-%d %H:%i:%s') payment_time,\n" +
				"    DATE_FORMAT(sended_time, '%Y-%m-%d %H:%i:%s') sended_time,\n" +
				"    DATE_FORMAT(close_time, '%Y-%m-%d %H:%i:%s') close_time,\n" +
				"    DATE_FORMAT(update_time, '%Y-%m-%d %H:%i:%s') update_time\n" +
				"from \n" +
				" `order`\n" +
				"where uuid=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{uuid}, new OrderRowMapper());
	}
	
	/**
	 * 新增订单信息
	 *
	 * @param order
	 * @return
	 */
	@Transactional
	public Order addOrder(Order order) {
		String sql = "insert into `order`(\n" +
				"    uuid,\n" +
				"    customer_id,\n" +
				"    `status`,\n" +
				"    deal_price,\n" +
				"    final_price,\n" +
				"    coupon_used_id,\n" +
				"    shop_id,\n" +
				"    address,\n" +
				"    receive_name, \n" +
				"    receive_phone, \n" +
				"    payment_type,\n" +
				"    order_time,\n" +
				"    update_time,\n" +
				"    message)" +
				"    values(\n" +
				"    ?,\n" +
				"    ?,\n" +
				"    ?,\n" +
				"    ?,\n" +
				"    ?,\n" +
				"    ?,\n" +
				"    ?,\n" +
				"    ?,\n" +
				"    ?,\n" +
				"    ?,\n" +
				"    ?,\n" +
				"    DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'),\n" +
				"    DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'),\n" +
				"    ?" +
				"    )";
		
		KeyHolder holder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, order.getUuid());
				ps.setInt(2, order.getCustomer_id());
				ps.setInt(3, order.getStatus());
				ps.setDouble(4, order.getDeal_price());
				ps.setDouble(5, order.getFinal_price());
				ps.setInt(6, order.getCoupon_used_id());
				ps.setInt(7, order.getShop_id());
				ps.setString(8, order.getAddress());
				ps.setString(9, order.getReceiveName());
				ps.setString(10, order.getReceivePhone());
				ps.setInt(11, order.getPayment_type());
				ps.setString(12, order.getOrder_time());
				ps.setString(13, order.getUpdate_time());
				ps.setString(14, order.getMessage());
				
				return ps;
			}
		}, holder);
		
		order.setOrder_id(holder.getKey().intValue());
		
		return order;
	}
	
	/**
	 * 当用户支付成功后，将订单状态修改为待发货
	 * @param order_id
	 * @param onlinePayType 在线支付类型
	 * @return
	 */
	public int updateOrderStatusToWaitingSend(int order_id, int onlinePayType){
		
		String sql = "update `order` set status=?, update_time=now(), payment_time=now(), onlinepay_type=? where id=?";
		
		int result = jdbcTemplate.update(sql, new Object[]{OrderStatus.WAITING_SEND.ordinal(), onlinePayType, order_id});
		
		logger.info("updateOrderStatusToWaitingSend >>> sql:{}, order_id={}, result={}", sql, order_id, result);
		
		if(result <= 0) return 0;
		return 1;
	}
	
	/**
	 * 当卖家发货后，将订单状态修改为待收货
	 * @param order_id
	 * @return
	 */
	public int updateOrderStatusToWaitingReceive(int order_id){
		String sql = "update `order` set status=?, update_time=now(), sended_time=now() where id=?";
		
		int result = jdbcTemplate.update(sql, new Object[]{OrderStatus.WAITING_RECEIVE.ordinal(), order_id});
		
		logger.info("updateOrderStatusToWaitingReceive >>> sql:{}, order_id={}, result={}", sql, order_id, result);
		
		if(result <= 0) return 0;
		return 1;
	}
	
	/**
	 * 当买家确认收货，或者超过收货期限后，将订单状态修改为待评价
	 *
	 * @param order_id
	 * @return
	 */
	public int updateOrderStatusToWaitingComments(int order_id){
		
		String sql = "update `order` set status=?, update_time=now(), received_time=now() where id=?";
		
		int result = jdbcTemplate.update(sql, new Object[]{OrderStatus.WAITING_COMMENT.ordinal(), order_id});
		
		logger.info("updateOrderStatusToWaitingComments >>> sql:{}, order_id={}, result={}", sql, order_id, result);
		
		if(result <= 0) return 0;
		return 1;
	}
	
	/**
	 * 当买家开始退款/售后时，将订单状态修改为退款/售后中
	 *
	 * @param order_id
	 * @return
	 */
	public int updateOrderStatusToRefund(int order_id){
		
		String sql = "update `order` set status=?, update_time=now(), reqrefund_time=now() where id=?";
		
		int result = jdbcTemplate.update(sql, new Object[]{OrderStatus.REFUND.ordinal(), order_id});
		
		logger.info("updateOrderStatusToRefund >>> sql:{}, order_id={}, result={}", sql, order_id, result);
		
		if(result <= 0) return 0;
		return 1;
	}
	
	/**
	 * 将订单状态修改为已完成
	 *
	 * @param order_id
	 * @return
	 */
	public int updateOrderStatusToFinish(int order_id){
		
		String sql = "update `order` set status=?, update_time=now(), finish_time=now() where id=?";
		
		int result = 0;
		try {
			result = jdbcTemplate.update(sql, new Object[]{OrderStatus.FINISHED.ordinal(), order_id});
		} catch (DataAccessException e) {
			logger.error(e.toString(), e);
		}
		
		logger.info("updateOrderStatusToFinish >>> sql:{}, order_id={}, result={}", sql, order_id, result);
		
		if(result <= 0) return 0;
		return 1;
	}
	
	/**
	 * 将订单状态修改为已关闭
	 *
	 * @param order_id
	 * @return
	 */
	public int updateOrderStatusToClose(int order_id){
		
		String sql = "update `order` set status=?, update_time=now(), close_time=now() where id=?";
		
		int result = 0;
		try {
			result = jdbcTemplate.update(sql, new Object[]{OrderStatus.CLOSED.ordinal(), order_id});//订单状态变更为待评价
		} catch (DataAccessException e) {
			logger.error(e.toString(), e);
		}
		
		logger.info("updateOrderStatusToClose >>> sql:{}, order_id={}, result={}", sql, order_id, result);
		
		if(result <= 0) return 0;
		return 1;
	}
	
	
	/**
	 * 修改订单商品表状态信息
	 * @param order_id
	 * @param status
	 * @return
	 */
	public int updateOrderGoodsInfoStatus(int order_id, int status){
		String sql = "update order_goods_info set status=? where order_id=?";
		int result = jdbcTemplate.update(sql, new Object[]{status, order_id});
		
		if(result <= 0) return 0;
		return 1;
	}
}