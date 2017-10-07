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
}
