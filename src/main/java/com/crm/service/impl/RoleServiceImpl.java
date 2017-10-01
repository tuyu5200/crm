package com.crm.service.impl;

import com.crm.dao.ResourceDao;
import com.crm.entity.Resource;
import com.crm.entity.Role;
import com.crm.entity.RoleResource;
import com.crm.service.RoleService;
import com.crm.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author walker tu
 * @date 2017/9/30
 * @description：
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    @Autowired
    private ResourceDao resourceDao;

    @Override
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

}
