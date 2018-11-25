package com.github.cloud.upms.biz.service.impl;

import com.github.cloud.common.core.util.Result;
import com.github.cloud.common.mybatis.dto.PageDTO;
import com.github.cloud.common.mybatis.service.impl.BaseServiceImpl;
import com.github.cloud.common.mybatis.util.PageHelper;
import com.github.cloud.upms.biz.domain.entity.SysRoleDO;
import com.github.cloud.upms.biz.domain.request.RoleQueryRequest;
import com.github.cloud.upms.biz.domain.vo.RoleVO;
import com.github.cloud.upms.biz.mapper.RoleMapper;
import com.github.cloud.upms.biz.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Caixin
 * @date 2018/10/18 14:25
 */
@Service
@AllArgsConstructor
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, SysRoleDO> implements RoleService {

    private final RoleMapper roleMapper;

    @Override
    public Result<PageDTO<RoleVO>> listPage(RoleQueryRequest request) {
        return Result.success(PageHelper.convert(roleMapper.listPage(request.convertFor(),request)));
    }

}
