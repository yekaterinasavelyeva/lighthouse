<%@ page import="lv.javaguru.java2.domain.Resource" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Resource Page</title>
</head>
<body>
<%Resource resource = (Resource)request.getAttribute("model");%>
<h2><b><U>Resource Id: <%=(Long)resource.getResourceId()%></U></b></h2>

      <table align="center" border="1" width="80%">
        <tr>
              <td width="200"><%=resource.getTitle()%></td>
              <td width="200"><%=resource.getAuthor()%></td>
              <td width="200"><%=resource.getReleaseYear()%></td>
              <td width="200"><%=resource.getResourceType()%></td>
        </tr>
      </table>

<nav>
    <ul class="nav nav-justified">
        <li class="item1">
            <form action="/java2/resourceReservations" method="post">
                <a href="javascript:;" onclick="parentNode.submit();">Show reservations for resource</a>
                    <input type="hidden" name="resourceId" value=<%=(Long)resource.getResourceId()%>></form>
        <li class="item2"><a href="/java2/hello">Edit resource</a></li>
        <li class="item3"><a href="/java2/hello">Delete resource</a></li>
    </ul>
</nav>
<h1>${requestScope.data}</h1>
    <a href="/java2/resourceSearch">Back to search</a>
    <a href="/java2/adminPage">Return to administrator menu</a>
</body>
</html>