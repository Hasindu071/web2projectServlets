<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.HashMap" %>
<%
    // Get the book data from the request attribute
    HashMap<Object, Object> bookData = (HashMap<Object, Object>) request.getAttribute("bookData");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Book</title>
    <link rel="stylesheet" href="styles.css"> <!-- Link to your CSS file for styling -->
</head>
<body>
<h1>Edit Book</h1>
<form action="handle_form_servlet" method="post">
    <input type="hidden" name="action" value="edit">
    <input type="hidden" name="id" value="<%= bookData.get("id") %>">

    <label for="title">Title:</label>
    <input type="text" id="title" name="title" value="<%= bookData.get("title") %>" required>

    <label for="author">Author:</label>
    <input type="text" id="author" name="author" value="<%= bookData.get("author") %>" required>

    <label for="publisher">Publisher:</label>
    <input type="text" id="publisher" name="publisher" value="<%= bookData.get("publisher") %>" required>

    <label for="edition">Edition:</label>
    <input type="text" id="edition" name="edition" value="<%= bookData.get("edition") %>" required>

    <label for="cover_type">Cover Type:</label>
    <input type="text" id="cover_type" name="cover_type" value="<%= bookData.get("coverType") %>" required>

    <label for="category">Category:</label>
    <input type="text" id="category" name="category" value="<%= bookData.get("category") %>" required>

    <label for="floor">Floor:</label>
    <input type="text" id="floor" name="floor" value="<%= bookData.get("floor") %>" required>

    <label for="shelf_location">Shelf Location:</label>
    <input type="text" id="shelf_location" name="shelf_location" value="<%= bookData.get("shelfLocation") %>" required>

    <label for="quantity_on_hand">Quantity on Hand:</label>
    <input type="number" id="quantity_on_hand" name="quantity_on_hand" value="<%= bookData.get("quantityOnHand") %>" required>

    <label for="unit_price">Unit Price:</label>
    <input type="text" id="unit_price" name="unit_price" value="<%= bookData.get("unitPrice") %>" required>

    <button type="submit">Save Changes</button>
    <a href="viewBook.jsp">Cancel</a>
</form>
</body>
</html>
