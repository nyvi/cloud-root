package com.github.cloud.upms.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.cloud.common.core.util.BeanUtils;
import com.github.cloud.common.mybatis.service.impl.BaseServiceImpl;
import com.github.cloud.upms.api.dto.UserDTO;
import com.github.cloud.upms.biz.entity.SysRoleDO;
import com.github.cloud.upms.biz.entity.SysUserDO;
import com.github.cloud.upms.biz.mapper.UserMapper;
import com.github.cloud.upms.biz.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : czk
 * @date 2018-09-06 15:47
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<UserMapper, SysUserDO> implements UserService {

    private final UserMapper userMapper;

    @Override
    public UserDTO queryUserByAccount(String account) {
        SysUserDO sysUserDO = userMapper.selectOne(new QueryWrapper<SysUserDO>().lambda().eq(SysUserDO::getAccount, account));

        if (sysUserDO == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(sysUserDO, userDTO);

        List<SysRoleDO> sysRoleList = userMapper.listUserRole(sysUserDO.getId());
        List<String> roleList = sysRoleList.stream().map(SysRoleDO::getRoleCode).collect(Collectors.toList());
        userDTO.setRoleList(roleList);

        List<String> permissionsList = userMapper.listUserPermissions(sysUserDO.getId());
        userDTO.setPermissionsList(permissionsList);

        return userDTO;
    }
}
