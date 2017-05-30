<%@ page import="lv.javaguru.java2.domain.UserAccount" %>
<%@ page import="lv.javaguru.java2.domain.Reservation" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href= <c:url value="/resources/css/styles.css" /> rel="stylesheet" type="text/css">
    <title>Account Page</title>
</head>
<body>
<div align="center">
<%UserAccount account = (UserAccount)request.getAttribute("model");%>
<h2><b><U>User Account Id: <%=(Long)account.getAccountId()%></U></b></h2>

      <table align="center" border="1" width="80%">
        <tr>
              <td width="200"><%=account.getAccountId()%></td>
              <td width="200"><%=account.getFirstName()%></td>
              <td width="200"><%=account.getLastName()%></td>
              <td width="200"><%=account.getState()%></td>
        </tr>
      </table>

<div align="left">
       <nav>
           <ul class="nav nav-justified">
               <li class="item1">
                   <form action="/java2/userReservationsSearchResult" method="post">
                       <a href="javascript:;" onclick="parentNode.submit();">View Bookings</a>
                           <input type="hidden" name="accountId" value=<%=(Long)account.getAccountId()%>></form>
           </ul>
       </nav>


<h1>${requestScope.data}</h1>
    <a href="/java2/adminPage">Return to administrator menu</a>
</body>
</html>