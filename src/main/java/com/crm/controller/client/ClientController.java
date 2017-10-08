package com.crm.controller.client;

import com.crm.beans.ClientBean;
import com.crm.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

/**
 * @author walker tu
 * @date 2017/10/7
 * @description
 */
@Controller
@RequestMapping("client/")
public class ClientController {
    @Autowired
    private ClientService clientService;

    private final String URL = "redirect:/client/queryAll.do";

    @RequestMapping("queryAll.do")
    public ModelAndView queryAll() {
        return new ModelAndView("/client/client.jsp", "clients", this.clientService.queryAll());
    }

    @RequestMapping("queryById.do")
    @ResponseBody
    public ClientBean queryById(Integer id) {
        if (Objects.isNull(id))
            return null;
        return new ClientBean(this.clientService.queryById(id));
    }

    @RequestMapping("addClient.do")
    public String addClient(ClientBean clientBean) {
        if (Objects.isNull(clientBean))
            throw new IllegalArgumentException("添加客户的参数不正确");
        this.clientService.addClient(clientBean);
        return this.URL;
    }

    @RequestMapping("updata.do")
    public String updata(ClientBean clientBean) {
        this.clientService.updateClient(clientBean);
        return this.URL;
    }

    @RequestMapping("delete.do")
    public String delete(Integer id) {
        if (Objects.isNull(id))
            throw new IllegalArgumentException("删除客户的参数不正确");
        this.clientService.delete(id);
        return this.URL;
    }

    @RequestMapping("deleteByIds.do")
    @ResponseBody
    public String deleteByIds(@RequestParam(value = "ids[]") Integer[] ids) {
        this.clientService.deleteByIds(ids);
        return "success";
    }
}
