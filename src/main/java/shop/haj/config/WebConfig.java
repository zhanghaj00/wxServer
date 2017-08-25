package shop.haj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import shop.haj.CustomerRequestInterceptor;
import shop.haj.TimeCostInterceptor;

/**
 * <p>Title: shop.ha.config</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：5/14/17
 */
@Configuration
@EnableScheduling
public class WebConfig extends WebMvcConfigurerAdapter{
	
	
	@Bean
	public CustomerRequestInterceptor customerRequestInterceptor(){
		return new CustomerRequestInterceptor();
		
	}
	
	public void addInterceptors(InterceptorRegistry registry) {
//		logger.info("---addInterceptors---");
		registry.addInterceptor(new TimeCostInterceptor()).addPathPatterns(new String[]{"/**"});
		
		registry.addInterceptor(customerRequestInterceptor()).addPathPatterns(
				new String[]{
						"/v1/customer/orders/**",
						"/v1/customer/addresses/**",
						"/v1/customer/coupons/**",
						"/v1/customer/carts/**",
						"/v1/customer/favorite_goods/**",
						"/v1/customer/visit_shops/**",
						"/v1/customer/comments/**"
				});
	}
	
}
