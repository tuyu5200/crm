package com.crm.service.impl;

import com.crm.beans.DeptBean;
import com.crm.dao.CompanyDao;
import com.crm.entity.Company;
import com.crm.entity.Dept;
import com.crm.service.CompanyService;
import com.crm.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author walker tu
 * @date 2017/10/2
 * @description
 */
@Service
public class CompanyServiceImpl extends BaseServiceImpl<Company> implements CompanyService {

    private CompanyDao companyDao;

    @Autowired
    public void setCompanyDao(CompanyDao companyDao) {
        super.setBaseDao(companyDao);
        this.companyDao = companyDao;
    }

    @Override
    public List<DeptBean> queryAllDepts(Integer companyId) {
        List<DeptBean> deptBeans = new ArrayList<>();
        List<Dept> deptList = this.companyDao.queryAllDepts(companyId);
        for (int i = 0; i < deptList.size(); i++) {
            deptBeans.add(new DeptBean(deptList.get(i)));
        }
        return deptBeans;
    }
}
