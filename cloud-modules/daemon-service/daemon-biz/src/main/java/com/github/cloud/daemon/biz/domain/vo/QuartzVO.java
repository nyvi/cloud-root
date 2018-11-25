package com.github.cloud.daemon.biz.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : czk
 * @date 2018-11-24
 */
@Data
public class QuartzVO implements Serializable {

    private static final long serialVersionUID = 8706481762264455565L;

    @ApiModelProperty(value = "任务名称")
    private String jobName;

    @ApiModelProperty(value = "任务分组")
    private String jobGroup;

    @ApiModelProperty(value = "任务描述")
    private String description;

    @ApiModelProperty(value = "执行类")
    private String jobClassName;

    @ApiModelProperty(value = "执行时间")
    private String cronExpression;

    @ApiModelProperty(value = "状态")
    private String triggerState;
}
