<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<html>
<head>
  <title>View Book Entries</title>
  <style>
    /* Page and Container Styling */
    body {
      font-family: 'Roboto', Arial, sans-serif;
      background-image: url('poth.jpg');
      margin: 0;
      padding: 20px;
    }
    .container {
      background-color: #fff;
      padding: 30px;
      border-radius: 8px;
      max-width: 1300px; /* Set a reasonable max-width */
      margin: auto;
      box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
    }
    h2 {
      text-align: center;
      color: #4a4a4a;
    }

    /* Search Bar Styling */
    .search-container {
      display: flex;
      justify-content: center;
      margin: 20px 0;
    }
    .search-bar {
      width: 300px;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }
    .search-button {
      background-color: #007bff;
      border: none;
      color: white;
      padding: 10px 15px;
      cursor: pointer;
      border-radius: 5px;
      margin-left: 5px;
    }
    .search-button:hover {
      background-color: #0056b3;
    }

    /* Table Styling */
    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px;
    }
    th, td {
      padding: 15px;
      text-align: left;
      border-bottom: 1px solid #e0e0e0;
    }
    th {
      background-color: #007bff;
      color: #fff;
    }
    tr:hover {
      background-color: #f9f9f9;
    }
    .no-data {
      text-align: center;
      padding: 20px;
      color: #666;
    }

    /* Action Buttons */
    .action-buttons {
      display: flex;
      gap: 8px;
    }
    .edit-button, .delete-button {
      padding: 8px 12px;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      color: white;
      font-size: 0.9em;
    }
    .edit-button {
      background-color: #28a745;
    }
    .delete-button {
      background-color: #dc3545;
    }

    /* Back Link */
    .back-link {
      display: block;
      margin-top: 20px;
      text-align: center;
      text-decoration: none;
      color: #007bff;
      font-weight: bold;
    }
    .back-link:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>Book Entries</h2>

  <!-- Search Bar -->
  <div class="search-container">
    <form action="handle_form_servlet" method="get">
      <input type="text" name="search" class="search-bar" placeholder="Search by title, author, or category" />
      <input type="hidden" name="action" value="search" />
      <button type="submit" class="search-button">Search</button>
    </form>
  </div>

  <!-- Book Entries Table -->
  <%
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
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
      for (HashMap<Object, Object> entry : entriesData) {
        String bookId = (String) entry.get("id");
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

  <!-- Back Link -->
  <a class="back-link" href="index.jsp">Back to Book List</a>
</div>
</body>
</html>
