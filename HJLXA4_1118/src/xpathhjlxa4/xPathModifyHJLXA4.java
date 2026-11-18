package xpathhjlxa4;

import java.nio.file.Path;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public final class xPathModifyHJLXA4 {
    private xPathModifyHJLXA4() {
    }

    public static void main(String[] args) throws Exception {
        Path input = XmlSupport.inputPath(args);
        Path output = XmlSupport.outputPath(args, "orarendHJLXA43.xml");
        Path submissionOutput = Path.of("orarendHJLXA41.xml");
        Document document = XmlSupport.parse(input);
        XPath xpath = XPathFactory.newInstance().newXPath();
        Node root = (Node) xpath.evaluate("/MVB_orarend", document, XPathConstants.NODE);
        if (root == null) {
            throw new IllegalArgumentException("Az XML nem tartalmaz MVB_orarend gyökérelemet.");
        }

        // The metadata changes leave the original timetable entries untouched.
        root.appendChild(metadata(document, "neptunKod", "HJLXA4"));
        root.appendChild(metadata(document, "feldolgozo", "xPathModifyHJLXA4"));
        root.appendChild(metadata(document, "adatforras", input.getFileName().toString()));

        System.out.println("Három XPath-módosítás végrehajtva:");
        System.out.println("  neptunKod = HJLXA4");
        System.out.println("  feldolgozo = xPathModifyHJLXA4");
        System.out.println("  adatforras = " + input.getFileName());
        System.out.println("Módosított, strukturált XML:");
        XmlSupport.print(document);

        XmlSupport.write(document, output);
        System.out.println("A módosított XML mentve: " + output);
        if (!output.toAbsolutePath().normalize()
                .equals(submissionOutput.toAbsolutePath().normalize())) {
            XmlSupport.write(document, submissionOutput);
            System.out.println("A beadandó XML mentve: " + submissionOutput);
        }
    }

    private static Element metadata(Document document, String name, String value) {
        Element element = document.createElement(name);
        element.setTextContent(value);
        return element;
    }
}
