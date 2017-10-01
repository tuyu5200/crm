package com.crm.dao.impl;

import com.crm.dao.UserDao;
import com.crm.dao.base.impl.BaseDaoImpl;
import com.crm.entity.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author walker tu
 * @date 2017/9/29
 * @description：
 */
@SuppressWarnings("all")
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    public User queryByUsername(String username) {
        return (User) this.getSession().createCriteria(User.class).add(Restrictions.eq("username", username)).uniqueResult();
    }

    public boolean isExist(String username, String password) {
        Map<String, String> conditions = new HashMap<String, String>();
        conditions.put("username", username);
        conditions.put("password", password);
        User user = (User) this.getSession().createCriteria(User.class).add(Restrictions.allEq(conditions)).uniqueResult();
        return user == null ? false : true;

    }

    public List<User> queryByDept(int deptId) {
        return this.getSession().createCriteria(User.class).add(Restrictions.eq("dept_id", deptId)).list();
    }

    public List<User> queryByCompany(int companyId) {
        return this.getSession().createCriteria(User.class).add(Restrictions.eq("company_id", companyId)).list();
    }
}
