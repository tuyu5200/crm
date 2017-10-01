package com.crm.controller.sys;

import com.crm.entity.Resource;
import com.crm.entity.Role;
import com.crm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author walker tu
 * @date 2017/9/30
 * @description：
 */
@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    public void addResource(Role role, List<Resource> resources) {

    }

}
