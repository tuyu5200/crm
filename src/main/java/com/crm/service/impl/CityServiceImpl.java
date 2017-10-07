package com.crm.service.impl;

import com.crm.dao.CityDao;
import com.crm.entity.City;
import com.crm.service.CityService;
import com.crm.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
