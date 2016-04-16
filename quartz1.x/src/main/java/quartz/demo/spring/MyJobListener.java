package quartz.demo.spring;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.SchedulerException;

public class MyJobListener implements JobListener {

	public String getName() {
		// TODO Auto-generated method stub
		return "testListener";
	}

	public void jobExecutionVetoed(JobExecutionContext arg0) {
		System.err.println("The JobDetail was about to be executed");

	}

	public void jobToBeExecuted(JobExecutionContext arg0) {
		System.err.println("The JobDetail is about to be executed ");
		System.err.println(CronUtil.formatDateByPattern(new Date(System.currentTimeMillis()), "yyyy-M-d H:m:s"));

	}

	public void jobWasExecuted(JobExecutionContext context,
			JobExecutionException arg1) {
		System.err.println("The JobDetail has been executed");
		System.err.println(CronUtil.formatDateByPattern(new Date(System.currentTimeMillis()), "yyyy-M-d H:m:s"));
		try {
			QuartzServiceImpl quartzServiceImpl = MyApplicationContext.getBean(QuartzServiceImpl.class);
			quartzServiceImpl.removeJob(context.getJobDetail().getName(), context.getJobDetail().getGroup()
					, context.getTrigger().getName(), context.getTrigger().getGroup());
			System.err.println("removed job");
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
