package com.crm.controller.sys;

import com.crm.beans.ResourceBean;
import com.crm.beans.RoleBean;
import com.crm.commons.ResourceConstantEnum;
import com.crm.dao.RoleContantDao;
import com.crm.entity.Resource;
import com.crm.entity.Role;
import com.crm.entity.RoleResource;
import com.crm.security.annotation.Authorize;
import com.crm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author walker tu
 * @date 2017/9/30
 * @description：
 */
@Controller
@RequestMapping("sys/role/")
public class RoleController {
    @Autowired
    private RoleResourceService roleResourceService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private RoleContantDao roleContantDao;
    @Autowired
    private ResourceService resourceService;

    /**
     * 分配资源时的ztree 数据以及数据回显
     *
     * @param roleId
     * @return
     */
    @Authorize(ResourceConstantEnum.SYS_ROLE_VIEW)
    @RequestMapping("resourceEhco.do")
    @ResponseBody
    public List<ResourceBean> resourceEhco(Integer roleId) {
        List<ResourceBean> resourceBeans = new ArrayList<>();
        if (Objects.isNull(roleId)) {
            throw new IllegalArgumentException("roleId参数不能为空");
        }
        Role role = this.roleService.queryById(roleId);
        List<Resource> resources = this.resourceService.queryAll();
        //构造资源
        for (Resource resource : resources) {
            //确定哪些节点时checked
            ResourceBean resourceBean = new ResourceBean(resource);
            resourceBeans.add(resourceBean);
            for (RoleResource rr : role.getRoleResources()) {
                if (Objects.equals(rr.getResource(), resource)) {
                    resourceBean.setChecked(true);
                    break;
                }
            }
        }
        return resourceBeans;
    }

    /**
     * 给指定的角色分配资源
     *
     * @param roleId      角色的id
     * @param resourceIds 要分配的资源id数组
     */
    @Authorize(ResourceConstantEnum.SYS_ROLE_ALLOC_RESOURCE)
    @RequestMapping("allocResource.do")
    public String allocation(Integer roleId, Integer[] resourceIds) {
        if (Objects.isNull(resourceIds) || Objects.isNull(roleId) || resourceIds.length < 1) {
            throw new IllegalArgumentException("分配资源时参数不正确");
        }
        this.roleResourceService.allocation(roleId, resourceIds);
        return "redirect:/sys/role/queryAll.do";
    }

    /**
     * 查询所有的角色信息
     *
     * @return
     */
    @Authorize(ResourceConstantEnum.SYS_ROLE_VIEW)
    @RequestMapping("queryAll.do")
    public ModelAndView queryAll() {
        ModelAndView modelAndView = new ModelAndView("sys/role/index.jsp", "roles", this.roleService.queryAll());
        modelAndView.addObject("companies", this.companyService.queryAll());
        modelAndView.addObject("depts", this.deptService.queryAll());
        modelAndView.addObject("constants", this.roleContantDao.queryAll());
        return modelAndView;
    }

    /**
     * 应该定义一个bean的
     *
     * @param name
     * @param roleContant
     * @param enabled
     * @param dept
     * @param company
     * @param description
     * @return
     */
    @Authorize(ResourceConstantEnum.SYS_ROLE_SAVE)
    @RequestMapping("add.do")
    public String addRole(String name, String roleContant, int enabled, int dept, int company, String description) {
        this.roleService.addRole(name, roleContant, enabled, dept, company, description);
        return "redirect:/sys/role/queryAll.do";
    }

    @Authorize(ResourceConstantEnum.SYS_ROLE_UPDATE)
    @RequestMapping("update.do")
    public String update(int roleId, String name, String roleContant, int enabled, int dept, int company, String description) {
        this.roleService.update(roleId, name, roleContant, enabled, dept, company, description);
        return "redirect:/sys/role/queryAll.do";
    }

    @Authorize(ResourceConstantEnum.SYS_ROLE_DELETE)
    @RequestMapping("delete.do")
    public String delete(int roleId) {
        this.roleService.delete(roleId);
        return "redirect:/sys/role/queryAll.do";
    }

    @Authorize(ResourceConstantEnum.SYS_ROLE_VIEW)
    @RequestMapping("queryById.do")
    @ResponseBody
    public RoleBean queryById(int id) {
        return new RoleBean(this.roleService.queryById(id));
    }
}
