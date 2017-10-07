<%--
  User: Walker
  Date: 2017/10/7
  Time: 13:33
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .nav li a.active {
        background-color: #337ab7;
        color: #fff;
    }

    .nav li a.active:hover {
        color: #fff;
    }
</style>

<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading">系统管理</div>
    <c:set var="menu" value="${sysmenu.get(0)}" scope="request"/>
    <!-- List group -->
    <ul class="nav" id="left_panel_ul">
        <jsp:include page="menu.jsp"/>
    </ul>
</div>

<script>
    function collapseChild(obj) {
        var $this = $(obj);
        if ($this.attr('href') === 'javascript:void(0);' && $this.next('ul').length > 0) {
            $this.next('ul').collapse('toggle');
            changeIco($this);
        }
    }

    function changeIco($a) {
        $icon = $a.children('span').last();
        if ($icon.hasClass('glyphicon-chevron-up'))
            $icon.removeClass('glyphicon-chevron-up').addClass('glyphicon-chevron-down');
        else
            $icon.removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-up');
    }

    $(document).ready(function () {
        var $active = $('#left_panel_ul').find('a.active');
        if ($active.next('ul').length > 0) {
            $active.next('ul').collapse('toggle');
            changeIco($active);
        }
        if ($active.length > 0)
            expand($active);
    });

    function expand($obj) {
        var $ul = $obj.parent().parent();
        if ($ul.attr('id') !== "left_panel_ul") {
            $ul.collapse('toggle');
            changeIco($ul.prev('a'));
            expand($ul);
        }
    }
</script>