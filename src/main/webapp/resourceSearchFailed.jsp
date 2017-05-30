<%@ page import="java.lang.String" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Resource Search</title>
</head>
<body>
<%String errors = (String)request.getAttribute("model");%>
<h3>
    <%=errors %>
</h3>
<div align="center">
    <form method="post" action="/java2/resourceSearchResult">
    Enter resource ID: <input type="text" name="resourceId" /><br/>
    <br/>
    <input type="submit" value="Search" />
    </form>
</div>
<h1>${requestScope.data}</h1>
    <a href="/java2/adminPage">Return to administrator menu</a>
</body>
<html>