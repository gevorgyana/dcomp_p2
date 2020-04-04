package Client;

import University.Group;
import University.Student;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private Socket sock;
    private DataOutputStream out;
    private DataInputStream in;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public Client(String ip, int port) throws IOException {
        sock = new Socket(ip, port);
        in = new DataInputStream(sock.getInputStream());
        out = new DataOutputStream(sock.getOutputStream());
        objectOutputStream = new ObjectOutputStream(sock.getOutputStream());
        objectInputStream = new ObjectInputStream(sock.getInputStream());
    }

    private int sendQuery(int operation, int value1, int value2) throws IOException {
        out.writeInt(operation);
        out.writeInt(value1);
        out.writeInt(value2);
        int res = in.readInt();
        return res;
    }

    public boolean addGroup(String name, int course) throws IOException {
        Query operation = Query.addGroup;
        objectOutputStream.writeObject(operation);
        out.writeUTF(name);
        out.writeInt(course);
        return in.readBoolean();
    }

    public boolean deleteGroup(int groupID) throws IOException {
        Query operation = Query.deleteGroup;
        objectOutputStream.writeObject(operation);
        out.writeInt(groupID);
        return in.readBoolean();
    }

    public boolean addStudent(String firstName, String lastName, int age, String groupName) throws IOException {
        Query operation = Query.addStudent;
        objectOutputStream.writeObject(operation);
        out.writeUTF(firstName);
        out.writeUTF(lastName);
        out.writeInt(age);
        out.writeUTF(groupName);
        return in.readBoolean();
    }

    public boolean deleteStudent(int studentID) throws IOException {
        Query operation = Query.deleteStudent;
        objectOutputStream.writeObject(operation);
        out.writeInt(studentID);
        return in.readBoolean();
    }

    public boolean changeGroupForStudent(int studentID,int newGroupID) throws IOException {
        Query operation = Query.changeGroupForStudent;
        objectOutputStream.writeObject(operation);
        out.writeInt(studentID);
        out.writeInt(newGroupID);
        return in.readBoolean();
    }

    public ArrayList<Student> getStudentsInCurrentGroup(String groupName) throws IOException, ClassNotFoundException {
        Query operation = Query.changeGroupForStudent;
        objectOutputStream.writeObject(operation);
        out.writeUTF(groupName);
        return (ArrayList<Student>)objectInputStream.readObject();
    }

    public ArrayList<Student> getAllStudents() throws IOException, ClassNotFoundException {
        Query operation = Query.getStudents;
        objectOutputStream.writeObject(operation);
        return (ArrayList<Student>)objectInputStream.readObject();
    }

    public ArrayList<Group> getAllGroups() throws IOException, ClassNotFoundException {
        Query operation = Query.getGroups;
        objectOutputStream.writeObject(operation);
        return (ArrayList<Group>)objectInputStream.readObject();
    }

    public void disconnect() throws IOException {
        sock.close();
    }

    public static void main(String[] args) {
        try {
            Client client = new Client("localhost", 12345);
            System.out.println(client.addGroup("IPS-32",3));//6
            System.out.println(client.addStudent("Stanislav","Dzundza",20,"IPS-32"));//1
            System.out.println(client.addStudent("Andrii","Blahii",20,"IPS-32"));//2
            System.out.println(client.addGroup("IPS-31",3));//7
            System.out.println(client.addStudent("Vladislav","Tochanenko",20,"IPS-31"));//3
            System.out.println(client.addStudent("Bohdan","Khomich",20,"IPS-31"));//4
            System.out.println(client.changeGroupForStudent(4,6));
            ArrayList<Student>students = client.getAllStudents();
            for(Student student:students){
                System.out.println(student);
            }
            System.out.println(client.deleteGroup(7));
            students = client.getAllStudents();
            for(Student student:students){
                System.out.println(student);
            }
            client.disconnect();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Connection error");
        }
    }
}
