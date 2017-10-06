package com.crm.dao;

import com.crm.dao.base.BaseDao;
import com.crm.entity.Dept;

/**
 * @author walker tu
 * @date 2017/9/30
 * @description：
 */
public interface DeptDao extends BaseDao<Dept> {

    Dept queryByDname(String dname);
}
