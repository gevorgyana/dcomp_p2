package University;

import java.io.Serializable;

public class Student implements Serializable {
    private int studentID;
    private int groupID;
    private String firstName;
    private String lastName;
    private int age;

    public Student() {
        studentID = groupID = age = -1;
        firstName = lastName = "";
    }

    public Student(int studentID, String firstName, String lastName, int age, int groupID) {
        this.studentID = studentID;
        this.groupID = groupID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public String toString(){
        return String.format("Student %s %s , id : %d, age : %d, group id : %d",firstName,lastName,studentID,age,groupID);
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }
}
