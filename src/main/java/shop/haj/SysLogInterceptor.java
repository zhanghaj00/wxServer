package shop.haj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title: shop.ha</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/20/17
 */
public class SysLogInterceptor implements HandlerInterceptor {
	private Logger logger = LogManager.getLogger(SysLogInterceptor.class);
	
	public SysLogInterceptor() {
	}
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
		this.logger.info("------------preHandle------------");
		this.logger.info("request url:{}, address:{}, haders:{}", request.getRequestURL(), request.getRemoteAddr());
		return true;
	}
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
		this.logger.info("------------postHandle------------");
	}
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
		this.logger.info("------------afterCompletion------------");
	}
}
