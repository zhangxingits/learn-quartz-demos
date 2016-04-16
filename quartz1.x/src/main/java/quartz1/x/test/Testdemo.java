package quartz1.x.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Before;
import org.junit.Test;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import quartz.demo.spring.CronUtil;
import quartz.demo.spring.QuartzServiceImpl;
import quartz.demo.spring.SpringJobModel;

public class Testdemo {
	private QuartzServiceImpl quartzService = null;

	@Before
	public void init() {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext(
					"classpath:applicationcontext.xml");
			quartzService = context.getBean(QuartzServiceImpl.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMultThread() {
		ExecutorService executorService = Executors.newFixedThreadPool(6);
		for (int i = 0; i < 10; i++) {
			executorService.execute(new Runnable() {
				private int j;
				public Runnable setJ(int j) {
					this.j=j;
					return this;
				}
				public void run() {
//					System.err.println(Thread.currentThread().getName());
					String time = CronUtil.formatDateByPattern(new Date(System.currentTimeMillis()+(j+8)*1000l), "yyyy-M-d H:m:s");
					try {
						Testdemo.this.quartzService
								.addJob("jobName_"
										+ Thread.currentThread().getId(),
										"jobGroupName_"
												+ Thread.currentThread()
														.getId(),
										"triggerName_"
												+ Thread.currentThread()
														.getId(),
										"triggerGroupName_"
												+ Thread.currentThread()
														.getId(),
										new HelloJob(),
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
			}.setJ(i));
		}
	}

	@Test
	public void testQz() throws SchedulerException, ParseException {
		// System.out.println(quartzService.getScheduler().getSchedulerName());
		// this.quartzService.addJob("jobName", "jobGroupName", "triggerName",
		// "triggerGroupName", new HelloJob(), CronUtil
		// .getCron(new SimpleDateFormat("yyyy-M-d H:m:s")
		// .parse("2016-2-25 15:33:19")));

//		this.quartzService.addJob("jobName1", "jobGroupName1", "triggerName1",
//				"triggerGroupName1", new SpringJobModel(), CronUtil
//						.getCron(new SimpleDateFormat("yyyy-M-d H:m:s")
//								.parse("2016-4-2 10:4:39")));
		this.quartzService.addJob("jobName1", "jobGroupName1", "triggerName1",
				"triggerGroupName1", new SpringJobModel(), "0/2 * * * * ?");

	}
}
