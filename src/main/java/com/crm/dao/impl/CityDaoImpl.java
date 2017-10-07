package com.crm.dao.impl;

import com.crm.dao.CityDao;
import com.crm.dao.base.impl.BaseDaoImpl;
import com.crm.entity.City;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author walker tu
 * @date 2017/10/7
 * @description
 */
@Repository
public class CityDaoImpl extends BaseDaoImpl<City> implements CityDao {
    @Override
    public List<Map<String, Object>> queryProvince() {
        return this.getSession().createQuery("from City c where c.parent.id is null ").list();
    }

    @Override
    public List<Map<String, Object>> queryCityByProvinceId(int province_id) {
//        return this.getSession().createQuery("from City ");
        return null;
    }

    @Override
    public List<Map<String, Object>> queryCountyByCityId(int city_id) {
        return null;
    }
}
