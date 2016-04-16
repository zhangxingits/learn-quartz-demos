package quartz.ramdemo.test;

import java.util.concurrent.TimeUnit;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Bootstrap {
    private static Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    public static void main(String[] args) {

        try {
            // ��ȡSchedulerʵ��
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();

            // ��������
            JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();

            // ����ʱ���
            SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(2).repeatForever();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                    .startNow().withSchedule(simpleScheduleBuilder).build();

            // ����Scheduler���Ŵ���
            scheduler.scheduleJob(job, trigger);
            
            /* Ϊ�۲�������У�������������˯��3���Ӳż����������У�����һ�������ǡ��ر�Scheduler���� */
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // �ر�Scheduler
            scheduler.shutdown();

        } catch (SchedulerException se) {
            logger.error(se.getMessage(), se);
        }
    }

}
