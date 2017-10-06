package com.crm.dao.impl;

import com.crm.dao.DeptDao;
import com.crm.dao.base.impl.BaseDaoImpl;
import com.crm.entity.Dept;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * @author walker tu
 * @date 2017/9/30
 * @description：
 */
@Repository
public class DeptDaoImpl extends BaseDaoImpl<Dept> implements DeptDao {

    @Override
    public Dept queryByDname(String dname) {
        List<Dept> deptList = this.getSession().createQuery("from  Dept d where d.dname=?").setParameter(0, dname).list();
        if (Objects.isNull(deptList) || deptList.size() < 1) {
            return null;
        } else {
            return deptList.get(0);
        }
    }
}
