package com.github.cloud.upms.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.cloud.common.core.request.DeleteRequest;
import com.github.cloud.common.core.util.BeanUtils;
import com.github.cloud.common.core.util.CollectionUtils;
import com.github.cloud.common.core.util.Result;
import com.github.cloud.common.mybatis.dto.PageDTO;
import com.github.cloud.common.mybatis.service.impl.BaseServiceImpl;
import com.github.cloud.common.mybatis.util.PageHelper;
import com.github.cloud.upms.api.dto.UserDTO;
import com.github.cloud.upms.biz.domain.entity.SysRoleDO;
import com.github.cloud.upms.biz.domain.entity.SysUserDO;
import com.github.cloud.upms.biz.domain.request.UserInsertRequest;
import com.github.cloud.upms.biz.domain.request.UserQueryRequest;
import com.github.cloud.upms.biz.domain.vo.UserVO;
import com.github.cloud.upms.biz.mapper.UserMapper;
import com.github.cloud.upms.biz.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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

    @Override
    public Result<Boolean> saveOrUpdate(UserInsertRequest userInsertRequest) {

        SysUserDO sysUserDO = new SysUserDO();
        BeanUtils.copyProperties(userInsertRequest, sysUserDO);

        List<SysUserDO> list = this.list(new QueryWrapper<SysUserDO>().lambda().eq(SysUserDO::getAccount, userInsertRequest.getAccount()));
        if (CollectionUtils.isNotEmpty(list) && !Objects.equals(list.get(0).getId(), userInsertRequest.getId())) {
            return Result.error("该账号已被注册!");
        }

        list = this.list(new QueryWrapper<SysUserDO>().lambda().eq(SysUserDO::getPhone, userInsertRequest.getPhone()));
        if (CollectionUtils.isNotEmpty(list) && !Objects.equals(list.get(0).getId(), userInsertRequest.getId())) {
            return Result.error("该手机号已被注册!");
        }

        if (Objects.isNull(userInsertRequest.getId())) {
            sysUserDO.setPwd(new BCryptPasswordEncoder().encode(userInsertRequest.getPwd()));
        } else {
            sysUserDO.setPwd(null);
        }

        // TODO
        sysUserDO.setGmtModify(LocalDateTime.now());
        sysUserDO.setGmtCreate(LocalDateTime.now());
        sysUserDO.setCreateBy(1L);
        sysUserDO.setModifyBy(1L);

        return this.saveOrUpdate(sysUserDO) ? Result.success() : Result.error();
    }

    @Override
    public Result<PageDTO<UserVO>> listPage(UserQueryRequest request) {
        return Result.success(PageHelper.convert(userMapper.listPage(request.convertFor(), request)));
    }

    @Override
    public Result<Boolean> delete(DeleteRequest deleteRequest) {
        return userMapper.deleteById(deleteRequest.getId()) > 0 ? Result.success() : Result.error();
    }

}
