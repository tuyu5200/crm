package com.crm.dao.impl;

import com.crm.dao.RoleContantDao;
import com.crm.dao.base.impl.BaseDaoImpl;
import com.crm.entity.RoleConstant;
import org.springframework.stereotype.Repository;

/**
 * @author walker tu
 * @date 2017/10/2
 * @description
 */
@Repository
public class RoleContantDaoImpl extends BaseDaoImpl<RoleConstant> implements RoleContantDao {
    @Override
    public RoleConstant queryByName(String name) {
        return (RoleConstant) this.getSession().createQuery("from RoleConstant  r where r.name=?").setParameter(0, name).list().get(0);
    }
}
