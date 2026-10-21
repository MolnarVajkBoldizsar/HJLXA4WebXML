package hjlxa4;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMModifyHJLXA4 {

    private static final String XML_FILE = "HJLXA4_XML.xml";

    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(XML_FILE));
        document.getDocumentElement().normalize();

        updateGuest(document);
        updateInternAttribute(document);
        removeOrders(document);
        printDocument(document);
    }

    private static void updateGuest(Document document) {
        NodeList guests = document.getElementsByTagName("vendeg");
        for (int i = 0; i < guests.getLength(); i++) {
            Element guest = (Element) guests.item(i);
            String id = guest.hasAttribute("Vkod")
                    ? guest.getAttribute("Vkod") : guest.getAttribute("v1");
            if ("V1".equalsIgnoreCase(id) || "v1".equalsIgnoreCase(id)) {
                setChildText(guest, "nev", "Teszt Vendég");
                String ageTag = guest.getElementsByTagName("kor").getLength() > 0
                        ? "kor" : "eletkor";
                setChildText(guest, ageTag, "35");
                return;
            }
        }
        throw new IllegalStateException("A V1 vendég nem található az XML-ben.");
    }

    private static void updateInternAttribute(Document document) {
        NodeList interns = document.getElementsByTagName("gyakornok");
        if (interns.getLength() == 0) {
            throw new IllegalStateException("Gyakornok elem nem található az XML-ben.");
        }

        Element intern = (Element) interns.item(0);
        if (intern.hasAttribute("e_gy")) {
            intern.setAttribute("e_gy", "e3");
        } else if (intern.hasAttribute("Gykod")) {
            intern.setAttribute("Gykod", "e3");
        } else {
            throw new IllegalStateException(
                    "A gyakornok e_gy attribútuma nem található az XML-ben.");
        }
    }

    private static void setChildText(Element parent, String tagName, String value) {
        NodeList elements = parent.getElementsByTagName(tagName);
        if (elements.getLength() == 0) {
            throw new IllegalStateException(
                    "A " + tagName + " elem nem található a vendég adatai között.");
        }
        elements.item(0).setTextContent(value);
    }

    private static void removeOrders(Document document) {
        NodeList orders = document.getElementsByTagName("rendeles");
        while (orders.getLength() > 0) {
            Node order = orders.item(0);
            order.getParentNode().removeChild(order);
        }
    }

    private static void printDocument(Document document) throws Exception {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(
                "{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.transform(new DOMSource(document), new StreamResult(System.out));
    }
}
