package hjlxa4;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class OrarendModifyHJLXA4 {

    public static void main(String[] args) throws Exception {
        File xmlFile = new File("orarendHJLXA4.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);
        document.getDocumentElement().normalize();

        updateLessonId(document, "o1", "HJLXA4_o1");
        updateLessonId(document, "o2", "HJLXA4_o2");
        updateLessonId(document, "o3", "HJLXA4_o3");

        printDocument(document);
    }

    private static void updateLessonId(Document document, String oldId, String newId) {
        NodeList lessons = document.getElementsByTagName("ora");
        for (int i = 0; i < lessons.getLength(); i++) {
            Element lesson = (Element) lessons.item(i);
            if (oldId.equals(lesson.getAttribute("id"))) {
                lesson.setAttribute("id", newId);
                return;
            }
        }
        throw new IllegalStateException("A(z) " + oldId + " azonosítójú óra nem található.");
    }

    private static void printDocument(Document document) throws Exception {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.transform(new DOMSource(document), new StreamResult(System.out));
    }
}
