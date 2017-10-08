package com.crm.service.impl;

import com.crm.beans.CityBean;
import com.crm.dao.CityDao;
import com.crm.entity.City;
import com.crm.service.CityService;
import com.crm.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author walker tu
 * @date 2017/10/7
 * @description
 */
@Service
public class CityServiceImpl extends BaseServiceImpl<City> implements CityService {

    private CityDao cityDao;

    @Autowired
    public void setCityDao(CityDao cityDao) {
        super.setBaseDao(cityDao);
        this.cityDao = cityDao;
    }


    @Override
    public List<CityBean> queryCitiesByLevel(Integer level) {
        return this.cityDao.queryCitiesByLevel(level);
    }

    @Override
    public List<CityBean> queryCitiesByParentId(Integer parentId) {
        City city = this.cityDao.queryById(parentId);
        List<City> temp = new ArrayList<>(city.getCities());
        List<CityBean> cityBeanList = new ArrayList<>();
        for (City c : temp) {
            cityBeanList.add(new CityBean(c));
        }
        return cityBeanList;
    }
}
