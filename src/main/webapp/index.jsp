<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-image: url('books.jpeg'); /* Replace with your image path */
            background-size: cover;
            background-position: center;
            color: #fff;
        }

        .container {
            text-align: center;
            padding: 50px;
            background-color: rgba(0, 0, 0, 0.5); /* Semi-transparent background */
            border-radius: 10px;
            margin: auto;
            width: 60%;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.7);
        }

        h1 {
            font-size: 2.5em;
            margin-bottom: 20px;
        }

        p {
            font-size: 1.2em;
            margin-bottom: 40px;
        }

        .button-container {
            display: flex;
            justify-content: center;
            gap: 20px; /* Space between buttons */
        }

        .button {
            background-color: #1c4379; /* Bootstrap green */
            color: white;
            padding: 15px 30px;
            text-decoration: none;
            border-radius: 5px;
            font-size: 1.1em;
            transition: background-color 0.3s, transform 0.3s; /* Animation effects */
        }

        .button:hover {
            background-color: #620edf; /* Darker green on hover */
            transform: scale(1.05); /* Slightly enlarge button */
        }

    </style>
</head>
<body>
<div class="container">
    <h1>Welcome to the Book Inventory System</h1>
    <div class="button-container">
        <a href="form.jsp" class="button">Go to Form</a>
        <a href="handle_form_servlet" class="button">View Inventory</a>
    </div>
</div>
</body>
</html>
