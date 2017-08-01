package shop.haj.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import shop.haj.entity.OrderRefund;
import shop.haj.repository.RowMapper.OrderRefundRowMapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * <p>Title: shop.ha.repository</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/30/17
 */
@Repository
public class OrderRefundRepository {
	
	private Logger logger = LogManager.getLogger(OrderRefundRepository.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 新增订单退款/退货记录
	 * @param or
	 * @return
	 */
	public int addOrderRefund(OrderRefund or){
		
		String sql = "insert into order_refund(order_id, order_uuid, refund_uuid, `type`, \n" +
				"order_status, status, goods_id, cause, contact_name, contact_phone, refund_price, create_time,\n" +
				"update_time, close_time, finish_time, seller_dealtime, is_agree, disagree_cause)\n" +
				"values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), now(), ?, ?, ?, ?, ?)";
		
		return jdbcTemplate.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, or.getOrder_id());
				ps.setString(2, or.getOrder_uuid());
				ps.setString(3, or.getRefund_uuid());
				ps.setInt(4, or.getType());
				ps.setInt(5, or.getOrder_status());
				ps.setInt(6, or.getStatus());
				ps.setInt(7, or.getGoods_id());
				ps.setString(8, or.getCause());
				ps.setString(9, or.getContact_name());
				ps.setString(10, or.getContact_phone());
				ps.setDouble(11, or.getRefund_price());
				ps.setString(12, or.getClose_time());
				ps.setString(13, or.getFinish_time());
				ps.setString(14, or.getSeller_dealtime());
				ps.setInt(15, or.getIs_agree());
				ps.setString(16, or.getDisagree_cause());
			}
		});
	}
	
	/**
	 * 查找该订单对应的退单记录
	 * @param order_id
	 * @return
	 */
	public OrderRefund findOrderRefund(int order_id){
		
		String sql = "select \n" +
				"\torder_id, \n" +
				"\torder_uuid, \n" +
				"    refund_uuid, \n" +
				"    `type`, \n" +
				"\torder_status, \n" +
				"    `status`, \n" +
				"    goods_id, \n" +
				"    cause, \n" +
				"    contact_name, \n" +
				"\tcontact_phone, \n" +
				"    refund_price, \n" +
				"    date_format(create_time, '%Y-%m-%d %H:%i:%s') create_time,\n" +
				"\tdate_format(update_time, '%Y-%m-%d %H:%i:%s') update_time,\n" +
				"\tdate_format(close_time, '%Y-%m-%d %H:%i:%s') close_time,\n" +
				"\tdate_format(finish_time, '%Y-%m-%d %H:%i:%s') finish_time,\n" +
				"\tdate_format(seller_dealtime, '%Y-%m-%d %H:%i:%s') seller_dealtime,\n" +
				"\tis_agree, \n" +
				"    disagree_cause \n" +
				"from order_refund \n" +
				"where order_id=? and `type`=0";
		
		logger.info("findOrderRefund repository >>> sql={}, args=({})", sql, order_id);
		
		try {
			return jdbcTemplate.queryForObject(sql, new Object[]{order_id}, new OrderRefundRowMapper());
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	/**
	 * 订单中某个商品的退货记录
	 * @param order_id
	 * @param goods_id
	 * @return
	 */
	public OrderRefund findOrderRefund(int order_id, int goods_id){
		
		String sql = "select \n" +
				"\torder_id, \n" +
				"\torder_uuid, \n" +
				"    refund_uuid, \n" +
				"    `type`, \n" +
				"\torder_status, \n" +
				"    `status`, \n" +
				"    goods_id, \n" +
				"    cause, \n" +
				"    contact_name, \n" +
				"\tcontact_phone, \n" +
				"    refund_price, \n" +
				"    date_format(create_time, '%Y-%m-%d %H:%i:%s') create_time,\n" +
				"\tdate_format(update_time, '%Y-%m-%d %H:%i:%s') update_time,\n" +
				"\tdate_format(close_time, '%Y-%m-%d %H:%i:%s') close_time,\n" +
				"\tdate_format(finish_time, '%Y-%m-%d %H:%i:%s') finish_time,\n" +
				"\tdate_format(seller_dealtime, '%Y-%m-%d %H:%i:%s') seller_dealtime,\n" +
				"\tis_agree, \n" +
				"    disagree_cause \n" +
				"from order_refund \n" +
				"where order_id=? and goods_id=? and `type`=1";
		
		logger.info("findOrderRefund repo >>> sql={}, args=(order_id={}, goods_id={})", sql, order_id, goods_id);
		
		return jdbcTemplate.queryForObject(sql, new Object[]{order_id, goods_id}, new OrderRefundRowMapper());
	}
	
	/**
	 * 将退单状态修改为买家主动关闭
	 * @param refund_uuid
	 * @return
	 */
	public int updateStatusToClose(String refund_uuid){
		String sql = "update order_refund set `status`=2, close_time=now() where refund_uuid=?";
		
		logger.info("updateStatusToClose repo >>> sql={}, args=(refund_uuid={})", sql, refund_uuid);
		
		return jdbcTemplate.update(sql, refund_uuid);
	}
}
