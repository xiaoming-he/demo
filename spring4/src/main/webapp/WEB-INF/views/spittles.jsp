<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ming
  Date: 18-6-2
  Time: 上午10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>spittles</title>
</head>
<body>
<c:forEach items="${spittleList}" var="spittle">
    <c:out value="${spittle.message}"/>
    <br>
</c:forEach>
</body>
</html>
