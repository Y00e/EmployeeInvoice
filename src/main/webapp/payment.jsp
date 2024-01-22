<form action="PaymentServlet" method="post">
    <label for="title">Title:</label>
    <input type="text" id="title" name="title"><br><br>

    <label for="date">Date:</label>
    <input type="text" id="date" name="date"><br><br>

    <label for="description">Description:</label><br>
    <textarea id="description" name="description"></textarea><br><br>

    <label for="category">Category:</label>
    <select id="category" name="category">
        <option value="Category1">Category 1</option>
        <option value="Category2">Category 2</option>
        <option value="Category3">Category 3</option>
        <!-- Add more options as needed -->
    </select><br><br>

    <label for="price">Price:</label>
    <input type="text" id="price" name="price"><br><br>

    <input type="submit" value="Submit">

    <label for="employeeId">Employee ID:</label>
    <input type="text" id="employeeId" name="employeeId"><br><br>
</form>