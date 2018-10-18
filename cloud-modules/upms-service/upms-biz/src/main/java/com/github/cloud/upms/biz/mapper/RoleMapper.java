package com.github.cloud.upms.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.cloud.upms.biz.entity.SysRoleDO;
import com.github.cloud.upms.biz.request.RoleQueryRequest;
import com.github.cloud.upms.biz.vo.RoleVO;
import org.apache.ibatis.annotations.Param;

/**
 * 角色Mapper
 *
 * @author : czk
 * @date 2018-09-22
 */
public interface RoleMapper extends BaseMapper<SysRoleDO> {

    /**
     * 角色分页查询
     *
     * @param page
     * @param request
     * @return 角色列表
     */
    IPage<RoleVO> listPage(Page page, @Param("pm") RoleQueryRequest request);

}
