package shop.haj.service.impl;

import com.google.common.collect.Maps;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.haj.entity.wxPay.WxPayConfig;
import shop.haj.entity.wxPay.request.*;
import shop.haj.entity.wxPay.result.*;
import shop.haj.mongo_repository.WxPayRepository;
import shop.haj.service.WxPayService;
import shop.haj.utils.SignUtils;
import shop.haj.utils.utils.qrcode.QrcodeUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Binary Wang on 2016/7/28.
 *
 * @author binarywang (https://github.com/binarywang)
 */
@Service
@Transactional
public class WxPayServiceImpl implements WxPayService {
	
	private static final String PAY_BASE_URL = "https://api.mch.weixin.qq.com";
	private Logger logger = LogManager.getLogger(WxPayServiceImpl.class);
	
	@Autowired
	private WxPayRepository wxPayRepository;
	
	@Override
	public WxPayConfig getWxConfig(int shop_id) {
		return null ; //wxPayRepository.getWxPayConfig(shop_id);
	}
	
	private String getPayBaseUrl() {
//    if (this.getConfig().useSandboxForWxPay()) {
//      return PAY_BASE_URL + "/sandboxnew";
//    }
		
		return PAY_BASE_URL;
	}
	
	@Override
	public WxPayRefundResult refund(WxPayRefundRequest request, SSLContext sslContext, String mchKey) throws WxErrorException {
//    request.checkAndSign(this.getConfig());
		
		String url = this.getPayBaseUrl() + "/secapi/pay/refund";
		String responseContent = this.postWithKey(url, request.toXML(), sslContext);
		WxPayRefundResult result = WxPayBaseResult.fromXML(responseContent, WxPayRefundResult.class);
		result.checkResult(mchKey);
		return result;
	}
	
	@Override
	public WxPayRefundQueryResult refundQuery(String transactionId, String outTradeNo, String outRefundNo, String refundId, String mchKey)
			throws WxErrorException {
		WxPayRefundQueryRequest request = new WxPayRefundQueryRequest();
		request.setOutTradeNo(StringUtils.trimToNull(outTradeNo));
		request.setTransactionId(StringUtils.trimToNull(transactionId));
		request.setOutRefundNo(StringUtils.trimToNull(outRefundNo));
		request.setRefundId(StringUtils.trimToNull(refundId));

//    request.checkAndSign(this.getConfig());
		
		String url = this.getPayBaseUrl() + "/pay/refundquery";
		String responseContent = this.post(url, request.toXML());
		WxPayRefundQueryResult result = WxPayBaseResult.fromXML(responseContent, WxPayRefundQueryResult.class);
		result.composeRefundRecords();
		result.checkResult(mchKey);
		return result;
	}
	
	@Override
	public WxPayOrderNotifyResult getOrderNotifyResult(String xmlData, String mchKey) throws WxErrorException {
		try {
			logger.debug("微信支付回调参数详细：{}", xmlData);
			WxPayOrderNotifyResult result = WxPayOrderNotifyResult.fromXML(xmlData);
			logger.debug("微信支付回调结果对象：{}", result);
			result.checkResult(mchKey);
			return result;
		} catch (WxErrorException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new WxErrorException(WxError.newBuilder().setErrorMsg("发生异常" + e.getMessage()).build());
		}
	}
	
	/**
	 * 保存订单支付结果
	 *
	 * @param wxPayOrderNotifyResult
	 * @return
	 */
	@Override
	public boolean saveOrderSuccessNotifyResult(WxPayOrderNotifyResult wxPayOrderNotifyResult, String order_id) {

		return true;
		//return wxPayRepository.updateOrderSucccessNotifyInfo(wxPayOrderNotifyResult, order_id);
		
	}
	
	/**
	 * 保存订单支付失败结果
	 *
	 * @param wxPayOrderNotifyResult
	 * @param order_id
	 * @return
	 */
	@Override
	public boolean saveOrderFailNotifyResult(WxPayOrderNotifyResult wxPayOrderNotifyResult, String order_id) {
		return true;
		//return wxPayRepository.updateOrderFailNotiftyInfo(wxPayOrderNotifyResult, order_id);
	}
	
	
	@Override
	public WxPaySendRedpackResult sendRedpack(WxPaySendRedpackRequest request, SSLContext sslContext) throws WxErrorException {
//    request.checkAndSign(this.getConfig());
		
		String url = this.getPayBaseUrl() + "/mmpaymkttransfers/sendredpack";
		if (request.getAmtType() != null) {
			//裂变红包
			url = this.getPayBaseUrl() + "/mmpaymkttransfers/sendgroupredpack";
		}
		
		String responseContent = this.postWithKey(url, request.toXML(), sslContext);
		WxPaySendRedpackResult result = WxPayBaseResult.fromXML(responseContent, WxPaySendRedpackResult.class);
		//毋须校验，因为没有返回签名信息
		// this.checkResult(result);
		return result;
	}
	
	@Override
	public WxPayRedpackQueryResult queryRedpack(String mchBillNo, SSLContext sslContext, String mchKey) throws WxErrorException {
		WxPayRedpackQueryRequest request = new WxPayRedpackQueryRequest();
		request.setMchBillNo(mchBillNo);
		request.setBillType("MCHT");
//    request.checkAndSign(this.getConfig());
		
		String url = this.getPayBaseUrl() + "/mmpaymkttransfers/gethbinfo";
		String responseContent = this.postWithKey(url, request.toXML(), sslContext);
		WxPayRedpackQueryResult result = WxPayBaseResult.fromXML(responseContent, WxPayRedpackQueryResult.class);
		result.checkResult(mchKey);
		return result;
	}
	
	@Override
	public WxPayOrderQueryResult queryOrder(String transactionId, String outTradeNo, String mchKey) throws WxErrorException {
		WxPayOrderQueryRequest request = new WxPayOrderQueryRequest();
		request.setOutTradeNo(StringUtils.trimToNull(outTradeNo));
		request.setTransactionId(StringUtils.trimToNull(transactionId));
//    request.checkAndSign(this.getConfig());
		
		String url = this.getPayBaseUrl() + "/pay/orderquery";
		String responseContent = this.post(url, request.toXML());
		if (StringUtils.isBlank(responseContent)) {
			throw new WxErrorException(WxError.newBuilder().setErrorMsg("无响应结果").build());
		}
		
		WxPayOrderQueryResult result = WxPayBaseResult.fromXML(responseContent, WxPayOrderQueryResult.class);
		result.composeCoupons();
		result.checkResult(mchKey);
		return result;
	}
	
	@Override
	public WxPayOrderCloseResult closeOrder(String outTradeNo, String mchKey) throws WxErrorException {
		if (StringUtils.isBlank(outTradeNo)) {
			throw new IllegalArgumentException("out_trade_no不能为空");
		}
		
		WxPayOrderCloseRequest request = new WxPayOrderCloseRequest();
		request.setOutTradeNo(StringUtils.trimToNull(outTradeNo));
//    request.checkAndSign(this.getConfig());
		
		String url = this.getPayBaseUrl() + "/pay/closeorder";
		String responseContent = this.post(url, request.toXML());
		WxPayOrderCloseResult result = WxPayBaseResult.fromXML(responseContent, WxPayOrderCloseResult.class);
		result.checkResult(mchKey);
		
		return result;
	}
	
	@Override
	public WxPayUnifiedOrderResult unifiedOrder(WxPayUnifiedOrderRequest request, String mchKey) throws WxErrorException {
//    request.checkAndSign(this.getConfig());
		
		String url = this.getPayBaseUrl() + "/pay/unifiedorder";
		String responseContent = this.post(url, request.toXML());
		WxPayUnifiedOrderResult result = WxPayBaseResult.fromXML(responseContent, WxPayUnifiedOrderResult.class);
		result.checkResult(mchKey);
		return result;
	}
	
	@Override
	public Map<String, String> getPayInfo(WxPayUnifiedOrderRequest request, String mchKey, int order_id) throws WxErrorException {
		
		//该订单之前是否已经申请过prepay_id
		Map<String, Object> prepayInfoMap = null ;//wxPayRepository.findPrepayInfo(order_id);
		String prepayId = "";
		String codeUrl = "";
		
		if(prepayInfoMap == null || prepayInfoMap.size() == 0){//没有申请过,或者已经失效,则重新申请
			WxPayUnifiedOrderResult unifiedOrderResult = this.unifiedOrder(request, mchKey);
			prepayId = unifiedOrderResult.getPrepayId();
			codeUrl = unifiedOrderResult.getCodeURL();
			if (StringUtils.isBlank(prepayId)) {
				throw new RuntimeException(String.format("无法获取prepay id，错误代码： '%s'，信息：%s。",
						unifiedOrderResult.getErrCode(), unifiedOrderResult.getErrCodeDes()));
			}
			
			//将prepayId更新至微信订单支付表
			boolean isSave = true ;//wxPayRepository.saveOrderPrepay(order_id, prepayId, codeUrl);
			if(!isSave){
				logger.error("saveOrderPrepay failure >>> order_id={}, prepayId={}, codeUrl={}", order_id, prepayId, codeUrl);
			}else {
				logger.info("saveOrderPrepay success >>> order_id={}, prepayId={}, codeUrl={}", order_id, prepayId, codeUrl);
			}
			
		}else {
			prepayId = (String)prepayInfoMap.get("prepay_id");
			codeUrl = (String)prepayInfoMap.get("code_url");
		}
		
		
		Map<String, String> payInfo = new HashMap<>();
		payInfo.put("appId", request.getAppid());
		// 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。
		// 但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
		payInfo.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
		payInfo.put("nonceStr", String.valueOf(System.currentTimeMillis()));
		payInfo.put("package", "prepay_id=" + prepayId);
		payInfo.put("signType", "MD5");
		if ("NATIVE".equals(request.getTradeType())) {
			payInfo.put("codeUrl", codeUrl);
		}
		payInfo.put("paySign", SignUtils.createSign(payInfo, mchKey));
		return payInfo;
	}
	
	@Override
	public WxEntPayResult entPay(WxEntPayRequest request, SSLContext sslContext, String mchKey) throws WxErrorException {
//    request.checkAndSign(this.getConfig());
		String url = this.getPayBaseUrl() + "/mmpaymkttransfers/promotion/transfers";
		
		String responseContent = this.postWithKey(url, request.toXML(), sslContext);
		WxEntPayResult result = WxPayBaseResult.fromXML(responseContent, WxEntPayResult.class);
		result.checkResult(mchKey);
		return result;
	}
	
	@Override
	public WxEntPayQueryResult queryEntPay(String partnerTradeNo, SSLContext sslContext, String mchKey) throws WxErrorException {
		WxEntPayQueryRequest request = new WxEntPayQueryRequest();
		request.setPartnerTradeNo(partnerTradeNo);
//    request.checkAndSign(this.getConfig());
		
		String url = this.getPayBaseUrl() + "/mmpaymkttransfers/gettransferinfo";
		String responseContent = this.postWithKey(url, request.toXML(), sslContext);
		WxEntPayQueryResult result = WxPayBaseResult.fromXML(responseContent, WxEntPayQueryResult.class);
		result.checkResult(mchKey);
		return result;
	}
	
	@Override
	public byte[] createScanPayQrcodeMode1(String productId, File logoFile, Integer sideLength, String app_id, String mchId, String mchKey) {
		String content = this.createScanPayQrcodeMode1(productId, app_id, mchId, mchKey);
		return this.createQrcode(content, logoFile, sideLength);
	}
	
	@Override
	public String createScanPayQrcodeMode1(String productId, String app_id, String mchId, String mchKey) {
		//weixin://wxpay/bizpayurl?sign=XXXXX&appid=XXXXX&mch_id=XXXXX&product_id=XXXXXX&time_stamp=XXXXXX&nonce_str=XXXXX
		StringBuilder codeUrl = new StringBuilder("weixin://wxpay/bizpayurl?");
		Map<String, String> params = Maps.newHashMap();
		params.put("appid", app_id);
		params.put("mch_id", mchId);
		params.put("product_id", productId);
		params.put("time_stamp", String.valueOf(System.currentTimeMillis() / 1000));//这里需要秒，10位数字
		params.put("nonce_str", String.valueOf(System.currentTimeMillis()));
		
		String sign = SignUtils.createSign(params, mchKey);
		params.put("sign", sign);
		
		
		for (String key : params.keySet()) {
			codeUrl.append(key + "=" + params.get(key) + "&");
		}
		
		String content = codeUrl.toString().substring(0, codeUrl.length() - 1);
		logger.debug("扫码支付模式一生成二维码的URL:{}", content);
		return content;
	}
	
	@Override
	public byte[] createScanPayQrcodeMode2(String codeUrl, File logoFile, Integer sideLength) {
		return this.createQrcode(codeUrl, logoFile, sideLength);
	}
	
	private byte[] createQrcode(String content, File logoFile, Integer sideLength) {
		if (sideLength == null || sideLength < 1) {
			return QrcodeUtils.createQrcode(content, logoFile);
		}
		
		return QrcodeUtils.createQrcode(content, sideLength, logoFile);
	}
	
	public void report(WxPayReportRequest request, String mchKey) throws WxErrorException {
//    request.checkAndSign(this.getConfig());
		
		String url = this.getPayBaseUrl() + "/payitil/report";
		String responseContent = this.post(url, request.toXML());
		WxPayCommonResult result = WxPayBaseResult.fromXML(responseContent, WxPayCommonResult.class);
		result.checkResult(mchKey);
	}
	
	@Override
	public File downloadBill(String billDate, String billType, String tarType, String deviceInfo, String mchKey) throws WxErrorException {
		WxPayDownloadBillRequest request = new WxPayDownloadBillRequest();
		request.setBillType(billType);
		request.setBillDate(billDate);
		request.setTarType(tarType);
		request.setDeviceInfo(deviceInfo);

//    request.checkAndSign(this.getConfig());
		
		String url = this.getPayBaseUrl() + "/pay/downloadbill";
		//TODO 返回的内容可能是文件流，也有可能是xml，需要区分对待
		String responseContent = this.post(url, request.toXML());
		
		WxPayCommonResult result = WxPayBaseResult.fromXML(responseContent, WxPayCommonResult.class);
		result.checkResult(mchKey);
		//TODO 待实现，暂时无测试帐号，无法调试
		return null;
	}
	
	@Override
	public WxPayMicropayResult micropay(WxPayMicropayRequest request, String mchKey) throws WxErrorException {
//    request.checkAndSign(this.getConfig());
		
		String url = this.getPayBaseUrl() + "/pay/micropay";
		String responseContent = this.post(url, request.toXML());
		WxPayMicropayResult result = WxPayBaseResult.fromXML(responseContent, WxPayMicropayResult.class);
		result.checkResult(mchKey);
		return result;
	}
	
	@Override
	public WxPayOrderReverseResult reverseOrder(WxPayOrderReverseRequest request, SSLContext sslContext, String mchKey) throws WxErrorException {
//    request.checkAndSign(this.getConfig());
		
		String url = this.getPayBaseUrl() + "/secapi/pay/reverse";
		String responseContent = this.postWithKey(url, request.toXML(), sslContext);
		WxPayOrderReverseResult result = WxPayBaseResult.fromXML(responseContent, WxPayOrderReverseResult.class);
		result.checkResult(mchKey);
		return result;
	}
	
	@Override
	public String shorturl(WxPayShorturlRequest request, String mchKey) throws WxErrorException {
//    request.checkAndSign(this.getConfig());
		
		String url = this.getPayBaseUrl() + "/tools/shorturl";
		String responseContent = this.post(url, request.toXML());
		WxPayShorturlResult result = WxPayBaseResult.fromXML(responseContent, WxPayShorturlResult.class);
		result.checkResult(mchKey);
		return result.getShortUrl();
	}
	
	@Override
	public String shorturl(String longUrl) throws WxErrorException {
//		return this.shorturl(new WxPayShorturlRequest(longUrl));
		return "";
	}
	
	@Override
	public String authcode2Openid(WxPayAuthcode2OpenidRequest request, String mchKey) throws WxErrorException {
//    request.checkAndSign(this.getConfig());
		
		String url = this.getPayBaseUrl() + "/tools/authcodetoopenid";
		String responseContent = this.post(url, request.toXML());
		WxPayAuthcode2OpenidResult result = WxPayBaseResult.fromXML(responseContent, WxPayAuthcode2OpenidResult.class);
		result.checkResult(mchKey);
		return result.getOpenid();
	}
	
	@Override
	public String authcode2Openid(String authCode) throws WxErrorException {
//		return this.authcode2Openid(new WxPayAuthcode2OpenidRequest(authCode));
		return "";
	}
	
	/**
	 * 是否已经请求过统一支付接口
	 *
	 * @param order_id
	 * @return
	 */
	@Override
	public boolean isPrepay(int order_id) {
		
		return true ;//wxPayRepository.isPrepay(order_id);
	}
	
	private String post(String url, String xmlParam) {
		String requestString = xmlParam;
		try {
			requestString = new String(xmlParam.getBytes(CharEncoding.UTF_8), CharEncoding.ISO_8859_1);
		} catch (UnsupportedEncodingException e) {
			//实际上不会发生该异常
			e.printStackTrace();
		}
		
		HttpRequest request = HttpRequest.post(url).body(requestString);
		HttpResponse response = request.send();
		String responseString = null;
		try {
			responseString = new String(response.bodyText().getBytes(CharEncoding.ISO_8859_1), CharEncoding.UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		this.logger.debug("\n[URL]: {}\n[PARAMS]: {}\n[RESPONSE]: {}", url, xmlParam, responseString);
		return responseString;
	}
	
	/**
	 * 由于暂时未找到使用jodd-http实现证书配置的办法，故而暂时使用httpclient
	 */
	private String postWithKey(String url, String requestStr, SSLContext sslContext) throws WxErrorException {
		try {
//      if (null == sslContext) {
//        sslContext = this.getConfig().initSSLContext();
//      }
			
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,
					new String[]{"TLSv1"}, null, new DefaultHostnameVerifier());
			
			HttpPost httpPost = new HttpPost(url);
			
			try (CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build()) {
				httpPost.setEntity(new StringEntity(new String(requestStr.getBytes(CharEncoding.UTF_8), CharEncoding.ISO_8859_1)));
				try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
					String result = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
					this.logger.debug("\n[URL]:  {}\n[PARAMS]: {}\n[RESPONSE]: {}", url, requestStr, result);
					return result;
				}
			} finally {
				httpPost.releaseConnection();
			}
		} catch (Exception e) {
			this.logger.error("\n[URL]:  {}\n[PARAMS]: {}\n[EXCEPTION]: {}", url, requestStr, e.getMessage());
			throw new WxErrorException(WxError.newBuilder().setErrorCode(-1).setErrorMsg(e.getMessage()).build(), e);
		}
	}
	
}
