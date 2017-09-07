import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import sun.rmi.runtime.Log;

import java.io.IOException;


/**
 * Created by Ars on 07.09.2017.
 */
public class StudentErrorHandler extends DefaultHandler {
    //create error register
    private Logger logger=Logger.getLogger("");

    public StudentErrorHandler(String log) throws IOException {
        //set file and format for errors
        logger.addAppender(new FileAppender(new SimpleLayout(), log));
    }

    public void warning(SAXParseException e) {
        logger.warn(getLineAddress(e)+"-"+e.getMessage());

    }

    public void error(SAXParseException e) {
        logger.error(getLineAddress(e) + " - " + e.getMessage());
    }

    public void fatalError(SAXParseException e) {
        logger.fatal(getLineAddress(e) + " : " + e.getColumnNumber());
    }

    public String getLineAddress(SAXParseException e) {
        return e.getLineNumber()+" : "+e.getColumnNumber();
    }
}
