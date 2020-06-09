<%@ page import="java.util.ArrayList" %>
<%@ page import="University.Student" %>
<%@ page import="University.Group" %><%--
  Created by IntelliJ IDEA.
  User: ahev
  Date: 4/14/2020
  Time: 7:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%ArrayList students = (ArrayList)request.getAttribute("students");%>
<%ArrayList groups = (ArrayList)request.getAttribute("groups");%>
<html>
<head>
    <title>University info</title>
</head>
<body>
<hr>
<h2>Students : </h2>
<table>
    <tr>
        <td>ID</td>
        <td>FirstName</td>
        <td>LastName</td>
        <td>Age</td>
        <td>GroupID</td>
    </tr>
    <hr>
    <% for(int i = 0; i < students.size(); i++){%>
    <tr>
        <% Student w = (Student)students.get(i);%>
        <td><%= w.getStudentID()%></td>
        <td><%= w.getFirstName()%></td>
        <td><%= w.getLastName()%></td>
        <td><%= w.getAge()%></td>
        <td><%= w.getGroupID()%></td>
    </tr>
    <%}%>
</table>

<hr>

<h2>Groups : </h2>
<table>
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Course</td>
    </tr>
    <hr>
    <% for(int i = 0; i < groups.size(); i++){%>
    <tr>
        <% Group w = (Group)groups.get(i);%>
        <td><%= w.getGroupID()%></td>
        <td><%= w.getName()%></td>
        <td><%= w.getCourse()%></td>
    </tr>
    <%}%>
</table>
</body>
</html>
