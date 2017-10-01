package com.crm.dao.impl;

import com.crm.beans.ResourceBean;
import com.crm.dao.ResourceDao;
import com.crm.dao.base.impl.BaseDaoImpl;
import com.crm.entity.Resource;
import com.crm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author walker tu
 * @date 2017/9/30
 * @description：
 */
@Repository
public class ResourceDaoImpl extends BaseDaoImpl<Resource> implements ResourceDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String QUERY_SQL = "";

    @Override
    public List<ResourceBean> queryByUser(User user) {
        this.jdbcTemplate.queryForList(this.QUERY_SQL);
        return null;
    }

    @Override
    public List<Resource> queryByIds(Integer[] ids) {
        return this.getSession().createQuery("from  Resource r where r.id in :ids").setParameterList("ids", ids).list();
    }

}
