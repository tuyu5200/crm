package com.crm.service.impl;

import com.crm.beans.LinkManBean;
import com.crm.dao.ClientDao;
import com.crm.dao.LinkManDao;
import com.crm.entity.Client;
import com.crm.entity.Linkman;
import com.crm.service.LinkManService;
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
public class LinkManServiceImpl extends BaseServiceImpl<Linkman> implements LinkManService {

    private LinkManDao linkManDao;
    @Autowired
    private ClientDao clientDao;

    @Autowired
    public void setLinkManDao(LinkManDao linkManDao) {
        super.setBaseDao(linkManDao);
        this.linkManDao = linkManDao;
    }

    @Override
    public void addLinkman(LinkManBean linkManBean, Client client) {
        if (Objects.isNull(linkManBean))
            throw new IllegalArgumentException("添加联系人时参数不能为空");

        Linkman linkman = new Linkman();
        linkman.setActive(linkManBean.getActive());
        linkman.setBirthday(linkManBean.getBirthday());
        linkman.setClient(this.clientDao.queryById(linkManBean.getClient().getClientId()));
//        linkman.setComplaints(linkManBean.getComplaints());
        linkman.setContent(linkManBean.getContent());
        linkman.setEmail(linkManBean.getEmail());
        linkman.setGender(linkManBean.getGender());
        linkman.setJob(linkManBean.getJob());
//        linkman.setLinkrecords(linkManBean.getLinkrecords());
        linkman.setName(linkManBean.getName());
        linkman.setPhone(linkManBean.getPhone());
        linkman.setClient(client);
        this.linkManDao.save(linkman);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        if (!Objects.isNull(ids) && ids.length > 0) {
            for (int i = 0; i < ids.length; i++) {
                this.linkManDao.delete(ids[i]);
            }
        }
    }

    @Override
    public void updateLinkman(LinkManBean linkManBean) {
        if (Objects.isNull(linkManBean))
            throw new IllegalArgumentException("修改联系人时参数不能为空");

        Linkman linkman = this.linkManDao.queryById(linkManBean.getId());
        linkman.setActive(linkManBean.getActive());
        linkman.setBirthday(linkManBean.getBirthday());
        linkman.setClient(this.clientDao.queryById(linkManBean.getClient().getClientId()));
//        linkman.setComplaints(linkManBean.getComplaints());
        linkman.setContent(linkManBean.getContent());
        linkman.setEmail(linkManBean.getEmail());
        linkman.setGender(linkManBean.getGender());
        linkman.setJob(linkManBean.getJob());
//        linkman.setLinkrecords(linkManBean.getLinkrecords());
        linkman.setName(linkManBean.getName());
        linkman.setPhone(linkManBean.getPhone());
        this.linkManDao.update(linkman);
    }
}
