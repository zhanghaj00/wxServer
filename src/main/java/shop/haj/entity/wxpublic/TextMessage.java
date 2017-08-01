package shop.haj.entity.wxpublic;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import shop.haj.utils.CDATAConvert;

/**
 * <p>Title: shop.ha.entity.wxpublic</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/9/17
 */
@XStreamAlias("xml")
public class TextMessage{
	
	@XStreamConverter(CDATAConvert.class)
	private String ToUserName;
	
	@XStreamConverter(CDATAConvert.class)
	private String FromUserName;
	
	private long CreateTime;
	
	@XStreamConverter(CDATAConvert.class)
	private String MsgType;
	
	@XStreamConverter(CDATAConvert.class)
	private String Content;
	
	public String getToUserName() {
		return ToUserName;
	}
	
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	
	public String getFromUserName() {
		return FromUserName;
	}
	
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	
	public long getCreateTime() {
		return CreateTime;
	}
	
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	
	public String getMsgType() {
		return MsgType;
	}
	
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	
	public String getContent() {
		return Content;
	}
	
	public void setContent(String content) {
		Content = content;
	}
	
}
