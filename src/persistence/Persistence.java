package persistence;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

public class Persistence {

    public static Document convertXMLFileToXMLDocument(String fileName) {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try
        {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();

            //Parse the content to Document object
            Document xmlDocument = builder.parse(new File(fileName));

            return xmlDocument;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
