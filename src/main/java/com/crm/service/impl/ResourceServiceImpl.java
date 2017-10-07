package com.crm.service.impl;

import com.crm.dao.ResourceDao;
import com.crm.dao.RoleDao;
import com.crm.entity.Resource;
import com.crm.entity.Role;
import com.crm.entity.RoleResource;
import com.crm.service.ResourceService;
import com.crm.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author walker tu
 * @date 2017/10/6
 * @description
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements ResourceService {

    private ResourceDao resourceDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setResourceDao(ResourceDao resourceDao) {
        super.setBaseDao(resourceDao);
        this.resourceDao = resourceDao;
    }

    @Override
    public List<Resource> queryByRoleId(Integer roleId) {
        Role role = this.roleDao.queryById(roleId);
        List<Resource> resources = new ArrayList<>();
        for (RoleResource roleResource : role.getRoleResources()) {
            resources.add(roleResource.getResource());
        }
        return resources;
    }
}
