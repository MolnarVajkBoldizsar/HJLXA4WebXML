package domneptunkod;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DOMReadNeptunkod {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("orarendNeptunkod.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList oraList = document.getElementsByTagName("ora");

            System.out.println("=== Órarend ===");
            for (int i = 0; i < oraList.getLength(); i++) {
                Element ora = (Element) oraList.item(i);

                String id = ora.getAttribute("id");
                String tipus = ora.getAttribute("tipus");
                String targy = getTextContent(ora, "targy");
                String nap = getTextContent(ora, "idopont", "nap");
                String tol = getTextContent(ora, "idopont", "tol");
                String ig = getTextContent(ora, "idopont", "ig");
                String helyszin = getTextContent(ora, "helyszin");
                String oktato = getTextContent(ora, "oktato");
                String szak = getTextContent(ora, "szak");

                System.out.println("\n--- Óra " + (i + 1) + " ---");
                System.out.println("ID: " + id);
                System.out.println("Típus: " + tipus);
                System.out.println("Tárgy: " + targy);
                System.out.println("Nap: " + nap);
                System.out.println("Időpont: " + tol + " - " + ig);
                System.out.println("Helyszín: " + helyszin);
                System.out.println("Oktató: " + oktato);
                System.out.println("Szak: " + szak);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getTextContent(Element parent, String tagName) {
        NodeList list = parent.getElementsByTagName(tagName);
        if (list.getLength() == 0) {
            return "";
        }
        return list.item(0).getTextContent();
    }

    private static String getTextContent(Element parent, String parentTag, String childTag) {
        NodeList parentList = parent.getElementsByTagName(parentTag);
        if (parentList.getLength() == 0) {
            return "";
        }

        Element timeElement = (Element) parentList.item(0);
        NodeList childList = timeElement.getElementsByTagName(childTag);
        if (childList.getLength() == 0) {
            return "";
        }

        return childList.item(0).getTextContent();
    }
}
