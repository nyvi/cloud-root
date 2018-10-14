package com.github.cloud.upms.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.cloud.common.core.request.DeleteRequest;
import com.github.cloud.upms.biz.entity.SysRoleDO;
import com.github.cloud.upms.biz.entity.SysUserDO;
import com.github.cloud.upms.biz.request.UserQueryRequest;
import com.github.cloud.upms.biz.vo.UserVO;
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

    /**
     * 分页查询
     *
     * @param page    分页参数
     * @param request 请求参数
     * @return 用户列表
     */
    IPage<UserVO> listPage(Page page, @Param("pm") UserQueryRequest request);


    /**
     * 删除
     *
     * @param deleteRequest 删除参数
     * @return 影响行数
     */
    int delete(DeleteRequest deleteRequest);
}
