package Server;

import Client.Query;
import Client.QueryType;
import DatabaseConnection.UniversityDatabaseConnection;
import University.Group;
import University.Student;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.*;
import java.util.ArrayList;

public class Server {
    //URL of the JMS server. DEFAULT_BROKER_URL will just mean that JMS server is on localhost
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    // default broker URL is : tcp://localhost:61616"
    private static String subject = "DatabaseQueries";
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageProducer producer;
    private MessageConsumer consumer;

    public void start() throws IOException, ClassNotFoundException, JMSException {
        // Getting JMS connection from the server
        connectionFactory = new ActiveMQConnectionFactory(url);
        connectionFactory.setTrustAllPackages(true);
        connection = connectionFactory.createConnection();
        connection.start();
        //Creating a non transactional session to send/receive JMS message.
        session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);
        //Destination represents here our queue 'DatabaseQueries' on the JMS server.
        //The queue will be created automatically on the server.
        destination = session.createQueue(subject);
        // MessageProducer is used for sending messages to the queue.
        producer = session.createProducer(destination);
        // MessageConsumer is used for receiving (consuming) messages
        consumer = session.createConsumer(destination);
        while (true) {
            processQuery();
        }
    }

    private void processQuery() throws JMSException {
        Query query = (Query) ((ObjectMessage) consumer.receive()).getObject();
        QueryType operation = query.queryType;
        ObjectMessage answerMessage = null;
        if (operation.equals(QueryType.addGroup)) {
            Boolean result = addGroup(query.groupName, query.groupCourse);
            answerMessage = session.createObjectMessage(result);
        } else if (operation.equals(QueryType.addStudent)) {
            Boolean result = addStudent(query.studentFirstName, query.studentLastName, query.studentAge, query.groupName);
            answerMessage = session.createObjectMessage(result);
        } else if (operation.equals(QueryType.deleteGroup)) {
            Boolean result = deleteGroup(query.groupName);
            answerMessage = session.createObjectMessage(result);
        } else if (operation.equals(QueryType.deleteStudent)) {
            Boolean result = deleteStudent(query.studentID);
            answerMessage = session.createObjectMessage(result);
        } else if (operation.equals(QueryType.changeGroupForStudent)) {
            Boolean result = changeGroupForStudent(query.studentID, query.newGroupID);
            answerMessage = session.createObjectMessage(result);
        } else if (operation.equals(QueryType.getStudentsInCurrentGroup)) {
            ObjectMessage message = session.createObjectMessage(getStudentsInCurrentGroup(query.groupName));
        } else if (operation.equals(QueryType.getStudents)) {
            answerMessage = session.createObjectMessage(getAllStudents());
        } else if (operation.equals(QueryType.getGroups)) {
            answerMessage = session.createObjectMessage(getAllGroups());
        }
        producer.send(answerMessage);
    }

    private boolean addGroup(String name, int course) {
        return UniversityDatabaseConnection.addGroup(name, course);
    }

    private boolean addStudent(String firstName, String lastName, int age, String groupName) {
        return UniversityDatabaseConnection.addStudentToGroup(groupName, firstName, lastName, age);
    }

    private boolean deleteGroup(String name) {
        return UniversityDatabaseConnection.deleteGroupByName(name);
    }

    private boolean deleteStudent(int id) {
        return UniversityDatabaseConnection.deleteStudentByID(id);
    }

    public boolean changeGroupForStudent(int studentID, int newGroupID) {
        return UniversityDatabaseConnection.changeGroupForStudent(newGroupID, studentID);
    }

    public ArrayList<Student> getStudentsInCurrentGroup(String groupName) {
        return UniversityDatabaseConnection.getStudentsByGroupName(groupName);
    }

    public ArrayList<Student> getAllStudents() {
        return UniversityDatabaseConnection.getAllStudents();
    }

    public ArrayList<Group> getAllGroups() {
        return UniversityDatabaseConnection.getAllGroups();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, JMSException {
        Server server = new Server();
        server.start();
    }

}