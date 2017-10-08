package com.crm.service;

import com.crm.beans.ClientBean;
import com.crm.entity.Client;
import com.crm.service.base.BaseService;

/**
 * @author walker tu
 * @date 2017/10/7
 * @description
 */
public interface ClientService extends BaseService<Client> {
    void addClient(ClientBean clientBean);

    /**
     * 通过ids 数组 删除所有
     *
     * @param ids
     */
    void deleteByIds(Integer[] ids);

    /**
     * 修改客户
     *
     * @param clientBean
     */
    void updateClient(ClientBean clientBean);
}
