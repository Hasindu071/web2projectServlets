package com.example.web2project;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/handle_form_servelet") // Corrected spelling from 'servelet' to 'servlet'
public class handle_form_servelet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(handle_form_servelet.class.getName());

    @Override
    public void init() throws ServletException {
        super.init();
        LOGGER.log(Level.INFO, "Servlet initialized");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the form data
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        String edition = request.getParameter("edition");
        String coverType = request.getParameter("cover_type");
        String category = request.getParameter("category");
        String floor = request.getParameter("floor");
        String shelfLocation = request.getParameter("shelf_location");
        String quantityOnHandStr = request.getParameter("quantity_on_hand");
        String unitPriceStr = request.getParameter("unit_price");

        // Validate the input fields
        if (isNullOrEmpty(title) || isNullOrEmpty(author) || isNullOrEmpty(publisher) ||
                isNullOrEmpty(edition) || isNullOrEmpty(coverType) || isNullOrEmpty(category) ||
                isNullOrEmpty(floor) || isNullOrEmpty(shelfLocation) ||
                isNullOrEmpty(quantityOnHandStr) || isNullOrEmpty(unitPriceStr)) {

            LOGGER.log(Level.WARNING, "Missing required fields in form submission");
            response.sendRedirect("index.jsp?error=All fields are required!");
            return;
        }

        // Parse quantity and unit price
        int quantityOnHand;
        double unitPrice;
        try {
            quantityOnHand = Integer.parseInt(quantityOnHandStr);
            unitPrice = Double.parseDouble(unitPriceStr.replace("$", "").trim()); // Remove $ sign if present
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Invalid quantity or price format", e);
            response.sendRedirect("index.jsp?error=Invalid quantity or price format!");
            return;
        }

        // Create a unique ID based on the current timestamp
        String id = String.valueOf(new Date().getTime());

        // Use getServletContext().getRealPath() to get the actual path for the XML file
        String xmlFilePath = getServletContext().getRealPath("/WEB-INF/data.xml");
        LOGGER.log(Level.INFO, "XML File Path: {0}", xmlFilePath);

        // Save data to XML
        try {
            handle_XML.saveDataToXML(id, title, author, publisher, edition, coverType, category, floor, shelfLocation, quantityOnHand, unitPrice, xmlFilePath);
            LOGGER.log(Level.INFO, "Data saved successfully to XML");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to save data to XML", e);
            response.sendRedirect("index.jsp?error=Failed to save data to XML!");
            return;
        }

        // Redirect to a success page
        response.sendRedirect("index.jsp?success=Data submitted successfully");
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
