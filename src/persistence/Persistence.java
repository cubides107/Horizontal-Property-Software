package persistence;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class Persistence {

    public static Document convertXMLFileToXMLDocument(String fileName) {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();

            //Parse the content to Document object
            Document xmlDocument = builder.parse(new File(fileName));

            return xmlDocument;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<String> readProperties() {
        ArrayList<String> arrayList = new ArrayList();
        Document document = convertXMLFileToXMLDocument("data/Properties.xml");
        NodeList apartment = document.getElementsByTagName("Apartment");
        NodeList house = document.getElementsByTagName("House");
        for (int i = 0; i < apartment.getLength(); i++) {
            Node tempNode = apartment.item(i);
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
                String value = tempNode.getChildNodes().item(1).getTextContent().trim() + "#" + tempNode.getNodeName();
                arrayList.add(value);
            }
        }
        for (int i = 0; i < house.getLength(); i++) {
            Node tempNode = house.item(i);
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
                String value = tempNode.getChildNodes().item(1).getTextContent().trim() + "#" + tempNode.getNodeName();
                arrayList.add(value);
            }
        }
        return arrayList;
    }


    public static DefaultTableModel readUserForTable() {
        Document document = convertXMLFileToXMLDocument("data/HorizontalPropertyUsers.xml");
        NodeList nodeList = document.getDocumentElement().getChildNodes();
        ArrayList<Node> listUsers = new ArrayList();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node tempNode = nodeList.item(i);
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
                listUsers.add(tempNode);
            }
        }

//        searchUserToProperty(listUsers,2);
        ArrayList<String> listPropertiesID = readProperties();

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Conjunto", "Inmueble", "Dueño"});
//        Object[][] matrix = new Object[listUsers.size()][];

//        for (int i = 0; i < listUsers.size(); i++) {
//            ArrayList<String> listProperties = new ArrayList();
//            caseProperty(listUsers.get(i), listProperties);
        for (String ID : listPropertiesID) {
            String[] split = ID.split("#");
//                model.addRow(new Object[]{document.getDocumentElement().getNodeName(), listProperty, listUsers.get(i).getNodeName()});
            String[] strings = searchUserToProperty(listUsers, Integer.parseInt(split[0]));
            if (strings != null) {
                for (int i = 1; i < strings.length; i++) {
                    model.addRow(new Object[]{document.getDocumentElement().getNodeName(), split[1] + " " + split[0], strings[i]});
                }
            }
        }
//        }
        return model;
    }

    private static void caseProperty(Node user, ArrayList listProperties) {
        NodeList childNodes = user.getChildNodes();
        for (int i = 2; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                String value = item.getChildNodes().item(1).getTextContent().trim();
//                listProperties.add(item.getNodeName() + " " + value);
                listProperties.add(value);
            }
        }
    }

    private static String[] searchUserToProperty(ArrayList<Node> listUsers, int idProperty) {
        String result = null;
        for (Node user : listUsers) {
            NodeList listPropertiesUser = user.getChildNodes();
            for (int i = 2; i < listPropertiesUser.getLength(); i++) {
                Node item = listPropertiesUser.item(i);
                if (item.getNodeType() == Node.ELEMENT_NODE) {
                    int value = Integer.parseInt(item.getChildNodes().item(1).getTextContent().trim());
                    if (value == idProperty) {
                        result += "#" + user.getNodeName();
                    }
                }
            }
        }
        if (result == null) {
            return null;
        } else {
            return result.split("#");
        }
    }

    public static void main(String[] args) {
        new Persistence().readUserForTable();
    }
}


