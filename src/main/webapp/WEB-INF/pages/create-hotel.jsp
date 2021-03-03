<%--
  Created by IntelliJ IDEA.
  User: Василь
  Date: 02.03.2021
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddHotel</title>
</head>
<body>

<h2>New Hotel registration</h2>
<form action="/hotels/add" method="post">
    <table>
        <tr>
            <td>
                <label for="name">Hotel title:</label>
            </td>
            <td>
                <input type="text"  id="name" name="name"/>
            </td>

        </tr>
        <tr>
            <td>
                <label for="country">Country:</label>
            </td>
            <td>
                <input type="text" id="country" name="country" />
            </td>
        </tr>
        <tr>
            <td>
                <label for="stars">Stars:</label>
            </td>
            <td>
                <input type="text" id="stars" name="stars"/>
            </td>
        </tr>
        <tr>
            <td>
                <label for="rooms">Number of rooms:</label>
            </td>
            <td>
                <input type="text" id="rooms" name="rooms"/>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" value="Register" />
                <input type="reset" value="Clear" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>
