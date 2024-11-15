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
            background-image: linear-gradient(rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.7)), url('books.jpeg'); /* Replace with your image path */
            background-size: cover;
            background-position: center;
            color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            text-align: center;
            padding: 40px;
            background-color: rgba(255, 255, 255, 0.1); /* Semi-transparent glass effect */
            border-radius: 15px;
            width: 80%;
            max-width: 600px;
            box-shadow: 0 4px 30px rgba(0, 0, 0, 0.5);
            backdrop-filter: blur(10px); /* Glassmorphism effect */
        }

        h1 {
            font-size: 3em;
            margin-bottom: 20px;
            font-weight: bold;
            color: #e0b0ff; /* Lavender shade */
        }

        p {
            font-size: 1.2em;
            margin-bottom: 30px;
            color: #dcdcdc; /* Soft white shade */
        }

        .button-container {
            display: flex;
            justify-content: space-evenly;
        }

        .button {
            background: linear-gradient(90deg, #1e90ff, #ff69b4); /* Gradient color */
            color: #fff;
            padding: 15px 25px;
            text-decoration: none;
            border-radius: 50px;
            font-size: 1.2em;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.2);
        }

        .button:hover {
            transform: translateY(-5px);
            box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.3);
        }

        .button:active {
            transform: translateY(0);
            box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.2);
        }

        footer {
            margin-top: 20px;
            font-size: 0.9em;
            color: #dcdcdc;
        }

        footer a {
            color: #e0b0ff;
            text-decoration: none;
        }

        footer a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>📚 Welcome to the Book Inventory System 📚</h1>
    <p>Manage and explore your book inventory with ease. Click below to get started!</p>
    <div class="button-container">
        <a href="form.jsp" class="button">Add a Book</a>
        <a href="handle_form_servlet" class="button">View Inventory</a>
    </div>
</div>
</body>
</html>
