<%@ page import="com.itacademy.model.Hotel" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Василь
  Date: 02.03.2021
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hotels-list</title>
</head>
<body>
    <div>
        <h2>All hotels</h2>
    </div>
    <div>
        <table border="2">
            <tr>
                <th>No.</th>
                <th>Name</th>
                <th>Country</th>
                <th>Stars</th>
            </tr>
            <%
                for (Hotel hotel : (List<Hotel>)request.getAttribute("hotels")) {

            %>
            <tr>
                <td><%=hotel.getId()%></td>
                <td><%=hotel.getName()%></td>
                <td><%=hotel.getCountry()%><td>

            </tr>
            <%
                }
            %>
        </table>
    </div>
</body>
</html>
