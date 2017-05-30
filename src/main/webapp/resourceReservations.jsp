<%@ page import="lv.javaguru.java2.domain.Reservation" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Resource reservations</title>
</head>
<body>
<%List<Reservation> reservations = (ArrayList<Reservation>)request.getAttribute("model");%>

<h2>Resource reservations</h2>
      <table align="center" border="1" width="80%">
          <tr>
              <td width="200"><b>Reservation ID</b></td>
              <td width="200"><b>Account ID</b></td>
              <td width="200"><b>Date From</b></td>
              <td width="200"><b>Date To</b></td>
              <td width="200"><b>Resource ID</b></td>
              <td width="200"><b>Status</b></td>
          </tr>
          <%for(Reservation reservation:reservations){ %>
                <tr>
                    <td width="200"><%=reservation.getReservationID()%></td>
                    <td width="200"><%=reservation.getAccountID()%></td>
                    <td width="200"><%=reservation.getDateFrom()%></td>
                    <td width="200"><%=reservation.getDateTo()%></td>
                    <td width="200"><%=reservation.getResourceID()%></td>
                    <td width="200"><%=reservation.getStatus()%></td>
                </tr>
          <%}%>
      </table>
<h1>${requestScope.data}</h1>
    <a href="/java2/resourceSearch">Back to  resource search</a>
    <a href="/java2/adminPage">Return to administrator menu</a>
</body>
</html>