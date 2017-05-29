<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href= <c:url value="/resources/css/styles.css" /> rel="stylesheet" type="text/css">
    <title>All Accounts</title>
</head>
<body>
<div align="center">
    <form method="post" action="/java2/userAccountSearchResult">
        User Account:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="accountId" /><br/>
        <br/>
        <input type="submit" value="Search" />
    </form>
</div>
<h1>${requestScope.data}</h1>
    <a href="/java2/adminPage">Return to back</a>
</body>
</html>