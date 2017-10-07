package com.crm.security;

import com.crm.entity.Resource;
import com.crm.entity.Role;
import com.crm.entity.User;
import com.crm.security.annotation.Authorize;
import com.crm.service.ResourceService;
import com.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author walker tu
 * @date 2017/9/29
 * @description：
 */

/**
 * @author walker tu
 * @date 2017/9/29
 * @description：
 */

@Component
public class SecurityManager extends HandlerInterceptorAdapter {

    private static final String SQL_CONSTNANT_QUERY = "SELECT " +
            " res.constant  " +
            " FROM " +
            " crm_resource res " +
            " LEFT OUTER JOIN crm_role_resource ro_res ON res.id = ro_res.resource_id " +
            " LEFT OUTER JOIN crm_role ro ON ro_res.role_id = ro.id " +
            " LEFT OUTER JOIN crm_user_role cur ON ro.id = cur.role_id " +
            " LEFT OUTER JOIN crm_user u ON cur.user_id = u.id  " +
            " WHERE " +
            " u.id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private ResourceService resourceService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //判断用户是否登陆
        User user = (User) request.getSession().getAttribute("user");
        if (Objects.isNull(user)) {
            response.sendRedirect("/login.jsp");
            return false;
        }
        //加载资源
        Set<Resource> resources = this.getResourcesSet(user.getId());
        //设置根节点
        List<Resource> menu = this.setLeftPannel(resources);
        //设置功能按钮
        List<String> functionButton = this.setFunctionButton(resources);
        request.setAttribute("sysmenu", menu);
        request.setAttribute("sysfeature", functionButton);
        //判断权限
        HandlerMethod method = (HandlerMethod) handler;
        Authorize authorize = method.getMethodAnnotation(Authorize.class);
//        尚未指定权限表示通过
        if (authorize == null) {
            return true;
        }

        if (!authorize.ignore()) {
            String value = authorize.value().toString();
            //没有权限
            if (!this.isAuthorized(value, user.getId())) {
                request.getRequestDispatcher("/WEB-INF/views/commons/unauthorized.jsp").forward(request, response);
                return false;
            }
        }
        return true;
    }

    /**
     * 判断当前用户中是否有给定的权限
     *
     * @param permission 注解上的资源唯一标识符
     * @param uid        用户 id 用户sql查询
     * @return 是否含有
     */
    private boolean isAuthorized(String permission, Integer uid) {
        if (permission == null || permission.isEmpty()) {
            return false;
        }

        List<String> permissions = this.jdbcTemplate.queryForList(SQL_CONSTNANT_QUERY, new Object[]{uid}, String.class);
        return permissions.contains(permission);
    }

    /**
     * 获取所有的功能按钮资源，并传递到页面上
     *
     * @param resources
     * @return
     */
    private List<String> setFunctionButton(Set<Resource> resources) {
        List<String> rs = new ArrayList<>();
        for (Resource resource : resources) {
            if (resource.getType() == 2) {
                rs.add(resource.getConstant());
            }
        }
        return rs;
    }

    /**
     * 通过递归得到树形结构，用于显示左侧菜单栏
     *
     * @param resources
     * @return
     */
    private List<Resource> setLeftPannel(Set<Resource> resources) {
        List<Resource> rs = new ArrayList<>();
        for (Resource resource : resources) {
            if (resource.getType() == 1) {
                rs.add(resource);
            }
        }
        // 将得到的树形数据 通过递归 转换为 树形结构
        return this.transform(rs, null);
    }

    /**
     * 获取该用户所有的资源
     *
     * @param uid 用户id
     * @return
     */
    private Set<Resource> getResourcesSet(Integer uid) {
        Set<Resource> resources = new HashSet<>();
        User user = this.userService.queryById(uid);
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (role.getEnabled() == 0) {
                //如果当前角色不可用
                continue;
            }
            List<Resource> rs = this.resourceService.queryByRoleId(role.getId());
            for (Resource r : rs) {
                if (Objects.isNull(r))
                    continue;
                if (r.getEnabled() == 1) {
                    resources.add(r);
                }
            }
        }
        return resources;
    }


    /**
     * 将资源集合转换为树形结构
     *
     * @param nodes 要进行转换的list集合
     * @param id    节点的id
     * @return
     */
    private List<Resource> transform(List<Resource> nodes, Integer id) {
        List<Resource> roots = new ArrayList<Resource>();
        for (Resource node : nodes) {
            Resource parent = node.getParent();
            Integer pid = parent == null ? null : parent.getId();
            if (Objects.equals(id, pid)) {
                List<Resource> children = transform(nodes, node.getId());
                node.setResources(new HashSet<Resource>(children));
                roots.add(node);
            }
        }
        return roots;
    }

}
