<%--
  User: Walker Tu
  Date: 2017/9/29
  Time: 10:26
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>角色管理</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <link href="/assets/plugins/ztree/css/metroStyle/metroStyle.css" rel="stylesheet">
    <script src="/assets/js/jquery.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>
    <script src="/assets/plugins/ztree/js/jquery.ztree.all-3.5.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/commons/navbar.jsp">
    <jsp:param name="activeName" value="SYSTEM"/>
</jsp:include>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3">
            <jsp:include page="/WEB-INF/views/commons/leftpanel.jsp">
                <jsp:param name="activeName" value="ROLE_MANAGE"/>
            </jsp:include>
        </div>
        <div class="col-md-9">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <b>角色管理</b>
                    <div class="btn-group pull-right" role="group" aria-label="...">
                        <button data-toggle="modal" data-target="#addRole" type="button"
                                class="btn btn-primary btn-small" style="margin-top: -5px">添加角色
                        </button>
                    </div>
                </div>
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <td>角色编号</td>
                        <td>角色名</td>
                        <td>状态</td>
                        <td>所属部门</td>
                        <td>所属公司</td>
                        <td>操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${roles}" var="role">
                        <tr>
                            <td>${role.id}</td>
                            <td>${role.name}</td>
                            <td>
                                <button class="btn btn-xs ${role.enabled==1?'btn-primary':'btn-danger'}">${role.enabled==1?'启用':'禁用'}</button>
                            </td>
                            <td>${role.dept.dname}</td>
                            <td>${role.company.cname}</td>
                            <td>
                                <button class="btn btn-primary btn-xs" data-toggle="modal" data-target="#allocResource"
                                        data-roleId="${role.id}" data-roleName="${role.name}">分配资源
                                </button>
                                <button class="btn btn-xs btn-warning" data-toggle="modal" data-target="#modifyRole"
                                        data-id="${role.id}">修改
                                </button>
                                <a class="btn btn-danger btn-xs" href="#"
                                   data-href="/sys/role/delete.do?roleId=${role.id}" data-toggle="modal"
                                   data-target="#confirm-delete">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<%--分配资源的modal--%>
<div class="modal fade" id="allocResource" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="allocResTitle">分配资源:**</h4>
            </div>
            <div class="modal-body">
                <ul id="tree" class="ztree"></ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <a class="btn btn-primary" data-toggle="submit">分配资源</a>
            </div>
        </div>
    </div>
</div>

<%--删除时的确认框--%>
<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                请确认
            </div>
            <div class="modal-body">
                确认删除该记录吗？
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <a class="btn btn-danger btn-ok">删除记录</a>
            </div>
        </div>
    </div>
</div>
<!-- 添加角色的modal -->
<div>
    <div class="modal fade" id="addRole" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">添加角色</h4>
                </div>

                <form action="/sys/role/add.do" class="form-horizontal" method="post">
                    <div class="modal-body">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">角色名</label>
                                        <div class="col-sm-4">
                                            <input type="text" name="name" class="form-control" placeholder="角色名">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">角色类别</label>
                                        <div class="col-sm-4">
                                            <input type="text" name="roleContant" class="form-control"
                                                   placeholder="角色类别"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">是否启用</label>
                                        <div class="col-sm-4">
                                            <label class="radio-inline">
                                                <input type="radio" name="enabled" value="1"> 启用
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="enabled" value="0"> 禁用
                                            </label>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">所属部门</label>
                                        <div class="col-sm-4">
                                            <select class="form-control" name="dept">
                                                <option value="-1">-请选择-</option>
                                                <c:forEach items="${depts}" var="dept">
                                                    <option value="${dept.id}">${dept.dname}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">所属公司</label>
                                        <div class="col-sm-4">
                                            <select class="form-control" name="company">
                                                <option value="-1">-请选择-</option>
                                                <c:forEach items="${companies}" var="company">
                                                    <option value="${company.id}">${company.cname}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">角色描述</label>
                                    <div class="col-sm-4">
                                        <textarea name="description" rows="4" class="form-control"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="submit" class="btn btn-primary">添加</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- 修改角色的modal -->
<div>
    <div class="modal fade" id="modifyRole" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">修改角色</h4>
                </div>

                <form action="/sys/role/update.do" class="form-horizontal" method="post">
                    <div class="modal-body">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">角色名</label>
                                        <<input type="hidden" name="roleId" id="update-id">
                                        <div class="col-sm-4">
                                            <input type="text" name="name" id="update-name" class="form-control"
                                                   placeholder="角色名">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">角色类别</label>
                                        <div class="col-sm-4">
                                            <input type="text" name="roleContant" id="update-roleContant"
                                                   class="form-control"
                                                   placeholder="角色类别"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">是否启用</label>
                                        <div class="col-sm-4">
                                            <label class="radio-inline">
                                                <input type="radio" name="enabled" id="enabled1" value="1"> 启用
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="enabled" id="enabled0" value="0"> 禁用
                                            </label>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">所属部门</label>
                                        <div class="col-sm-4">
                                            <select class="form-control" name="dept" id="deptSelect">
                                                <option value="-1">-请选择-</option>
                                                <c:forEach items="${depts}" var="dept">
                                                    <option value="${dept.id}">${dept.dname}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">所属公司</label>
                                        <div class="col-sm-4">
                                            <select class="form-control" name="company" id="comSelect">
                                                <option value="-1">-请选择-</option>
                                                <c:forEach items="${companies}" var="company">
                                                    <option value="${company.id}">${company.cname}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">角色描述</label>
                                    <div class="col-sm-4">
                                        <textarea name="description" rows="4" class="form-control"
                                                  id="update-description"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="submit" class="btn btn-primary">修改</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
<script>
    var zTreeObj;
    var setting = {
        check: {
            enable: true,
            chkDisabledInherit: true
        },
        data: {
            simpleData: {
                enable: true,
                idKey: 'id',
                pIdKey: 'parent',
                rootPId: 0
            }
        }
    };
    $(function () {
        //绑定按钮的click事件   当click事件发生以后  去绑定shown.bs.modal事件  当事件被触发以后  则用Ajax去加载数据  进行显示
        $('[data-roleId]').on('click', function () {
            var id = $(this).attr('data-roleId');
            var roleName = $(this).attr('data-roleName');
            $("#allocResTitle").html("分配资源:" + roleName);
            var target = $(this).data('target');
            $(target).one('shown.bs.modal', function () {
                //发送ajax
                $.ajax({
                    url: 'sys/role/resourceEhco.do',
                    data: {"roleId": id},
                    success: function (data) {
                        console.log(data);
                        //配置显示 tree
                        $.each(data, function (i, o) {
                            o.chkDisabled = !o.enabled;
                        });
                        zTreeObj = $.fn.zTree.init($('#tree'), setting, data);
                        zTreeObj.expandAll(true);
                    }
                })
            });
            $(target).one('hidden.bs.modal', function () {
                $('#tree').empty();
                zTreeObj = null;
            });

            $(target).find('[data-toggle="submit"]').one('click', function () {
                //得到选中的所有节点
                var nodes = zTreeObj.getCheckedNodes(true);
                //将节点数据提交到后台
                var checked = [];
                $.each(nodes, function (i, o) {
                    checked.push(o.id);
                });
                // 序列化参数
                // 第二个参数就是  去掉序列化后的参数中的中括号
                var params = $.param({"resourceIds": checked, "roleId": id}, true);
                $.ajax({
                    url: 'sys/role/allocResource.do',
                    data: params,
                    type: 'post',
                    success: function () {
                        $(target).modal('hide');
                    }
                })
                //修改成功后  隐藏当前的modal
            });
        })

    });


    //绑定按钮的click事件   当click事件发生以后  去绑定shown.bs.modal事件  当事件被触发以后  则用Ajax去加载数据  进行显示
    $(function () {
        $('[data-id]').on('click', function () {
            var id = $(this).data('id');
            var target = $(this).data('target');
            $(target).one('shown.bs.modal', function () {
                //发送ajax
                $.ajax({
                    url: 'sys/role/queryById.do',
                    data: {'id': id},
                    success: function (data) {
                        console.log(data);
                        $('#update-id').val(data['id']);
                        $('#update-name').val(data['name']);
                        $('#update-roleContant').val(data['name']);
                        $('#update-description').val(data['description']);
                        if (data['enabled']) {
                            $('#enabled1').attr('checked', 'checked');
                        } else {
                            $('#enabled0').attr('checked', 'checked');
                        }
                        var cid = data['companyId'];
                        var did = data['deptId'];
                        $("#deptSelect").find("option").eq(did).attr("selected", true);
                        $("#comSelect").find("option").eq(cid).attr("selected", true);
                    }
                })
            });
            //修改的模态框隐藏时执行的操作
            $(target).one('hidden.bs.modal', function () {

            })
        })
    });
</script>