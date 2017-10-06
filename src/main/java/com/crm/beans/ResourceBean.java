package com.crm.beans;

import com.crm.entity.Resource;
import lombok.Data;

import java.util.Objects;

/**
 * @author walker tu
 * @date 2017/9/30
 * @description：
 */
@Data
public class ResourceBean {
    private int id;
    private String name;
    private boolean enabled = true;
    private Integer parent = 0;
    private boolean checked = false;

    public ResourceBean(Resource resource) {
        if (Objects.isNull(resource)) {
            throw new IllegalArgumentException("resource对象不能为空");
        }
        this.id = resource.getId();
        this.name = resource.getName();
        if (!Objects.isNull(resource.getParent())) {
            this.parent = resource.getParent().getId();
        }
        this.enabled = resource.getEnabled() == 1;
    }
}
