package com.github.cloud.daemon.biz.domain.request;

import com.github.cloud.common.core.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

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
    @Length(max = 20)
    @ApiModelProperty(value = "任务名称", required = true)
    private String jobName;

    @NotBlank
    @Length(max = 20)
    @ApiModelProperty(value = "任务分组", required = true)
    private String jobGroup;

    @NotBlank
    @Length(max = 200)
    @ApiModelProperty(value = "任务描述", required = true)
    private String description;

    @NotBlank
    @Length(max = 250)
    @ApiModelProperty(value = "执行类", required = true)
    private String jobClassName;

    @NotBlank
    @Length(max = 120)
    @ApiModelProperty(value = "执行时间", required = true)
    private String cronExpression;

    @ApiModelProperty(value = "任务名称 用于修改")
    private String oldJobName;

    @ApiModelProperty(value = "任务分组 用于修改")
    private String oldJobGroup;
}
