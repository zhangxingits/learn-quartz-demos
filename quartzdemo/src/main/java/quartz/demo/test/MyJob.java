package quartz.demo.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job {

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("Hello quzrtz  "
				+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")
						.format(new Date()));
	}

}
