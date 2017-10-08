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
                    <li class="list-group-item"><span class="glyphicon glyphicon-user">&nbsp;</span><a
                            href="/linkman/queryAll.do">联系人</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="col-md-9">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <b>客户信息</b>
                    <div class="pull-right">
                        <button class="btn btn-xs btn-danger" onclick="deleteAll()"><span
                                class="glyphicon glyphicon-trash"></span>批量删除
                        </button>
                        <button class="btn btn-xs btn-primary" data-toggle="modal" data-target="#addClient"><span
                                class="glyphicon glyphicon-plus" id="addClientClick"></span>添加客户
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
                        <th><input type="checkbox" id="ckeckAll"></th>
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
                    <c:forEach items="${clients}" var="linkman">
                        <tr>
                            <td><input type="checkbox" name="clientIds" value="${linkman.id}"></td>
                            <td>${linkman.id}</td>
                            <td>${linkman.name}</td>
                            <td>${linkman.legal}</td>
                            <td>${linkman.city.name}</td>
                            <td>${linkman.phone}</td>
                            <td>${linkman.postcode}</td>
                            <td>${linkman.email}</td>
                            <td>${linkman.registerDate}</td>
                            <td>${linkman.nextTime}</td>
                            <td>${linkman.level}</td>
                            <td>
                                <button class="btn btn-xs btn-warning" data-toggle="modal" data-target="#updateClient"
                                        data-clientId="${linkman.id}"><span
                                        class="glyphicon glyphicon-pencil"></span>修改
                                </button>
                                <a class="btn btn-xs btn-danger" data-toggle="modal"
                                   data-href="/client/delete.do?id=${linkman.id}" data-target="#confirm-delete"
                                   href="#"><span
                                        class="glyphicon glyphicon-trash"></span>删除
                                </a>
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
                                    <input type="hidden" name="clientId" value="">
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
                                        <div class="form-inline">
                                            <label for="client_province">省</label>
                                            <select class="form-control" style="width: auto;"
                                            <%--name="cities"--%>
                                                    id="client_province"
                                                    onchange="getCityChildrenByParent(this)">
                                            </select>
                                            <label for="client_city">市</label>
                                            <select class="form-control" style="width: auto;"
                                            <%--name="cities"--%>
                                                    id="client_city"
                                                    onchange="getCityChildrenByParent(this)">
                                                <option value="0">--请选择--</option>
                                            </select>
                                            <label for="client_county">县</label>
                                            <select class="form-control" style="width: auto;"
                                            <%--name="cities"--%>
                                                    id="client_county">
                                                <option value="0">--请选择--</option>
                                            </select>
                                        </div>
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
<%--修改客户的modal--%>
<div class="modal fade" id="updateClient" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">修改客户</h4>
            </div>

            <form action="/client/updata.do" class="form-horizontal" method="post">
                <div class="modal-body">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <input type="hidden" name="clientId">
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
                                        <%--<div class="form-inline">--%>
                                        <%--<label for="client_province">省</label>--%>
                                        <%--<select class="form-control" style="width: auto;"--%>
                                        <%--&lt;%&ndash;name="cities"&ndash;%&gt;--%>
                                        <%--id="client_province"--%>
                                        <%--onchange="getCityChildrenByParent(this)">--%>
                                        <%--</select>--%>
                                        <%--<label for="client_city">市</label>--%>
                                        <%--<select class="form-control" style="width: auto;"--%>
                                        <%--&lt;%&ndash;name="cities"&ndash;%&gt;--%>
                                        <%--id="client_city"--%>
                                        <%--onchange="getCityChildrenByParent(this)">--%>
                                        <%--<option value="0">--请选择--</option>--%>
                                        <%--</select>--%>
                                        <%--<label for="client_county">县</label>--%>
                                        <%--<select class="form-control" style="width: auto;"--%>
                                        <%--&lt;%&ndash;name="cities"&ndash;%&gt;--%>
                                        <%--id="client_county">--%>
                                        <%--<option value="0">--请选择--</option>--%>
                                        <%--</select>--%>
                                        <%--</div>--%>
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
                    <button type="submit" class="btn btn-primary">修改</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
<script src="/assets/js/datatimepicker.js"></script>
<script>
    <%--修改用户的数据回显--%>
    //绑定按钮的click事件   当click事件发生以后  去绑定shown.bs.modal事件  当事件被触发以后  则用Ajax去加载数据  进行显示
    $(function () {
        $('[data-clientId]').on('click', function () {
            var id = $(this).attr('data-clientId');

            var target = $(this).data('target');
            $(target).one('shown.bs.modal', function () {
                //发送ajax
                $.ajax({
                    url: 'client/queryById.do',
                    data: {'id': id},
                    success: function (data) {
                        //数据回显
                        for (var key in data) {
                            $("input[name='" + key + "']").val(data[key]);
                        }
                    }
                })
            });
            //修改的模态框隐藏时执行的操作
            $(target).one('hidden.bs.modal', function () {

            })
        })
    })

    <%--删除之前的提示--%>
    $('#confirm-delete').on('show.bs.modal', function (e) {
        $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
    });
    //    实现全选和反选功能
    $(function () {
        $("#ckeckAll").on('click', function () {
            if (this.checked) {
                $("input[name='clientIds']").attr('checked', true);
            } else {
                $("input[name='clientIds']").attr('checked', false);
            }
        });
    });

    //批量删除
    function deleteAll() {
        if (confirm("确定要删除所有选中的客户吗?")) {
            var ids = new Array();
            $("input[name='clientIds']").each(function () {
                if (this.checked) {
                    ids.push(parseInt($(this).val()));
                }
            });
            console.log(ids);
            $.ajax({
                url: 'client/deleteByIds.do',
                data: {'ids': ids},
                success: function (data) {
                    if ($.trim(data) == "success") {
                        window.location.href = "/client/queryAll.do";
                    }
                }
            })
        }
    }

    //TODO:添加city 的js
    //    $(function () {
    //        $("#addClientClick").on('click', function () {
    //            var target = $(this).data('target');
    //            $(target).one('shown.bs.modal', function () {
    //                alert("click");
    //                //发送ajax
    //                $.ajax({
    //                    url: 'client/queryProvince.do',
    //                    data: {},
    //                    success: function (data) {
    //                        console.log(data);
    //
    //                    }
    //                })
    //            })
    //        })
    //    })
</script>