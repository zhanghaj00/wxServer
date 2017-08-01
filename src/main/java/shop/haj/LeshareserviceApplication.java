package shop.haj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LeshareserviceApplication{
	private Logger logger = LogManager.getLogger(LeshareserviceApplication.class);
	
	public LeshareserviceApplication() {
	}
	
	public static void main(String[] args) {
		SpringApplication.run(LeshareserviceApplication.class, args);
		
	}
}
