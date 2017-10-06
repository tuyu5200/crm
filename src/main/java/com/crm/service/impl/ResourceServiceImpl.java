package com.crm.service.impl;

import com.crm.dao.ResourceDao;
import com.crm.entity.Resource;
import com.crm.service.ResourceService;
import com.crm.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author walker tu
 * @date 2017/10/6
 * @description
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements ResourceService {

    private ResourceDao resourceDao;

    @Autowired
    public void setResourceDao(ResourceDao resourceDao) {
        super.setBaseDao(resourceDao);
        this.resourceDao = resourceDao;
    }
}
