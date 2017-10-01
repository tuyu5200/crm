package com.crm.dao;

import com.crm.dao.base.BaseDao;
import com.crm.entity.Role;

import java.util.List;

/**
 * @author walker tu
 * @date 2017/9/30
 * @description：
 */
public interface RoleDao extends BaseDao<Role> {

    /**
     * 通过给定的id数组查询所有的角色信息
     *
     * @param ids
     * @return
     */
    List queryByIds(Integer[] ids);

}
