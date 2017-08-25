package shop.haj.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.haj.entity.wxpublic.WxAuth;
import shop.haj.service.WxAuthService;
import shop.haj.utils.HttpRequest;
import shop.haj.utils.RedisUtil;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@Service
public class WxAuthServiceImpl implements WxAuthService{
	
	private Logger logger = LogManager.getLogger(WxAuthServiceImpl.class);
	
	@Autowired
	private WxAuth wxAuth;
	@Autowired
	private RedisUtil redisUtil;
	
	/**
	 * 根据小程序登录返回的code获取openid和session_key
	 * https://mp.weixin.qq.com/debug/wxadoc/dev/api/api-login.html?t=20161107
	 * @param wxCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> getWxSession(String wxCode){

		StringBuffer sb = new StringBuffer();
		sb.append("appid=").append(wxAuth.getAppId());
		sb.append("&secret=").append(wxAuth.getSecret());
		sb.append("&js_code=").append(wxCode);
		sb.append("&grant_type=").append(wxAuth.getGrantType());
		String res = HttpRequest.sendGet(wxAuth.getSessionHost(), sb.toString());
		
		logger.info("getWxSession by wxCode:{}", wxCode);
		
		if(res == null || res.equals("")){
			return null;
		}
		return JSON.parseObject(res, Map.class);
	}
	/**
	 * 缓存微信openId和session_key
	 * @param wxOpenId		微信用户唯一标识
	 * @param wxSessionKey	微信服务器会话密钥
	 * @param expires		会话有效期, 以秒为单位, 例如2592000代表会话有效期为30天
	 * @return
	 */

	public String create3rdSession(String wxOpenId, String wxSessionKey, Long expires){
		
		String thirdSessionKey = RandomStringUtils.randomAlphanumeric(64);
		StringBuffer sb = new StringBuffer();
		sb.append(wxSessionKey).append("#").append(wxOpenId);
		try {
			redisUtil.add(thirdSessionKey, expires, new String(sb.toString().getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString(), e);
		}
		logger.info("create3rdSession:{}, openId:{}, sessionKey:{}, expires:{}", thirdSessionKey, wxOpenId, wxSessionKey, expires);
		
		return thirdSessionKey;
	}
	
	/**
	 * 根据第三方sessionId获取open_id
	 *
	 * @param sessionId
	 * @return
	 */
	@Override
	public String getOpenId(String sessionId) {
		
		Object wxSessionObj = redisUtil.get(sessionId);
		if(null == wxSessionObj){
			logger.error("sessionKey not exists, by sessionId:{}", sessionId);
			return null;
		}
		String wxSessionStr = (String)wxSessionObj;
		
		if(wxSessionStr == null || wxSessionStr.length() == 0) return null;
		
		String open_id = null;
		try {
			open_id = wxSessionStr.split("#")[1];
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		
		return open_id;
	}
	
	/**
	 * 根据第三方sessionId获取sessionKey
	 *
	 * @param sessionId
	 * @return
	 */
	@Override
	public String getSessionKey(String sessionId) {
		
		Object wxSessionObj = redisUtil.get(sessionId);
		if(null == wxSessionObj){
			logger.error("sessionKey not exists, by sessionId:{}", sessionId);
			return null;
		}
		String wxSessionStr = (String)wxSessionObj;
		
		if(wxSessionStr == null || wxSessionStr.length() == 0) return null;
		
		String sessionKey = null;
		try {
			sessionKey = wxSessionStr.split("#")[0];
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		
		return sessionKey;
	}
	
	/**
	 * 将customer_id加密后返回给小程序
	 * @param customer_id
	 * @return
	 */
	@Override
	public String gethaSession(String customer_id){
		//wx_login_session, random_code, create_time
		//String random_code = RandomStringUtils.randomAlphanumeric(32);
		String baseInfo = customer_id ;
		String wxLoginCode = new String(Base64.encodeBase64(baseInfo.getBytes()));
		
		if(wxLoginCode == null) return null;
		
		try {
			redisUtil.add(wxLoginCode, baseInfo);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			return null;
		}
		
		return wxLoginCode;
	}
	
	/**
	 * 解密小程序Session，返回customer_id
	 * @param wxLoginCode
	 * @return
	 */
	@Override
	public String decodehaSession(String wxLoginCode){
		String random_code = (String)redisUtil.get(wxLoginCode);
		
		if(random_code == null || random_code.length() == 0) return null;
		
		String customer_info = new String(Base64.decodeBase64(wxLoginCode.getBytes()));
		
		return customer_info;//.replace("#" + random_code, "");
	}
}
