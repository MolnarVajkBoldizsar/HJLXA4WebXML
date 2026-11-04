package hjlxa4;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class OrarendReadHJLXA4 {

    public static void main(String[] args) throws Exception {
        File xmlFile = new File("orarendHJLXA4.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);
        document.getDocumentElement().normalize();

        NodeList lessons = document.getElementsByTagName("ora");
        for (int i = 0; i < lessons.getLength(); i++) {
            printLesson((Element) lessons.item(i));
        }
    }

    private static void printLesson(Element lesson) {
        System.out.println("Aktuális elem: " + lesson.getTagName());
        System.out.println("  Óra ID: " + lesson.getAttribute("id"));
        System.out.println("  Típus: " + lesson.getAttribute("tipus"));

        NodeList children = lesson.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                printElement((Element) child, 1);
            }
        }
        System.out.println();
    }

    private static void printElement(Element element, int depth) {
        String indent = "  ".repeat(depth);
        NodeList children = element.getChildNodes();
        boolean hasElementChild = false;

        for (int i = 0; i < children.getLength(); i++) {
            if (children.item(i).getNodeType() == Node.ELEMENT_NODE) {
                hasElementChild = true;
                break;
            }
        }

        String label = labelFor(element.getTagName());
        if (hasElementChild) {
            System.out.println(indent + label + ":");
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    printElement((Element) child, depth + 1);
                }
            }
        } else {
            System.out.println(indent + label + ": " + element.getTextContent().trim());
        }
    }

    private static String labelFor(String tagName) {
        switch (tagName) {
            case "targy":
                return "Tárgy";
            case "idopont":
                return "Időpont";
            case "nap":
                return "Nap";
            case "tol":
                return "Órától";
            case "ig":
                return "Óráig";
            case "helyszin":
                return "Helyszín";
            case "oktato":
                return "Oktató";
            case "szak":
                return "Szak";
            default:
                return tagName;
        }
    }
}
