import Client.Client;
import Server.ServerRMI;
import University.Student;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, NotBoundException, ClassNotFoundException {
        Client client = new Client();
        System.out.println(client.addGroup("K-28", 3));
        System.out.println(client.addStudent("Bohdan", "Dzundza", 20, "K-28"));//1
        System.out.println(client.addStudent("Oleh", "Blahii", 20, "K-28"));//2
        System.out.println(client.addGroup("K-29", 3));
        System.out.println(client.addStudent("Vladislav", "Igorov", 20, "K-29"));//3
        System.out.println(client.addStudent("Bohdan", "Ivanov", 20, "K-29"));//4
        ArrayList<Student> students = client.getAllStudents();
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("Students in group K-29 : ");
        students = client.getStudentsInCurrentGroup("K-29");
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
