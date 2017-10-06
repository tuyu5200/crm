package com.crm.service.impl;

import com.crm.dao.ResourceDao;
import com.crm.dao.RoleDao;
import com.crm.dao.RoleResourceDao;
import com.crm.entity.Role;
import com.crm.entity.RoleResource;
import com.crm.service.RoleResourceService;
import com.crm.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author walker tu
 * @date 2017/10/2
 * @description：
 */
@Service
public class RoleResourceServiceImpl extends BaseServiceImpl<RoleResource> implements RoleResourceService {

    private RoleResourceDao roleResourceDao;

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    public void setRoleResourceDao(RoleResourceDao roleResourceDao) {
        super.setBaseDao(roleResourceDao);
        this.roleResourceDao = roleResourceDao;
    }

    @Override
    public void allocation(int roleId, Integer[] resourceIds) {
        if (Objects.isNull(resourceIds) || resourceIds.length < 1) {
            return;
        }
        Role role = this.roleDao.queryById(roleId);
        for (int i = 0; i < resourceIds.length; i++) {
            RoleResource roleResource = new RoleResource();
            roleResource.setRole(role);
            roleResource.setResource(this.resourceDao.queryById(resourceIds[i]));
            //默认启用该资源，也可以通过页面禁用该角色对应的资源
            roleResource.setEnabled(1);
            this.roleResourceDao.save(roleResource);
        }
    }
}
