package University;

import DatabaseConnection.UniversityDatabaseConnection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class UniversityRemoteObject extends UnicastRemoteObject implements IUniversity {
    public UniversityRemoteObject() throws RemoteException {
        super();
    }

    public synchronized boolean addGroup(String name, int course) throws RemoteException {
        return UniversityDatabaseConnection.addGroup(name, course);
    }

    public synchronized boolean addStudent(String firstName, String lastName, int age, String groupName) throws RemoteException {
        return UniversityDatabaseConnection.addStudentToGroup(groupName, firstName, lastName, age);
    }

    public synchronized boolean deleteGroup(int id) throws RemoteException {
        return UniversityDatabaseConnection.deleteGroupByID(id);
    }

    public synchronized boolean deleteStudent(int id) throws RemoteException {
        return UniversityDatabaseConnection.deleteStudentByID(id);
    }

    public synchronized boolean changeGroupForStudent(int studentID, int newGroupID) throws RemoteException {
        return UniversityDatabaseConnection.changeGroupForStudent(newGroupID, studentID);
    }

    public ArrayList<Student> getStudentsInCurrentGroup(String groupName) throws RemoteException {
        return UniversityDatabaseConnection.getStudentsByGroupName(groupName);
    }

    public ArrayList<Student> getAllStudents() throws RemoteException {
        return UniversityDatabaseConnection.getAllStudents();
    }

    public ArrayList<Group> getAllGroups() throws RemoteException {
        return UniversityDatabaseConnection.getAllGroups();
    }
}
