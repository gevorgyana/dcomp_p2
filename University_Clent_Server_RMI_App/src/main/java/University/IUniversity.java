package University;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IUniversity extends Remote {
    boolean addGroup(String name, int course) throws RemoteException;

    boolean addStudent(String firstName, String lastName, int age, String groupName) throws RemoteException;

    boolean deleteGroup(int id) throws RemoteException;

    boolean deleteStudent(int id) throws RemoteException;

    boolean changeGroupForStudent(int studentID, int newGroupID) throws RemoteException;

    ArrayList<Student> getStudentsInCurrentGroup(String groupName) throws RemoteException;

    ArrayList<Student> getAllStudents() throws RemoteException;

    ArrayList<Group> getAllGroups() throws RemoteException;
}
