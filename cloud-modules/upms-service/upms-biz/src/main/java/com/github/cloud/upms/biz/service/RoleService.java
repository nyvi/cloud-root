package com.github.cloud.upms.biz.service;

import com.github.cloud.common.core.util.Result;
import com.github.cloud.common.mybatis.dto.PageDTO;
import com.github.cloud.common.mybatis.service.BaseService;
import com.github.cloud.upms.biz.domain.entity.SysRoleDO;
import com.github.cloud.upms.biz.domain.request.RoleQueryRequest;
import com.github.cloud.upms.biz.domain.vo.RoleVO;

/**
 * @author : huweihua
 * @date 2023-07-06 15:46
 */
public interface RoleService extends BaseService<SysRoleDO> {

    /**
     * 角色页查询
     *
     * @param request 请求参数
     * @return 角色列表
     */
    Result<PageDTO<RoleVO>> listPage(RoleQueryRequest request);


}
