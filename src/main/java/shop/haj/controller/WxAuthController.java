package shop.haj.controller;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.haj.entity.Customer;
import shop.haj.service.CustomerService;
import shop.haj.service.WxAuthService;
import shop.haj.utils.Api;
import shop.haj.utils.ApiConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信用户认证相关
 * @author xiaoqiang
 *
 */
@RestController
@RequestMapping(value = "/v1")
public class WxAuthController extends BaseController{
	
	private Logger logger = LogManager.getLogger(WxAuthController.class);
	
	@Autowired
	private WxAuthService wxAuthService;
	
	@Autowired
	private CustomerService customerService;

	/**
	 * 根据客户端传过来的code从微信服务器获取appid和session_key，然后生成3rdkey返回给客户端，后续请求客户端传3rdkey来维护客户端登录态
	 * @param wxCode	小程序登录时获取的code
	 * @return
	 */
	@ApiOperation(value = "获取sessionId", notes = "小用户允许登录后，使用code 换取 session_key api，将 code 换成 openid 和 session_key")
	@ApiImplicitParam(name = "code", value = "用户登录回调内容会带上 ", required = true, dataType = "String")
	@Api(name = ApiConstant.WX_CODE)
	@GetMapping(value = "/customer/auth/session")
	//@HystrixCommand(fallbackMethod = "errorParam",commandProperties={@HystrixProperty(name = "fallback.enabled",value = "true")
	//		,@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "6000")})
	public Map<String,Object> createSssion(@RequestParam(value = "code")String wxCode){


		/*Map<String,Object> wxSessionMap = wxAuthService.getWxSession(wxCode);

		if(null == wxSessionMap){
			logger.error("can not find session with wxCode: {}, errCode:{}", wxCode, "50010");
			return rtnParam(50010, null);
		}
		
		//获取异常
		if(wxSessionMap.containsKey("errcode")){
			logger.error("find error code in response, return code:50020");
			return rtnParam(50020, null);
		}

		String wxOpenId = (String)wxSessionMap.get("openid");
		String wxSessionKey = (String)wxSessionMap.get("session_key");
		
		logger.info("openid={}, session_key={}", wxOpenId, wxSessionKey);
		
		Long expires = Long.valueOf(String.valueOf(wxSessionMap.get("expires_in")));
		String thirdSession = wxAuthService.create3rdSession(wxOpenId, wxSessionKey, expires);
		logger.info("thirdSession={}, expires time is {}", thirdSession, expires);*/
		
		/*return rtnParam(0, ImmutableMap.of("sessionId",thirdSession));*/
		Map<String,Object> wxSessionMap = new HashMap<>();
		wxSessionMap.put("openid","wx1cebfdebba0a65ab");
		wxSessionMap.put("session_key","bc45f5cbf6b5bd2d79df496b3906b07c");
		wxSessionMap.put("expires_in",10000L);

		String thirdSession = wxAuthService.create3rdSession("wx1cebfdebba0a65ab", "bc45f5cbf6b5bd2d79df496b3906b07c", 100000L);

		Map<String,Object> result = new ImmutableMap.Builder<String,Object>().put("third_session",thirdSession).build();
		return rtnParam(0, result);




	}



	@ApiOperation(value = "检查SessionId是否有效", notes = "检查SessionId是否有效")
	@GetMapping(value = {"/customer/auth/checkSession","/seller/auth/check"})
	public Map<String,Object> checkSession(@RequestParam(value = "third_session")String sessionId){
		
		//从缓存中获取open_id
		/*String customer_id = wxAuthService.decodehaSession(wxLoginCode);
		
		Customer customer = customerService.findById(customer_id);
		
		if(customer == null) {
			logger.error("customer not exists, by wxLoginCode:{}", wxLoginCode);
			return rtnParam(40010, "session is expired.");
		}*/

		String sessionCode = wxAuthService.getSessionKey(sessionId);
		String appId = wxAuthService.getOpenId(sessionId);

		if(sessionCode == null) {
			logger.error("customer not exists, by wxLoginCode:{}", sessionCode);
			return rtnParam(40010, "session is expired.");
		}

		ImmutableMap<String,Object> result = new ImmutableMap.Builder<String,Object>().put("appId",appId).build();
		return rtnParam(0, result);
	}
	
	/**
	 * 验证用户信息完整性
	 * @param rawData	微信用户基本信息
	 * @param signature	数据签名
	 * @param sessionId	会话ID
	 * @return
	 */
	@Api(name = ApiConstant.WX_CHECK_USER)
	@GetMapping(value = "/customer/auth/checkUserInfo")
	public Map<String,Object> checkUserInfo(@RequestParam(value = "rawData")String rawData,
			@RequestParam(value = "signature")String signature,
			@RequestParam(value = "sessionId")String sessionId) {
		
		/*String sessionKey = wxAuthService.getSessionKey(sessionId);
		
		if(null == sessionKey){
			logger.error("sessionKey not exists, by sessionId:{}", sessionId);
			return rtnParam(40008, null);
		}
		
		StringBuffer sb = new StringBuffer(rawData);
		sb.append(sessionKey);

		String encryData = DigestUtils.sha1Hex(sb.toString());*/
		Boolean checkStatus = true ;//Arrays.equals(encryData.getBytes(), signature.getBytes());
		
		//logger.info("Data={}, after sha1={}, signature={}, checkStatus={}", sb.toString(), encryData, signature, checkStatus);
		
		return rtnParam(0, ImmutableMap.of("checkPass", checkStatus));
	}

	/**
	 * 获取用户openId和unionId数据(如果没绑定微信开放平台，解密数据中不包含unionId)
	 * @param encryptedData 加密数据
	 * @param iv			加密算法的初始向量	
	 * @param sessionId		会话ID
	 * @return
	 */
	@Api(name = ApiConstant.WX_DECODE_USERINFO)
	@GetMapping(value = "/customer/auth/decodeUserInfo")
	public Map<String,Object> decodeUserInfo(@RequestParam(value = "encryptedData")String encryptedData,
			@RequestParam(value = "iv")String iv,
			@RequestParam(value = "sessionId")String sessionId){
		
		//从缓存中获取session_key
		
		String sessionKey = wxAuthService.getSessionKey(sessionId);
		String open_id = wxAuthService.getOpenId(sessionId);
		
		if(null == sessionKey || null == open_id){
			logger.error("sessionKey or open_id not exists, by sessionId:{}, sessionKey={}, open_id={}", sessionId, sessionKey, open_id);
			return rtnParam(40008, null);
		}
		
		/*try {
			AES aes = new AES();
			byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
			if(null != resultByte && resultByte.length > 0){
				String userInfo = new String(resultByte, "UTF-8");
				
				//新增用户
				Gson gson = new Gson();
				Customer customer = addCustomer(gson.fromJson(userInfo, Customer.class), open_id);
				
				logger.info("encryptedData:{}, sessionKey:{}, iv:{} --> userInfo:{}", encryptedData, sessionKey, iv, customer);
				
				if(customer.getId() >= 0){
					
					//加密信息
					String wxLoginCode = wxAuthService.gethaSession(customer.getId());
					
					Map<String, Object> data = Maps.newHashMap();
					data.put("session_id", wxLoginCode);
					data.put("user", customer);
					
					logger.info("customer={}, wxLoginCode={}", customer, wxLoginCode);
					
					return rtnParam(0, data);
				}
			}
		} catch (InvalidAlgorithmParameterException e) {
			logger.error(e.toString(), e);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString(), e);
		}
		return rtnParam(50021, null);*/

		Gson gson = new Gson();
		Customer customer = addCustomer(gson.fromJson(encryptedData, Customer.class), open_id);
		if(customer.getId()!= null){

			//加密信息
			String wxLoginCode = wxAuthService.gethaSession(customer.getId());

			Map<String, Object> data = Maps.newHashMap();
			data.put("wx_login_code", wxLoginCode);
			data.put("user", customer);

			logger.info("customer={}, wxLoginCode={}", customer, wxLoginCode);

			return rtnParam(0, data);
		}else{
			return rtnParam(50021, null);
		}
	}
	
	private Customer addCustomer(Customer customer, String open_id){
		if(customer == null) return null;
		
		//如果open_id已经被记录过，则更新数据，否则新增用户
		if(customerService.findByOpenID(open_id) == null){
			customer.setOpenId(open_id);
			return customerService.add(customer);
		}else {
			if(customer.getId() == null){
				Customer new_customer = customerService.findByOpenID(open_id);
				customer.setId(new_customer.getId());
			}
			return customer;
		}
	}

	@ApiOperation(value = "获取sessionId", notes = "小用户允许登录后，使用code 换取 session_key api，将 code 换成 openid 和 session_key")
	@ApiImplicitParam(name = "code", value = "用户登录回调内容会带上 ", required = true, dataType = "String")
	@Api(name = ApiConstant.WX_CODE)
	@GetMapping(value = "/seller/auth/login")
	public Map<String,Object> sellerAuthLogin(@RequestParam(value = "code")String wxCode){
		/*Map<String,Object> wxSessionMap = wxAuthService.getWxSession(wxCode);

		if(null == wxSessionMap){
			logger.error("can not find session with wxCode: {}, errCode:{}", wxCode, "50010");
			return rtnParam(50010, null);
		}

		//获取异常
		if(wxSessionMap.containsKey("errcode")){
			logger.error("find error code in response, return code:50020");
			return rtnParam(50020, null);
		}

		String wxOpenId = (String)wxSessionMap.get("openid");
		String wxSessionKey = (String)wxSessionMap.get("session_key");

		logger.info("openid={}, session_key={}", wxOpenId, wxSessionKey);

		Long expires = Long.valueOf(String.valueOf(wxSessionMap.get("expires_in")));
		String thirdSession = wxAuthService.create3rdSession(wxOpenId, wxSessionKey, expires);
		logger.info("thirdSession={}, expires time is {}", thirdSession, expires);*/

		/*return rtnParam(0, ImmutableMap.of("sessionId",thirdSession));*/
		Map<String,Object> wxSessionMap = new HashMap<>();
		wxSessionMap.put("openid","wx1cebfdebba0a65ab");
		wxSessionMap.put("session_key", "bc45f5cbf6b5bd2d79df496b3906b07c");
		wxSessionMap.put("expires_in", 10000L);

		String thirdSession = wxAuthService.create3rdSession("wx1cebfdebba0a65ab", "bc45f5cbf6b5bd2d79df496b3906b07c", 100000L);

		Map<String,Object> result = new ImmutableMap.Builder<String,Object>().put("third_session",thirdSession).build();
		return rtnParam(0, result);
	}

	/*@ApiOperation(value = "获取sessionId", notes = "小用户允许登录后，使用code 换取 session_key api，将 code 换成 openid 和 session_key")
	@ApiImplicitParam(name = "code", value = "用户登录回调内容会带上 ", required = true, dataType = "String")
	@Api(name = ApiConstant.WX_CODE)
	@GetMapping(value = "/seller/auth/check")
	public Map<String,Object> sellerAuthCheck(@RequestParam(value = "sessionId")String sessionId){

		String sessionCode = wxAuthService.getSessionKey(sessionId);

		if(sessionCode == null) {
			logger.error("customer not exists, by wxLoginCode:{}", sessionCode);
			return rtnParam(40010, "session is expired.");
		}

		return rtnParam(0, true);
	}*/
}
