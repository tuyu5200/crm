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
    <title>联系人管理</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/bootstrap-datetimepicker.min.css">
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
            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading"><b>客户管理</b>
                </div>
                <!-- List group -->
                <ul class="list-group">
                    <li class="list-group-item"><span class="glyphicon glyphicon-user">&nbsp;</span><a
                            href="/client/queryAll.do">客户</a></li>
                    <li class="list-group-item active"><span class="glyphicon glyphicon-user">&nbsp;</span>联系人</li>
                </ul>
            </div>
        </div>
        <div class="col-md-9">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <b>联系人信息</b>
                    <div class="pull-right">
                        <button class="btn btn-xs btn-danger" onclick="deleteAll()"><span
                                class="glyphicon glyphicon-trash"></span>批量删除
                        </button>
                        <button class="btn btn-xs btn-primary" data-toggle="modal" data-target="#addLinkman"
                                id="addLinkmanEcho"><span
                                class="glyphicon glyphicon-plus"></span>添加联系人
                        </button>
                        <button class="btn btn-xs btn-primary"><span class="glyphicon glyphicon-export"></span>导出Excel
                        </button>
                        <button class="btn btn-primary btn-sm pull-right" style="margin-left: 5px;margin-top: -5px">搜索
                        </button>
                        <input type="text" class="form-control pull-right"
                               style="width: auto;margin-top: -5px;margin-left: 5px"
                               placeholder="搜索">
                    </div>
                </div>
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th><input type="checkbox" id="ckeckAllLinkMan"></th>
                        <th>联系人编号</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>出生日期</th>
                        <th>工作</th>
                        <th>主要联系人</th>
                        <th>电话</th>
                        <th>邮箱</th>
                        <th>备注</th>
                        <th>对应客户</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${linkmen}" var="linkman">
                        <tr>
                            <td><input type="checkbox" name="linkmanIds" value="${linkman.id}"></td>
                            <td>${linkman.id}</td>
                            <td>${linkman.name}</td>
                            <td>${linkman.gender==1?'男':'女'}</td>
                            <td>${linkman.birthday}</td>
                            <td>${linkman.job}</td>
                            <td>${linkman.active==1?'是':'否'}</td>
                            <td>${linkman.phone}</td>
                            <td>${linkman.email}</td>
                            <td>${linkman.content}</td>
                            <td>${linkman.client.name}
                                <c:if test="${linkman.client!=null}">-></c:if>
                                    ${linkman.client.city.name}</td>
                            <td>
                                <button class="btn btn-xs btn-warning" data-toggle="modal" data-target="#updateLinkman"
                                        data-linkmanId="${linkman.id}"><span
                                        class="glyphicon glyphicon-pencil"></span>修改
                                </button>
                                <a class="btn btn-xs btn-danger" data-toggle="modal"
                                   data-href="/linkman/delete.do?id=${linkman.id}" data-target="#confirm-delete"
                                   href="#"><span
                                        class="glyphicon glyphicon-trash"></span>删除
                                </a>
                                <button class="btn btn-xs btn-primary"><span class="glyphicon glyphicon-export"></span>联系记录
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
<%--添加联系人的modal--%>
<div class="modal fade" id="addLinkman" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">新增联系人</h4>
            </div>

            <form action="/linkman/add.do" class="form-horizontal" method="post">
                <div class="modal-body">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <input type="hidden" name="clientId" value="">
                                    <label class="col-sm-2 control-label">姓名</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="name" class="form-control" placeholder="姓名">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">性别</label>
                                    <div class="col-sm-4">
                                        <label class="radio-inline">
                                            <input type="radio" name="gender" value="1"> 男
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="gender" value="0"> 女
                                        </label>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">出生日期</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="birthday" readonly size="16"
                                               class="form-control form_datetime"
                                               placeholder="请选择出生日期">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">职业</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="job" class="form-control" placeholder="职业">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">是否为主要联系人</label>
                                    <div class="col-sm-4">
                                        <label class="radio-inline">
                                            <input type="radio" name="active" value="1"> 是
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="active" value="0"> 否
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">手机号</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="phone" class="form-control" placeholder="邮编">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">邮箱</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="email" class="form-control" placeholder="邮箱">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">对应客户</label>
                                    <div class="col-sm-4">
                                        <select class="form-control" style="width: auto" id="addselect"
                                                name="selClientId">
                                            <option>-请选择-</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">备注</label>
                                    <div class="col-sm-4">
                                        <textarea class="form-control" rows="3" name="content"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class=" modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="reset" class="btn btn-warning">重置</button>
                    <button type="submit" class="btn btn-primary">添加</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%--修改联系人的modal--%>
<div class="modal fade" id="updateLinkman" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">修改联系人</h4>
            </div>

            <form action="/linkman/update.do" class="form-horizontal" method="post">
                <div class="modal-body">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <input type="hidden" name="clientId" value="">
                                    <label class="col-sm-2 control-label">姓名</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="name" class="form-control" placeholder="姓名">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">性别</label>
                                    <div class="col-sm-4">
                                        <label class="radio-inline">
                                            <input type="radio" name="gender" value="1"> 男
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="gender" value="0"> 女
                                        </label>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">出生日期</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="birthday" readonly size="16"
                                               class="form-control form_datetime"
                                               placeholder="请选择出生日期">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">职业</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="job" class="form-control" placeholder="职业">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">是否为主要联系人</label>
                                    <div class="col-sm-4">
                                        <label class="radio-inline">
                                            <input type="radio" name="active" value="1"> 是
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="active" value="0"> 否
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">手机号</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="phone" class="form-control" placeholder="邮编">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">邮箱</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="email" class="form-control" placeholder="邮箱">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">对应客户</label>
                                    <div class="col-sm-4">
                                        <select class="form-control" style="width: auto" id="updateSelect"
                                                name="selClientId">
                                            <option>-请选择-</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">备注</label>
                                    <div class="col-sm-4">
                                        <textarea class="form-control" rows="3" name="content"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class=" modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="reset" class="btn btn-warning">重置</button>
                    <button type="submit" class="btn btn-primary">修改</button>
                </div>
            </form>
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
</body>
</html>
<script src="/assets/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="/assets/js/bootstrap-datetimepicker.js"></script>
<script src="/assets/js/datatimepicker.js"></script>
<script>
    <%--删除之前的提示--%>
    $('#confirm-delete').on('show.bs.modal', function (e) {
        $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
    });
    //    实现全选和反选功能
    $(function () {
        $("#ckeckAllLinkMan").on('click', function () {
            if (this.checked) {
                $("input[name='linkmanIds']").attr('checked', true);
            } else {
                $("input[name='linkmanIds']").attr('checked', false);
            }
        });
    });

    //    添加联系人前获取所有的客户填充到select中
    $(function () {
        $('#addLinkmanEcho').on('click', function () {
            var target = $(this).data('target');
            //清空select
            $("#addselect").html("<option value='-1'>-请选择-</option>")
            $(target).one('shown.bs.modal', function () {
                //发送ajax
                $.ajax({
                    url: 'linkman/queryAllClients.do',
                    data: {},
                    success: function (data) {
                        //填充到添加联系人的modal的select中
                        $.each(data, function (index, item) {
                            $("#addselect").append("<option value='" + data[index]['clientId'] + "'>" + data[index]['name'] + " >> " + data[index]['city'] + "</option>");
                        })
                    }
                })
            });
        })
    });

    //批量删除
    function deleteAll() {
        if (confirm("确定要删除所有选中的客户吗?")) {
            var ids = new Array();
            $("input[name='linkmanIds']").each(function () {
                if (this.checked) {
                    ids.push(parseInt($(this).val()));
                }
            });
            console.log(ids);
            $.ajax({
                url: 'linkman/deleteByIds.do',
                data: {'ids': ids},
                success: function (data) {
                    if ($.trim(data) == "success") {
                        window.location.href = "/linkman/queryAll.do";
                    }
                }
            })
        }
    }

    <%--修改联系人时的数据回显--%>
    //绑定按钮的click事件   当click事件发生以后  去绑定shown.bs.modal事件  当事件被触发以后  则用Ajax去加载数据  进行显示
    $(function () {
        $('[data-linkmanId]').on('click', function () {
            var id = $(this).attr('data-linkmanId');
            var target = $(this).data('target');
            $("#updateSelect").html("");
            $(target).one('shown.bs.modal', function () {
                //发送ajax查询联系人的信息
                $.ajax({
                    url: 'linkman/queryById.do',
                    data: {'id': id},
                    success: function (data) {
                        console.log(data);
                        //选中该联系人对应的client
                        $("#updateSelect").val(data['client']['clientId']);
                        //数据回显
                        for (var key in data) {
                            $("input[name='" + key + "']").val(data[key]);
                        }
                    }
                });
                //发送ajax查询所有客户的信息
                $.ajax({
                    url: 'linkman/queryAllClients.do',
                    data: {},
                    success: function (data) {
                        //填充到修改联系人的modal的select中
                        $.each(data, function (index, item) {
                            $("#updateSelect").append("<option value='" + data[index]['clientId'] + "'>" + data[index]['name'] + " >> " + data[index]['city'] + "</option>");
                        })
                    }
                })
            });
        })
    })
</script>