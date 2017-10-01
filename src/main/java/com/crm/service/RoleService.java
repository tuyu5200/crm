package com.crm.service;

import com.crm.entity.Role;
import com.crm.service.base.BaseService;

/**
 * @author walker tu
 * @date 2017/9/30
 * @description：
 */
public interface RoleService extends BaseService<Role> {
    void allocationResources(int roleId, Integer[] resources);
}
