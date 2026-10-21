package neptunkod;

import java.io.File;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMQueryHJLXA4 {

    private static final String XML_FILE = "HJLXA4_XML.xml";

    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(XML_FILE));
        document.getDocumentElement().normalize();

        printMatchingElements(
                document, "szakacs", "Szakácsok szakközépiskolai végzettséggel",
                element -> containsText(element, "vegzettseg", "szakközépiskola"));
        printMatchingElements(
                document, "etterem", "Ötcsillagos éttermek",
                element -> "5".equals(getChildText(element, "csillag")));
        printMatchingElements(
                document, "gyakornok", "Délutáni műszakra beosztott gyakornokok",
                element -> containsText(element, "muszak", "délután"));
    }

    private static void printMatchingElements(
            Document document, String tagName, String title, ElementMatcher matcher) {
        System.out.println("\n=== " + title + " ===");
        NodeList elements = document.getElementsByTagName(tagName);
        boolean found = false;

        for (int i = 0; i < elements.getLength(); i++) {
            Element element = (Element) elements.item(i);
            if (matcher.matches(element)) {
                found = true;
                printElement(element, 0);
                System.out.println();
            }
        }

        if (!found) {
            System.out.println("Nincs találat.");
        }
    }

    private static boolean containsText(Element parent, String tagName, String expected) {
        NodeList values = parent.getElementsByTagName(tagName);
        String expectedLower = expected.toLowerCase(Locale.ROOT);
        for (int i = 0; i < values.getLength(); i++) {
            String value = values.item(i).getTextContent().trim().toLowerCase(Locale.ROOT);
            if (value.contains(expectedLower)) {
                return true;
            }
        }
        return false;
    }

    private static String getChildText(Element parent, String tagName) {
        NodeList values = parent.getElementsByTagName(tagName);
        if (values.getLength() == 0) {
            return "";
        }
        return values.item(0).getTextContent().trim();
    }

    private static void printElement(Element element, int depth) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indent.append("  ");
        }

        NodeList children = element.getChildNodes();
        boolean hasElementChild = false;
        for (int i = 0; i < children.getLength(); i++) {
            if (children.item(i).getNodeType() == Node.ELEMENT_NODE) {
                hasElementChild = true;
                break;
            }
        }

        if (hasElementChild) {
            System.out.println(indent + element.getTagName());
        } else {
            System.out.println(indent + element.getTagName() + ": "
                    + element.getTextContent().trim());
        }

        for (int i = 0; i < element.getAttributes().getLength(); i++) {
            Node attribute = element.getAttributes().item(i);
            System.out.println(indent + "  @" + attribute.getNodeName() + ": "
                    + attribute.getNodeValue());
        }

        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                printElement((Element) child, depth + 1);
            }
        }
    }

    private interface ElementMatcher {
        boolean matches(Element element);
    }
}
