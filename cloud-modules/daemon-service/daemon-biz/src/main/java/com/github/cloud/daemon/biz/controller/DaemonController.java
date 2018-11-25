package com.github.cloud.daemon.biz.controller;

import com.github.cloud.common.core.annotations.RepeatSubmit;
import com.github.cloud.common.core.util.Result;
import com.github.cloud.common.mybatis.dto.PageDTO;
import com.github.cloud.common.web.util.ServiceHelper;
import com.github.cloud.daemon.biz.domain.request.QuartzInsertRequest;
import com.github.cloud.daemon.biz.domain.request.SearchRequest;
import com.github.cloud.daemon.biz.domain.request.TriggerRequest;
import com.github.cloud.daemon.biz.domain.vo.QuartzVO;
import com.github.cloud.daemon.biz.service.DaemonService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : czk
 * @date 2018-11-23
 */
@RestController
@RequestMapping("job")
public class DaemonController {

    private final DaemonService daemonService;

    @Autowired
    public DaemonController(DaemonService daemonService) {
        this.daemonService = daemonService;
    }

    @RepeatSubmit
    @PostMapping("saveOrUpdate")
    @ApiOperation("定时任务添加/更新")
    public Result<Boolean> saveOrUpdate(@RequestBody QuartzInsertRequest request) {
        return ServiceHelper.execute(request, () -> daemonService.saveOrUpdate(request));
    }

    @RepeatSubmit
    @PostMapping("trigger")
    @ApiOperation("触发定时任务")
    public Result<Boolean> trigger(@RequestBody TriggerRequest request) {
        return ServiceHelper.execute(request, () -> daemonService.trigger(request));
    }

    @RepeatSubmit
    @PostMapping("pause")
    @ApiOperation("停止任务")
    public Result<Boolean> pause(@RequestBody TriggerRequest request) {
        return ServiceHelper.execute(request, () -> daemonService.pause(request));
    }

    @RepeatSubmit
    @PostMapping("resume")
    @ApiOperation("恢复任务")
    public Result<Boolean> resume(@RequestBody TriggerRequest request) {
        return ServiceHelper.execute(request, () -> daemonService.resume(request));
    }

    @RepeatSubmit
    @PostMapping("remove")
    @ApiOperation("删除任务")
    public Result<Boolean> remove(@RequestBody TriggerRequest request) {
        return ServiceHelper.execute(request, () -> daemonService.remove(request));
    }

    @PostMapping("listPage")
    @ApiOperation("定时任务列表")
    public Result<PageDTO<QuartzVO>> listPage(@RequestBody SearchRequest request) {
        return ServiceHelper.execute(request, () -> daemonService.listPage(request));
    }
}
