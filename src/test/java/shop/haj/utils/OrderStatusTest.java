package shop.haj.utils;

import org.junit.Test;

/**
 * <p>Title: shop.ha.utils</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：4/8/17
 */
public class OrderStatusTest {
	
	@Test
	public void testEnum(){
		System.out.println(OrderStatus.WAITING_SEND.ordinal());
	}
	
}