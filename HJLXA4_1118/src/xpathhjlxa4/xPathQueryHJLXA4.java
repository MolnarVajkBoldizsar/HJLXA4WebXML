package xpathhjlxa4;

import java.nio.file.Path;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public final class xPathQueryHJLXA4 {
    private static final String[][] QUERIES = {
        {"Előadások", "/MVB_orarend/ora[@tipus='eloadas']"},
        {"Szerdai órák", "/MVB_orarend/ora[idopont/nap='Szerda']"},
        {"Dr. Bednarik László órái", "/MVB_orarend/ora[oktato='Dr. Bednarik László']"}
    };

    private xPathQueryHJLXA4() {
    }

    public static void main(String[] args) throws Exception {
        Path input = XmlSupport.inputPath(args);
        Path output = XmlSupport.outputPath(args, "orarendHJLXA42.xml");
        Document source = XmlSupport.parse(input);
        Document results = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .newDocument();
        Element root = results.createElement("XPathLekerdezesek");
        root.setAttribute("forras", input.getFileName().toString());
        results.appendChild(root);

        XPath xpath = XPathFactory.newInstance().newXPath();
        for (String[] query : QUERIES) {
            NodeList matches = (NodeList) xpath.evaluate(
                    query[1], source, XPathConstants.NODESET);
            Element result = results.createElement("lekérdezés");
            result.setAttribute("nev", query[0]);
            result.setAttribute("darab", Integer.toString(matches.getLength()));
            Element expression = results.createElement("xPath");
            expression.setTextContent(query[1]);
            result.appendChild(expression);

            System.out.printf("%s (%d találat)%n", query[0], matches.getLength());
            for (int i = 0; i < matches.getLength(); i++) {
                org.w3c.dom.Element match = (org.w3c.dom.Element) matches.item(i);
                result.appendChild(results.importNode(match, true));
                System.out.printf("  %s: %s, %s %s-%s, %s%n",
                        match.getAttribute("id"),
                        match.getElementsByTagName("targy").item(0).getTextContent(),
                        match.getElementsByTagName("nap").item(0).getTextContent(),
                        match.getElementsByTagName("tol").item(0).getTextContent(),
                        match.getElementsByTagName("ig").item(0).getTextContent(),
                        match.getElementsByTagName("helyszin").item(0).getTextContent());
            }
            root.appendChild(result);
        }

        XmlSupport.write(results, output);
        System.out.println("A strukturált lekérdezési eredmény mentve: " + output);
    }
}
