import Parser.DOM.DomXmlParser;
import University.University;
import java.io.*;

public class Main {
    public static void main(String[]args) throws Exception {
      System.out.println("Started;");

      University university = new University();
      DomXmlParser xmlParser = new DomXmlParser(university);
      xmlParser.loadXMLDocument("src/main/resources/University.xml");
      university.addStudent("IPS-32","Gleb Pilipets",20);
      university.addGroup("K-28",2);
      university.addStudent("K-28","Ivanov Yevhen",30);
      university.deleteStudent("Blahii Andrii");
      xmlParser.saveXMLDocument("src/main/resources/UniversityNew.xml");

      System.out.println("Finished;");
    }
}
