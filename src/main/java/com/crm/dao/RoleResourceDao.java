package com.crm.dao;

import com.crm.dao.base.BaseDao;
import com.crm.entity.Role;
import com.crm.entity.RoleResource;

import java.util.List;

/**
 * @author walker tu
 * @date 2017/10/2
 * @description：
 */
public interface RoleResourceDao extends BaseDao<RoleResource> {

    /**
     * 通过角色查询该角色对应的所有资源，用于分配资源时进行清空
     *
     * @param role
     * @return
     */
    List<RoleResource> queryByRole(Role role);
}
