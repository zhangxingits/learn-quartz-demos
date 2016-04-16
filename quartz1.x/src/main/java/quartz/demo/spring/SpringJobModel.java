package quartz.demo.spring;

import java.lang.reflect.Method;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.context.ContextLoader;

public class SpringJobModel implements Job {

	public static String SRPTING_BEAN_NAME = "beanName";

	public static String SRPTING_METHOD_NAME = "methodName";

	public static String SRPTING_METHOD_ARGS = "args";

	public void execute(JobExecutionContext context)
			throws JobExecutionException {

		JobDataMap data = context.getJobDetail().getJobDataMap();
		System.err.println("bean:" + data.getString(SRPTING_BEAN_NAME)
				+ "------------method:" + data.getString(SRPTING_METHOD_NAME)
				+ "------------args:" + data.getString(SRPTING_METHOD_ARGS));
		try {
			invokeMethod(data.getString(SRPTING_BEAN_NAME),
					data.getString(SRPTING_METHOD_NAME), new Object[]{data.getString(SRPTING_METHOD_ARGS)});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void invokeMethod(String owner, String methodName, Object[] args)
			throws Exception {
		System.err
				.println("ContextLoader.getCurrentWebApplicationContext()-----------"
						+ ContextLoader.getCurrentWebApplicationContext());
		Object ownerClass = ContextLoader.getCurrentWebApplicationContext()
				.getBean(owner);
		Method method = ownerClass.getClass().getMethod("sayHello", String.class);
		String result = (String) method.invoke(ownerClass, args);
		System.err.println("result is : "+result);
	}

}
