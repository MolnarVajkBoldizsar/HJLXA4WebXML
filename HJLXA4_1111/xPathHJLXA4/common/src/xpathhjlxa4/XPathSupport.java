package xpathhjlxa4;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

final class XPathSupport {
    private XPathSupport() {
    }

    static XPath newXPath() {
        return XPathFactory.newInstance().newXPath();
    }
}
