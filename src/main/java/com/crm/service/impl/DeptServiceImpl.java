package com.crm.service.impl;

import com.crm.beans.DeptBean;
import com.crm.beans.RoleBean;
import com.crm.dao.DeptDao;
import com.crm.dao.UserDao;
import com.crm.entity.Company;
import com.crm.entity.Dept;
import com.crm.entity.Role;
import com.crm.entity.User;
import com.crm.service.DeptService;
import com.crm.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author walker tu
 * @date 2017/9/30
 * @description：
 */
@Service
public class DeptServiceImpl extends BaseServiceImpl<Dept> implements DeptService {

    private DeptDao deptDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    public void setDeptDao(DeptDao deptDao) {
        super.setBaseDao(deptDao);
        this.deptDao = deptDao;
    }

    @Override
    public List<DeptBean> queryAllDeptBeans(User user) {
        User user1 = this.userDao.queryById(user.getId());
        Company company = user1.getCompany();
        List<DeptBean> deptBeans = new ArrayList<>();
        if (company != null) {
            List<Dept> depts = new ArrayList<>();
            if (company.getDepts().size() > 0) {
                depts.addAll(company.getDepts());
                for (int i = 0; i < depts.size(); i++) {
                    DeptBean deptBean = new DeptBean(depts.get(i));
                    deptBean.setCompany(company);
                    deptBeans.add(deptBean);
                }
            }
        }
        return deptBeans;
    }

    @Override
    public List<RoleBean> queryAllRolesByDeptId(Integer deptId) {
        Set<Role> roles = this.deptDao.queryById(deptId).getRoles();
        List<RoleBean> roleBeans = new ArrayList<>();
        List<Role> roleList = new ArrayList<>();
        roleList.addAll(roles);
        for (int i = 0; i < roleList.size(); i++) {
            roleBeans.add(new RoleBean(roleList.get(i)));
        }
        return roleBeans;
    }

    @Override
    public Dept queryByDname(String dname) {
        return this.deptDao.queryByDname(dname);
    }
}
