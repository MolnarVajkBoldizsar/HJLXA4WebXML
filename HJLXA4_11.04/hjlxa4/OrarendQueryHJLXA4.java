package hjlxa4;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class OrarendQueryHJLXA4 {

    public static void main(String[] args) throws Exception {
        File xmlFile = new File("orarendHJLXA4.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);
        document.getDocumentElement().normalize();

        queryClassesForDay(document, "Hetfo");
        queryClassesForInstructor(document, "Agárdi Anita");
        countClassesByType(document);
    }

    private static void queryClassesForDay(Document document, String day) {
        System.out.println("1. " + day + "i órák:");
        NodeList classes = document.getElementsByTagName("ora");
        int matches = 0;

        for (int i = 0; i < classes.getLength(); i++) {
            Element lesson = (Element) classes.item(i);
            Element time = childElement(lesson, "idopont");
            if (day.equals(childText(time, "nap"))) {
                System.out.printf("  %s-%s | %s | %s%n",
                        childText(time, "tol"),
                        childText(time, "ig"),
                        childText(lesson, "targy"),
                        lesson.getAttribute("tipus"));
                matches++;
            }
        }

        System.out.println("  Találatok: " + matches);
    }

    private static void queryClassesForInstructor(Document document, String instructor) {
        System.out.println("\n2. " + instructor + " órái:");
        NodeList classes = document.getElementsByTagName("ora");
        int matches = 0;

        for (int i = 0; i < classes.getLength(); i++) {
            Element lesson = (Element) classes.item(i);
            if (instructor.equals(childText(lesson, "oktato"))) {
                Element time = childElement(lesson, "idopont");
                System.out.printf("  %s %s-%s | %s | %s%n",
                        childText(time, "nap"),
                        childText(time, "tol"),
                        childText(time, "ig"),
                        childText(lesson, "targy"),
                        childText(lesson, "helyszin"));
                matches++;
            }
        }

        System.out.println("  Találatok: " + matches);
    }

    private static void countClassesByType(Document document) {
        System.out.println("\n3. Órák száma típusonként:");
        NodeList classes = document.getElementsByTagName("ora");
        int lectures = 0;
        int practices = 0;

        for (int i = 0; i < classes.getLength(); i++) {
            Element lesson = (Element) classes.item(i);
            String type = lesson.getAttribute("tipus");
            if ("eloadas".equals(type)) {
                lectures++;
            } else if ("gyakorlat".equals(type)) {
                practices++;
            }
        }

        System.out.println("  Előadás: " + lectures);
        System.out.println("  Gyakorlat: " + practices);
        System.out.println("  Összesen: " + classes.getLength());
    }

    private static Element childElement(Element parent, String name) {
        NodeList children = parent.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE
                    && name.equals(child.getNodeName())) {
                return (Element) child;
            }
        }
        throw new IllegalStateException(
                "A(z) " + parent.getTagName() + " elemben nem található: " + name);
    }

    private static String childText(Element parent, String name) {
        return childElement(parent, name).getTextContent().trim();
    }
}
