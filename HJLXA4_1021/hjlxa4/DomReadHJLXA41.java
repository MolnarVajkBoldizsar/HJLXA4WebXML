package hjlxa4;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomReadHJLXA41 {

    public static void main(String[] args) throws Exception {
        File xmlFile = new File("orarendHJLXA4.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);
        document.getDocumentElement().normalize();

        printElement(document.getDocumentElement(), 0);
    }

    private static void printElement(Element element, int depth) {
        StringBuilder indentBuilder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indentBuilder.append("  ");
        }
        String indent = indentBuilder.toString();
        NodeList children = element.getChildNodes();

        boolean hasElementChild = false;
        for (int i = 0; i < children.getLength(); i++) {
            if (children.item(i).getNodeType() == Node.ELEMENT_NODE) {
                hasElementChild = true;
                break;
            }
        }

        String text = element.getTextContent().trim();
        if (!hasElementChild) {
            System.out.println(indent + element.getTagName() + ": " + text);
            printAttributes(element, indent + "  ");
            return;
        }

        System.out.println(indent + element.getTagName());
        printAttributes(element, indent + "  ");

        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                printElement((Element) child, depth + 1);
            }
        }
    }

    private static void printAttributes(Element element, String indent) {
        NamedNodeMap attributes = element.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            Node attribute = attributes.item(i);
            System.out.println(indent + "@" + attribute.getNodeName() + ": "
                    + attribute.getNodeValue());
        }
    }
}
