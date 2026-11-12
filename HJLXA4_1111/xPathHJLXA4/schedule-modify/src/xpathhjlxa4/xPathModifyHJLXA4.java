package xpathhjlxa4;

import java.nio.file.Path;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public final class xPathModifyHJLXA4 {
    private xPathModifyHJLXA4() {
    }

    public static void main(String[] args) throws Exception {
        Document document = XmlSupport.parse(Path.of("orarendHJLXA42.xml"));

        Element secondClass = XmlSupport.requireSingleElement(
                document, "/orarend/ora[@id='ORA-02']/terem");
        secondClass.setTextContent("B-202");

        Element thirdClass = XmlSupport.requireSingleElement(
                document, "/orarend/ora[@id='ORA-03']");
        Element note = document.createElement("megjegyzes");
        note.setTextContent("Konzultáció előzetes egyeztetéssel.");
        thirdClass.appendChild(note);

        Element addedClass = document.createElement("ora");
        addedClass.setAttribute("id", "ORA-06");
        appendTextElement(document, addedClass, "tantargy", "XML konzultáció");
        appendTextElement(document, addedClass, "tipus", "Konzultáció");
        appendTextElement(document, addedClass, "nap", "Péntek");
        appendTextElement(document, addedClass, "kezdet", "12:00");
        appendTextElement(document, addedClass, "veg", "13:00");
        appendTextElement(document, addedClass, "terem", "C-105");
        appendTextElement(document, addedClass, "oktato", "Dr. Kovács Anna");
        document.getDocumentElement().appendChild(addedClass);

        Path output = Path.of("orarendHJLXA41.xml");
        XmlSupport.write(document, output);
        System.out.println(XmlSupport.serialize(document));
        System.out.println("\nMódosított órarend: " + output);
    }

    private static void appendTextElement(
            Document document, Element parent, String name, String value) {
        Element element = document.createElement(name);
        element.setTextContent(value);
        parent.appendChild(element);
    }
}
