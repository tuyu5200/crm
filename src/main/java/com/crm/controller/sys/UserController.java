package com.crm.controller.sys;

import com.crm.entity.User;
import com.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping("dataEcho.do")
    public ModelAndView updateView(int userId) {
        User user = this.userService.queryById(userId);
        return new ModelAndView("sys/user/modify.jsp", "updatingUser", user);
    }

    @RequestMapping("updateUser.do")
    public String updateUser(User user) {
        this.userService.update(user);
        return "redirect:sys/user/queryAll.do";
    }
}
