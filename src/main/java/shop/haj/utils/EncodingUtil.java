package shop.haj.utils;

import shop.haj.utils.aes.WXBizMsgCrypt;

/**
 * <p>Title: shop.ha.utils</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/7/17
 */
public class EncodingUtil {
	
	private static String encodingAesKey = "EONjJX0jmAJygnOFzLotQeNAjU3snAkAoZjTGFnZvc4";
	private static String token = "weijihaizhe1987";
	private static String appId = "wx36b9de83df162a4f";
	
	/**
	 * 加密
	 * @param replyMsg
	 * @param timestamp
	 * @param nonce
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String replyMsg, String timestamp, String nonce) throws Exception{
		
		WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
		String mingwen = pc.encryptMsg(replyMsg, timestamp, nonce);
		System.out.println("加密后: " + mingwen);
		
		return mingwen;
	}
	
	/**
	 *  解密
	 * @param msgSignature
	 * @param timeStamp
	 * @param nonce
	 * @param postData
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String msgSignature, String timeStamp, String nonce, String postData) throws  Exception{
		WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
		return pc.decryptMsg(msgSignature, timeStamp, nonce, postData);
	}
	
	public static void main(String[] args) {
		String responseXml = "你好";
		try {
			System.out.println(EncodingUtil.encrypt(responseXml, "1494136696", "792759426"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
