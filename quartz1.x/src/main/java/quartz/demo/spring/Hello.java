package quartz.demo.spring;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import quartz1.x.test.HelloJob;
import quartz1.x.test.Testdemo;

@Service("hello")
public class Hello {
	public String sayHello(String hello) {
		return hello+" to you !";
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:applicationcontext.xml");
		QuartzServiceImpl quartzService = context.getBean(QuartzServiceImpl.class);
		ExecutorService executorService = Executors.newFixedThreadPool(6);
		for (int i = 0; i < 10; i++) {
			executorService.execute(new Runnable() {
				private int j;
				private QuartzServiceImpl quartzService;
				public Runnable setJ(int j,QuartzServiceImpl quartzService) {
					this.j=j;
					this.quartzService=quartzService;
					return this;
				}
				public void run() {
//					System.err.println(Thread.currentThread().getName());
					String time = CronUtil.formatDateByPattern(new Date(System.currentTimeMillis()+(j+22)*1000l), "yyyy-M-d H:m:s");
					try {
						HelloJob job = new HelloJob();
						quartzService
								.addJob("jobName_"
										+ j,
										"jobGroupName_"
												+ j,
										"triggerName_"
												+ j,
										"triggerGroupName_"
												+ j,
										job,
										CronUtil.getCron(new SimpleDateFormat(
												"yyyy-M-d H:m:s")
												.parse(time)));
					} catch (SchedulerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.setJ(i,quartzService));
		}
	}
}
