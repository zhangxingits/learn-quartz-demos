package xin.quartz.demo.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * Created by 新 on 2016/4/16.
 */
public class MyJobListener implements JobListener {
    //命名jobListener 只对非全局监听器有效
    @Override
    public String getName() {
        return "myjoblistener";
    }

    //Scheduler 在 JobDetail 将要被执行时调用这个方法。
    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        System.out.println("jobToBeExecuted");
    }

    //Scheduler 在 JobDetail 即将被执行，但又被否决时调用这个方法。
    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        System.out.println("jobExecutionVetoed");
    }

    //Scheduler 在 JobDetail 被执行之后调用这个方法。
    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        System.out.println("jobWasExecuted***");
    }
}
