package com.crm.dao.impl;

import com.crm.dao.RoleResourceDao;
import com.crm.dao.base.impl.BaseDaoImpl;
import com.crm.entity.Role;
import com.crm.entity.RoleResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * @author walker tu
 * @date 2017/10/2
 * @description：
 */
@Repository
public class RoleResourceDaoImpl extends BaseDaoImpl<RoleResource> implements RoleResourceDao {


    @Override
    public List<RoleResource> queryByRole(Role role) {
        if (Objects.isNull(role))
            throw new IllegalArgumentException("查询角色资源表的参数不正确");
        return this.getSession().createQuery("from RoleResource r where r.role.id=?").setParameter(0, role.getId()).list();
    }
}
