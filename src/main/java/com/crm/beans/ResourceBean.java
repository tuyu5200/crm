package com.crm.beans;

import com.crm.entity.Resource;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author walker tu
 * @date 2017/9/30
 * @description：
 */
@Data
public class ResourceBean {
    private int id;
    private String name;
    private String title;
    private String href;
    private String target;
    private String constant;
    private Integer shown;
    private Integer enabled;
    private Integer type;
    private ResourceBean parent;
    private List<ResourceBean> childes;

    public ResourceBean(Resource resource) {
        this.id = resource.getId();
        this.name = resource.getName();
        this.title = resource.getTitle();
        this.href = resource.getHref();
        this.target = resource.getTarget();
        this.constant = resource.getConstant();
        this.shown = resource.getShown();
        this.enabled = resource.getEnabled();
        this.type = resource.getType();
        this.parent = new ResourceBean(resource.getParent());
        this.childes = new ArrayList<>();
        Set<Resource> resources = resource.getResources();
        for (Resource r : resources) {
            this.childes.add(new ResourceBean(r));
        }
    }
}
