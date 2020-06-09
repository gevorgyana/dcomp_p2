package Parser.DOM;

import Parser.XmlFileValidator;
import University.Group;
import University.University;
import University.Student;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class DomXmlParser {
  private static final String pathToValidator = "src/main/resources/University.xsd";
  private Logger logger = Logger.getLogger(University.class.getName());
  private University university;

  public DomXmlParser(University university) {
    this.university = university;
  }

  public void loadXMLDocument(String pathToXML) throws Exception {
    //if(XmlFileValidator.validateXMLDocument(pathToXML,pathToValidator)) {
    DocumentBuilderFactory dbf = null;
    DocumentBuilder documentBuilder = null;
    try {
      dbf = DocumentBuilderFactory.newInstance();
      documentBuilder = dbf.newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      logger.info(e.getMessage());
    }
    Document document = null;
    try {
      document = documentBuilder.parse(new File(pathToXML));
    } catch (SAXException e) {
      logger.info(e.getMessage());
    } catch (IOException e) {
      logger.info(e.getMessage());
    }

    Element root = document.getDocumentElement();

    if (root.getTagName().equals("university")) {
      NodeList listGroups = root.getElementsByTagName("group");
      for (int i = 0; i < listGroups.getLength(); i++) {
        Element group = (Element) listGroups.item(i);
        String groupName = group.getAttribute("name");
        String groupCourse = group.getAttribute("course");
        Group universityGroup = new Group(groupName, Integer.parseInt(groupCourse));
        university.addGroup(universityGroup);
        NodeList listStudents = group.getElementsByTagName("student");
        for (int j = 0; j < listStudents.getLength(); j++) {
          Element student = (Element) listStudents.item(j);
          String name = student.getAttribute("name");
          String age = student.getAttribute("age");
          university.addStudent(universityGroup, name, Integer.parseInt(age));
        }
      }
    }
    //}
    /*else{
      throw new Exception("Validating error");
      }*/
  }


  public void saveXMLDocument(String pathToXML) {
    DocumentBuilderFactory dbf = null;
    DocumentBuilder documentBuilder = null;
    Document document = null;
    dbf = DocumentBuilderFactory.newInstance();
    try {
      documentBuilder = dbf.newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      logger.info(e.getMessage());
    }
    document = documentBuilder.newDocument();

    Element root = document.createElement("university");
    document.appendChild(root);

    ArrayList<Group> groups = university.getGroups();
    for (Group group : groups) {
      Element groupElem = document.createElement("group");
      groupElem.setAttribute("name", group.getName());
      groupElem.setAttribute("course", String.valueOf(group.getCourse()));
      root.appendChild(groupElem);
      ArrayList<Student> groupStudents = university.getStudents(group.getName());
      for (Student student : groupStudents) {
        Element studentElem = document.createElement("student");
        studentElem.setAttribute("name", student.getName());
        studentElem.setAttribute("age", String.valueOf(student.getAge()));
        groupElem.appendChild(studentElem);
      }
    }

    Transformer tr = null;
    try {
      tr = TransformerFactory.newInstance().newTransformer();
    } catch (TransformerConfigurationException e) {
      logger.info(e.getMessage());
    }
    tr.setOutputProperty(OutputKeys.INDENT, "yes");
    tr.setOutputProperty(OutputKeys.METHOD, "xml");
    tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
    tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
    // send DOM to file
    try {
      tr.transform(new DOMSource(document),
                   new StreamResult(new FileOutputStream(pathToXML)));
    } catch (TransformerException e) {
      logger.info(e.getMessage());
    } catch (FileNotFoundException e) {
      logger.info(e.getMessage());
    }

  }
}
