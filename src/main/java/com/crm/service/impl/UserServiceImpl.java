package com.crm.service.impl;

import com.crm.dao.UserDao;
import com.crm.entity.User;
import com.crm.service.UserService;
import com.crm.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    private UserDao userDao;

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
}
