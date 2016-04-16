package quartz.demo2.test;

import java.lang.reflect.Method;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.context.ContextLoader;

public class SpringJobModel implements Job {

	public static String SRPTING_BEAN_NAME = "beanName";

	public static String SRPTING_METHOD_NAME = "methodName";

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		JobDataMap data = context.getJobDetail().getJobDataMap();

		System.out.println("bean:" + data.getString(SRPTING_BEAN_NAME)
				+ "------------method:" + data.getString(SRPTING_METHOD_NAME));

		try {

			invokeMethod(data.getString(SRPTING_BEAN_NAME),
					data.getString(SRPTING_METHOD_NAME), null);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	public void invokeMethod(String owner, String methodName, Object[] args)throws Exception {
		System.out.println("ContextLoader.getCurrentWebApplicationContext()-----------"
						+ ContextLoader.getCurrentWebApplicationContext());

		Object ownerClass = ContextLoader.getCurrentWebApplicationContext()
				.getBean(owner);

		Method method = ownerClass.getClass().getMethod(methodName, null);

		method.invoke(ownerClass, args);

	}

}
