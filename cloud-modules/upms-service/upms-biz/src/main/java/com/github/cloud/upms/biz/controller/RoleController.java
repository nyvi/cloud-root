package com.github.cloud.upms.biz.controller;

import com.github.cloud.common.core.util.Result;
import com.github.cloud.common.mybatis.dto.PageDTO;
import com.github.cloud.common.web.util.ServiceHelper;
import com.github.cloud.upms.biz.domain.request.RoleQueryRequest;
import com.github.cloud.upms.biz.domain.vo.RoleVO;
import com.github.cloud.upms.biz.service.RoleService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统角色
 *
 * @author Caixin
 * @date 2018/10/18 13:52
 */

@RestController
@RequestMapping("role")
@AllArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("listPage")
    @ApiOperation(value = "角色查询")
    public Result<PageDTO<RoleVO>> listPage(@RequestBody RoleQueryRequest request) {
        return ServiceHelper.execute(request, () -> roleService.listPage(request));
    }
}
