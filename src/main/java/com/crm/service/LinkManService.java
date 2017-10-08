package com.crm.service;

import com.crm.beans.LinkManBean;
import com.crm.entity.Client;
import com.crm.entity.Linkman;
import com.crm.service.base.BaseService;

/**
 * @author walker tu
 * @date 2017/10/7
 * @description
 */
public interface LinkManService extends BaseService<Linkman> {

    /**
     * 添加联系人
     *
     * @param linkManBean
     */
    void addLinkman(LinkManBean linkManBean, Client client);

    /**
     * 通过ids 数组 删除所有对应的联系人
     *
     * @param ids
     */
    void deleteByIds(Integer[] ids);

    /**
     * 修改联系人
     *
     * @param linkManBean
     */
    void updateLinkman(LinkManBean linkManBean);
}
