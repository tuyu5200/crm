package com.crm.service;

import com.crm.beans.DeptBean;
import com.crm.entity.Dept;
import com.crm.entity.User;
import com.crm.service.base.BaseService;

import java.util.List;

/**
 * @author walker tu
 * @date 2017/9/30
 * @description：
 */
public interface DeptService extends BaseService<Dept> {

    /**
     * 得到当前用户对应的公司下的所有部门
     *
     * @param user
     * @return
     */
    List<DeptBean> queryAllDeptBeans(User user);
}
