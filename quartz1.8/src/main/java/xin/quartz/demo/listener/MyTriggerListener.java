package xin.quartz.demo.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

/**
 * Created by 新 on 2016/4/16.
 */
public class MyTriggerListener implements TriggerListener {
    //命名triggerListener 只对非全局监听器有效
    @Override
    public String getName() {
        return "mytriggerlistener";
    }

    //当与监听器相关联的 Trigger 被触发，Job 上的 execute() 方法将要被执行时，调用这个方法。
    //在全局TriggerListener 情况下，这个方法为所有 Trigger 被调用。(不要做耗时操作)
    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        System.err.println("triggerFired");
    }

    //在 Trigger 触发后，Job 将要被执行时由调用这个方法。
    //TriggerListener给了一个选择去否决 Job 的执行。假如这个方法返回 true，这个 Job 将不会为此次 Trigger 触发而得到执行。
    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        System.err.println("triggervetoJobExecution");
        return false;
    }

    // Scheduler 调用这个方法是在 Trigger 错过触发时。
    // JavaDoc 指出:你应该关注此方法中持续时间长的逻辑：在出现许多错过触发的 Trigger 时，长逻辑会导致骨牌效应。你应当保持这上方法尽量的小
    @Override
    public void triggerMisfired(Trigger trigger) {
        System.err.println("triggerMisfired");
    }

    //Trigger 被触发并且完成了Job的执行时调用这个方法。
    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, int triggerInstructionCode) {
        System.err.println("triggerComplete");
    }
}
