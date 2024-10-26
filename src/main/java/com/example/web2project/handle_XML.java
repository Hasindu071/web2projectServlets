package com.example.web2project;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
    public static void saveDataToXML(String id, String title, String author, String publisher, String edition, String coverType,
                                     String category, String floor, String shelfLocation, int quantityOnHand, double unitPrice, String xmlFilePath) throws IOException {
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

            Element bookId = document.createElement("id");
            bookId.appendChild(document.createTextNode(id));
            book.appendChild(bookId);

            Element bookTitle = document.createElement("title");
            bookTitle.appendChild(document.createTextNode(title));
            book.appendChild(bookTitle);

            Element bookAuthor = document.createElement("author");
            bookAuthor.appendChild(document.createTextNode(author));
            book.appendChild(bookAuthor);

            Element bookPublisher = document.createElement("publisher");
            bookPublisher.appendChild(document.createTextNode(publisher));
            book.appendChild(bookPublisher);

            Element bookEdition = document.createElement("edition");
            bookEdition.appendChild(document.createTextNode(edition));
            book.appendChild(bookEdition);

            Element bookCoverType = document.createElement("coverType");
            bookCoverType.appendChild(document.createTextNode(coverType));
            book.appendChild(bookCoverType);

            Element bookCategory = document.createElement("category");
            bookCategory.appendChild(document.createTextNode(category));
            book.appendChild(bookCategory);

            Element bookFloor = document.createElement("floor");
            bookFloor.appendChild(document.createTextNode(floor));
            book.appendChild(bookFloor);

            Element bookShelfLocation = document.createElement("shelfLocation");
            bookShelfLocation.appendChild(document.createTextNode(shelfLocation));
            book.appendChild(bookShelfLocation);

            Element bookQuantityOnHand = document.createElement("quantityOnHand");
            bookQuantityOnHand.appendChild(document.createTextNode(String.valueOf(quantityOnHand)));
            book.appendChild(bookQuantityOnHand);

            Element bookUnitPrice = document.createElement("unitPrice");
            bookUnitPrice.appendChild(document.createTextNode(String.valueOf(unitPrice)));
            book.appendChild(bookUnitPrice);

            document.getDocumentElement().appendChild(book);

            // Write the content into the XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(xmlFile);
            transformer.transform(domSource, streamResult);

        } catch (Exception e) {
            throw new IOException("Failed to save data to XML", e);
        }
    }
}
