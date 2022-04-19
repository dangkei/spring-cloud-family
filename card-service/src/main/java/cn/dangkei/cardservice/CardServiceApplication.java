package cn.dangkei.cardservice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class CardServiceApplication{
	private final static Log LOG =  LogFactory.getLog(CardServiceApplication.class);
	
	private static ApplicationContext applicationContext;
	
	public static void main(String[] args) throws Exception {
		applicationContext = SpringApplication.run(CardServiceApplication.class, args);
		//displayAllBeans();
	}

	public static void displayAllBeans(String... args) throws Exception {
		// TODO Auto-generated method stub
		String[] beans  = applicationContext.getBeanDefinitionNames();
		
		for(String name : beans) {
			LOG.info("bean name>>>>>>>"+ name);
		}
	}
}
