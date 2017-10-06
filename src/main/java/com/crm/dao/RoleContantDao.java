package com.crm.dao;

import com.crm.dao.base.BaseDao;
import com.crm.entity.RoleConstant;

/**
 * @author walker tu
 * @date 2017/10/2
 * @description
 */
public interface RoleContantDao extends BaseDao<RoleConstant> {
    RoleConstant queryByName(String name);
}
