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
        {"1", "/class/student"},
        {"2", "/class/student[@id='02']"},
        {"3", "//student"},
        {"4", "/class/student[2]"},
        {"5", "/class/student[last()]"},
        {"6", "/class/student[last()-1]"},
        {"7", "/class/student[position() <= 2]"},
        {"8", "/class/*"},
        {"9", "//student[@*]"},
        {"10", "//*"},
        {"11", "/class/student[kor > 20]"},
        {"12", "/class/student/*[self::keresztnev or self::vezeteknev]"}
    };

    private xPathQueryHJLXA4() {
    }

    public static void main(String[] args) throws Exception {
        Document source = XmlSupport.parseAndValidate(
                Path.of("studentHJLXA4.xml"), Path.of("studentHJLXA4.xsd"));
        Document results = source.getImplementation().createDocument(
                null, "xpath-results", null);
        Element root = results.getDocumentElement();
        root.setAttribute("source", "studentHJLXA4.xml");
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

        XmlSupport.write(results, Path.of("student1HJLXA4.xml"));
        System.out.println("\nEredményfájl: student1HJLXA4.xml");
    }
}
