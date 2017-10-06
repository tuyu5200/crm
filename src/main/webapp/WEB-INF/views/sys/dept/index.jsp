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
    <title>部门管理</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <script src="/assets/js/jquery.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>

</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">客户关系管理系统</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">用户管理 <span class="sr-only">(current)</span></a></li>
                <li><a href="#">角色管理</a></li>
                <li><a href="#">资源管理</a></li>

            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">${sessionScope.user.username}<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">个人信息</a></li>
                        <li><a href="#">修改密码</a></li>
                        <li><a href="#">查看任务</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="/logout.do">安全退出</a></li>
                    </ul>
                </li>
            </ul>
            <form class="navbar-form navbar-left pull-right" method="post" action="">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">搜索</button>
            </form>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">首页</h3>
                </div>
                <ul class="list-group">
                    <li class="list-group-item"><a href="/sys/company/queryAll.do">公司管理</a></li>
                    <li class="list-group-item active">部门管理</li>
                    <li class="list-group-item">菜单管理</li>
                    <li class="list-group-item">服务管理</li>
                    <li class="list-group-item"><a href="/sys/user/queryAllUser.do">用户管理</a></li>
                    <li class="list-group-item"><a href="/sys/role/queryAll.do">角色管理</a></li>
                </ul>
                <div class="panel-body">
                </div>
            </div>
        </div>
        <div class="col-md-9">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <b>部门管理</b>
                    <div class="btn-group pull-right" role="group" aria-label="...">
                        <button data-toggle="modal" data-target="#addDept" type="button"
                                class="btn btn-primary btn-small" style="margin-top: -5px">添加部门
                        </button>
                    </div>
                </div>
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <td>部门编号</td>
                        <td>部门名称</td>
                        <td>所属公司</td>
                        <td>部门描述</td>
                        <td>操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${depts.size()<1}">
                        <tr align="center">
                            <td colspan="5">
                                <font color="red">暂无数据</font>
                            </td>
                        </tr>
                    </c:if>
                    <c:forEach items="${depts}" var="dept">
                        <tr>
                            <td>${dept.id}</td>
                            <td>${dept.name}</td>
                            <td>${dept.company.cname}</td>
                            <td>${dept.description}</td>
                            <td>
                                <button class="btn btn-warning btn-xs" data-toggle="modal" data-target="#updateDept"
                                        data-id="${dept.id}">
                                    修改
                                </button>
                                <a class="btn btn-danger btn-xs" href="#"
                                   data-href="/sys/dept/deleteDept.do?id=${dept.id}&userId=${sessionScope.user.id}"
                                   data-toggle="modal"
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
<!-- 添加部门的modal -->
<div class="modal fade" id="addDept" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">添加部门</h4>
            </div>
            <form action="/sys/dept/addDept.do" class="form-horizontal" method="post">
                <div class="modal-body">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">所属公司</label>
                                    <div class="col-sm-4">
                                        <%--<input type="hidden" name="companyId" value="${depts.get(0).company.id}">--%>
                                        <input type="text" value="${depts.get(0).company.cname}" class="form-control"
                                               readonly>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <input type="hidden" value="${sessionScope.user.id}" name="userId">
                                    <label class="col-sm-2 control-label">部门名称</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="dname" class="form-control" placeholder="部门名称">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">部门描述</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="description" class="form-control"
                                               placeholder="部门描述">
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
<!-- x修改部门信息的modal -->
<div class="modal fade" id="updateDept" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">修改部门</h4>
            </div>

            <form action="/sys/dept/updateDept.do" class="form-horizontal" method="post">
                <div class="modal-body">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <input type="hidden" id="update-id" name="id">
                                    <input type="hidden" name="userId" value="${sessionScope.user.id}">
                                    <label class="col-sm-2 control-label">部门名称</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="dname" id="update-name" value="" class="form-control"
                                               placeholder="部门名称">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">部门描述</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="description" id="update-description" value=""
                                               class="form-control"
                                               placeholder="部门描述">
                                    </div>
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
</body>
</html>
<script src="/assets/js/confirm-delete.js"></script>
<script>
    //绑定按钮的click事件   当click事件发生以后  去绑定shown.bs.modal事件  当事件被触发以后  则用Ajax去加载数据  进行显示
    $(function () {
        $('[data-id]').on('click', function () {
            var id = $(this).data('id');
            var target = $(this).data('target');
            $(target).one('shown.bs.modal', function () {
                //发送ajax
                $.ajax({
                    url: 'sys/dept/queryById.do',
                    data: {'id': id},
                    success: function (data) {
                        $('#update-id').val(data['id']);
                        $('#update-name').val(data['name']);
                        $('#update-description').val(data['description']);
                    }
                })
            });
            //修改的模态框隐藏时执行的操作
            $(target).one('hidden.bs.modal', function () {

            })
        })
    })

</script>