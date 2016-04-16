package quartz.ramdemo.webtest;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quartz.ramdemo.test.HelloJob;

public class ApplicationContextListener implements ServletContextListener {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static Scheduler scheduler = null;

	public void contextDestroyed(ServletContextEvent arg0) {
		this.logger.info("The application stop...");

		/* ע����ʱ���� */
		try {
			// �ر�Scheduler
			scheduler.shutdown();

			this.logger.info("The scheduler shutdown...");
		} catch (SchedulerException se) {
			logger.error(se.getMessage(), se);
		}
	}

	public void contextInitialized(ServletContextEvent arg0) {
		this.logger.info("The application start...");

		/* ע�ᶨʱ���� */
		try {
			// ��ȡSchedulerʵ��
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();

//			// ��������
//			JobDetail job = JobBuilder.newJob(HelloJob.class)
//					.withIdentity("job1", "group1").build();
//
//			// ����ʱ���
//			SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder
//					.simpleSchedule().withIntervalInSeconds(2).repeatForever();
//			Trigger trigger = TriggerBuilder.newTrigger()
//					.withIdentity("trigger1", "group1").startNow()
//					.withSchedule(simpleScheduleBuilder).build();
//
//			// ����Scheduler���Ŵ���
//			scheduler.scheduleJob(job, trigger);
			
			//Cron Schedule��ʹ��
			// ��������
			JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();

			// ����ʱ���
			CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/2 * * * * ? *");
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
			        .withSchedule(cronScheduleBuilder).build();

			// ����Scheduler���Ŵ���
			scheduler.scheduleJob(job, trigger);

			this.logger.info("The scheduler register...");
		} catch (SchedulerException se) {
			logger.error(se.getMessage(), se);
		}
	}

}
