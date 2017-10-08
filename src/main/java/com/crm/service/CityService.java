package com.crm.service;

import com.crm.beans.CityBean;
import com.crm.entity.City;
import com.crm.service.base.BaseService;

import java.util.List;

/**
 * @author walker tu
 * @date 2017/10/7
 * @description
 */
public interface CityService extends BaseService<City> {

    /**
     * 通过登记查找省市县
     *
     * @param level
     * @return
     */
    List<CityBean> queryCitiesByLevel(Integer level);

    /**
     * 通过父级节点查找所有的子节点
     *
     * @param parentId
     * @return
     */
    List<CityBean> queryCitiesByParentId(Integer parentId);
}
