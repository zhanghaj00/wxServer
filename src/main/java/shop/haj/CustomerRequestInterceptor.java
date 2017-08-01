package shop.haj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import shop.haj.entity.Customer;
import shop.haj.service.CustomerService;
import shop.haj.service.WxAuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title: shop.ha</p>
 * <p/>
 * <p>
 * Description: CustomerRequestInterceptor
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/20/17
 */
public class CustomerRequestInterceptor implements HandlerInterceptor {
	
	private Logger logger = LogManager.getLogger(CustomerRequestInterceptor.class);
	
	@Autowired
	private WxAuthService wxAuthService;
	
	@Autowired
	private CustomerService customerService;
	
	public CustomerRequestInterceptor() {
	}
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String wxLoginCode = request.getHeader("login_code");
		
		if(wxLoginCode == null || wxLoginCode.length() == 0) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return false;
		}
		
		//从缓存中获取customer_id
		String customer_id = wxAuthService.decodehaSession(wxLoginCode);
		
		if(null == customer_id){
			logger.error("sessionKey not exists, by sessionId:{}", wxLoginCode);
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return false;
		}
		
		Customer customer = customerService.findById(customer_id);
		
		if(customer == null) {
			
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			
			return false;
		}
		
		request.setAttribute("customer_id", customer.getId());
		
		return true;
	}
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
	}
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
	}
	
}
