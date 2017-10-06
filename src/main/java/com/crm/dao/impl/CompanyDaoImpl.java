package com.crm.dao.impl;

import com.crm.dao.CompanyDao;
import com.crm.dao.base.impl.BaseDaoImpl;
import com.crm.entity.Company;
import com.crm.entity.Dept;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author walker tu
 * @date 2017/10/2
 * @description
 */
@Repository
public class CompanyDaoImpl extends BaseDaoImpl<Company> implements CompanyDao {
    @Override
    public List<Dept> queryAllDepts(Integer companyId) {
        if (Objects.isNull(companyId)) {
            throw new IllegalArgumentException("参数错误");
        }
        Set<Dept> depts = this.queryById(companyId).getDepts();
        List<Dept> deptList = new ArrayList<>();
        deptList.addAll(depts);
        return deptList;
    }
}
