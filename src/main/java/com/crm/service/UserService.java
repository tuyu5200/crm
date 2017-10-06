package com.crm.service;

import com.crm.entity.User;
import com.crm.service.base.BaseService;

import java.util.List;

public interface UserService extends BaseService<User> {
    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    User queryByUsername(String username);

    /**
     * 用于检查用户的登陆是否成功
     *
     * @param username
     * @param password
     * @return
     */
    boolean isExist(String username, String password);

    /**
     * 通过部门查询用户
     *
     * @param deptId 部门号
     * @return
     */
    List<User> queryByDept(int deptId);

    /**
     * 通过公司查询所有的用户
     *
     * @param companyId
     * @return
     */
    List<User> queryByCompany(int companyId);

    /**
     * 为用户分配角色
     *
     * @param roleIds 角色id数组
     */
    void allocRoles(Integer userId, Integer[] roleIds);
}
