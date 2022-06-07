<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${users}" var="user">
    <c:out value="${user.username}" /><br />
    <c:out value="${user.age}" /><br />
</c:forEach>