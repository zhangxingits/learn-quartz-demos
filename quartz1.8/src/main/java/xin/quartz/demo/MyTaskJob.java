package xin.quartz.demo;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import java.util.Date;

/**
 * Created by 张新 on 2016/4/15.
 */
public class MyTaskJob implements StatefulJob {
    private static int count=0;
    private int timeout;

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /*@Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        count++;
        System.err.println("正在执行任务=="+count+"====="+new Date());
    }*/

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Object count = context.getJobDetail().getJobDataMap().get("count");
        if (count==null){
            context.getJobDetail().getJobDataMap().put("count", 0);
        }
        int num=Integer.parseInt(context.getJobDetail().getJobDataMap().get("count").toString());
        context.getJobDetail().getJobDataMap().put("count", ++num);
        System.err.println("正在执行任务=="+ num +"====="+new Date());
    }
}
