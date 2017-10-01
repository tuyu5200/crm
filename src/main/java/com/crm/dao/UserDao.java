package com.crm.dao;

import com.crm.dao.base.BaseDao;
import com.crm.entity.User;

import java.util.List;

/**
 * @author walker tu
 * @date 2017/9/28
 * @description：
 */
public interface UserDao extends BaseDao<User> {

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
     * @return true 登陆成功，false登陆失败
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

}

