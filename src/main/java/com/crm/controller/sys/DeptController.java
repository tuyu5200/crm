package com.crm.controller.sys;

import com.crm.beans.DeptBean;
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
import java.util.List;
import java.util.Set;

/**
 * @author walker tu
 * @date 2017/9/30
 * @description：
 */
@Controller
@RequestMapping("sys/dept/")
public class DeptController {
    private String url = "redirect:/sys/dept/queryAll.do";

    @Autowired
    private DeptService deptService;
    @Autowired
    private UserService userService;

    //TODO:添加部门未完成
    @RequestMapping("addDept.do")
    public String addDept(Dept dept, int userId) {
        User user = this.userService.queryById(userId);
        Company company = user.getCompany();
        Set<Dept> depts = company.getDepts();
        depts.add(dept);
        this.userService.update(user);
        return this.url;
    }

    @RequestMapping("queryAll.do")
    public ModelAndView queryAll(User user) {
        List<DeptBean> deptBeans = this.deptService.queryAllDeptBeans(user);
        return new ModelAndView("sys/dept/index.jsp", "depts", deptBeans);
    }

    @RequestMapping("updateDept.do")
    public String updateDept(Dept dept) {
        this.deptService.update(dept);
        return this.url;
    }

    @RequestMapping("deleteDept.do")
    public String deleteDept(int id) {
        this.deptService.delete(id);
        return this.url;
    }

    @RequestMapping("queryById.do")
    @ResponseBody
    public DeptBean queryById(int id) throws IOException {
        Dept dept = this.deptService.queryById(id);
        return new DeptBean(dept);
    }
}
