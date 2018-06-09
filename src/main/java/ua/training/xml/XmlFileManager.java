package ua.training.xml;

import org.w3c.dom.*;
import ua.training.json.Currency;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlFileManager {
    private String path;

    public XmlFileManager(String path) {
        this.path = path;
    }

    public List<Person> getData() {
        List<Person> result = new ArrayList<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(new File(path));
            doc.getDocumentElement().normalize();
            NodeList notebook = doc.getElementsByTagName("person");
            for (int i = 0; i < notebook.getLength(); i++) {
                Node person = notebook.item(i);
                if (person.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) person;
                    Person p = new Person();
                    p.setId(Long.valueOf(element.getAttribute("id")));
                    p.setName(element.getElementsByTagName("name")
                            .item(0)
                            .getTextContent());
                    p.setAddress(element.getElementsByTagName("address")
                            .item(0)
                            .getTextContent());
                    p.setCash(Integer.valueOf(element.getElementsByTagName("cash")
                            .item(0)
                            .getTextContent()));
                    p.setEducation(element.getElementsByTagName("education")
                            .item(0)
                            .getTextContent());
                    result.add(p);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void saveData(Person[] people) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element root = doc.createElement("catalog");
            doc.appendChild(root);
            Element notebook = doc.createElement("notebook");
            root.appendChild(notebook);

            for (Person p : people) {
                Element person = doc.createElement("person");
                notebook.appendChild(person);
                person.setAttribute("id", String.valueOf(p.getId()));

                Element name = doc.createElement("name");
                name.appendChild(doc.createTextNode(p.getName()));
                person.appendChild(name);

                Element address = doc.createElement("address");
                address.appendChild(doc.createTextNode(p.getAddress()));
                person.appendChild(address);

                Element cash = doc.createElement("cash");
                cash.appendChild(doc.createTextNode(String.valueOf(p.getCash())));
                person.appendChild(cash);

                Element education = doc.createElement("education");
                education.appendChild(doc.createTextNode(p.getEducation()));
                person.appendChild(education);
            }



            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path));
            TransformerFactory.newInstance().newTransformer().transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
