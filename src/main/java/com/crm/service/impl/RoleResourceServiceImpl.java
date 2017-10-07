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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author walker tu
 * @date 2017/10/2
 * @description：
 */
@Service
@Transactional(readOnly = true)
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
    @Transactional(propagation = Propagation.REQUIRED)
    public void allocation(int roleId, Integer[] resourceIds) {
        if (Objects.isNull(resourceIds) || resourceIds.length < 1) {
            return;
        }

        Role role = this.roleDao.queryById(roleId);
//        注意清空，否则重复数据太多
        List<RoleResource> roleResources = this.roleResourceDao.queryByRole(role);
        for (RoleResource r : roleResources) {
            if (Objects.isNull(r))
                continue;
            this.roleResourceDao.delete(r.getId());
        }
        //重新建立关系
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
