<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.HashMap" %>
<%
    // Retrieve the book data from the request attribute
    HashMap<String, Object> bookData = (HashMap<String, Object>) request.getAttribute("bookData");

    // Ensure bookData is not null before attempting to render the form
    if (bookData == null) {
        response.sendRedirect("viewBook.jsp"); // Redirect to the view page if no data exists
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Book</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('EDITBOOK.jpg');
            padding: 20px;
        }
        h1 {
            color: #000000;
        }
        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            margin: 0 auto;
        }
        label {
            display: block;
            margin: 8px 0 4px;
            font-weight: bold;
        }
        input[type="text"], input[type="number"], input[type="hidden"] {
            width: 100%;
            padding: 8px;
            margin: 8px 0;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .back-button {
            background-color: #e74c3c;
            color: white;
            border-radius: 4px;
            padding: 10px 20px;
            text-decoration: none;
            display: inline-block;
            margin-top: 20px;
        }
        button {
            background-color: #620edf;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #6d2ccf;
        }
    </style>
</head>
<body>

<!-- The form for editing the book data -->
<form action="handle_form_servlet" method="post">
    <h1>Edit Book</h1>
    <!-- Hidden fields for action and book ID -->
    <input type="hidden" name="action" value="edit">
    <input type="hidden" name="id" value="<%= bookData.get("id") != null ? bookData.get("id") : "" %>">

    <!-- Title input -->
    <label for="title">Title:</label>
    <input type="text" id="title" name="title" value="<%= bookData.get("title") != null ? bookData.get("title") : "" %>" required>

    <!-- Author input -->
    <label for="author">Author:</label>
    <input type="text" id="author" name="author" value="<%= bookData.get("author") != null ? bookData.get("author") : "" %>" required>

    <!-- Publisher input -->
    <label for="publisher">Publisher:</label>
    <input type="text" id="publisher" name="publisher" value="<%= bookData.get("publisher") != null ? bookData.get("publisher") : "" %>" required>

    <!-- Edition input -->
    <label for="edition">Edition:</label>
    <input type="text" id="edition" name="edition" value="<%= bookData.get("edition") != null ? bookData.get("edition") : "" %>" required>

    <!-- Cover Type input -->
    <label for="cover_type">Cover Type:</label>
    <input type="text" id="cover_type" name="cover_type" value="<%= bookData.get("coverType") != null ? bookData.get("coverType") : "" %>" required>

    <!-- Category input -->
    <label for="category">Category:</label>
    <input type="text" id="category" name="category" value="<%= bookData.get("category") != null ? bookData.get("category") : "" %>" required>

    <!-- Floor input -->
    <label for="floor">Floor:</label>
    <input type="text" id="floor" name="floor" value="<%= bookData.get("floor") != null ? bookData.get("floor") : "" %>" required>

    <!-- Shelf Location input -->
    <label for="shelf_location">Shelf Location:</label>
    <input type="text" id="shelf_location" name="shelf_location" value="<%= bookData.get("shelfLocation") != null ? bookData.get("shelfLocation") : "" %>" required>

    <!-- Quantity on Hand input -->
    <label for="quantity_on_hand">Quantity on Hand:</label>
    <input type="number" id="quantity_on_hand" name="quantity_on_hand" value="<%= bookData.get("quantityOnHand") != null ? bookData.get("quantityOnHand") : "" %>" required>

    <!-- Unit Price input -->
    <label for="unit_price">Unit Price:</label>
    <input type="text" id="unit_price" name="unit_price" value="<%= bookData.get("unitPrice") != null ? bookData.get("unitPrice") : "" %>" required>

    <!-- Submit button -->
    <button type="submit">Save Changes</button>

    <!-- Cancel link -->
    <a href="viewBook.jsp" class="back-button">Back</a>
</form>
</body>
</html>
