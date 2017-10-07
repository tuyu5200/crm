<%--
  User: Walker
  Date: 2017/10/7
  Time: 20:08
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
    <title>客户管理</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <script src="/assets/js/jquery.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/commons/navbar.jsp">
    <jsp:param name="activeName" value="CLIENT"/>
</jsp:include>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-3">
            <div class="panel panel-success">
                <!-- Default panel contents -->
                <div class="panel-heading"><h3>客户管理</h3></div>
                <div class="panel-body">
                    <p>...</p>
                </div>
                <!-- List group -->
                <ul class="list-group">
                    <li class="list-group-item">客户</li>
                    <li class="list-group-item">联系人</li>
                </ul>
            </div>
        </div>
        <div class="col-md-9">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <b>相关信息</b>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>