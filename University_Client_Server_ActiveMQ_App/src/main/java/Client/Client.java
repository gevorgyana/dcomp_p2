package Client;

import University.Student;

import java.util.ArrayList;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Client {
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

    public Client() throws JMSException {
        // Getting JMS connection from the server
        connectionFactory = new ActiveMQConnectionFactory(url);
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
    }

    public boolean addGroup(String name, int course) throws JMSException {
        Query query = new Query();
        query.queryType = QueryType.addGroup;
        query.groupName = name;
        query.groupCourse = course;
        ObjectMessage objectMessage = session.createObjectMessage(query);
        producer.send(objectMessage);
        Message message = consumer.receive();
        Boolean answer = (Boolean) (((ObjectMessage) message).getObject());
        if (answer != null) {
            return answer;
        } else {
            return false;
        }
    }

    public boolean addStudent(String firstName, String lastName, int age, String groupName) throws JMSException {
        Query query = new Query();
        query.queryType = QueryType.addStudent;
        query.studentFirstName = firstName;
        query.studentLastName = lastName;
        query.studentAge = age;
        query.groupName = groupName;
        ObjectMessage objectMessage = session.createObjectMessage(query);
        producer.send(objectMessage);
        Message message = consumer.receive();
        Boolean answer = (Boolean) (((ObjectMessage) message).getObject());
        if (answer != null) {
            return answer;
        } else {
            return false;
        }
    }

    public boolean deleteStudent(int studentID) throws JMSException {
        Query query = new Query();
        query.queryType = QueryType.deleteStudent;
        query.studentID = studentID;
        ObjectMessage objectMessage = session.createObjectMessage(query);
        producer.send(objectMessage);
        Message message = consumer.receive();
        Boolean answer = (Boolean) (((ObjectMessage) message).getObject());
        if (answer != null) {
            return answer;
        } else {
            return false;
        }
    }

    public ArrayList<Student> getAllStudents() throws JMSException {
        Query query = new Query();
        query.queryType = QueryType.getStudents;
        ObjectMessage objectMessage = session.createObjectMessage(query);
        producer.send(objectMessage);
        Message message = consumer.receive();
        ObjectMessage reply = (ObjectMessage) message;
        return (ArrayList<Student>) reply.getObject();
    }

    public void disconnect() throws JMSException {
        session.close();
    }

    public static void main(String[] args) {
        try {
            Client client = new Client();
            ArrayList<Student> students = client.getAllStudents();
            for (Student student : students) {
                System.out.println(student);
            }
            client.disconnect();
        } catch (JMSException e) {
            System.out.println("Connection error");
        }
    }
}
