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
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        .alert-sm {
            padding: 10px 35px 10px 15px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default" style="margin-top: 150px;">
                <div class="panel-heading">用户登录</div>
                <div class="panel-body">
                    <c:if test="${!((message eq null) or (message eq ''))}">
                        <div class="alert alert-danger alert-sm alert-dismissible" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                                ${message }
                        </div>
                    </c:if>
                    <form class="form-horizontal" action="/login.do" method="post">
                        <div class="form-group">
                            <label class="control-label sr-only">Username</label>
                            <div class="col-sm-12">
                                <div class="input-group">
							        		<span class="input-group-addon">
							        			<span class="glyphicon glyphicon-user"></span>
							        		</span>
                                    <input type="text" name="username" value="${username }" class="form-control"
                                           placeholder="用户名">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label sr-only">Password</label>
                            <div class="col-sm-12">
                                <div class="input-group">
							        		<span class="input-group-addon">
							        			<span class="glyphicon glyphicon-lock"></span>
							        		</span>
                                    <input type="password" name="password" value="${password }" class="form-control"
                                           placeholder="密码">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <button type="submit" class="btn btn-primary btn-block">登录</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>
<script type="text/javascript" src="assets/js/jquery.min.js"></script>
<script type="text/javascript" src="assets/js/bootstrap.min.js"></script>
</body>
</html>