package com.github.cloud.daemon.biz.domain.request;

import com.github.cloud.common.core.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * 触发请求
 *
 * @author : czk
 * @date 2018-11-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TriggerRequest extends BaseRequest {

    private static final long serialVersionUID = -859517214965228305L;

    @NotBlank
    @ApiModelProperty(value = "任务名称", required = true)
    private String jobName;

    @NotBlank
    @ApiModelProperty(value = "任务分组", required = true)
    private String jobGroup;
}
