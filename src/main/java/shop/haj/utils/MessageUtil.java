package shop.haj.utils;

import com.thoughtworks.xstream.XStream;
import org.apache.commons.lang3.StringEscapeUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import shop.haj.entity.wxpublic.TextMessage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: shop.ha.utils</p>
 * <p/>
 * <p>
 * Description: XML的处理
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/9/17
 */
public class MessageUtil {
	
	/**
	 * xml转成map
	 * @param xmlData
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static Map<String, String> xmlToMap(String xmlData) throws IOException, DocumentException {
		
		Map<String, String> map = new HashMap<>();
		
		SAXReader reader = new SAXReader();
		InputStream is = new ByteArrayInputStream(xmlData.getBytes("UTF-8"));
		Document document = reader.read(is);
		
		Element root = document.getRootElement();
		List<Element> elements = root.elements();
		
		for (Element element : elements) {
			map.put(element.getName(), element.getText());
		}
		
		is.close();
		return map;
	}
	
	/**
	 * 将文本类型对象转成xml
	 * @param textMessage
	 * @return
	 */
	public static String textMessageToXml(TextMessage textMessage){
		XStream xStream = new XStream();
		xStream.alias("xml", textMessage.getClass());
		String result = xStream.toXML(textMessage);
		return StringEscapeUtils.unescapeXml(result);
	}
	
	
	public static void main(String[] args) {
		
		TextMessage textMessage = new TextMessage();
		textMessage.setContent("hello");
		textMessage.setCreateTime(123456);
		textMessage.setFromUserName("username");
		textMessage.setMsgType("text");
		textMessage.setToUserName("tousername");
		
		System.out.println(MessageUtil.textMessageToXml(textMessage));
	}
}
