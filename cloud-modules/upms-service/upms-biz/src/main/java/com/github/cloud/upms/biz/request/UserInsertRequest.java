package com.github.cloud.upms.biz.request;

import com.github.cloud.common.core.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author : czk
 * @date 2018-09-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserInsertRequest extends BaseRequest {

    private static final long serialVersionUID = 188233919071414263L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "账号", required = true)
    private String account;

    @ApiModelProperty(value = "手机号", required = true)
    private String phone;

    @ApiModelProperty(value = "用户名", required = true)
    private String userName;

    @ApiModelProperty(value = "密码", required = true)
    private String pwd;

    @ApiModelProperty(value = "版本号")
    private Long versionNo;

}
