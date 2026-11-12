package xpathhjlxa4;

import java.nio.file.Path;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class xPathQueryHJLXA4 {
    private static final String[][] QUERIES = {
        {"1", "/orarend/ora[nap='Hétfő']"},
        {"2", "/orarend/ora[tipus='Gyakorlat']"},
        {"3", "/orarend/ora[oktato='Dr. Kovács Anna']"}
    };

    private xPathQueryHJLXA4() {
    }

    public static void main(String[] args) throws Exception {
        Document source = XmlSupport.parse(Path.of("orarendHJLXA42.xml"));
        Document results = source.getImplementation().createDocument(
                null, "xpath-results", null);
        Element root = results.getDocumentElement();
        root.setAttribute("source", "orarendHJLXA42.xml");
        XPath xpath = XPathSupport.newXPath();

        for (String[] query : QUERIES) {
            String number = query[0];
            String expression = query[1];
            NodeList matches = (NodeList) xpath.evaluate(
                    expression, source, XPathConstants.NODESET);
            Element result = results.createElement("query");
            result.setAttribute("number", number);
            result.setAttribute("expression", expression);
            result.setAttribute("matches", Integer.toString(matches.getLength()));
            root.appendChild(result);

            System.out.printf("%n%d) %s%n", Integer.parseInt(number), expression);
            if (matches.getLength() == 0) {
                System.out.println("(nincs találat)");
            }
            for (int index = 0; index < matches.getLength(); index++) {
                Node match = matches.item(index);
                System.out.println(XmlSupport.serialize(match));
                result.appendChild(results.importNode(match, true));
            }
        }

        XmlSupport.write(results, Path.of("orarendHJLXA4-query-results.xml"));
        System.out.println("\nEredményfájl: orarendHJLXA4-query-results.xml");
    }
}
