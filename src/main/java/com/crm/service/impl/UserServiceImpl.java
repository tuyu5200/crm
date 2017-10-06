package com.crm.service.impl;

import com.crm.dao.RoleDao;
import com.crm.dao.UserDao;
import com.crm.entity.Role;
import com.crm.entity.User;
import com.crm.service.UserService;
import com.crm.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        super.setBaseDao(userDao);
        this.userDao = userDao;
    }

    @Override
    public User queryByUsername(String username) {
        return this.userDao.queryByUsername(username);
    }

    @Override
    public boolean isExist(String username, String password) {
        return this.userDao.isExist(username, password);
    }

    @Override
    public List<User> queryByDept(int deptId) {
        return this.userDao.queryByDept(deptId);
    }

    @Override
    public List<User> queryByCompany(int companyId) {
        return this.userDao.queryByCompany(companyId);
    }

    @Override
    public void allocRoles(Integer userId, Integer[] roleIds) {
        if (!Objects.isNull(roleIds) && roleIds.length > 0) {
            User user = this.userDao.queryById(userId);
            user.getRoles().clear();
            List<Role> roleList = this.roleDao.queryByIds(roleIds);
            user.getRoles().addAll(roleList);
            this.userDao.update(user);
        }
    }
}
