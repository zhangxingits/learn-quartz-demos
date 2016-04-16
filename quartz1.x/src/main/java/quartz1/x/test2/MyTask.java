package quartz1.x.test2;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MyTask extends QuartzJobBean {


    private int timeout;
    //调度工厂实例化后，经过timeout时间开始执行调度
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		System.err.println("任务调度中=======10秒钟执行一次"+new Date());

	}

}
