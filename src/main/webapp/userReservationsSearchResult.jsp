<%@ page import="lv.javaguru.java2.domain.UserAccount" %>
<%@ page import="lv.javaguru.java2.domain.Reservation" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href= <c:url value="/resources/css/styles.css" /> rel="stylesheet" type="text/css">
    <title>Reservations Page for account</title>
</head>
<body>
<div align="center">
<%List<Reservation> reservations = (ArrayList<Reservation>)request.getAttribute("model");%>

<h2>View Bookings</h2>
      <table align="center" border="1" width="80%">
          <tr>
              <td width="200"><b>Reservation ID</b></td>
              <td width="200"><b>Account ID</b></td>
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
                        <td width="200"><%=reservation.getAccountID()%></td>
                        <td width="200"><%=reservation.getDateFrom()%></td>
                        <td width="200"><%=reservation.getDateTo()%></td>
                        <td width="200"><%=reservation.getResourceID()%></td>
                        <td width="200"><%=reservation.getStatus()%></td>

                </tr>
                </table>
<%}%>

<h1>${requestScope.data}</h1>
    <a href="/java2/adminPage">Return to administrator menu</a>
</body>
</html>