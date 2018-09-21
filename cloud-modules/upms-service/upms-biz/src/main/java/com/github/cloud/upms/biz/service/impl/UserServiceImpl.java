package com.github.cloud.upms.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.cloud.common.core.util.BeanUtils;
import com.github.cloud.upms.api.dto.UserDTO;
import com.github.cloud.upms.biz.entity.SysUserDO;
import com.github.cloud.upms.biz.mapper.UserMapper;
import com.github.cloud.upms.biz.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author : czk
 * @date 2018-09-06 15:47
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public UserDTO queryUserByAccount(String account) {
        SysUserDO sysUserDO = userMapper.selectOne(new QueryWrapper<SysUserDO>().lambda().eq(SysUserDO::getAccount, account));

        if (sysUserDO == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(sysUserDO, userDTO);

        return userDTO;
    }
}
