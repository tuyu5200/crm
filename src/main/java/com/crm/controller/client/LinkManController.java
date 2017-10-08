package com.crm.controller.client;

import com.crm.beans.ClientBean;
import com.crm.beans.LinkManBean;
import com.crm.entity.Client;
import com.crm.service.ClientService;
import com.crm.service.LinkManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    @Autowired
    private ClientService clientService;
    private final String FLUSH_URL = "redirect:/linkman/queryAll.do";

    @RequestMapping("queryAll.do")
    public ModelAndView queryAll() {
        return new ModelAndView("/client/linkman.jsp", "linkmen", this.linkManService.queryAll());
    }

    @RequestMapping("delete.do")
    public String delete(Integer id) {
        if (Objects.isNull(id))
            throw new IllegalArgumentException("删除联系人时id不能为空");
        this.linkManService.delete(id);
        return this.FLUSH_URL;
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("deleteByIds.do")
    @ResponseBody
    public String deleteByIds(@RequestParam(value = "ids[]") Integer[] ids) {
        this.linkManService.deleteByIds(ids);
        return "success";
    }

    @RequestMapping("add.do")
    public String add(LinkManBean linkManBean, Integer selClientId) {
        if (Objects.isNull(selClientId))
            throw new IllegalArgumentException("添加联系人时对应的客户不能为空");
        Client client = this.clientService.queryById(selClientId);
        this.linkManService.addLinkman(linkManBean, client);
        return this.FLUSH_URL;
    }

    @RequestMapping("update.do")
    public String update(LinkManBean linkManBean) {
        this.linkManService.updateLinkman(linkManBean);
        return this.FLUSH_URL;
    }

    /**
     * 更新时的ajax数据回显
     *
     * @param id
     * @return
     */
    @RequestMapping("queryById.do")
    @ResponseBody
    public LinkManBean linkManEcho(Integer id) {
        LinkManBean linkManBean = new LinkManBean(this.linkManService.queryById(id));
        return linkManBean;
    }

    @RequestMapping("queryAllClients.do")
    @ResponseBody
    public List<ClientBean> queryAllClients() {
        List<Client> clients = this.clientService.queryAll();
        List<ClientBean> clientBeans = new ArrayList<>();
        for (Client c : clients) {
            clientBeans.add(new ClientBean(c));
        }
        return clientBeans;
    }
}
