<%@ page import="lv.javaguru.java2.domain.UserAccount" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href= <c:url value="/resources/css/styles.css" /> rel="stylesheet" type="text/css">
    <title>All Accounts</title>
</head>
<body>
<div align="center">
<%List<UserAccount> accountsList = (ArrayList<UserAccount>)request.getAttribute("model");%>
    <h2><b><U>User Accounts overview list:</U></b></h2>
    <table align="center" border="1" width="80%">
    <tr>
        <td width="200"><b>Account ID</b></td>
        <td width="200"><b>First Name</b></td>
        <td width="200"><b>Last Name</b></td>
        <td width="200"><b>Account Status</b></td>
    </tr>
    </table>
    <HR>
    <BR>
    <%
      for(UserAccount account:accountsList){
         Long accountId = account.getUserAccountId();
      %>

      <table align="center" border="1" width="80%">
      <tr>
              <form method="post">
              <td width="200"><input type="submit" name="userAccountId" value="<%=account.getUserAccountId()%>">
              <input type="hidden" name="userAccountId" value="<%=account.getUserAccountId()%>">
              </td>
              </form>
              <td width="200"><%=account.getFirstName()%></td>
              <td width="200"><%=account.getLastName()%></td>
              <td width="200"><%=account.getState()%></td>


      </tr>
      </table>

      <%}%>

    <h1>${requestScope.data}</h1>
    <a href="/">Return to back</a>
</body>
</html>