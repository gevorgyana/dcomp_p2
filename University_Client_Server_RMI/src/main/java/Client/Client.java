package Client;

import University.Group;
import University.IUniversity;
import University.Student;

import java.io.*;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.ArrayList;

public class Client {
    private IUniversity universityRemote;
    private String remoteURL = "//127.0.0.1:123/University";

    public Client() throws IOException, NotBoundException {
        universityRemote = (IUniversity) Naming.lookup(remoteURL);
        System.out.println("RMI object found");
    }

    public boolean addGroup(String name, int course) throws IOException {
        return universityRemote.addGroup(name, course);
    }

    public boolean deleteGroup(int groupID) throws IOException {
        return universityRemote.deleteGroup(groupID);
    }

    public boolean addStudent(String firstName, String lastName, int age, String groupName) throws IOException {
        return universityRemote.addStudent(firstName, lastName, age, groupName);
    }

    public boolean deleteStudent(int studentID) throws IOException {
        return universityRemote.deleteStudent(studentID);
    }

    public boolean changeGroupForStudent(int studentID, int newGroupID) throws IOException {
        return universityRemote.changeGroupForStudent(studentID, newGroupID);
    }

    public ArrayList<Student> getStudentsInCurrentGroup(String groupName) throws IOException, ClassNotFoundException {
        return universityRemote.getStudentsInCurrentGroup(groupName);
    }

    public ArrayList<Student> getAllStudents() throws IOException, ClassNotFoundException {
        return universityRemote.getAllStudents();
    }

    public ArrayList<Group> getAllGroups() throws IOException, ClassNotFoundException {
        return universityRemote.getAllGroups();
    }
}
