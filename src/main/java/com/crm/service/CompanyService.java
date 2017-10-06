package com.crm.service;

import com.crm.beans.DeptBean;
import com.crm.entity.Company;
import com.crm.service.base.BaseService;

import java.util.List;

/**
 * @author walker tu
 * @date 2017/10/2
 * @description
 */
public interface CompanyService extends BaseService<Company> {
    /**
     * 查询指定公司下的所有部门集合
     *
     * @param companyId
     * @return
     */
    List<DeptBean> queryAllDepts(Integer companyId);
}
