package com.crm.service;

import com.crm.entity.RoleResource;
import com.crm.service.base.BaseService;

/**
 * @author walker tu
 * @date 2017/10/2
 * @description：
 */
public interface RoleResourceService extends BaseService<RoleResource> {

    /**
     * 給指定的角色分配相應的資源
     *
     * @param roleId      角色的编号
     * @param resourceIds 所有被分配的资源id
     */
    void allocation(int roleId, Integer[] resourceIds);

}
