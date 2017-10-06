package com.crm.beans;

import com.crm.entity.Role;
import com.crm.entity.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author walker tu
 * @date 2017/10/2
 * @description
 */
@Data
public class RoleBean {
    private Integer id;
    private String name;
    private Integer enabled;
    private String description;
    private String roleConstant;
    private int deptId;
    private int companyId;
    //包含的用户id集合
    private List<Integer> userIds = new ArrayList<>();

    public RoleBean(Role role) {
        this.id = role.getId();
        if (role.getName() == "")
            this.name = "";
        else
            this.name = role.getName();
        if (role.getEnabled() == null)
            this.enabled = 0;
        else
            this.enabled = role.getEnabled();
        if (role.getDescription() == "" || Objects.isNull(role.getDescription()))
            this.description = "";
        else
            this.description = role.getDescription();
        if (role.getRoleConstant().getName() == "" || role.getRoleConstant().getName() == null)
            this.roleConstant = "";
        else
            this.roleConstant = role.getRoleConstant().getName();
        if (Objects.isNull(role.getDept()))
            this.deptId = 0;
        else
            this.deptId = role.getDept().getId();
        if (Objects.isNull(role.getCompany()))
            this.companyId = 0;
        else
            this.companyId = role.getCompany().getId();

        if (!Objects.isNull(role.getUsers()) && role.getUsers().size() > 0) {
            List<User> users = new ArrayList<>();
            users.addAll(role.getUsers());
            for (int i = 0; i < users.size(); i++) {
                this.userIds.add(users.get(i).getId());
            }
        }

    }
}
