package com.crm.dao.impl;

import com.crm.dao.RoleDao;
import com.crm.dao.base.impl.BaseDaoImpl;
import com.crm.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author walker tu
 * @date 2017/9/30
 * @description：
 */
@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {
    @Override
    public List<Role> queryByIds(Integer[] ids) {
        return this.getSession().createQuery("from Role r where r.id in :ids").setParameterList("ids", ids).list();
    }

    @Override
    public List<Role> queryAllWithCompanyId(Integer companyId) {
        return this.getSession().createQuery("from Role r where r.company.id=?").setParameter(0, companyId).list();
    }

}
