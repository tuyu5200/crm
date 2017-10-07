<%--
  User: Walker Tu
  Date: 2017/9/29
  Time: 10:11
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
    <title>没权限</title>
    <base href="<%=basePath%>">
    <style>
        body {
            margin: 0;
            padding: 0;
            background: #fff;
        }

        body {
            font-family: "Microsoft YaHei", "Segoe UI", "Lucida Grande", Helvetica, Arial, sans-serif;
        }

        a:link, a:visited {
            color: #3986c8;
            text-decoration: none;
        }

        .topimg {
            position: relative;
            z-index: 2;
            width: 750px;
            height: 0;
            margin: 110px auto 15px;
            padding: 230px 0 0;
            overflow: hidden;
            background-image: url(/assets/images/power.jpg);
            background-repeat: no-repeat;
        }

        .words {
            position: absolute;
            top: 55px;
            left: 233px;
            margin: 0;
            font-size: 0;
            text-indent: -999px;
            -moz-user-select: none;
            -webkit-user-select: none;
            user-select: none;
            cursor: default;
            width: 404px;
            height: 90px;
        }

        .words em {
            display: block;
            text-indent: 0;
            letter-spacing: -5px;
            color: rgba(216, 226, 244, 0.3);
        }

        .words em span {
            font-family: Arial, Helvetica, sans-serif;
            font-size: 65px;
        }

        .link a {
            margin-right: 1em;
        }

        .link, .texts {
            width: 540px;
            margin: 0 auto 15px;
            color: #505050;
        }

        .texts {
            line-height: 2;
        }

        .texts dd {
            margin: 0;
            padding: 0 0 0 15px;
        }

        .texts ul {
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<body>
<p class="topimg"></p>
<p class="link">
    <a href="login.jsp" target="_self">&#9666;重新登陆</a>
    <a target="_self" href="javascript:void (0);" onClick="javascript:history.back(-1);">&#9666;返回上一页</a>
</p>
<dl class="texts">

    <dd>
        <ul>
            <li>不要返回吗 ?</li>
            <li>确定不要返回吗 ?</li>
            <li>真的真的确定不要返回吗 ?</li>
            <li>好吧.还是随便你要不要真的确定返回吧 ! ~</li>
        </ul>
    </dd>
</dl>
</body>
</html>