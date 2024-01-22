<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Invoice Page</title>
</head>
<body>
<h2>Your Payments</h2>

<c:if test="${not empty payments}">
    <table>
        <tr>
            <th>Title</th>
            <th>Date</th>
            <th>Description</th>
            <th>Category</th>
            <th>Price</th>
            <th>Employee_Id</th>
        </tr>
        <c:forEach var="payment" items="${payments}">
            <tr>
                <td>${payment.title}</td>
                <td>${payment.date}</td>
                <td>${payment.description}</td>
                <td>${payment.category}</td>
                <td>${payment.price}</td>
                <td>${payment.employeeId}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${empty payments}">
    <p>You have not registered any payments yet.</p>
</c:if>

<a href="payment.jsp">Register a new payment</a>
</body>
</html>