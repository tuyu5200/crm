package com.crm.service;

import com.crm.entity.Role;
import com.crm.service.base.BaseService;

import java.util.List;

/**
 * @author walker tu
 * @date 2017/9/30
 * @description：
 */
public interface RoleService extends BaseService<Role> {
    /**
     * 给指定的角色分配资源
     *
     * @param roleId
     * @param resources
     */
    void allocationResources(int roleId, Integer[] resources);

    /**
     * 添加角色
     *
     * @param name
     * @param roleContant
     * @param enabled
     * @param dept
     * @param company
     * @param description
     */
    void addRole(String name, String roleContant, int enabled, int dept, int company, String description);

    /**
     * 更新角色信息
     *
     * @param roleId
     * @param name
     * @param roleContant
     * @param enabled
     * @param dept
     * @param company
     * @param description
     */
    void update(int roleId, String name, String roleContant, int enabled, int dept, int company, String description);

    /**
     * 根据公司id查询该公司下的所有角色信息
     *
     * @param companyId
     * @return
     */
    List<Role> queryAllWithCompanyId(Integer companyId);


}
