package xin.quartz.demo.listener;

import org.quartz.SchedulerException;
import org.quartz.listeners.SchedulerListenerSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 新 on 2016/4/16.
 * 由于scheduler异常存在不打印问题,CRM使用监听器代码打印.
 */
public class MySchedulerListenerSupport extends SchedulerListenerSupport {
    private static final Logger LOGGER= LoggerFactory.getLogger(MySchedulerListenerSupport.class);

    @Override
    public void schedulerError(String msg, SchedulerException cause) {
        super.schedulerError(msg, cause);
        LOGGER.error("MySchedulerListenerSupport***"+msg);
    }
}
