package com.example.web2project;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class handle_XML {

    public static void saveDataToXML(String id, String title, String author, String publisher, String edition, String coverType,
                                     String category, String floor, String shelfLocation, int quantityOnHand, double unitPrice, String xmlFilePath) throws IOException {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            File xmlFile = new File(xmlFilePath);
            Document document;

            // Create document if it does not exist
            if (xmlFile.exists()) {
                document = documentBuilder.parse(xmlFile);
            } else {
                document = documentBuilder.newDocument();
                Element root = document.createElement("books");
                document.appendChild(root);
            }

            // Create a new book element
            Element book = document.createElement("book");
            book.appendChild(createElementWithText(document, "id", id));
            book.appendChild(createElementWithText(document, "title", title));
            book.appendChild(createElementWithText(document, "author", author));
            book.appendChild(createElementWithText(document, "publisher", publisher));
            book.appendChild(createElementWithText(document, "edition", edition));
            book.appendChild(createElementWithText(document, "coverType", coverType));
            book.appendChild(createElementWithText(document, "category", category));
            book.appendChild(createElementWithText(document, "floor", floor));
            book.appendChild(createElementWithText(document, "shelfLocation", shelfLocation));
            book.appendChild(createElementWithText(document, "quantityOnHand", String.valueOf(quantityOnHand)));
            book.appendChild(createElementWithText(document, "unitPrice", String.valueOf(unitPrice)));

            document.getDocumentElement().appendChild(book);

            writeDocumentToFile(document, xmlFile);

        } catch (Exception e) {
            throw new IOException("Failed to save data to XML", e);
        }
    }

    public static void updateDataInXML(String id, String title, String author, String publisher, String edition, String coverType,
                                       String category, String floor, String shelfLocation, int quantityOnHand, double unitPrice, String xmlFilePath) throws IOException {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            File xmlFile = new File(xmlFilePath);
            Document document;

            if (!xmlFile.exists()) {
                throw new IOException("XML file does not exist");
            }

            document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList books = document.getElementsByTagName("book");
            boolean bookFound = false;

            for (int i = 0; i < books.getLength(); i++) {
                Element book = (Element) books.item(i);
                if (book.getElementsByTagName("id").item(0).getTextContent().equals(id)) {
                    // Update book details
                    book.getElementsByTagName("title").item(0).setTextContent(title);
                    book.getElementsByTagName("author").item(0).setTextContent(author);
                    book.getElementsByTagName("publisher").item(0).setTextContent(publisher);
                    book.getElementsByTagName("edition").item(0).setTextContent(edition);
                    book.getElementsByTagName("coverType").item(0).setTextContent(coverType);
                    book.getElementsByTagName("category").item(0).setTextContent(category);
                    book.getElementsByTagName("floor").item(0).setTextContent(floor);
                    book.getElementsByTagName("shelfLocation").item(0).setTextContent(shelfLocation);
                    book.getElementsByTagName("quantityOnHand").item(0).setTextContent(String.valueOf(quantityOnHand));
                    book.getElementsByTagName("unitPrice").item(0).setTextContent(String.valueOf(unitPrice));
                    bookFound = true;
                    break;
                }
            }

            if (!bookFound) {
                throw new IOException("Book with ID " + id + " not found");
            }

            writeDocumentToFile(document, xmlFile);

        } catch (Exception e) {
            throw new IOException("Failed to update data in XML", e);
        }
    }

    public static void deleteDataFromXML(String id, String xmlFilePath) throws IOException {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            File xmlFile = new File(xmlFilePath);
            Document document;

            if (!xmlFile.exists()) {
                throw new IOException("XML file does not exist");
            }

            document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList books = document.getElementsByTagName("book");
            boolean bookFound = false;

            for (int i = 0; i < books.getLength(); i++) {
                Element book = (Element) books.item(i);
                if (book.getElementsByTagName("id").item(0).getTextContent().equals(id)) {
                    // Remove the book element
                    book.getParentNode().removeChild(book);
                    bookFound = true;
                    break;
                }
            }

            if (!bookFound) {
                throw new IOException("Book with ID " + id + " not found");
            }

            writeDocumentToFile(document, xmlFile);

        } catch (Exception e) {
            throw new IOException("Failed to delete data from XML", e);
        }
    }

    public static HashMap<Object, Object> getDataById(String id, String xmlFilePath) {
        HashMap<Object, Object> bookData = new HashMap<>();
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            File xmlFile = new File(xmlFilePath);

            if (!xmlFile.exists()) {
                throw new IOException("XML file does not exist");
            }

            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList books = document.getElementsByTagName("book");
            boolean bookFound = false;

            for (int i = 0; i < books.getLength(); i++) {
                Element book = (Element) books.item(i);
                if (book.getElementsByTagName("id").item(0).getTextContent().equals(id)) {
                    bookData.put("id", book.getElementsByTagName("id").item(0).getTextContent());
                    bookData.put("title", book.getElementsByTagName("title").item(0).getTextContent());
                    bookData.put("author", book.getElementsByTagName("author").item(0).getTextContent());
                    bookData.put("publisher", book.getElementsByTagName("publisher").item(0).getTextContent());
                    bookData.put("edition", book.getElementsByTagName("edition").item(0).getTextContent());
                    bookData.put("coverType", book.getElementsByTagName("coverType").item(0).getTextContent());
                    bookData.put("category", book.getElementsByTagName("category").item(0).getTextContent());
                    bookData.put("floor", book.getElementsByTagName("floor").item(0).getTextContent());
                    bookData.put("shelfLocation", book.getElementsByTagName("shelfLocation").item(0).getTextContent());
                    bookData.put("quantityOnHand", book.getElementsByTagName("quantityOnHand").item(0).getTextContent());
                    bookData.put("unitPrice", book.getElementsByTagName("unitPrice").item(0).getTextContent());
                    bookFound = true;
                    break;
                }
            }

            if (!bookFound) {
                throw new IOException("Book with ID " + id + " not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookData;
    }

    private static Element createElementWithText(Document document, String tagName, String text) {
        Element element = document.createElement(tagName);
        element.appendChild(document.createTextNode(text));
        return element;
    }

    private static void writeDocumentToFile(Document document, File xmlFile) throws Exception {
        // Write the content into the XML file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(xmlFile);
        transformer.transform(domSource, streamResult);
    }
}
