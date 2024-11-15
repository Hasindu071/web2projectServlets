<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Inventory Information Record Form</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-image: url('books.jpeg');
            margin: 0;
            padding: 40px;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        form {
            background-color: #fff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
            max-width: 700px;
            width: 100%;
            font-size: 16px;
        }

        h2 {
            text-align: center;
            color: #333;
            font-size: 28px;
            margin-bottom: 20px;
            position: relative;
            padding-bottom: 10px;
        }

        h2:after {
            content: '';
            display: block;
            width: 80px;
            height: 4px;
            background-color: #3498db;
            margin: 10px auto;
        }

        label {
            font-weight: bold;
            color: #333;
            display: block;
            margin-bottom: 8px;
        }

        input[type="text"], input[type="number"], input[type="tel"], select, input[type="radio"] {
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 8px;
            box-sizing: border-box;
            font-size: 14px;
            outline: none;
            transition: border 0.3s ease;
        }

        input[type="text"]:focus, input[type="number"]:focus, select:focus {
            border-color: #3498db;
            box-shadow: 0 0 8px rgba(52, 152, 219, 0.3);
        }

        .cover-type, .floor {
            display: flex;
            justify-content: space-between;
            margin-bottom: 20px;
        }

        .cover-type label, .floor label {
            margin-right: 15px;
        }

        .category, .shelf-location {
            position: relative;
            margin-bottom: 20px;
        }

        input[type="submit"], .back-button {
            background-color: #3498db;
            color: white;
            padding: 12px;
            border: none;
            border-radius: 8px;
            width: 100%;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover, .back-button:hover {
            background-color: #2980b9;
        }

        /* Back button styling */
        .back-button {
            background-color: #e74c3c;
            margin-bottom: 200px;
            top: 10px;
            right: 10px;
            padding: 10px 20px;


        }

        /* Placeholder styling */
        ::placeholder {
            color: #aaa;
        }
        .toast {
            position: fixed;
            top: 20px;
            right: 20px;
            min-width: 250px;
            z-index: 1050;
            opacity: 1;
            transition: opacity 0.5s ease, transform 0.5s ease;
            transform: translateX(100%);
        }

        .toast.show {
            opacity: 1;
            transform: translateX(0);
        }

        .toast-header {
            background-color: #28a745; /* Green color for success */
            color: #fff;
            font-weight: bold;
            padding: 10px 15px;
            border-top-left-radius: 12px;
            border-top-right-radius: 12px;
        }

        .toast-body {
            padding: 15px;
            font-size: 14px;
            color: #333;
            background-color: #d4edda;
            border-bottom-left-radius: 12px;
            border-bottom-right-radius: 12px;
        }

        .btn-close {
            background: none;
            border: none;
            color: white;
            font-size: 1.5rem;
            font-weight: bold;
            cursor: pointer;
            margin-left: auto;
            line-height: 1;
            padding: 0 10px;
            transition: color 0.3s ease;
        }

        .btn-close:hover {
            color: #ffcccc;
        }
    </style>
</head>
<body>

<form action="handle_form_servlet" method="post">
    <input type="hidden" name="action" value="save">
    <!-- Back button -->
    <a href="index.jsp" class="back-button">Back</a>
    <h2>Book Inventory Information Record Form</h2>


    <label for="title">Title:</label>
    <input type="text" id="title" name="title" placeholder="Enter Book Title" required>

    <label for="author">Author:</label>
    <input type="text" id="author" name="author" placeholder="Enter Author's Name" required>

    <label for="publisher">Publisher:</label>
    <input type="text" id="publisher" name="publisher" placeholder="Enter Publisher's Name" required>

    <label for="edition">Edition:</label>
    <input type="text" id="edition" name="edition" placeholder="Enter Edition (e.g., 1st, 2nd)" required>

    <div class="cover-type">
        <label>Cover Type:</label>
        <div>
            <input type="radio" id="hardback" name="cover_type" value="Hardback" required>
            <label for="hardback">Hardback</label>
        </div>
        <div>
            <input type="radio" id="paperback" name="cover_type" value="Paperback" required>
            <label for="paperback">Paperback</label>
        </div>
    </div>

    <label for="category">Category:</label>
    <select id="category" name="category" required>
        <option value="">Please Select</option>
        <option value="Comics">Comics</option>
        <option value="History">History</option>
        <option value="Classics">Classics</option>
        <option value="Romance">Romance</option>
        <option value="Horror">Horror</option>
        <option value="Fantasy Fiction">Fantasy Fiction</option>
    </select>

    <div class="floor">
        <label>Floor:</label>
        <div>
            <input type="radio" id="first_floor" name="floor" value="First Floor" required>
            <label for="first_floor">First Floor</label>
        </div>
        <div>
            <input type="radio" id="second_floor" name="floor" value="Second Floor" required>
            <label for="second_floor">Second Floor</label>
        </div>
    </div>

    <label for="shelf_location">Shelf Location:</label>
    <select id="shelf_location" name="shelf_location" required>
        <option value="">Please Select</option>
        <option value="A-35">A-35</option>
        <option value="A-36">A-36</option>
        <option value="B-20">B-20</option>
        <option value="B-25">B-25</option>
    </select>

    <label for="quantity_on_hand">Quantity on Hand:</label>
    <input type="number" id="quantity_on_hand" name="quantity_on_hand" placeholder="ex: 5" required>

    <label for="unit_price">Unit Price:</label>
    <input type="text" id="unit_price" name="unit_price" placeholder="Rs 18.99" required>
    <small id="error_message" style="color: red; display: none;">Please enter a valid unit price.</small>

    <script>
        const unitPriceInput = document.getElementById('unit_price');
        const errorMessage = document.getElementById('error_message');

        unitPriceInput.addEventListener('input', () => {
            const value = unitPriceInput.value.trim();

            // Remove existing error message
            errorMessage.style.display = 'none';

            // Validation logic
            try {
                if (!value) throw "Unit price cannot be empty.";
                const numericValue = parseFloat(value.replace(/[Rs,]/g, ''));

                if (isNaN(numericValue)) throw "Please enter a numeric value.";
                if (numericValue <= 0) throw "Unit price must be greater than zero.";
                if (numericValue > 10000) throw "Unit price seems too high.";

                unitPriceInput.style.borderColor = ""; // Clear error styling
            } catch (err) {
                unitPriceInput.style.borderColor = "red"; // Highlight input
                errorMessage.textContent = err; // Display error message
                errorMessage.style.display = 'block';
            }
        });
    </script>




    <input type="submit" value="Save Changes">
</form>
<!-- Toast Notification for Success Message -->
<%
    String success = request.getParameter("success");
    if (success != null && success.equals("Data submitted successfully")) {
%>
<div id="successToast" class="toast show">
    <div class="toast-header">
        <strong class="me-auto">Success</strong>
        <button type="button" class="btn-close" onclick="hideToast()">
            <!-- Close icon (SVG or PNG) -->
            <img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='white' class='bi bi-x' viewBox='0 0 16 16'%3E%3Cpath d='M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z'/%3E%3C/svg%3E" alt="Close">
        </button>
    </div>
    <div class="toast-body">
        Data submitted successfully!
    </div>
</div>
<% } %>
<script>
    // Function to hide the toast manually
    function hideToast() {
        document.getElementById("successToast").style.display = "none";
    }

    // Auto-hide the toast after 3 seconds
    setTimeout(hideToast, 3000);
</script>

</body>
</html>
