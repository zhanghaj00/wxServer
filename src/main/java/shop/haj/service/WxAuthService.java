package shop.haj.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface WxAuthService {
	
	/**
	 * 根据小程序登录返回的code获取openid和session_key
	 * https://mp.weixin.qq.com/debug/wxadoc/dev/api/api-login.html?t=20161107
	 * @param wxCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	Map<String,Object> getWxSession(String wxCode);
	
	/**
	 * 缓存微信openId和session_key
	 * @param wxOpenId		微信用户唯一标识
	 * @param wxSessionKey	微信服务器会话密钥
	 * @param expires		会话有效期, 以秒为单位, 例如2592000代表会话有效期为30天
	 * @return
	 */
	String create3rdSession(String wxOpenId, String wxSessionKey, Long expires);
	
	/**
	 * 根据第三方sessionId获取open_id
	 * @param sessionId
	 * @return
	 */
	String getOpenId(String sessionId);
	
	
	/**
	 * 根据第三方sessionId获取sessionKey
	 * @param sessionId
	 * @return
	 */
	String getSessionKey(String sessionId);
	
	/**
	 * 将customer_id加密后返回给小程序
	 * @param customer_id
	 * @return
	 */
	String gethaSession(String customer_id);
	
	/**
	 * 解密小程序Session，返回customer_id
	 * @param wxLoginCode
	 * @return
	 */
	String decodehaSession(String wxLoginCode);
}
