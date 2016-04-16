package quartz1.x.test;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloJob implements Job {

	private static final Logger logger = LoggerFactory.getLogger(HelloJob.class);
	
	private String id;

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
        //// 此任务仅打印日志便于调试、观察
        logger.debug(this.getClass().getName() + " trigger...");
        System.err.println("AAAAAAAAAAAAAAAAAAAHello World! ====="+getId()+"====test="+arg0.getJobDetail().getJobDataMap().get("test").toString());
    }

}
