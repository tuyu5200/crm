<%--
  User: Walker
  Date: 2017/10/5
  Time: 19:58
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <li class="<c:if test="${param.activeName == 'SYSTEM'}">active</c:if>"><a href="/sysIndex.do">系统管理 <span
                        class="sr-only">(current)</span></a></li>
                <li class="<c:if test="${param.activeName == 'CLIENT'}">active</c:if>"><a href="/client/queryAll.do">客户管理</a>
                </li>
                <li class="<c:if test="${param.activeName == 'SERVICE'}">active</c:if>"><a href="#">服务管理</a></li>

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