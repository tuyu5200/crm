package com.crm.controller.sys;

import com.crm.beans.DeptBean;
import com.crm.entity.User;
import com.crm.service.CompanyService;
import com.crm.service.DeptService;
import com.crm.service.RoleService;
import com.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

/**
 * @author walker tu
 * @date 2017/9/29
 * @description：
 */
@Controller
@RequestMapping("sys/user/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("addUser.do")
    public ModelAndView addUser(User user) {
        this.userService.save(user);
        return this.queryAll();
    }

    @RequestMapping("queryAllUser.do")
    private ModelAndView queryAll() {
        return new ModelAndView("sys/user/index.jsp", "users", this.userService.queryAll());
    }

    @RequestMapping("deleteUser.do")
    public ModelAndView deleteUserById(int userId) {
        this.userService.delete(userId);
        return queryAll();
    }

    /**
     * 修改用户时的数据回显
     *
     * @param userId
     * @return
     */
    @RequestMapping("dataEcho.do")
    public ModelAndView updateView(int userId) {
        User user = this.userService.queryById(userId);
        ModelAndView modelAndView = new ModelAndView("sys/user/modify.jsp", "updatingUser", user);
        modelAndView.addObject("depts", this.deptService.queryAll());
        modelAndView.addObject("companies", this.companyService.queryAll());
        return modelAndView;
    }

    /**
     * 更新用户
     *
     * @param user
     * @param companyId
     * @param deptId
     * @return
     */
    @RequestMapping("updateUser.do")
    public String updateUser(User user, Integer companyId, Integer deptId) {
        if (Objects.isNull(companyId)) {
            companyId = 1;
        }
        user.setDept(this.deptService.queryById(deptId));
        user.setCompany(this.companyService.queryById(companyId));
        this.userService.update(user);
        return "redirect:/sys/user/queryAllUser.do";
    }

    /**
     * 为用户分配角色
     */
    @RequestMapping("allocRole.do")
    public String allocRole(Integer userId, Integer[] roleIds) {
        if (!Objects.isNull(roleIds) && roleIds.length > 0) {
            this.userService.allocRoles(userId, roleIds);
        }
        return "redirect:/sys/user/queryAllUser.do";
    }

    /**
     * 返回该公司下所有的部门
     *
     * @param companyId
     * @return
     */
    @RequestMapping("queryDeptsByCompanyId.do")
    @ResponseBody
    public List<DeptBean> queryDeptsByCompanyId(Integer companyId) {
        List<DeptBean> deptList = this.companyService.queryAllDepts(companyId);
        return deptList;
    }
}
