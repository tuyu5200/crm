package com.crm.dao;

import com.crm.beans.ResourceBean;
import com.crm.dao.base.BaseDao;
import com.crm.entity.Resource;
import com.crm.entity.User;

import java.util.List;

/**
 * @author walker tu
 * @date 2017/9/30
 * @description：
 */
public interface ResourceDao extends BaseDao<Resource> {

    /**
     * 查询该用户拥有的所有资源
     *
     * @return
     */
    List<ResourceBean> queryByUser(User user);

    List<Resource> queryByIds(Integer[] ids);

}
