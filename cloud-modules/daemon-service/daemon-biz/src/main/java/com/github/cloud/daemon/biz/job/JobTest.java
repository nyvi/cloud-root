package com.github.cloud.daemon.biz.job;

import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author : czk
 * @date 2018-11-22
 */
public class JobTest extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        System.out.println(jobExecutionContext);
        System.out.println("------- 定时任务测试 --------");
    }
}
