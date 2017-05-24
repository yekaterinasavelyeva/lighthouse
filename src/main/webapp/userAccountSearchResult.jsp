<%@ page import="lv.javaguru.java2.domain.UserAccount" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href= <c:url value="/resources/css/styles.css" /> rel="stylesheet" type="text/css">
    <title>Account Page</title>
</head>
<body>
<div align="center">
<h2><b><U>User Accounts Id: <%=(Long)request.getAttribute("accountId")%></U></b></h2>

<h1>${requestScope.data}</h1>
    <a href="/">Return to back</a>
</body>
</html>