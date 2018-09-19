package com.github.cloud.upms.biz.service.impl;

import com.github.cloud.common.core.util.BeanUtils;
import com.github.cloud.upms.api.dto.UserDTO;
import com.github.cloud.upms.biz.dao.UserMapper;
import com.github.cloud.upms.biz.entity.SysUserDO;
import com.github.cloud.upms.biz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : czk
 * @date 2018-09-06 15:47
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO queryUser(Long userId) {
        SysUserDO sysUserDO = userMapper.selectById(userId);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(sysUserDO, userDTO);
        return userDTO;
    }
}
