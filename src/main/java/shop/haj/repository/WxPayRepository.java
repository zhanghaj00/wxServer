package shop.haj.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import shop.haj.entity.wxPay.WxPayConfig;
import shop.haj.entity.wxPay.result.WxPayOrderNotifyResult;
import shop.haj.repository.RowMapper.WxPayConfigRowMapper;

import java.util.Map;

/**
 * <p>Title: shop.ha.repository</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/16/17
 */
@Repository
public class WxPayRepository {
	
	private Logger logger = LogManager.getLogger(WxPayRepository.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查找店家的微信支付配置
	 * @param shop_id
	 * @return
	 */
	public WxPayConfig getWxPayConfig(int shop_id){
		
		String sql = "select app_id, mch_id, mchKey, keyPath from wxpay_info where shop_id=?";
		
		Object object = null;
		
		try {
			object = jdbcTemplate.queryForObject(sql, new Object[]{shop_id}, new WxPayConfigRowMapper());
			
		} catch (DataAccessException e) {
			logger.error(e.toString(), e);
		}
		
		if(object == null) return null;
		
		return (WxPayConfig)object;
	}
	
	/**
	 * 当预支付成功后，保存该订单的预支付ID号
	 * @param prepay_id
	 * @param order_id
	 * @return
	 */
	public boolean saveOrderPrepay(int order_id, String prepay_id, String code_url){
		
		String sql = "insert into order_wxpay(order_id, prepay_id, code_url, prepay_time, create_time)\n" +
				"values(?, ?, ?, now(), now())";
		
		int result = jdbcTemplate.update(sql, new Object[]{order_id, prepay_id, code_url});
		
		if(result <= 0) return false;
		return true;
	}
	
	/**
	 * 当服务端申请prepayid成功后，但小程序端未完成支付流程时，如果超过2个小时后，
	 * 用户重新发起支付申请，则需要重新申请prepay_id
	 * @param order_id
	 * @param prepay_id
	 * @return
	 */
	public boolean updateOrderPrepay(int order_id, String prepay_id, String code_url){
		
		String sql = "update order_wxpay set prepay_id=?, code_url=?, prepay_time=now() where order_id=?";

		int result = jdbcTemplate.update(sql, prepay_id, code_url, order_id);
		
		if(result <= 0) return false;
		return true;
	}
	
	/**
	 * 当微信服务端返回成功的通知时，更新的数据
	 */
	public boolean updateOrderSucccessNotifyInfo(WxPayOrderNotifyResult result, int order_id){
		String sql = "update order_wxpay set return_code=?, return_msg=?, return_xml=?, notify_time=now(), transaction_id=?,\n" +
				"trade_type=?, bank_type=?, openid=?, result_code=?, err_code=?, err_code_des=?, is_subscribe=?,\n" +
				"total_fee=?, settlement_total_fee=?, fee_type=?, cash_fee=?, cash_fee_type=?, coupon_fee=?, coupon_count=?,\n" +
				"time_end=? where order_id=?";
		
		int status = 0;
		try {
			status = jdbcTemplate.update(sql, result.getReturnCode(), result.getReturnMsg(), result.getXmlString(), result.getTransactionId(),
					result.getTradeType(), result.getBankType(), result.getOpenid(), result.getResultCode(), result.getErrCode(),
					result.getErrCodeDes(), result.getIsSubscribe(), result.getTotalFee(), result.getSettlementTotalFee(),
					result.getFeeType(), result.getCashFee(), result.getCashFeeType(), result.getCouponFee(), result.getCouponCount(),
					result.getTimeEnd(), order_id);
		} catch (DataAccessException e) {
			logger.error(e.toString(), e);
		}
		
		logger.info("updateOrderSucccessNotifyInfo >>> order_id:{}, update result={}, sql=({})", order_id, status, sql);
		
		if(status <= 0) return false;
		return true;
	}
	
	/**
	 * 当微信服务端返回失败的通知时，更新的数据
	 */
	public boolean updateOrderFailNotiftyInfo(WxPayOrderNotifyResult result, int order_id){
		String sql = "update order_wxpay set return_code=?, return_msg=?, return_xml=?, notify_time=now() where order_id=?";
		int status = jdbcTemplate.update(sql, result.getReturnCode(), result.getReturnMsg(), result.getXmlString(), order_id);
		
		logger.info("updateOrderFailNotiftyInfo >>> update result={}, sql=({})", status, sql);
		
		if(status <= 0) return false;
		return true;
	}
	
	/**
	 * 根据订单号去查询prepay_id，当在2个小时内时，则直接返回目前的prepay_id，否则返回空
	 * @param order_id
	 * @return
	 */
	public Map<String, Object> findPrepayInfo(int order_id){
		String sql = "select prepay_id, code_url, hour(timediff(now(), prepay_time)) diff from order_wxpay where order_id=?";
		
		Map<String, Object> resultMap = null;
		try {
			resultMap = jdbcTemplate.queryForMap(sql, order_id);
		} catch (DataAccessException e) {
		}
		
		if(resultMap == null || resultMap.size() == 0) return null;
		
		long diff = (long)resultMap.get("diff");
		
		if(diff >= 2) return null;
		
		return resultMap;
	}
	
	/**
	 * 查询是否存在记录
	 * @param order_id
	 * @return
	 */
	public boolean isPrepay(int order_id){
		
		String sql = "select prepay_id, date_format(time_end, '%Y-%m-%d %H:%i:%s') time_end from order_wxpay where order_id=?";
		
		try {
			Map<String, Object> resultMap = jdbcTemplate.queryForMap(sql, new Object[]{order_id});
			
			if(resultMap == null || resultMap.size() == 0){
				return false;//无记录，则未支付过
			}
			
			return true;
		} catch (DataAccessException e) {
			return false;//查询不到数据说明未支付过
		}
	}
	
}
