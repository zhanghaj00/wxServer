package shop.haj.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import shop.haj.entity.wxpublic.TextMessage;
import shop.haj.utils.MessageUtil;

import java.util.Date;
import java.util.Map;

/**
 * <p>Title: shop.ha.controller</p>
 * <p/>
 * <p>
 * Description: WechatPublicController
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/4/17
 */
@RestController
@RequestMapping(value = "/v1")
public class WechatPublicController {
	
	private Logger logger = LogManager.getLogger(WechatPublicController.class);
	
	@GetMapping(value = "/weapppub")
	public String weapp_pub(@RequestParam(value = "signature") String signature,
	                        @RequestParam(value = "timestamp") String timestamp,
	                        @RequestParam(value = "nonce") String nonce,
	                        @RequestParam(value = "echostr") String echostr){
		
		logger.info("signature={}, timestamp={}, nonce={}, echostr={}",
				signature, timestamp, nonce, echostr);
		
		return echostr;
	}
	
	@PostMapping(value = "/weapppub")
	public String weapp_pub(@RequestParam(value = "signature") String signature,
	                        @RequestParam(value = "timestamp") String timestamp,
	                        @RequestParam(value = "nonce") String nonce,
	                        @RequestParam(value = "openid") String openid,
	                        @RequestBody String xmlData){
		
		try {
		
			logger.info("[weapp_pub post] signature={}, timestamp={}, nonce={}, openid={}, xml = {}",
					signature, timestamp, nonce, openid, xmlData);
			
			Map<String,String> xmlMap = MessageUtil.xmlToMap(xmlData);//接收到客户端的消息
			
			String responseXml = "";//定义准备返回的xml变量
			
			String msgType = xmlMap.get("MsgType");//获取msgType信息
			
			if("text".equalsIgnoreCase(msgType)){//处理文本信息
				//生成文本对象
				TextMessage textMessage = new TextMessage();
				textMessage.setToUserName(xmlMap.get("FromUserName"));
				textMessage.setFromUserName(xmlMap.get("ToUserName"));
				textMessage.setCreateTime(System.currentTimeMillis());
				textMessage.setMsgType("text");
				textMessage.setContent("您发送的消息: " + xmlMap.get("Content"));
				responseXml = MessageUtil.textMessageToXml(textMessage);
				
			}else if("event".equalsIgnoreCase(msgType)){//处理菜单信息
				
				String event = xmlMap.get("Event");
				String eventKey = xmlMap.get("EventKey");
				
				TextMessage textMessage = new TextMessage();
				textMessage.setToUserName(xmlMap.get("FromUserName"));
				textMessage.setFromUserName(xmlMap.get("ToUserName"));
				textMessage.setCreateTime(System.currentTimeMillis());
				textMessage.setMsgType("text");
				textMessage.setContent(eventKey + "功能暂未开放，尽请期待!");
				responseXml = MessageUtil.textMessageToXml(textMessage);
			}
			
			logger.info("response = {}", responseXml);
			
			return responseXml;
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		
		return "";
	}
}
