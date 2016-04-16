package quartz.peisidemo.webtest;

import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quartz.peisidemo.test.HelloJob;

public class ApplicationContextListener implements ServletContextListener {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static Scheduler scheduler = null;

	public void contextDestroyed(ServletContextEvent arg0) {
		this.logger.info("The application stop...");

		/* 注销定时任务 */
		try {
			// 关闭Scheduler
			TriggerKey triggerKey=new TriggerKey("trigger1", "group1");
			scheduler.pauseTrigger(triggerKey);//停止触发器  
			scheduler.unscheduleJob(triggerKey);
			scheduler.deleteJob(new JobKey("job1", "group1"));
			scheduler.shutdown();

			this.logger.info("The scheduler shutdown...");
		} catch (SchedulerException se) {
			logger.error(se.getMessage(), se);
		}
	}

	public void contextInitialized(ServletContextEvent arg0) {
		this.logger.info("The application start...");

		 /* 注册定时任务 */
		try {
			// 获取Scheduler实例
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();

//			// 具体任务
//			JobDetail job = JobBuilder.newJob(HelloJob.class)
//					.withIdentity("job1", "group1").build();
//
//			// 触发时间点
//			SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder
//					.simpleSchedule().withIntervalInSeconds(2).repeatForever();
//			Trigger trigger = TriggerBuilder.newTrigger()
//					.withIdentity("trigger1", "group1").startNow()
//					.withSchedule(simpleScheduleBuilder).build();
//
//			// 交由Scheduler安排触发
//			scheduler.scheduleJob(job, trigger);
			
			//Cron Schedule的使用
			// 具体任务
			JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();
			JobDataMap map = job.getJobDataMap();
			map.put("key", UUID.randomUUID().toString());
			// 触发时间点
			CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/3 * * * * ? *");
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
			        .withSchedule(cronScheduleBuilder)/*.endAt(new Date(System.currentTimeMillis()+50000l))*/.build();

			// 交由Scheduler安排触发
			scheduler.scheduleJob(job, trigger);

			this.logger.info("The scheduler register...");
		} catch (SchedulerException se) {
			logger.error(se.getMessage(), se);
		}
	}

}
