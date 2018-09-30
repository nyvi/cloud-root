package com.github.cloud.upms.biz.service;

import com.github.cloud.common.core.util.Result;
import com.github.cloud.common.mybatis.service.BaseService;
import com.github.cloud.upms.api.dto.UserDTO;
import com.github.cloud.upms.biz.entity.SysUserDO;
import com.github.cloud.upms.biz.request.UserInsertRequest;

/**
 * @author : czk
 * @date 2018-09-06 15:46
 */
public interface UserService extends BaseService<SysUserDO> {

    /**
     * 查询用户
     *
     * @param account 登录账号
     * @return 登录成功返回用户信息, 否则返回null
     */
    UserDTO queryUserByAccount(String account);

    /**
     * 用户保存/更新
     *
     * @param userInsertRequest 请求参数
     * @return 成功返回true, 失败返回false
     */
    Result<Boolean> saveOrUpdate(UserInsertRequest userInsertRequest);
}
