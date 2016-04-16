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
        System.err.println("正在执行任务="+count+"====="+new Date());
    }*/

    public void execute(JobExecutionContext context) throws JobExecutionException {
        Object count = context.getJobDetail().getJobDataMap().get("count");
        if (count==null){
            context.getJobDetail().getJobDataMap().put("count", 0);
        }
        int num=Integer.parseInt(context.getJobDetail().getJobDataMap().get("count").toString());
        context.getJobDetail().getJobDataMap().put("count", ++num);

        System.err.println("正在开始执行任务=="+ num +"====="+new Date());
        /*try {
            Thread.sleep(4000l);
            System.out.println("任务"+num+"执行完成！"+new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
