package quartz.ramdemo.test;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloJob implements Job {

	Logger logger = LoggerFactory.getLogger(this.getClass());

    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        // ���������ӡ��־���ڵ��ԡ��۲�
        this.logger.debug(this.getClass().getName() + " trigger...");
        System.out.println("Hello World");
    }

}
