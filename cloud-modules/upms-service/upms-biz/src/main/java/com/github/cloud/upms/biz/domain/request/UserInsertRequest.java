package com.github.cloud.upms.biz.domain.request;

import com.github.cloud.common.core.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;


/**
 * @author : huweihua
 * @date 2023-07-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserInsertRequest extends BaseRequest {

    private static final long serialVersionUID = 188233919071414263L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @NotBlank
    @Length(max = 20)
    @ApiModelProperty(value = "账号", required = true)
    private String account;

    @NotBlank
    @Length(max = 11)
    @ApiModelProperty(value = "手机号", required = true)
    private String phone;

    @NotBlank
    @Length(max = 20)
    @ApiModelProperty(value = "用户名", required = true)
    private String userName;

    @NotBlank
    @Length(max = 20)
    @ApiModelProperty(value = "密码", required = true)
    private String pwd;

    @ApiModelProperty(value = "版本号")
    private Long versionNo;

}
