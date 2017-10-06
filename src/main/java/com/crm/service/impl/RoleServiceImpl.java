package com.crm.service.impl;

import com.crm.dao.*;
import com.crm.entity.Resource;
import com.crm.entity.Role;
import com.crm.entity.RoleConstant;
import com.crm.entity.RoleResource;
import com.crm.service.RoleService;
import com.crm.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @author walker tu
 * @date 2017/9/30
 * @description：
 */
@Service
@Transactional(readOnly = true)
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    @Autowired
    private ResourceDao resourceDao;
    @Autowired
    private DeptDao deptDao;
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private RoleContantDao roleContantDao;

    private RoleDao roleDao;

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        super.setBaseDao(roleDao);
        this.roleDao = roleDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void allocationResources(int roleId, Integer[] resourceIds) {
        //查询数据
        Role role = this.queryById(roleId);
        if (role == null) {
            return;
        }
        Set<RoleResource> roleResources = role.getRoleResources();
        //清空关系
        roleResources.clear();
        if (resourceIds != null && resourceIds.length > 0) {
            List<Resource> resources = this.resourceDao.queryByIds(resourceIds);

        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRole(String name, String roleContant, int enabled, int dept, int company, String description) {

        RoleConstant roleConstant = new RoleConstant();
        if (roleContant.isEmpty()) {
            return;
        }
        roleConstant.setName(roleContant);
        this.roleContantDao.save(roleConstant);
        Role role = new Role();
        if (name.isEmpty()) {
            return;
        }
        role.setName(name);
        role.setEnabled(enabled);
        role.setDept(this.deptDao.queryById(dept));
        role.setCompany(this.companyDao.queryById(company));
        role.setDescription(description);
        role.setRoleConstant(this.roleContantDao.queryByName(roleContant));
        this.roleDao.save(role);
    }

    @Override
    public void update(int roleId, String name, String roleContant, int enabled, int dept, int company, String description) {
        Role role = this.roleDao.queryById(roleId);
        RoleConstant roleConstant = new RoleConstant();
        if (roleContant.isEmpty()) {
            return;
        }
        roleConstant.setName(roleContant);
        this.roleContantDao.save(roleConstant);
        if (name.isEmpty()) {
            return;
        }
        role.setName(name);
        role.setEnabled(enabled);
        role.setDept(this.deptDao.queryById(dept));
        role.setCompany(this.companyDao.queryById(company));
        role.setDescription(description);
        role.setRoleConstant(this.roleContantDao.queryByName(roleContant));
        this.roleDao.update(role);
    }

    @Override
    public List<Role> queryAllWithCompanyId(Integer companyId) {
        return this.roleDao.queryAllWithCompanyId(companyId);
    }

}
