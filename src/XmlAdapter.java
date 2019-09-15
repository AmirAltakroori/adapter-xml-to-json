
import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XmlAdapter extends NodeFiles {

    private XmlFiles xmlFile;

    public XmlAdapter(XmlFiles xmlFile) {
        this.xmlFile = xmlFile;
    }

    @Override
    public File getJsonFile() {
        File file = new File("Asdas");
        return file;
    }

    private JSONObject parsingDoc (Document doc){
        JSONObject nodeDetails = new JSONObject();

        NodeList nodes = doc.getElementsByTagName(doc.getDocumentElement().getNodeName());

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element note = (Element) node;
                NodeList contentList = note.getChildNodes();
                for (int j = 0; j < contentList.getLength(); j++) {
                    Node n = contentList.item(j);
                    if (n.getNodeType() == Node.ELEMENT_NODE) {
                        Element e = (Element) n;
                        nodeDetails.put(e.getTagName(),e.getTextContent());
                    }
                }
            }
        }

        return (JSONObject) new JSONObject().put(doc.getDocumentElement().getNodeName(),nodeDetails);
    }

    public File parseFile() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {
            builder = factory.newDocumentBuilder();

            Document doc = builder.parse(xmlFile.getXmlFile());
            doc.getDocumentElement().normalize();

            JSONObject jsonObject = parsingDoc(doc);


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
