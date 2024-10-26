<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<html>
<head>
  <title>View Book Entries</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      padding: 20px;
    }
    .container {
      background-color: #fff;
      padding: 20px;
      border-radius: 5px;
      max-width: 900px;
      margin: auto;
      box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    }
    h2 {
      text-align: center;
      color: #333;
    }
    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px;
    }
    th, td {
      padding: 12px;
      text-align: left;
      border-bottom: 1px solid #ccc;
    }
    th {
      background-color: #007bff;
      color: white;
    }
    tr:hover {
      background-color: #f1f1f1;
    }
    .no-data {
      text-align: center;
      padding: 20px;
      color: #666;
    }
    .action-buttons {
      display: flex;
      gap: 10px;
    }
    .edit-button, .delete-button {
      padding: 5px 10px;
      border: none;
      border-radius: 3px;
      cursor: pointer;
      color: white;
    }
    .edit-button {
      background-color: #28a745; /* Green */
    }
    .delete-button {
      background-color: #dc3545; /* Red */
    }
    .back-link {
      display: block;
      margin-top: 20px;
      text-align: center;
      text-decoration: none;
      color: #007bff;
    }
    .back-link:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>Book Entries</h2>
  <%
    // Retrieve the list of book entries from the request attribute
    List<HashMap<Object, Object>> entriesData = (List<HashMap<Object, Object>>) request.getAttribute("entriesData");
    if (entriesData != null && !entriesData.isEmpty()) {
  %>
  <table>
    <thead>
    <tr>
      <th>ID</th>
      <th>Title</th>
      <th>Author</th>
      <th>Publisher</th>
      <th>Edition</th>
      <th>Cover Type</th>
      <th>Category</th>
      <th>Floor</th>
      <th>Shelf Location</th>
      <th>Quantity on Hand</th>
      <th>Unit Price</th>
      <th>Actions</th> <!-- Column for actions -->
    </tr>
    </thead>
    <tbody>
    <%
      for (HashMap<Object, Object> entry : entriesData) {
        String bookId = (String) entry.get("id"); // Get the ID for editing/deleting
    %>
    <tr>
      <td><%= bookId %></td>
      <td><%= entry.get("title") %></td>
      <td><%= entry.get("author") %></td>
      <td><%= entry.get("publisher") %></td>
      <td><%= entry.get("edition") %></td>
      <td><%= entry.get("coverType") %></td>
      <td><%= entry.get("category") %></td>
      <td><%= entry.get("floor") %></td>
      <td><%= entry.get("shelfLocation") %></td>
      <td><%= entry.get("quantityOnHand") %></td>
      <td><%= entry.get("unitPrice") %></td>
      <td>
        <div class="action-buttons">
          <a href="editBook?id=<%= bookId %>" class="edit-button">Edit</a>
          <a href="deleteBook?id=<%= bookId %>" class="delete-button" onclick="return confirm('Are you sure you want to delete this book?');">Delete</a>
        </div>
      </td>
    </tr>
    <%
      }
    %>
    </tbody>
  </table>
  <%
  } else {
  %>
  <div class="no-data">No book entries available.</div>
  <%
    }
  %>
  <a class="back-link" href="index.jsp">Back to Book List</a>
</div>
</body>
</html>
