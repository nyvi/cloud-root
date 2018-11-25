package com.github.cloud.daemon.biz.service.impl;

import com.github.cloud.common.core.util.Result;
import com.github.cloud.common.core.util.StrUtils;
import com.github.cloud.common.mybatis.dto.PageDTO;
import com.github.cloud.common.mybatis.util.PageHelper;
import com.github.cloud.daemon.biz.domain.request.QuartzInsertRequest;
import com.github.cloud.daemon.biz.domain.request.SearchRequest;
import com.github.cloud.daemon.biz.domain.request.TriggerRequest;
import com.github.cloud.daemon.biz.domain.vo.QuartzVO;
import com.github.cloud.daemon.biz.mapper.DaemonMapper;
import com.github.cloud.daemon.biz.service.DaemonService;
import lombok.AllArgsConstructor;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : czk
 * @date 2018-11-23
 */
@Service
@AllArgsConstructor
public class DaemonServiceImpl implements DaemonService {

    private final Scheduler scheduler;

    private final DaemonMapper daemonMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @SuppressWarnings("all")
    public Result<Boolean> saveOrUpdate(QuartzInsertRequest request) throws Exception {
        // 修改就删除旧的添加新的
        if (StrUtils.isNotBlank(request.getOldJobGroup())) {
            JobKey key = new JobKey(request.getOldJobName(), request.getOldJobGroup());
            scheduler.deleteJob(key);
        }
        Class cls = Class.forName(request.getJobClassName());
        cls.newInstance();
        // 构建定时任务
        JobDetail job = JobBuilder.newJob(cls).withIdentity(request.getJobName(), request.getJobGroup())
                .withDescription(request.getDescription())
                .build();
        // 触发时间点
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(request.getCronExpression());
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger" + request.getJobName(), request.getJobGroup())
                .startNow()
                .withSchedule(cronScheduleBuilder)
                .build();
        scheduler.scheduleJob(job, trigger);
        return Result.success();
    }

    @Override
    public Result<Boolean> trigger(TriggerRequest request) {
        JobKey key = new JobKey(request.getJobName(), request.getJobGroup());
        try {
            scheduler.triggerJob(key);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return Result.error();
        }
        return Result.success();
    }

    @Override
    public Result<Boolean> pause(TriggerRequest request) {
        JobKey key = new JobKey(request.getJobName(), request.getJobGroup());
        try {
            scheduler.pauseJob(key);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return Result.error();
        }
        return Result.success();
    }

    @Override
    public Result<Boolean> resume(TriggerRequest request) {
        JobKey key = new JobKey(request.getJobName(), request.getJobGroup());
        try {
            scheduler.resumeJob(key);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return Result.error();
        }
        return Result.success();
    }

    @Override
    public Result<Boolean> remove(TriggerRequest request) {
        TriggerKey triggerKey = TriggerKey.triggerKey(request.getJobName(), request.getJobGroup());
        try {
            // 停止触发器
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器
            scheduler.unscheduleJob(triggerKey);
            // 删除任务
            scheduler.deleteJob(JobKey.jobKey(request.getJobName(), request.getJobGroup()));
        } catch (SchedulerException e) {
            e.printStackTrace();
            return Result.error();
        }
        return Result.success();
    }

    @Override
    public Result<PageDTO<QuartzVO>> listPage(SearchRequest request) {
        return Result.success(PageHelper.convert(daemonMapper.listPage(request.convertFor(), request)));
    }


}
