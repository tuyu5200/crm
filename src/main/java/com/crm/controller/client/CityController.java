package com.crm.controller.client;

import com.crm.beans.CityBean;
import com.crm.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author walker tu
 * @date 2017/10/8
 * @description
 */
@Controller
public class CityController {
    @Autowired
    private CityService cityService;

    /**
     * json 通过父级查找所有的子节点
     *
     * @param parentId
     * @return
     */
    @RequestMapping("getChilds.do")
    @ResponseBody
    public List<CityBean> queryCitiesByParentId(Integer parentId) {
        return this.cityService.queryCitiesByParentId(parentId);
    }

    @RequestMapping("queryProvince.do")
    @ResponseBody
    public List<CityBean> queryProvince() {
        return this.cityService.queryCitiesByLevel(1);
    }
}
