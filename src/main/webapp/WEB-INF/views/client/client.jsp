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
    <link rel="stylesheet" href="/assets/css/bootstrap-datetimepicker.min.css">
    <script src="/assets/js/jquery.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>
    <script src="/assets/js/confirm-delete.js"></script>
    <script src="/assets/js/bootstrap-datetimepicker.js"></script>
    <script src="/assets/js/bootstrap-datetimepicker.zh-CN.js"></script>

</head>
<body>
<jsp:include page="/WEB-INF/views/commons/navbar.jsp">
    <jsp:param name="activeName" value="CLIENT"/>
</jsp:include>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-3">
            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading"><b>客户管理</b>
                </div>
                <!-- List group -->
                <ul class="list-group">
                    <li class="list-group-item active"><span class="glyphicon glyphicon-user">&nbsp;</span> 客户</li>
                    <li class="list-group-item"><span class="glyphicon glyphicon-user">&nbsp;</span>联系人</li>
                </ul>
            </div>
        </div>
        <div class="col-md-9">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <b>客户信息</b>
                    <div class="pull-right">
                        <button class="btn btn-xs btn-danger"><span class="glyphicon glyphicon-trash"></span>批量删除
                        </button>
                        <button class="btn btn-xs btn-primary" data-toggle="modal" data-target="#addClient"><span
                                class="glyphicon glyphicon-plus"></span>添加客户
                        </button>
                        <button class="btn btn-xs btn-primary"><span class="glyphicon glyphicon-export"></span>导出Excel
                        </button>
                        <button class="btn btn-primary btn-sm pull-right" style="margin-left: 5px;margin-top: -5px">搜索
                        </button>
                        <input type="text" class="form-control pull-right"
                               style="width: auto;margin-top: -5px;margin-left: 5px"
                               placeholder="搜索">
                        <select class="form-control pull-right" style="width: auto;margin-top: -5px;margin-left: 5px">
                            <option>城市</option>
                        </select>
                    </div>
                </div>
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>客户编号</th>
                        <th>客户名称</th>
                        <th>法人代表</th>
                        <th>城市</th>
                        <th>电话</th>
                        <th>邮编</th>
                        <th>邮箱</th>
                        <th>登记时间</th>
                        <th>下次联系时间</th>
                        <th>等级</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${clients}" var="client">
                        <tr>
                            <td>${client.id}</td>
                            <td>${client.name}</td>
                            <td>${client.legal}</td>
                            <td>${client.city.name}</td>
                            <td>${client.phone}</td>
                            <td>${client.postcode}</td>
                            <td>${client.email}</td>
                            <td>${client.registerDate}</td>
                            <td>${client.nextTime}</td>
                            <td>${client.level}</td>
                            <td>
                                <button class="btn btn-xs btn-warning"><span
                                        class="glyphicon glyphicon-pencil"></span>修改
                                </button>
                                <button class="btn btn-xs btn-danger"><span
                                        class="glyphicon glyphicon-trash"></span>删除
                                </button>
                                <button class="btn btn-xs btn-primary"><span
                                        class="glyphicon glyphicon-export"></span>联系人
                                </button>
                                <button class="btn btn-xs btn-primary"><span
                                        class="glyphicon glyphicon-tag"></span>资料
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tfoot>
                    </tfoot>
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
<%--添加客户的modal--%>
<div class="modal fade" id="addClient" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">新增客户</h4>
            </div>

            <form action="/client/addClient.do" class="form-horizontal" method="post">
                <div class="modal-body">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">客户名称</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="name" class="form-control" placeholder="客户名称">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">客户等级</label>
                                    <div class="col-sm-4">
                                        <select class="form-control" style="width: auto" name="level">
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                            <option value="5">5</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">接待人员</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="userId" class="form-control" placeholder="接待人员">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">法人代表</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="legal" class="form-control" placeholder="法人代表">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">所在城市</label>
                                    <div class="col-sm-4">
                                        <select class="form-control pull-left" style="width: auto;margin-left: 5px;">
                                            <option>省</option>
                                        </select>
                                        <select class="form-control pull-left" style="width: auto;margin-left: 5px;">
                                            <option>市</option>
                                        </select>
                                        <select class="form-control pull-left" style="width: auto;margin-left: 5px;">
                                            <option>县</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">邮编</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="postcode" class="form-control" placeholder="邮编">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">手机号</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="phone" class="form-control" placeholder="手机号">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">邮箱</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="email" class="form-control" placeholder="邮箱">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">登记时间</label>
                                    <div class="col-sm-4">
                                        <input class="form-control form_datetime" name="registerDate" type="text"
                                               readonly
                                               value="选择登记时间"
                                               size="16">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">下次联系时间</label>
                                    <div class="col-sm-4">
                                        <input class="form-control form_datetime" name="nextTime" type="text" readonly
                                               value="选择下次联系时间"
                                               size="16">
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
<script src="/assets/js/datatimepicker.js"></script>
</body>
</html>