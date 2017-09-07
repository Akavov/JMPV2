package sax;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Ars on 08.09.2017.
 */
public class SimpleStudentHandler extends DefaultHandler {
    @Override
    public void startDocument(){
        System.out.println("Parsing started");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        String s=localName;
        //gettin and output info about attributes of element
        for (int i = 0; i < attrs.getLength(); i++) {
            s += " " + attrs.getLocalName(i) + "=" + attrs.getValue(i);
        }
        System.out.println(s.trim());
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        System.out.println(new String(ch,start,length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        System.out.println(localName);
    }

    @Override
    public void endDocument() {
        System.out.println("\nParsing ended");
    }
}
