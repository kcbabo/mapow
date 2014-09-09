package org.mapow.util;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class XMLHelper {
    
    public static Document parse(String xml) throws Exception {
        return newBuilder().parse(new InputSource(new StringReader(xml)));
    }
    
    public static Document newDocument() throws Exception {
        return newBuilder().newDocument();
    }

    public static DocumentBuilder newBuilder() throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        dbf.setIgnoringElementContentWhitespace(true);
        return dbf.newDocumentBuilder();
    }
    
    public static String xpath(String xml, String xpath) throws Exception {
        XPath xp = XPathFactory.newInstance().newXPath();
        Document doc = parse(xml);
        return xp.evaluate(xpath, doc.getDocumentElement());
    }
}
