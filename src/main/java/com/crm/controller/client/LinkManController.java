package com.crm.controller.client;

import com.crm.service.LinkManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author walker tu
 * @date 2017/10/7
 * @description
 */
@Controller
@RequestMapping("linkman/")
public class LinkManController {
    @Autowired
    private LinkManService linkManService;

    @RequestMapping("queryAll.do")
    public ModelAndView queryAll() {
        return new ModelAndView("/client/linkman.jsp", "linkmen", this.linkManService.queryAll());
    }
}
