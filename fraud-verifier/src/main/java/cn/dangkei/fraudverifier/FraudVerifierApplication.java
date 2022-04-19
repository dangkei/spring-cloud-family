package cn.dangkei.fraudverifier;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FraudVerifierApplication {
	private static final Log LOG = LogFactory.getLog(FraudVerifierApplication.class);
	
	private static ApplicationContext  applicationContext;

	public static void main(String[] args) throws Exception {
		applicationContext = SpringApplication.run(FraudVerifierApplication.class, args);

		LOG.debug("Application Started !!!!");

		displayAllBeans();
	}

	public static void displayAllBeans(String... args) throws Exception {
		// TODO Auto-generated method stub
		String[] beans = applicationContext.getBeanDefinitionNames();

		for (String name : beans) {
			LOG.info("bean name>>>>>>>" + name);
		}
	}
}
