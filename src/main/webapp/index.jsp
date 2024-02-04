<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="se">
<head>
    <meta charset="UTF-8">
    <title>JSP - Hello World</title>
</head>
<body>
<form action="/payment/" method="POST">
    <label>Title</label>
    <input type="text" name="title"/>

    <br>

    <label>Date</label>
    <input type="text" name="date"/>

    <br>

    <label>description</label>
    <textarea name="description"></textarea>

    <br>

    <label>Category</label>
    <input type="text" name="category"/>

    <br>

    <label>Price</label>
    <input type="text" name="price"/>

    <br>

    <label>Employee_Id</label>
    <input type="text" name="employee_id"/>

    <br>
    <button>Submit</button>
</form>

<form method="POST" action="/auth/logout">
    <button>Logout</button>
</form>
</body>
</html>