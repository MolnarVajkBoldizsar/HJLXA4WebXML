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

    public static void main(String[] args) throws Exception {
        File xmlFile = new File("VendeglatasHJLXA4.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);
        document.getDocumentElement().normalize();

        updateGuest(document);
        updateInternAttribute(document);
        removeOrders(document);
        printDocument(document);
    }

    private static void updateGuest(Document document) {
        NodeList guests = document.getElementsByTagName("Vendeg");
        for (int i = 0; i < guests.getLength(); i++) {
            Element guest = (Element) guests.item(i);
            if ("V1".equals(guest.getElementsByTagName("Vkod").item(0).getTextContent().trim())) {
                guest.getElementsByTagName("Nev").item(0).setTextContent("Kiss Bela HJLXA4");
                guest.getElementsByTagName("Eletkor").item(0).setTextContent("35");
                return;
            }
        }
        throw new IllegalStateException("A V1 azonosítójú vendég nem található.");
    }

    private static void updateInternAttribute(Document document) {
        NodeList interns = document.getElementsByTagName("Gyakornok");
        for (int i = 0; i < interns.getLength(); i++) {
            Element intern = (Element) interns.item(i);
            if ("G3".equals(intern.getElementsByTagName("Gykod").item(0).getTextContent().trim())) {
                intern.setAttribute("e_gy", "e3");
                return;
            }
        }
        throw new IllegalStateException("A G3 azonosítójú gyakornok nem található.");
    }

    private static void removeOrders(Document document) {
        NodeList orders = document.getElementsByTagName("Rendeles");
        while (orders.getLength() > 0) {
            Node order = orders.item(0);
            order.getParentNode().removeChild(order);
        }
    }

    private static void printDocument(Document document) throws Exception {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.transform(new DOMSource(document), new StreamResult(System.out));
    }
}
