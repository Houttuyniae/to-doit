package io.todoit.modules.sys.service;

import io.todoit.modules.sys.entity.User;

import java.util.Set;

/**
 * @author:zhangd
 * @date:2019/4/4 16:04
 */
public interface IShiroService {

    /**
     * 通过用户登陆名查询用户
     * @param userName 用户登陆名称
     * @return
     */
    User getOneByUserName(String userName);

    /**
     * 根据用户id查询用户角色
     * @param userId
     * @return
     */
    Set<String> selectRoleByUserId(Long userId);

    /**
     * 根据用户id查询用户权限
     * @param userId
     * @return
     */
    Set<String> selectMenuByUserId(Long userId);

}
