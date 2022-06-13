<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2022/6/13
  Time: 下午9:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>500</title>
</head>
<body>
    <% out.println(request.getAttribute("exceptionStack")); %>
</body>
</html>
