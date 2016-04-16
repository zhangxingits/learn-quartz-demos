package quartz.demo.spring.service;

import java.io.Serializable;

public class SysJob implements Serializable{

	private static final long serialVersionUID = -860995656179167881L;

	private Long id; //定时器JobKey的group
	
	private String name; //定时器名称

    private String jobName; //定时器JobKey的Name

    private String beanName;//bean名称

    private String methodName;//方法名称

    private String expression;// 调度表达式

    private String starttime;// 开始时间

    private String endtime;// 结束时间

    private String remark;//备注 

    private String createtime;// 创建时间 

    private Integer state=2;// 运行状态：1、运行  2、停止

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
    
    
}
