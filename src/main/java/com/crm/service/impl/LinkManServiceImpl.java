package com.crm.service.impl;

import com.crm.dao.LinkManDao;
import com.crm.entity.Linkman;
import com.crm.service.LinkManService;
import com.crm.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author walker tu
 * @date 2017/10/7
 * @description
 */
@Service
public class LinkManServiceImpl extends BaseServiceImpl<Linkman> implements LinkManService {
    private LinkManDao linkManDao;

    @Autowired
    public void setLinkManDao(LinkManDao linkManDao) {
        super.setBaseDao(linkManDao);
        this.linkManDao = linkManDao;
    }
}
