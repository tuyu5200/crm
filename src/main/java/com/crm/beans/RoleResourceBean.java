package com.crm.beans;

import lombok.Data;

/**
 * @author walker tu
 * @date 2017/10/2
 * @description 角色资源vo
 */
@Data
public class RoleResourceBean {

    private Integer roleId;
    private Integer resourceId;
    private Integer enabled;

    public RoleResourceBean() {
    }

    public RoleResourceBean(Integer roleId, Integer resourceId, Integer enabled) {
        this.roleId = roleId;
        this.resourceId = resourceId;
        this.enabled = enabled;
    }
}
