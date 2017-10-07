package com.crm.dao;

import com.crm.dao.base.BaseDao;
import com.crm.entity.City;

import java.util.List;
import java.util.Map;

/**
 * @author walker tu
 * @date 2017/10/7
 * @description
 */
public interface CityDao extends BaseDao<City> {

    List<Map<String, Object>> queryProvince();

    List<Map<String, Object>> queryCityByProvinceId(int province_id);

    List<Map<String, Object>> queryCountyByCityId(int city_id);
}
