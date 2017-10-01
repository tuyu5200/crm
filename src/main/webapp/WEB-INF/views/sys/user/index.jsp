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
    <title>用户登录</title>
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
                       aria-expanded="false">${user.username}<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">个人信息</a></li>
                        <li><a href="#">修改密码</a></li>
                        <li><a href="#">查看任务</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">安全退出</a></li>
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
                    <li class="list-group-item">公司管理</li>
                    <li class="list-group-item">部门管理</li>
                    <li class="list-group-item">菜单管理</li>
                    <li class="list-group-item">服务管理</li>
                    <li class="list-group-item active">用户管理</li>
                </ul>
                <div class="panel-body">
                </div>
            </div>
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
                        <td>邮箱</td>
                        <td>性别</td>
                        <td>员工职务</td>
                        <td>所属部门</td>
                        <td>所属公司</td>
                        <td>员工操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="u">
                        <tr>
                            <td>${u.id}</td>
                            <td>${u.username}</td>
                            <td>${u.email}</td>
                            <td>${u.sex==1?'男':'女'}</td>
                            <td>*</td>
                            <td>${u.dept.dname}</td>
                            <td>${u.company.cname}</td>
                            <td>
                                <button class="btn btn-primary btn-xs">分配角色</button>
                                <a class="btn btn-warning btn-xs"
                                   href="/sys/user/dataEcho.do?userId=${u.id}">修改
                                </a>
                                <a class="btn btn-danger btn-xs" href="/sys/user/deleteUser.do?userId=${u.id}">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<!-- 添加用户的modal -->
<div>
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
</div>

</body>
</html>