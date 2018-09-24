package com.github.cloud.upms.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.cloud.upms.biz.entity.SysRoleDO;
import com.github.cloud.upms.biz.entity.SysUserDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户 Mapper
 *
 * @author : czk
 * @date 2018-09-19
 */
public interface UserMapper extends BaseMapper<SysUserDO> {

    /**
     * 查询用户角色
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    List<SysRoleDO> listUserRole(@Param("userId") Long userId);

    /**
     * 查询用户权限
     *
     * @param userId 用户ID
     * @return 权限码
     */
    List<String> listUserPermissions(@Param("userId") Long userId);
}
