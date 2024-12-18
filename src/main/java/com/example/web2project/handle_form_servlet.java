package com.example.web2project;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/handle_form_servlet")
public class handle_form_servlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(handle_form_servlet.class.getName());

    @Override
    public void init() throws ServletException {
        super.init();
        LOGGER.log(Level.INFO, "Servlet initialized");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the form data
        String id = request.getParameter("id");
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
        String action = request.getParameter("action");

        String xmlFilePath = getServletContext().getRealPath("/WEB-INF/data.xml");
        LOGGER.log(Level.INFO, "XML File Path: {0}", xmlFilePath);

        try {
            validateForm(action, id, title, author, publisher, edition, coverType, category, floor, shelfLocation, quantityOnHandStr, unitPriceStr);
            int quantityOnHand = Integer.parseInt(quantityOnHandStr);
            double unitPrice = Double.parseDouble(unitPriceStr.replace("$", "").trim());

            // If update is clicked this happens
            if ("edit".equals(action)) {
                handle_XML.updateDataInXML(id, title, author, publisher, edition, coverType, category, floor, shelfLocation, quantityOnHand, unitPrice, xmlFilePath);
                LOGGER.log(Level.INFO, "Data updated successfully in XML");
                response.sendRedirect(request.getRequestURI() + "?success=Data updated successfully");
                // if data is saved this happens
            } else if ("save".equals(action)){
                String newId = String.valueOf(new Date().getTime());
                handle_XML.saveDataToXML(newId, title, author, publisher, edition, coverType, category, floor, shelfLocation, quantityOnHand, unitPrice, xmlFilePath);
                LOGGER.log(Level.INFO, "Data saved successfully to XML");
                response.sendRedirect("form.jsp?success=Data submitted successfully");
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "XML operation failed", e);
            response.sendRedirect("form.jsp?error=" + e.getMessage());
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Invalid quantity or price format", e);
            response.sendRedirect("form.jsp?error=Invalid quantity or price format!");
        }
    }

    private void validateForm(String action, String id, String title, String author, String publisher,
                              String edition, String coverType, String category, String floor,
                              String shelfLocation, String quantityOnHandStr, String unitPriceStr) throws IOException {
        if ("edit".equals(action) && isNullOrEmpty(id)) {
            throw new IOException("ID is required for editing.");
        }
        if (isNullOrEmpty(title) || isNullOrEmpty(author) || isNullOrEmpty(publisher) ||
                isNullOrEmpty(edition) || isNullOrEmpty(coverType) || isNullOrEmpty(category) ||
                isNullOrEmpty(floor) || isNullOrEmpty(shelfLocation) ||
                isNullOrEmpty(quantityOnHandStr) || isNullOrEmpty(unitPriceStr)) {
            throw new IOException("All fields are required!");
        }
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String xmlFilePath = getServletContext().getRealPath("/WEB-INF/data.xml");
        List<HashMap<Object, Object>> entriesData = null;

        try {
            // If no action is specified, load the data (this is the default behavior)
            if (action == null || action.isEmpty()) {
                entriesData = readXMLData(xmlFilePath);
            }

            // Handle delete action
            if ("delete".equals(action)) {
                String id = request.getParameter("id");
                handle_XML.deleteDataFromXML(id, xmlFilePath);
                response.sendRedirect("viewBook.jsp?success=Data deleted successfully");
                return; // Ensure the rest of the code does not execute after redirect
            }

            // Handle edit action
            else if ("edit".equals(action)) {
                String id = request.getParameter("id");
                HashMap<Object, Object> bookData = handle_XML.getDataById(id, xmlFilePath);
                request.setAttribute("bookData", bookData);
                request.getRequestDispatcher("/editBook.jsp").forward(request, response);
                return; // Ensure the rest of the code does not execute after forward
            }

            // Handle search action
            else if ("search".equals(action)) {
                String searchTerm = request.getParameter("search").toLowerCase();
                String selectedCategory = request.getParameter("category").toLowerCase();

                entriesData = readXMLData(xmlFilePath);
                List<HashMap<Object, Object>> filteredEntries = new ArrayList<>();

                // Filter data based on search term and selected category
                for (HashMap<Object, Object> entry : entriesData) {
                    String title = ((String) entry.get("title")).toLowerCase();
                    String author = ((String) entry.get("author")).toLowerCase();
                    String category = ((String) entry.get("category")).toLowerCase();

                    boolean matchesSearch = (searchTerm == null || searchTerm.isEmpty()) ||
                            title.contains(searchTerm) ||
                            author.contains(searchTerm) ||
                            category.contains(searchTerm);

                    boolean matchesCategory = (selectedCategory == null || selectedCategory.isEmpty()) ||
                            category.equals(selectedCategory);

                    // Add entry to filtered results if it matches both search term and category
                    if (matchesSearch && matchesCategory) {
                        filteredEntries.add(entry);
                    }
                }

                entriesData = filteredEntries; // Set the filtered data
            }

            // If entriesData is null or empty, show a message
            if (entriesData == null || entriesData.isEmpty()) {
                request.setAttribute("entriesData", null);
                request.setAttribute("message", "No data available.");
            } else {
                request.setAttribute("entriesData", entriesData);
            }

            // Forward the request to viewBook.jsp
            request.getRequestDispatcher("/viewBook.jsp").forward(request, response);

        } catch (Exception e) {
            response.getWriter().println("<p>Error reading XML data: " + e.getMessage() + "</p>");
        }
    }


    private List<HashMap<Object, Object>> readXMLData(String xmlFilePath) throws Exception {
        List<HashMap<Object, Object>> entriesData = new ArrayList<>();
        File xmlFile = new File(xmlFilePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);
        document.getDocumentElement().normalize();

        NodeList entries = document.getElementsByTagName("book");
        for (int i = 0; i < entries.getLength(); i++) {
            Element entry = (Element) entries.item(i);
            HashMap<Object, Object> entryData = new HashMap<>();
            entryData.put("id", entry.getElementsByTagName("id").item(0).getTextContent());
            entryData.put("title", entry.getElementsByTagName("title").item(0).getTextContent());
            entryData.put("author", entry.getElementsByTagName("author").item(0).getTextContent());
            entryData.put("publisher", entry.getElementsByTagName("publisher").item(0).getTextContent());
            entryData.put("edition", entry.getElementsByTagName("edition").item(0).getTextContent());
            entryData.put("coverType", entry.getElementsByTagName("coverType").item(0).getTextContent());
            entryData.put("category", entry.getElementsByTagName("category").item(0).getTextContent());
            entryData.put("floor", entry.getElementsByTagName("floor").item(0).getTextContent());
            entryData.put("shelfLocation", entry.getElementsByTagName("shelfLocation").item(0).getTextContent());
            entryData.put("quantityOnHand", entry.getElementsByTagName("quantityOnHand").item(0).getTextContent());
            entryData.put("unitPrice", entry.getElementsByTagName("unitPrice").item(0).getTextContent());

            entriesData.add(entryData);
        }
        return entriesData;
    }
}
