package org.example;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        ArrayList<Book> books = readBooksFromXml("src/main/resources/books.xml");

        int choice = -1;
        while (choice != 0) {
            switch (choice) {
                case 1 -> kiir(books);
                case 2 -> hozaad(books);
                case 3 -> modosit(books);
                case 4 -> torol(books);
                case 5 -> saveBooksToXml(books, "src/main/resources/books.xml");
            }
            System.out.println("1 - Könyvek listája\n2 - Új könyv hozzáadása\n3 - Meglévő könyv módosítása\n4 - Könyv törlése\n5 - Mentés\n0 - Mentés és kilépés\n");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice < 0 || choice > 5) {
                    System.out.println("Nem érvényes opció");
                }
            } catch (InputMismatchException e) {
                System.out.println("Nem érvényes opció");
                scanner.nextLine();
            }
        }

        saveBooksToXml(books, "src/main/resources/books.xml");


    }




//xml olvas
    public static ArrayList<Book> readBooksFromXml(String filepath) {
        ArrayList<Book> books = new ArrayList<>();
        try {
            DocumentBuilderFactory documentBuilderFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder =
                    documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(filepath);

            Element rootElement = document.getDocumentElement();

            NodeList childNodesList = rootElement.getChildNodes();

            int numberOfElementNodes = 0;
            Node node;
            for (int i = 0; i < childNodesList.getLength(); i++) {
                node = childNodesList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    numberOfElementNodes++;
                    NodeList childNodesOfBooksTag = node.getChildNodes();
                    String id = "", szerzo = "", cim = "", oldal = "", borito = "";
                    for (int j = 0; j < childNodesOfBooksTag.getLength(); j++) {

                        if (childNodesOfBooksTag.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            switch (childNodesOfBooksTag.item(j).getNodeName()) {
                                case "id" -> id = childNodesOfBooksTag.item(j).getTextContent();
                                case "szerzo" -> szerzo = childNodesOfBooksTag.item(j).getTextContent();
                                case "cim" -> cim = childNodesOfBooksTag.item(j).getTextContent();
                                case "oldal" -> oldal = childNodesOfBooksTag.item(j).getTextContent();
                                case "borito" -> borito = childNodesOfBooksTag.item(j).getTextContent();
                            }
                        }
                    }
                    books.add(new Book(Integer.parseInt(id), szerzo, cim,
                            Integer.parseInt(oldal), Borito.valueOf(borito) ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    // könyvek kiirása

    private static void kiir(ArrayList<Book> books) {
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getId() + " " + books.get(i).getSzerzo() + " " + books.get(i).getCim() + " " + books.get(i).getOldal() + " " + books.get(i).getBorito() + "\n");
        }
    }

//új könyv hozzáadása
private static void hozaad(ArrayList<Book> books) {
    Book book = new Book(books.size());
    books.add(book);
}


//meglévő könyv módosítása
    private static void modosit(ArrayList<Book> books) {
        int id;
        try {System.out.print("Add meg a módósítandó könyv id-jét: ");
            id = scanner.nextInt();
            scanner.nextLine();
        }
        catch (InputMismatchException ex) {
            //ex.printStackTrace();
            scanner.nextLine();
            System.out.println("Érvénytelen könyvid\n");
            return;
        }
        for (int i=0; i<books.size(); i++ ) {
            if (books.get(i).getId()==id) {
                books.get(i).modosit();
            } else { System.out.println("Nincs ilyen azonosítójú könyv\n");}
        }
    }

// könyv törlése
    private static void torol(ArrayList<Book> books) {
        int id;
        System.out.print("add meg a módósítandó könyv id-jét: ");
        try {id = scanner.nextInt();
        }
        catch (InputMismatchException ex) {
            //ex.printStackTrace();
            scanner.nextLine();
            System.out.println("Érvénytelen könyvid\n");
            return;
        }
        for (int i=0; i<books.size(); i++ ) {
            if (books.get(i).getId()==id) {
                books.remove(i);
                while (i < books.size()){
                    books.get(i).setId(books.get(i).getId()-1);
                    i++;
                }
                System.out.println("Sikerse törlés\n");
                return;
            }
        }
        System.out.println("Nincs ilyen azonosítójú könyv\n");
    }

//mentés
    public static void saveBooksToXml(ArrayList<Book> books, String filepath) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element rootElement = document.createElement("Books");
            document.appendChild(rootElement);

            for (Book book : books) {
                Element bookElement = document.createElement("book");
                rootElement.appendChild(bookElement);
                createChildElement(document, bookElement, "id", String.valueOf(book.getId()));
                createChildElement(document, bookElement, "szerzo", book.getSzerzo());
                createChildElement(document, bookElement, "cim", book.getCim());
                createChildElement(document, bookElement, "oldal", String.valueOf(book.getOldal()));
                createChildElement(document, bookElement, "borito", String.valueOf(book.getBorito()));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileOutputStream(filepath));

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            System.out.println("Sikeres mentés\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void createChildElement(Document document, Element parent,
                                           String tagName, String value) {
        Element element = document.createElement(tagName);
        element.setTextContent(value);
        parent.appendChild(element);
    }
}