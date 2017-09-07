import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by Ars on 07.09.2017.
 */
public class ValidatorSAX {
    public static void main(String[] args) {
        String filename = "resources/students.xml";
        String schemaname = "resources/students.xsd";
        String logname = "logs/log.txt";
        Schema schema=null;
        String language= XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        try {
            //set checking with XSD
            schema = factory.newSchema(new File(schemaname));
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setSchema(schema);
            //creating parser-object
            SAXParser parser = spf.newSAXParser();
            //set handler for error and launch
            parser.parse(filename, new StudentErrorHandler(logname));
            System.out.println(filename + " is valid");

        } catch (ParserConfigurationException e) {
            System.err.println(filename + " config error: " + e.getMessage());
        } catch (SAXException e) {
            System.err.println(filename + " SAX error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: "+e.getMessage());
        }
    }
}
