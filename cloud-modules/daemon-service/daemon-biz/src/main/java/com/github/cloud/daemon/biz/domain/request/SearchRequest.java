package com.github.cloud.daemon.biz.domain.request;

import com.github.cloud.common.mybatis.request.PageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 触发请求
 *
 * @author : czk
 * @date 2018-11-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SearchRequest extends PageRequest {

    private static final long serialVersionUID = -859517214965228305L;

    @ApiModelProperty(value = "任务名称")
    private String jobName;

    @ApiModelProperty(value = "任务分组")
    private String jobGroup;

    @ApiModelProperty(value = "状态")
    private String triggerState;
}
