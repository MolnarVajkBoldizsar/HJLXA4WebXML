package xpathhjlxa4;

import java.nio.file.Path;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public final class xPathModifyHJLXA4 {
    private xPathModifyHJLXA4() {
    }

    public static void main(String[] args) throws Exception {
        Document document = XmlSupport.parseAndValidate(
                Path.of("studentHJLXA4.xml"), Path.of("studentHJLXA4.xsd"));
        Element student = XmlSupport.requireSingleElement(
                document, "/class/student[@id='01']");
        XmlSupport.requireSingleElement(
                student, "./keresztnev").setTextContent("HJLXA4");

        System.out.println("Módosított hallgató:");
        System.out.println(XmlSupport.serialize(student));
    }
}
