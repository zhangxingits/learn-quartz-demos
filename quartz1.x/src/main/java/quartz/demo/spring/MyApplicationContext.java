package quartz.demo.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MyApplicationContext implements ApplicationContextAware {

	public static ApplicationContext ctx;

	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		MyApplicationContext.ctx = arg0;
		
	}
	public static <T> T getBean(Class<T> requireType) {
		return MyApplicationContext.ctx.getBean(requireType);
	}

}
