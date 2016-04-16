package xin.quartz.demo.listener;

import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.SchedulerListener;
import org.quartz.Trigger;

/**
 * Created by 新 on 2016/4/16.
 */
public class MySchedulerListener implements SchedulerListener {

    //有新的JobDetail部署调用这个方法。
    @Override
    public void jobScheduled(Trigger trigger) {
        System.out.println("jobScheduled");
    }

    //卸载时调用这个方法。
    @Override
    public void jobUnscheduled(String triggerName, String triggerGroup) {
        System.out.println("jobUnscheduled");
    }

    //当一个Trigger到达再也不会触发时调用这个方法。
    @Override
    public void triggerFinalized(Trigger trigger) {
        System.out.println("triggerFinalized");
    }

    //Scheduler 调用这个方法是发生在一个Trigger或多个Trigger被暂停时。假如是多个Trigger的话，triggerName 参数将为null。
    @Override
    public void triggersPaused(String triggerName, String triggerGroup) {
        System.out.println("triggersPaused");
    }

    //Scheduler 调用这个方法是发生成一个 Trigger 或 Trigger 组从暂停中恢复时。假如是多个Trigger的话，triggerName 参数将为 null。
    @Override
    public void triggersResumed(String triggerName, String triggerGroup) {
        System.out.println("triggersResumed");
    }

    //当一个或一组 Job 从暂停上恢复时调用这个方法。假如是多个Job，jobName参数将为 null。
    @Override
    public void jobAdded(JobDetail jobDetail) {
        System.out.println("jobAdded");
    }

    @Override
    public void jobDeleted(String jobName, String groupName) {
        System.out.println("jobDeleted");
    }

    //当一个或一组 JobDetail 暂停时调用这个方法。
    @Override
    public void jobsPaused(String jobName, String jobGroup) {
        System.out.println("jobsPaused");
    }

    //当一个或一组 Job 从暂停上恢复时调用这个方法。假如是多个Job，jobName参数将为 null。
    @Override
    public void jobsResumed(String jobName, String jobGroup) {
        System.out.println("jobsResumed");
    }

    // 在Scheduler 的正常运行期间产生一个严重错误时调用这个方法。错误的类型会各式的，但是下面列举了一些错误例子：
    // 可以使用 SchedulerException 的 getErrorCode() 或者 getUnderlyingException() 方法或获取到特定错误的更详尽的信息
    @Override
    public void schedulerError(String msg, SchedulerException cause) {
        System.out.println("schedulerError");
    }

    @Override
    public void schedulerInStandbyMode() {
        System.out.println("schedulerInStandbyMode");
    }

    @Override
    public void schedulerStarted() {
        System.out.println("schedulerStarted");
    }

    //Scheduler 调用这个方法用来通知 SchedulerListener Scheduler 将要被关闭。
    @Override
    public void schedulerShutdown() {
        System.out.println("schedulerShutdown");
    }

    @Override
    public void schedulerShuttingdown() {
        System.out.println("schedulerShuttingdown");
    }
}
