package ua.training.parser.impl;

import org.w3c.dom.*;
import ua.training.entity.Person;
import ua.training.parser.Parser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlParser extends Parser<Person> {
    private static final String PERSON = "person";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String CASH = "cash";
    private static final String CATALOG = "catalog";
    private static final String NOTEBOOK = "notebook";

    public XmlParser(File file) {
        super(file);
    }

    @Override
    public List<Person> getData() {
        List<Person> result = new ArrayList<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList notebook = doc.getElementsByTagName(PERSON);
            for (int i = 0; i < notebook.getLength(); i++) {
                Node person = notebook.item(i);
                if (person.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) person;
                    Person p = new Person();
                    p.setId(Long.valueOf(element.getAttribute(ID)));
                    p.setName(element.getElementsByTagName(NAME)
                            .item(0)
                            .getTextContent());
                    p.setAddress(element.getElementsByTagName(ADDRESS)
                            .item(0)
                            .getTextContent());
                    p.setCash(Integer.valueOf(element.getElementsByTagName(CASH)
                            .item(0)
                            .getTextContent()));
                    result.add(p);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void saveData(List<Person> people, File file) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element root = doc.createElement(CATALOG);
            doc.appendChild(root);
            Element notebook = doc.createElement(NOTEBOOK);
            root.appendChild(notebook);

            for (Person p : people) {
                Element person = doc.createElement(PERSON);
                notebook.appendChild(person);
                person.setAttribute(ID, String.valueOf(p.getId()));

                Element name = doc.createElement(NAME);
                name.appendChild(doc.createTextNode(p.getName()));
                person.appendChild(name);

                Element address = doc.createElement(ADDRESS);
                address.appendChild(doc.createTextNode(p.getAddress()));
                person.appendChild(address);

                Element cash = doc.createElement(CASH);
                cash.appendChild(doc.createTextNode(String.valueOf(p.getCash())));
                person.appendChild(cash);
            }
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            TransformerFactory.newInstance().newTransformer().transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
