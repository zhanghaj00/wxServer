package shop.haj;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

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
public class TimeCostInterceptor implements HandlerInterceptor {
	private Logger logger = LogManager.getLogger(TimeCostInterceptor.class);
	
	public TimeCostInterceptor() {
	}
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", Long.valueOf(startTime));
		if(handler instanceof HandlerMethod) {
			StringBuilder sb = new StringBuilder(1000);
			sb.append("-----------------------").append((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date())).append("-------------------------------------\n");
			HandlerMethod h = (HandlerMethod)handler;
			sb.append("Controller: ").append(h.getBean().getClass().getName()).append("\n");
			sb.append("Method    : ").append(h.getMethod().getName()).append("\n");
			sb.append("Params    : ").append(this.getParamString(request.getParameterMap())).append("\n");
			sb.append("URI       : ").append(request.getRequestURI()).append("\n");
			sb.append("URL       : ").append(request.getRequestURL()).append("\n");
			sb.append("Address   : ").append(request.getRemoteAddr()).append("\n");
			this.logger.info(sb.toString());
		}
		
		return true;
	}
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		long startTime = ((Long)request.getAttribute("startTime")).longValue();
		long endTime = System.currentTimeMillis();
		long executeTime = endTime - startTime;
		if(handler instanceof HandlerMethod) {
			StringBuilder sb = new StringBuilder(1000);
			sb.append("URL       : ").append(request.getRequestURL()).append("\n");
			sb.append("Address   : ").append(request.getRemoteAddr()).append("\n");
			sb.append("CostTime  : ").append(executeTime).append("ms").append("\n");
			sb.append("-------------------------------------------------------------------------------");
			this.logger.info(sb.toString());
		}
		
	}
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
	}
	
	private String getParamString(Map<String, String[]> map) {
		StringBuilder sb = new StringBuilder();
		Iterator var3 = map.entrySet().iterator();
		
		while(true) {
			while(var3.hasNext()) {
				Entry e = (Entry)var3.next();
				sb.append((String)e.getKey()).append("=");
				String[] value = (String[])e.getValue();
				if(value != null && value.length == 1) {
					sb.append(value[0]).append("\t");
				} else {
					sb.append(Arrays.toString(value)).append("\t");
				}
			}
			
			return sb.toString();
		}
	}
}
