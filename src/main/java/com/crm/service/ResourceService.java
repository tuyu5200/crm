package com.crm.service;

import com.crm.entity.Resource;
import com.crm.service.base.BaseService;

import java.util.List;

/**
 * @author walker tu
 * @date 2017/10/6
 * @description
 */
public interface ResourceService extends BaseService<Resource> {
    List<Resource> queryByRoleId(Integer id);
}
