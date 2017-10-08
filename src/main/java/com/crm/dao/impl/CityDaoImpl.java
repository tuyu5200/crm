package com.crm.dao.impl;

import com.crm.beans.CityBean;
import com.crm.dao.CityDao;
import com.crm.dao.base.impl.BaseDaoImpl;
import com.crm.entity.City;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author walker tu
 * @date 2017/10/7
 * @description
 */
@Repository
public class CityDaoImpl extends BaseDaoImpl<City> implements CityDao {
    @Override
    public List<CityBean> queryCitiesByLevel(Integer leval) {
        return this.getSession().createQuery("from City c where c.level=?").setParameter(0, leval).list();
    }
}
