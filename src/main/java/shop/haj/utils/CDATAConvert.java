package shop.haj.utils;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * <p>Title: shop.ha.utils</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/9/17
 */
public class CDATAConvert implements Converter {
	
	@Override
	public void marshal(Object o, HierarchicalStreamWriter hierarchicalStreamWriter, MarshallingContext marshallingContext) {
		String prefix = "<![CDATA[";
		String suffix = "]]>";
		String trans = prefix + o + suffix;
		hierarchicalStreamWriter.setValue(trans);
	}
	
	@Override
	public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
		return hierarchicalStreamReader.getValue();
	}
	
	@Override
	public boolean canConvert(Class paramClass) {
		return String.class.isAssignableFrom(paramClass);
	}
}
