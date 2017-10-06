package com.crm.dao;

import com.crm.dao.base.BaseDao;
import com.crm.entity.Company;
import com.crm.entity.Dept;

import java.util.List;

/**
 * @author walker tu
 * @date 2017/10/2
 * @description
 */
public interface CompanyDao extends BaseDao<Company> {

    /**
     * 查询该公司下的所有部门
     *
     * @param companyId 公司id
     * @return 所有的部门集合
     */
    List<Dept> queryAllDepts(Integer companyId);

}
