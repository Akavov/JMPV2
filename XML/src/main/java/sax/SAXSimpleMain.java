package sax;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

/**
 * Created by Ars on 08.09.2017.
 */
public class SAXSimpleMain {
    public static void main(String[] args) {
        try {
            //create SAX analyzator
            XMLReader reader = XMLReaderFactory.createXMLReader();
            SimpleStudentHandler handler = new SimpleStudentHandler();
            reader.setContentHandler(handler);
            reader.parse("src/main/resources/students.xml");

        } catch (SAXException e) {
            System.err.println("SAX parser error " + e);
        } catch (IOException e) {
            System.err.println("I/O error "+e);
        }
    }
}
