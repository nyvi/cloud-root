package com.github.cloud.upms.biz.domain.request;

import com.github.cloud.common.mybatis.request.PageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Caixin
 * @date 2018/10/18 14:02
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleQueryRequest extends PageRequest {

    private static final long serialVersionUID = 5953646988306462195L;

    @ApiModelProperty(value = "角色名")
    private String roleName;

    @ApiModelProperty(value = "角色编码")
    private String roleCode;

}
