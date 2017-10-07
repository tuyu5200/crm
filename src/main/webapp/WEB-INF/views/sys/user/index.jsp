<%--
  User: Walker Tu
  Date: 2017/9/29
  Time: 10:26
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>员工管理</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <script src="/assets/js/jquery.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/commons/navbar.jsp">
    <jsp:param name="activeName" value="SYSTEM"/>
</jsp:include>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3">
            <jsp:include page="/WEB-INF/views/commons/leftpanel.jsp">
                <jsp:param name="activeName" value="USER_MANAGE"/>
            </jsp:include>
        </div>
        <div class="col-md-9">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <b>用户管理</b>
                    <div class="btn-group pull-right" role="group" aria-label="...">
                        <button data-toggle="modal" data-target="#addUser" type="button"
                                class="btn btn-primary btn-small" style="margin-top: -5px">添加用户
                        </button>
                    </div>
                </div>
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <td>员工编号</td>
                        <td>员工姓名</td>
                        <td>员工账号</td>
                        <td>账号状态</td>
                        <td>邮箱</td>
                        <td>性别</td>
                        <td>员工职务</td>
                        <td>所属部门</td>
                        <td>所属公司</td>
                        <td>员工操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.name}</td>
                            <td>${user.username}</td>
                            <td>
                                <button class="btn btn-xs ${user.enabled==1?'btn-primary':'btn-danger'}">${user.enabled==1?'启用':'禁用'}</button>
                            </td>
                            <td>${user.email}</td>
                            <td>${user.sex==1?'男':'女'}</td>
                            <td><c:forEach items="${user.roles}" var="role">
                                <button class="btn btn-default btn-xs">${role.name}</button>
                            </c:forEach></td>
                            <td>${user.dept.dname}</td>
                            <td>${user.company.cname}</td>
                            <td>
                                <button class="btn btn-primary btn-xs" data-toggle="modal" data-target="#allocRole"
                                        data-id="${sessionScope.user.company.id}" data-username="${user.name}"
                                        data-userId=${user.id}>
                                    分配角色
                                </button>
                                <a class="btn btn-warning btn-xs"
                                   href="/sys/user/dataEcho.do?userId=${user.id}">修改
                                </a>
                                <a class="btn btn-danger btn-xs" href="#"
                                   data-href="/sys/user/deleteUser.do?userId=${user.id}" data-toggle="modal"
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


<script>
    <%--删除之前的提示--%>
    $('#confirm-delete').on('show.bs.modal', function (e) {
        $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
    });

    $('[data-id]').on('click', function () {
        var companyId = $(this).data('id');
        var username = $(this).data('username');
        userId = parseInt($(this).attr('data-userId'));  //得到的属性是string类型的，需要转为int进行比较
        $('#allocUserName').html("分配角色：" + username);
        $('#allocUserId').attr('value', userId);
        var target = $(this).data('target');
        $('#deptSelect').empty();
        $(target).one('shown.bs.modal', function () {
//            发送ajax得到该公司下的所有部门集合
            $.ajax({
                url: 'sys/user/queryDeptsByCompanyId.do',
                data: {'companyId': companyId},
                success: function (data) {
                    for (var i = 0; i < data.length; i++) {
                        $('#deptSelect').append('<option value=' + data[i]['id'] + '>' + data[i]['name'] + '</option>');
                    }
                    $.ajax({
                        url: 'sys/dept/queryAllRoles.do',
                        data: {'deptId': data[0]['id']},
                        success: function (data1) {
                            $('#roleTbody').empty();
                            for (var i = 0; i < data1.length; i++) {
                                $('#roleTbody').append('<tr><td><input ' + (data1[i]['userIds'].indexOf(userId) === -1 ? '' : 'checked'
                                    )
                                    + ' type="checkbox" name="roleIds" value="' + data1[i]['id'] + '"></td><td>' + data1[i]['id'] + '</td><td>' + data1[i]['name'] + '</td><td>' + data1[i]['description'] + '</td><td>' + (data1[i]['enabled'] == 1 ? '<font color="#1e90ff">启用</font>' : '<font color="red">禁用</font>') + '</td></tr>'
                                )
                                ;
                            }
                        }
                    })
                }
            });
        })
    });
    //    选择部门时加载该部门下的所有角色信息
    $(document).ready(function () {
        $('#deptSelect').change(function () {
            var deptId = $(this).children('option:selected').val();
            $.ajax({
                url: 'sys/dept/queryAllRoles.do',
                data: {'deptId': deptId},
                success: function (data1) {
                    $('#roleTbody').empty();
                    for (var i = 0; i < data1.length; i++) {
                        $('#roleTbody').append('<tr><td><input ' + (data1[i]['userIds'].indexOf(userId) === -1 ? '' : 'checked') + ' type="checkbox" name="roleIds" value="' + data1[i]['id'] + '"></td><td>' + data1[i]['id'] + '</td><td>' + data1[i]['name'] + '</td><td>' + data1[i]['description'] + '</td><td>' + (data1[i]['enabled'] == 1 ? '<font color="#1e90ff">启用</font>' : '<font color="red">禁用</font>') + '</td></tr>');
                    }
                }
            })
        });
    });

    //    实现全选和反选功能
    $(function () {
        $("#checkAll").click(function () {
            if (this.checked) {
                $("input[name='roleIds']").attr('checked', true);
            } else {
                $("input[name='roleIds']").attr('checked', false);
            }
        });
    });

</script>
<%--给用户分配角色的modal--%>
<div class="modal fade" id="allocRole" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form method="post" action="/sys/user/allocRole.do" class="form-horizontal">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <H4 class="modal-title" id="allocUserName"></H4>
                    <input type="hidden" name="userId" id="allocUserId">
                    <div class="row" style="margin-top: 5px">
                        <div class="col-sm-3">请选择部门</div>
                        <div class="col-sm-6">
                            <select class="form-control" name="deptId" id="deptSelect">
                            </select>
                        </div>
                    </div>
                </div>
                <div class="modal-body">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <div class="col-sm-6">
                                        <table class="table table-bordered">
                                            <thead>
                                            <tr>
                                                <th><input type="checkbox" id="checkAll"></th>
                                                <th>角色编号</th>
                                                <th>角色类型</th>
                                                <th>角色描述</th>
                                                <th>是否启用</th>
                                            </tr>
                                            </thead>
                                            <tbody id="roleTbody">

                                            </tbody>
                                            <tfoot></tfoot>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary">分配</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 添加用户的modal -->
<div class="modal fade" id="addUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">添加用户</h4>
            </div>

            <form action="/sys/user/addUser.do" class="form-horizontal" method="post">
                <div class="modal-body">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">员工姓名</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="name" class="form-control" placeholder="员工姓名">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">性别</label>
                                    <div class="col-sm-4">
                                        <label class="radio-inline">
                                            <input type="radio" name="sex" value="1"> 男
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="sex" value="0"> 女
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">用户名</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="username" class="form-control" placeholder="用户名">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">密码</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="password" class="form-control" placeholder="密码">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">邮箱</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="email" class="form-control" placeholder="邮箱">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">状态</label>
                                    <div class="col-sm-4">
                                        <div>
                                            <label class="radio-inline">
                                                <input type="radio" name="locked" value="1"> 已锁定
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="locked" value="0"> 未锁定
                                            </label>
                                        </div>
                                        <div>
                                            <label class="radio-inline">
                                                <input type="radio" name="enabled" value="1"> 启用
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="enabled" value="0"> 禁用
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">备注</label>
                                    <div class="col-sm-4">
                                        <textarea name="description" rows="4" class="form-control"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="reset" class="btn btn-warning">重置</button>
                    <button type="submit" class="btn btn-primary">添加</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>