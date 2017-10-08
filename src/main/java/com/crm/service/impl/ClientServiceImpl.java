package com.crm.service.impl;

import com.crm.beans.ClientBean;
import com.crm.dao.CityDao;
import com.crm.dao.ClientDao;
import com.crm.dao.UserDao;
import com.crm.entity.Client;
import com.crm.entity.User;
import com.crm.service.ClientService;
import com.crm.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author walker tu
 * @date 2017/10/7
 * @description
 */
@Service
public class ClientServiceImpl extends BaseServiceImpl<Client> implements ClientService {

    private ClientDao clientDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CityDao cityDao;

    @Autowired
    public void setClientDao(ClientDao clientDao) {
        super.setBaseDao(clientDao);
        this.clientDao = clientDao;
    }

    @Override
    public void addClient(ClientBean clientBean) {
        if (Objects.isNull(clientBean)) {
            throw new IllegalArgumentException("添加客户参数不能为空");
        }
        Client client = new Client();
        client.setName(clientBean.getName());
        client.setLegal(clientBean.getLegal());
        client.setLevel(clientBean.getLevel());
        client.setPhone(clientBean.getPhone());
        client.setPostcode(clientBean.getPostcode());
        client.setEmail(clientBean.getEmail());
        client.setRegisterDate(clientBean.getRegisterDate());
        client.setNextTime(clientBean.getNextTime());
        client.setUser(this.userDao.queryById(clientBean.getUserId()));
//        client.setCity(this.cityDao.queryById(clientBean.getCityId()));

        this.clientDao.save(client);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        if (!Objects.isNull(ids) && ids.length > 0) {
            for (int i = 0; i < ids.length; i++) {
                this.clientDao.delete(ids[i]);
            }
        }
    }

    @Override
    public void updateClient(ClientBean clientBean) {
        if (Objects.isNull(clientBean))
            throw new IllegalArgumentException("更新客户时参数不能为空");
        Client client = this.clientDao.queryById(clientBean.getClientId());
        User user = this.userDao.queryById(clientBean.getUserId());
        client.setLevel(clientBean.getLevel());
        client.setUser(user);
        client.setNextTime(clientBean.getNextTime());
        client.setRegisterDate(clientBean.getRegisterDate());
        client.setEmail(clientBean.getEmail());
        client.setPostcode(clientBean.getPostcode());
        client.setPhone(clientBean.getPhone());
        client.setLegal(clientBean.getLegal());
        client.setName(clientBean.getName());
        this.clientDao.update(client);

    }
}
