package Server;

import Client.Query;
import DatabaseConnection.UniversityDatabaseConnection;
import University.Group;
import University.Student;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket server = null;
    private Socket sock = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    private ObjectInputStream objectInputStream = null;
    private ObjectOutputStream objectOutputStream = null;

    public void start(int port) throws IOException, ClassNotFoundException {
        server = new ServerSocket(port);
        while (true) {
            sock = server.accept();
            in = new DataInputStream(sock.getInputStream());
            objectInputStream = new ObjectInputStream(sock.getInputStream());
            objectOutputStream = new ObjectOutputStream(sock.getOutputStream());
            out = new DataOutputStream(sock.getOutputStream());
            while (true) {
                processQuery();
            }
        }
    }

    private void processQuery() throws IOException, ClassNotFoundException {
        Query operation = (Query) objectInputStream.readObject();
        if (operation.equals(Query.addGroup)) {
            out.writeBoolean(addGroup(in.readUTF(), in.readInt()));
        } else if (operation.equals(Query.addStudent)) {
            out.writeBoolean(addStudent(in.readUTF(), in.readUTF(), in.readInt(), in.readUTF()));
        }else if (operation.equals(Query.deleteGroup)){
            out.writeBoolean(deleteGroup(in.readUTF()));
        }else if (operation.equals(Query.deleteStudent)){
            out.writeBoolean(deleteStudent(in.readInt()));
        }else if (operation.equals(Query.changeGroupForStudent)){
            out.writeBoolean(changeGroupForStudent(in.readInt(),in.readInt()));
        }else if (operation.equals(Query.getStudentsInCurrentGroup)){
            objectOutputStream.writeObject(getStudentsInCurrentGroup(in.readUTF()));
        }else if (operation.equals(Query.getStudents)){
            objectOutputStream.writeObject(getAllStudents());
        }else if (operation.equals(Query.getGroups)){
            objectOutputStream.writeObject(getAllGroups());
        }
    }

    private boolean addGroup(String name, int course) {
        return UniversityDatabaseConnection.addGroup(name,course);
    }

    private boolean addStudent(String firstName, String lastName, int age, String groupName) {
        return UniversityDatabaseConnection.addStudentToGroup(groupName,firstName,lastName,age);
    }
    private boolean deleteGroup(String name) {
        return UniversityDatabaseConnection.deleteGroupByName(name);
    }

    private boolean deleteStudent(int id) {
        return UniversityDatabaseConnection.deleteStudentByID(id);
    }

    public boolean changeGroupForStudent(int studentID,int newGroupID){
        return UniversityDatabaseConnection.changeGroupForStudent(newGroupID,studentID);
    }

    public ArrayList<Student> getStudentsInCurrentGroup(String groupName){
        return UniversityDatabaseConnection.getStudentsByGroupName(groupName);
    }

    public ArrayList<Student> getAllStudents(){
        return UniversityDatabaseConnection.getAllStudents();
    }

    public ArrayList<Group> getAllGroups(){
        return UniversityDatabaseConnection.getAllGroups();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Server server = new Server();
        server.start(12345);
    }

}