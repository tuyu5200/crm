package com.crm.dao;

import com.crm.beans.CityBean;
import com.crm.dao.base.BaseDao;
import com.crm.entity.City;

import java.util.List;

/**
 * @author walker tu
 * @date 2017/10/7
 * @description
 */
public interface CityDao extends BaseDao<City> {

    /**
     * 通过级别获取相应的省市县
     *
     * @param leval
     * @return
     */
    List<CityBean> queryCitiesByLevel(Integer leval);
}
