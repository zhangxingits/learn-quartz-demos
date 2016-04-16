package quartz.demo.spring.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.UnableToInterruptJobException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("schedulerService")
public class SchedulerServiceImpl {
////  //调度器，任务调度的主API
//
//	@Autowired
//
//    private Scheduler scheduler;
//
//    @Resource
//
//    private SysJobDao sysJobDao;
//    
//  //一个具体任务的设置，指定了要执行的任务和执行任务 的时间策略（用于定义任务的实例）
//
//    @Autowired
//
//    private JobDetail jobDetail;
//
//    private Trigger trigger;// 容器中任务调度器
//    
//    @PostConstruct
//    public void init() {
//
//       try {
//
//           scheduler = StdSchedulerFactory.getDefaultScheduler();
//
//           scheduler.startDelayed(60);
//
////         SchedulerFactory sf = new StdSchedulerFactory();
//
////         scheduler =sf.getScheduler();
//
//           System.out.println("任务调度启动,延时1分钟后开始执行!");
//
//       } catch (Exception e) {
//
//           // TODO 自动生成 catch 块
//
//           e.printStackTrace();
//
//       }
//
//       /**
//
//        * 运行
//
//        */
//
//       @ServiceLog("运行定时任务")
//
//       public void createRunJobByJobId(Long id) {
//
//          try {
//
//              SysJob sysJob=sysJobDao.selectByPrimaryKey(id);
//
//              String name=sysJob.getJobName();
//
//              boolean flag=true;
//
//              if(name==null||sysJob.equals("")){
//
//                 flag=false;
//
//              }else{
//
//                 flag=scheduler.checkExists(new JobKey(name, sysJob.getId()+""));
//
//              }
//
//              //不存在则执行
//
//              if(!flag){
//
//                 name=UUID.randomUUID().toString();
//
//                 sysJob.setJobName(name);
//
//                 jobDetail =newJob(SpringJobModel.class).withIdentity(name, sysJob.getId()+"").build();
//
//                 jobDetail.getJobDataMap().put(SpringJobModel.SRPTING_BEAN_NAME, sysJob.getBeanName());
//
//                 jobDetail.getJobDataMap().put(SpringJobModel.SRPTING_METHOD_NAME, sysJob.getMethodName());
//
//                 // 复杂 的任务调度
//
//                 TriggerBuilder<CronTrigger> builder = newTrigger().withIdentity(name, jobDetail.getKey().getGroup())
//
//                        .withSchedule(cronSchedule(sysJob.getExpression()));
//
//                 // 若有起始时间，则设置
//
//                  String startTime=sysJob.getStarttime();
//
//                  String endTime=sysJob.getEndtime();
//
//                 if (startTime!=null && !startTime.equals("")) {
//
//                     builder.startAt(DateUtil.stringToDate(startTime));
//
//                 }
//
//                 // 设置终止时间
//
//                 if (endTime!=null && !endTime.equals("")) {
//
//                     builder.endAt(DateUtil.stringToDate(endTime));
//
//                 }
//
//                 trigger = builder.build();
//
//                 scheduler.scheduleJob(jobDetail, trigger);
//
//                 //更新
//
//                 sysJob.setState(1);//已运行
//
//              }else{
//
//                 //恢复Job任务开始执行
//
//                 scheduler.resumeJob(new JobKey(name, sysJob.getId()+""));
//
//                 sysJob.setState(1);//已运行
//
//              }
//
//              sysJobDao.updateByPrimaryKey(sysJob);
//
//          } catch (Exception e) {
//
//              e.printStackTrace();
//
//          }
//
//       }
//
//       /**
//
//        * 停止
//
//        */
//
//       @Override
//
//       @ServiceLog("停止定时任务")
//
//       public void createStopJobByJobId(Long id) {
//
//          SysJob sysJob=sysJobDao.selectByPrimaryKey(id);
//
//          try {
//
//              if(sysJob!=null){
//
//                 //暂停job
//
//                 scheduler.pauseJob(new JobKey(sysJob.getJobName(), sysJob.getId()+""));
//
//              }
//
//          } catch (UnableToInterruptJobException e) {
//
//              e.printStackTrace();
//
//          } catch (SchedulerException e) {
//
//              e.printStackTrace();
//
//          }
//
//          sysJob.setState(2);//已停止
//
//          sysJobDao.updateByPrimaryKey(sysJob);
//
//       }
//
//       /**
//
//        * 删除
//
//        */
//
//       @Override
//
//       @ServiceLog("删除定时任务")
//
//       public void deleteByPrimaryKey(Long id) {
//
//          SysJob sysJob=sysJobDao.selectByPrimaryKey(id);
//
//          if(sysJob!=null){
//
//              //删除job
//
//              try {
//
//                 boolean flag=true;
//
//                 String name=sysJob.getJobName();
//
//                 if(name==null||sysJob.equals("")){
//
//                     flag=false;
//
//                 }else{
//
//                     //判断是否存在
//
//                     flag=scheduler.checkExists(new JobKey(name, sysJob.getId()+""));
//
//                 }
//
//                 if(flag){
//
//                     scheduler.deleteJob(new JobKey(name, sysJob.getId()+""));
//
//                 }
//
//              } catch (SchedulerException e) {
//
//                 e.printStackTrace();
//
//              }
//
//          }
//
//          sysJobDao.deleteSysJob(sysJob);
//
//       }
// 
//
//    }
}
