<%--
  User: Walker Tu
  Date: 2017/9/30
  Time: 0:20
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
<div class="container" style="margin-top: 20px">
    <div class="row">
        <div class="col-md-6 col-lg-offset-3">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <strong>修改用户${updatingUser.name }的资料</strong>
                    <div class="btn-toolbar pull-right" style="margin-top: -5px;">
                        <div class="btn-group">
                            <button class="btn btn-warning btn-sm">
                                <span class="glyphicon glyphicon-arrow-left"></span>
                                <span id="goback">返回</span>
                                <script>
                                    $('#goback').one('click', function () {
                                        history.back();
                                    })
                                </script>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <form action="/sys/user/updateUser.do" class="form-horizontal" method="post">
                        <div class="modal-body">
                            <div class="container">
                                <div class="row">
                                    <div class="col-md-10">
                                        <div class="form-group">
                                            <input type="hidden" name="id" value="${updatingUser.id}">
                                            <label class="col-sm-2 control-label">员工姓名</label>
                                            <div class="col-sm-4">
                                                <input type="text" name="name" value="${updatingUser.name}"
                                                       class="form-control"
                                                       placeholder="员工姓名">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">所属公司</label>
                                            <div class="col-sm-4">
                                                <select class="form-control" name="companyId">
                                                    <option value="-1">-请选择-</option>
                                                    <c:forEach items="${companies}" var="company">
                                                        <option value="${company.id}" ${updatingUser.company.id==company.id?'selected':''}>${company.cname}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">所属部门</label>
                                            <div class="col-sm-4">
                                                <select class="form-control" name="deptId">
                                                    <option value="-1">-请选择-</option>
                                                    <c:forEach items="${depts}" var="dept">
                                                        <option value="${dept.id}" ${updatingUser.dept.id==dept.id?'selected':''}>${dept.dname}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">性别</label>
                                            <div class="col-sm-4">
                                                <label class="radio-inline">
                                                    <input type="radio" name="sex"
                                                           value="1" ${updatingUser.sex==1?'checked':''}> 男
                                                </label>
                                                <label class="radio-inline">
                                                    <input type="radio" name="sex"
                                                           value="0" ${updatingUser.sex==0?'checked':''}> 女
                                                </label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">用户名</label>
                                            <div class="col-sm-4">
                                                <input type="text" name="username" value="${updatingUser.username}"
                                                       class="form-control" placeholder="用户名">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">密码</label>
                                            <div class="col-sm-4">
                                                <input type="text" name="password" value="${updatingUser.password}"
                                                       class="form-control" placeholder="密码">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">邮箱</label>
                                            <div class="col-sm-4">
                                                <input type="text" name="email" value="${updatingUser.email}"
                                                       class="form-control"
                                                       placeholder="邮箱">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">状态</label>
                                            <div class="col-sm-4">
                                                <div>
                                                    <label class="radio-inline">
                                                        <input type="radio" name="locked"
                                                               value="1" ${updatingUser.locked==1?'checked':''}> 已锁定
                                                    </label>
                                                    <label class="radio-inline">
                                                        <input type="radio" name="locked"
                                                               value="0" ${updatingUser.locked==0?'checked':''}> 未锁定
                                                    </label>
                                                </div>
                                                <div>
                                                    <label class="radio-inline">
                                                        <input type="radio" name="enabled"
                                                               value="1" ${updatingUser.enabled==1?'checked':''}> 启用
                                                    </label>
                                                    <label class="radio-inline">
                                                        <input type="radio" name="enabled"
                                                               value="0" ${updatingUser.enabled==0?'checked':''}> 禁用
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">备注</label>
                                            <div class="col-sm-4">
                                    <textarea name="description" rows="4"
                                              class="form-control">${updatingUser.description}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="reset" class="btn btn-warning">重置</button>
                            <button type="submit" class="btn btn-primary">修改</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>