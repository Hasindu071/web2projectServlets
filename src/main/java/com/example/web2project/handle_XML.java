package com.example.web2project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class handle_XML {

    // Method to save book inventory data to XML
    public static void saveDataToXML(String id, String title, String author, String publisher, String edition, String coverType, String category, String floor, String shelfLocation, int quantityOnHand, double unitPrice, String xmlFilePath) throws IOException {
        File xmlFile = new File(xmlFilePath);

        // If the file doesn't exist, create it and add the root element
        if (!xmlFile.exists()) {
            try (FileWriter writer = new FileWriter(xmlFile)) {
                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                writer.write("<bookInventory>\n"); // Start root element
                writer.write("\t<book>\n");
                writer.write("\t\t<id>" + id + "</id>\n");
                writer.write("\t\t<title>" + title + "</title>\n");
                writer.write("\t\t<author>" + author + "</author>\n");
                writer.write("\t\t<publisher>" + publisher + "</publisher>\n");
                writer.write("\t\t<edition>" + edition + "</edition>\n");
                writer.write("\t\t<coverType>" + coverType + "</coverType>\n");
                writer.write("\t\t<category>" + category + "</category>\n");
                writer.write("\t\t<floor>" + floor + "</floor>\n");
                writer.write("\t\t<shelfLocation>" + shelfLocation + "</shelfLocation>\n");
                writer.write("\t\t<quantityOnHand>" + quantityOnHand + "</quantityOnHand>\n");
                writer.write("\t\t<unitPrice>" + unitPrice + "</unitPrice>\n");
                writer.write("\t</book>\n");
                writer.write("</bookInventory>\n"); // End root element
            }
        } else {
            // Read the current content of the file
            StringBuilder content = new StringBuilder();
            try (Scanner scanner = new Scanner(xmlFile)) {
                while (scanner.hasNextLine()) {
                    content.append(scanner.nextLine()).append("\n");
                }
            }

            // Check if the root element is intact
            if (content.indexOf("</bookInventory>") != -1) {
                // Insert the new book entry before the closing </bookInventory> tag
                content.insert(content.lastIndexOf("</bookInventory>"), "\t<book>\n" +
                        "\t\t<id>" + id + "</id>\n" +
                        "\t\t<title>" + title + "</title>\n" +
                        "\t\t<author>" + author + "</author>\n" +
                        "\t\t<publisher>" + publisher + "</publisher>\n" +
                        "\t\t<edition>" + edition + "</edition>\n" +
                        "\t\t<coverType>" + coverType + "</coverType>\n" +
                        "\t\t<category>" + category + "</category>\n" +
                        "\t\t<floor>" + floor + "</floor>\n" +
                        "\t\t<shelfLocation>" + shelfLocation + "</shelfLocation>\n" +
                        "\t\t<quantityOnHand>" + quantityOnHand + "</quantityOnHand>\n" +
                        "\t\t<unitPrice>" + unitPrice + "</unitPrice>\n" +
                        "\t</book>\n");

                // Write the updated content back to the XML file
                try (FileWriter writer = new FileWriter(xmlFile)) {
                    writer.write(content.toString());
                }
            } else {
                throw new IOException("Invalid XML structure: missing root element.");
            }
        }
    }
}
