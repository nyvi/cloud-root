package com.github.cloud.daemon.biz.domain.request;

import com.github.cloud.common.core.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @author : czk
 * @date 2018-11-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QuartzInsertRequest extends BaseRequest {

    private static final long serialVersionUID = -4946697504673549956L;

    @NotBlank
    @ApiModelProperty(value = "任务名称", required = true)
    private String jobName;

    @NotBlank
    @ApiModelProperty(value = "任务分组", required = true)
    private String jobGroup;

    @NotBlank
    @ApiModelProperty(value = "任务描述", required = true)
    private String description;

    @NotBlank
    @ApiModelProperty(value = "执行类", required = true)
    private String jobClassName;

    @NotBlank
    @ApiModelProperty(value = "执行时间", required = true)
    private String cronExpression;

    @ApiModelProperty(value = "任务名称 用于修改")
    private String oldJobName;

    @ApiModelProperty(value = "任务分组 用于修改")
    private String oldJobGroup;
}
