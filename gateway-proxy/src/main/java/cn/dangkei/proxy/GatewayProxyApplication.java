package cn.dangkei.proxy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class GatewayProxyApplication {
	private static final Log LOG = LogFactory.getLog(GatewayProxyApplication.class);
	
	private static ApplicationContext applicationContext;
	
	public static void main(String[] args) {
		applicationContext = SpringApplication.run(GatewayProxyApplication.class, args);
		// displayAllBeans();
	}

	public static void displayAllBeans(String... args) throws Exception {
		// TODO Auto-generated method stub
		String[] beans = applicationContext.getBeanDefinitionNames();

		for (String name : beans) {
			LOG.info("bean name>>>>>>>" + name);
		}
	}

}
