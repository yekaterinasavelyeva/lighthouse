<%@ page import="lv.javaguru.java2.domain.UserAccount" %>
<%@ page import="lv.javaguru.java2.domain.Reservation" %>
<%@ page import="lv.javaguru.java2.services.reservation.FindReservationByUserAccountIdServiceImpl" %>
lv.javaguru.java2.services.reservation
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href= <c:url value="/resources/css/styles.css" /> rel="stylesheet" type="text/css">
    <title>Account Page</title>
</head>
<body>
<div align="center">
<%UserAccount account = (UserAccount)request.getAttribute("model");%>
<%FindReservationByUserAccountIdServiceImpl service = new FindReservationByUserAccountIdServiceImpl();%>
<%List<Reservation> reservations = service.find((Long)account.getAccountId());%>
<h2><b><U>User Account Id: <%=(Long)account.getAccountId()%></U></b></h2>

      <table align="center" border="1" width="80%">
        <tr>
              <td width="200"><%=account.getAccountId()%></td>
              <td width="200"><%=account.getFirstName()%></td>
              <td width="200"><%=account.getLastName()%></td>
              <td width="200"><%=account.getState()%></td>
        </tr>
      </table>

      <h2>View Bookings</h2>
      <table align="center" border="1" width="80%">
          <tr>
              <td width="200"><b>Reservation ID</b></td>
              <td width="200"><b>Date From</b></td>
              <td width="200"><b>Date To</b></td>
              <td width="200"><b>Resource ID</b>
              <td width="200"><b>Status</b>
          </td>
          </tr>
          </table>

      <HR>
      <BR>
       <%
         for(Reservation reservation:reservations){
            Long reservationId = reservation.getReservationID();
         %>
           <table align="center" border="1" width="80%">
                <tr>
                        <td width="200"><%=reservation.getReservationID()%></td>
                        <td width="200"><%=reservation.getDateFrom()%></td>
                        <td width="200"><%=reservation.getDateTo()%></td>
                        <td width="200"><%=reservation.getResourceID()%></td>
                        <td width="200"><%=reservation.getStatus()%></td>

                </tr>
                </table>


<h1>${requestScope.data}</h1>
    <a href="/java2/adminPage">Return to back</a>
</body>
</html>