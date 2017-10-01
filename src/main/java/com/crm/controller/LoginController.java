package com.crm.controller;

import com.crm.entity.Role;
import com.crm.entity.User;
import com.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author walker tu
 * @date 2017/9/29
 * @description：
 */
@Controller
public class LoginController {
    @Autowired
    UserService userService;

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public ModelAndView login(String username, String password, HttpSession session, HttpServletRequest request) {
        if (username == null || username.trim().length() < 1) {
            return new ModelAndView("forward:login.jsp", "message", "用户名不能为空");
        }
        if (password == null || password.trim().length() < 1) {
            return new ModelAndView("forward:login.jsp", "message", "密码不能为空");
        }

        if (!this.userService.isExist(username, password)) {
            return new ModelAndView("forward:login.jsp", "message", "用户名或者密码不能为空");
        }

        User user = this.userService.queryByUsername(username);
        if (Objects.equals(user.getEnabled(), 0)) {
            return new ModelAndView("redirect:login.jsp", "message", "账号被禁用，请联系管理员");
        }
        if (Objects.equals(user.getLocked(), 1)) {
            return new ModelAndView("redirect:login.jsp", "message", "账号被锁定，请联系管理员");
        }
        session.setAttribute("user", user);
        List<Role> roles = new ArrayList<>();
        roles.addAll(user.getRoles());
        for (int i = 0; i < roles.size(); i++) {
            if (Objects.equals(roles.get(i).getRoleConstant().getName(), "SUPER_ADMIN")) {
                request.setAttribute("users", this.userService.queryAll());
                return new ModelAndView("sys/index.jsp");
            }
        }
        return new ModelAndView("sys/index.jsp");
    }

    /**
     * 安全退出
     *
     * @param session
     * @return
     */
    @RequestMapping("logout.do")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login.jsp";
    }
}
