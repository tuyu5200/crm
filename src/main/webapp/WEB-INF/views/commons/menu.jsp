<%--
  User: Walker
  Date: 2017/10/7
  Time: 13:57
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<li role="presentation">
    <a href="${menu.href eq '' || menu.href eq null?'javascript:void(0);':menu.href}"
       title="${menu.title}"
       class="<c:if test="${param.activeName == menu.constant}">active</c:if> "
       onclick="collapseChild(this)">
        <span style="margin-left: ${param.offset}px;"></span>
        <span class="glyphicon glyphicon-${menu.target}"></span>
        &nbsp;&nbsp;${menu.name}
        <c:if test="${not empty menu.resources}">
            <span class="glyphicon glyphicon-chevron-down pull-right"></span>
        </c:if>
    </a>
    <c:if test="${not empty menu.resources}">
        <ul class="nav collapse">
            <c:forEach var="menu" items="${menu.resources}">
                <c:set var="menu" value="${menu}" scope="request"/>
                <jsp:include page="menu.jsp">
                    <jsp:param name="offset" value="${param.offset+15}"/>
                </jsp:include>
            </c:forEach>
        </ul>
    </c:if>
</li>