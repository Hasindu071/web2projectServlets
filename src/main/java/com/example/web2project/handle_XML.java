package com.example.web2project;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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

public class handle_XML {

    public static void saveDataToXML(String id, String title, String author, String publisher, String edition,
                                     String coverType, String category, String floor, String shelfLocation,
                                     int quantityOnHand, double unitPrice, String xmlFilePath) throws IOException {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            File xmlFile = new File(xmlFilePath);
            Document document;

            if (xmlFile.exists()) {
                document = documentBuilder.parse(xmlFile);
            } else {
                document = documentBuilder.newDocument();
                Element root = document.createElement("books");
                document.appendChild(root);
            }

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

    private static Element createElementWithText(Document document, String tagName, String textContent) {
        Element element = document.createElement(tagName);
        element.appendChild(document.createTextNode(textContent));
        return element;
    }

    private static void writeDocumentToFile(Document document, File xmlFile) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(xmlFile);
        transformer.transform(domSource, streamResult);
    }

    public static void updateDataInXML(String id, String title, String author, String publisher, String edition,
                                       String coverType, String category, String floor, String shelfLocation,
                                       int quantityOnHand, double unitPrice, String xmlFilePath) throws IOException {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            File xmlFile = new File(xmlFilePath);
            Document document;

            if (xmlFile.exists()) {
                document = documentBuilder.parse(xmlFile);
                NodeList books = document.getElementsByTagName("book");

                for (int i = 0; i < books.getLength(); i++) {
                    Node bookNode = books.item(i);
                    Element bookElement = (Element) bookNode;

                    // Check if the book ID matches
                    if (bookElement.getElementsByTagName("id").item(0).getTextContent().equals(id)) {
                        // Update each field
                        bookElement.getElementsByTagName("title").item(0).setTextContent(title);
                        bookElement.getElementsByTagName("author").item(0).setTextContent(author);
                        bookElement.getElementsByTagName("publisher").item(0).setTextContent(publisher);
                        bookElement.getElementsByTagName("edition").item(0).setTextContent(edition);
                        bookElement.getElementsByTagName("coverType").item(0).setTextContent(coverType);
                        bookElement.getElementsByTagName("category").item(0).setTextContent(category);
                        bookElement.getElementsByTagName("floor").item(0).setTextContent(floor);
                        bookElement.getElementsByTagName("shelfLocation").item(0).setTextContent(shelfLocation);
                        bookElement.getElementsByTagName("quantityOnHand").item(0).setTextContent(String.valueOf(quantityOnHand));
                        bookElement.getElementsByTagName("unitPrice").item(0).setTextContent(String.valueOf(unitPrice));

                        // Write updated document to XML
                        writeDocumentToFile(document, xmlFile);
                        return;
                    }
                }
            }
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

            if (xmlFile.exists()) {
                document = documentBuilder.parse(xmlFile);
                NodeList books = document.getElementsByTagName("book");

                for (int i = 0; i < books.getLength(); i++) {
                    Node bookNode = books.item(i);
                    Element bookElement = (Element) bookNode;

                    // Check if the book ID matches
                    if (bookElement.getElementsByTagName("id").item(0).getTextContent().equals(id)) {
                        bookElement.getParentNode().removeChild(bookElement);

                        // Write updated document to XML
                        writeDocumentToFile(document, xmlFile);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            throw new IOException("Failed to delete data from XML", e);
        }
    }
}
