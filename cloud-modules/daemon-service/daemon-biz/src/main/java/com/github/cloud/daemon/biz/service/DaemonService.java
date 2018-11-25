package com.github.cloud.daemon.biz.service;

import com.github.cloud.common.core.util.Result;
import com.github.cloud.common.mybatis.dto.PageDTO;
import com.github.cloud.daemon.biz.domain.request.QuartzInsertRequest;
import com.github.cloud.daemon.biz.domain.request.SearchRequest;
import com.github.cloud.daemon.biz.domain.request.TriggerRequest;
import com.github.cloud.daemon.biz.domain.vo.QuartzVO;

/**
 * @author : czk
 * @date 2018-11-23
 */
public interface DaemonService {

    /**
     * 任务添加/修改
     *
     * @param request 请求参数
     * @return 返回状态
     * @throws Exception 异常
     */
    Result<Boolean> saveOrUpdate(QuartzInsertRequest request) throws Exception;

    /**
     * 触发启动
     *
     * @param request 请求参数
     * @return 返回状态
     */
    Result<Boolean> trigger(TriggerRequest request);

    /**
     * 停止
     *
     * @param request 请求参数
     * @return 返回状态
     */
    Result<Boolean> pause(TriggerRequest request);

    /**
     * 恢复
     *
     * @param request 请求参数
     * @return 返回状态
     */
    Result<Boolean> resume(TriggerRequest request);

    /**
     * 移除
     *
     * @param request 请求参数
     * @return 返回状态
     */
    Result<Boolean> remove(TriggerRequest request);

    /**
     * 分页查询
     *
     * @param request 请求参数
     * @return 定时任务
     */
    Result<PageDTO<QuartzVO>> listPage(SearchRequest request);
}
