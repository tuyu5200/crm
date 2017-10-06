package com.crm.controller.sys;

import com.crm.beans.DeptBean;
import com.crm.beans.RoleBean;
import com.crm.entity.Company;
import com.crm.entity.Dept;
import com.crm.entity.User;
import com.crm.service.DeptService;
import com.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

/**
 * @author walker tu
 * @date 2017/9/30
 * @description：
 */
@Controller
@RequestMapping("sys/dept/")
public class DeptController {

    @Autowired
    private DeptService deptService;
    @Autowired
    private UserService userService;

    @RequestMapping("addDept.do")
    public String addDept(Dept dept, int userId) {
        User user = this.userService.queryById(userId);
        Company company = user.getCompany();
        dept.setCompanies(new HashSet<>());
        dept.getCompanies().add(company);
        this.deptService.save(dept);
        return "redirect:/sys/dept/queryAll.do?id=" + userId;
    }


    @RequestMapping("queryAll.do")
    public ModelAndView queryAll(User user) {
        List<DeptBean> deptBeans = this.deptService.queryAllDeptBeans(user);
        return new ModelAndView("sys/dept/index.jsp", "depts", deptBeans);
    }

    @RequestMapping("updateDept.do")
    public String updateDept(Dept dept, Integer userId) {
        Dept dept1 = this.deptService.queryById(dept.getId());
        dept1.setDname(dept.getDname());
        dept1.setDescription(dept.getDescription());
        this.deptService.update(dept1);
        return "redirect:/sys/dept/queryAll.do?id=" + userId;
    }

    @RequestMapping("deleteDept.do")
    public String deleteDept(Integer id, Integer userId) {
        this.deptService.delete(id);
        return "redirect:/sys/dept/queryAll.do?id=" + userId;
    }

    @RequestMapping("queryById.do")
    @ResponseBody
    public DeptBean queryById(int id) throws IOException {
        Dept dept = this.deptService.queryById(id);
        return new DeptBean(dept);
    }

    /**
     * 查询该部门下所有的角色信息
     *
     * @param deptId 部门id
     * @return
     */
    @RequestMapping("queryAllRoles.do")
    @ResponseBody
    public List<RoleBean> queryAllRoles(Integer deptId) {
        return this.deptService.queryAllRolesByDeptId(deptId);
    }
}
