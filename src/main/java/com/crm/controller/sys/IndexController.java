package com.crm.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author walker tu
 * @date 2017/10/7
 * @description
 */
@Controller
public class IndexController {
    @RequestMapping("sysIndex.do")
    public ModelAndView index() {
        return new ModelAndView("sys/index.jsp");
    }
}
