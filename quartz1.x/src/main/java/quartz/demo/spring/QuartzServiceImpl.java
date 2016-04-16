package quartz.demo.spring;

import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdScheduler;
import org.springframework.stereotype.Service;

@Service
public class QuartzServiceImpl {
	
	private StdScheduler scheduler;

	private static String JOB_GROUP_NAME = "group1";
	private static String TRIGGER_GROUP_NAME = "trigger1";

	public void addJobWithSimpleTrig(String jobName,
			Job job, long delayTime) throws SchedulerException {

		JobDetail jobDetail = new JobDetail(jobName, Scheduler.DEFAULT_GROUP,
				job.getClass());
		jobDetail.getJobDataMap().put("test",
				"hello world! Xin, I love you very much!");
		SimpleTrigger trigger = new SimpleTrigger("simpleTrigger",
				Scheduler.DEFAULT_GROUP);
		trigger.setStartTime(new Date(System.currentTimeMillis() + delayTime));
		// trigger.setRepeatInterval((2000>delayTime?delayTime:2000));
		trigger.setRepeatInterval(delayTime);
		trigger.setRepeatCount(0);
		scheduler.scheduleJob(jobDetail, trigger);
		// 启动
		if (!scheduler.isShutdown())
			scheduler.start();
	}

	/**
	 * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
	 * 
	 * @param jobName
	 *            任务名
	 * @param job
	 *            任务
	 * @param time
	 *            时间设置，参考quartz说明文档
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	public void addJob(String jobName, Job job, String time)
			throws SchedulerException, ParseException {
		JobDetail jobDetail = new JobDetail(jobName, JOB_GROUP_NAME,
				job.getClass());// 任务名，任务组，任务执行类
		jobDetail.getJobDataMap().put("test",
				"hello world! Corb, I love you very much!  " + time);
		this.scheduler.addJobListener(new MyJobListener());
		jobDetail.addJobListener("testListener");
		// 触发器
		CronTrigger trigger = new CronTrigger(TRIGGER_GROUP_NAME,
				TRIGGER_GROUP_NAME);// 触发器名,触发器组
		trigger.setCronExpression(time);// 触发器时间设定
		this.scheduler.scheduleJob(jobDetail, trigger);
		// 启动
		if (!this.scheduler.isShutdown()) {
			this.scheduler.start();
			// //true表示等待本次任务执行完成后停止。
			// sched.shutdown(true);
		}
	}

	/**
	 * 添加一个定时任务
	 * 
	 * @param jobName
	 *            任务名
	 * @param jobGroupName
	 *            任务组名
	 * @param triggerName
	 *            触发器名
	 * @param triggerGroupName
	 *            触发器组名
	 * @param job
	 *            任务
	 * @param time
	 *            时间设置，参考quartz说明文档
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	public void addJob(String jobName, String jobGroupName, String triggerName,
			String triggerGroupName, Job job, String time)
			throws SchedulerException, ParseException {
		JobDetail jobDetail = new JobDetail(jobName, jobGroupName,
				job.getClass());// 任务名，任务组，任务执行类
		JobDataMap map = jobDetail.getJobDataMap();
		if (!(job instanceof SpringJobModel)) {
			map.put("test",
					"hello world! Corb, I love you very much! hhhh " + time);
		}else {
			map.put("beanName", "hello");
			map.put("methodName", "sayHello");
			map.put("args", "Hijacker ");
		}
		
		jobDetail.addJobListener("testListener");
		// 触发器
		CronTrigger trigger = new CronTrigger(triggerName, triggerGroupName);// 触发器名,触发器组
		trigger.setCronExpression(time);// 触发器时间设定
		
		this.scheduler.scheduleJob(jobDetail, trigger);
		if (!this.scheduler.isShutdown())
			this.scheduler.start();
	}

	/**
	 * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
	 * 
	 * @param triggerName
	 * @param time
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	public void modifyJobTime(Scheduler sched, String triggerName, String time)
			throws SchedulerException, ParseException {
		System.err.println(sched.hashCode());
		Trigger trigger = sched.getTrigger(triggerName, TRIGGER_GROUP_NAME);
		if (trigger != null) {
			CronTrigger ct = (CronTrigger) trigger;
			ct.setCronExpression(time);
			sched.resumeTrigger(triggerName, TRIGGER_GROUP_NAME);
		}
	}

	/**
	 * 修改一个任务的触发时间
	 * 
	 * @param triggerName
	 * @param triggerGroupName
	 * @param time
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	public void modifyJobTime(Scheduler sched, String triggerName,
			String triggerGroupName, String time) throws SchedulerException,
			ParseException {
		Trigger trigger = sched.getTrigger(triggerName, triggerGroupName);
		if (trigger != null) {
			CronTrigger ct = (CronTrigger) trigger;
			// 修改时间
			ct.setCronExpression(time);
			// 重启触发器
			sched.resumeTrigger(triggerName, triggerGroupName);
		}
	}

	/** */
	/**
	 * 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
	 * 
	 * @param jobName
	 * @throws SchedulerException
	 */
	public void removeJob(String jobName) throws SchedulerException {
		this.scheduler.pauseTrigger(jobName, TRIGGER_GROUP_NAME);// 停止触发器
		this.scheduler.unscheduleJob(jobName, TRIGGER_GROUP_NAME);// 移除触发器
		this.scheduler.deleteJob(jobName, JOB_GROUP_NAME);// 删除任务
	}

	/** */
	/**
	 * 移除一个任务
	 * 
	 * @param jobName
	 * @param jobGroupName
	 * @param triggerName
	 * @param triggerGroupName
	 * @throws SchedulerException
	 */
	public void removeJob(String jobName, String jobGroupName,
			String triggerName, String triggerGroupName)
			throws SchedulerException {
		this.scheduler.pauseTrigger(triggerName, triggerGroupName);// 停止触发器
		this.scheduler.unscheduleJob(triggerName, triggerGroupName);// 移除触发器
		this.scheduler.deleteJob(jobName, jobGroupName);// 删除任务
	}

	public StdScheduler getScheduler() {
		
		return scheduler;
	}
	@Resource(name = "scheduler")
	public void setScheduler(StdScheduler scheduler) {
		this.scheduler = scheduler;
		this.scheduler.addJobListener(new MyJobListener());
	}

}
