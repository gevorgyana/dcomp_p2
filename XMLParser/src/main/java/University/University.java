package University;

import java.util.ArrayList;

public class University {
    private ArrayList<Group> groups;
    private ArrayList<Student> students;

    public University() {
        groups = new ArrayList<>();
        students = new ArrayList<>();
    }

    public void addGroup(Group group) throws Exception {
        if (!groups.contains(group)) {
            groups.add(group);
        } else {
            throw new Exception("Group already exists");
        }
    }

    public void addGroup(String name, int course) throws Exception {
        Group group = new Group(name, course);
        if (!groups.contains(group)) {
            groups.add(group);
        } else {
            throw new Exception("Group already exists");
        }
    }

    public Group getGroup(String name) {
        for (Group group : groups) {
            if (group.getName().equals(name)) {
                return group;
            }
        }
        return null;
    }

    public Group getGroup(int index) throws Exception {
        if (index < 0 || index >= groups.size()) {
            throw new Exception("Index out of bounds");
        } else {
            return groups.get(index);
        }
    }

    public void addStudent(Student student) throws Exception {
        if (!students.contains(student)) {
            students.add(student);
        } else {
            throw new Exception("Student already exists");
        }
    }

    public void addStudent(String group, String name, int age) throws Exception {
        Group gr = getGroup(group);
        Student student = new Student(gr, name, age);
        if (!students.contains(student)) {
            students.add(student);
        } else {
            throw new Exception("Student already exists");
        }
    }

    public void addStudent(Group group, String name, int age) throws Exception {
        Student student = new Student(group, name, age);
        if (!students.contains(student)) {
            students.add(student);
        } else {
            throw new Exception("Student already exists");
        }
    }

    public Student getStudent(String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }

    public Student getStudent(int index) throws Exception {
        if (index < 0 || index >= students.size()) {
            throw new Exception("Index out of bounds");
        } else {
            return students.get(index);
        }
    }

    public ArrayList<Student> getStudents(String groupName) {
        ArrayList<Student> studentsList = new ArrayList<>();
        for (Student student : students) {
            if (student.getGroup().getName().equals(groupName)) {
                studentsList.add(student);
            }
        }
        return studentsList;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void deleteGroup(String name) {
        students.removeIf((Student st) -> {
            if (st.getGroup().getName().equals(name)) {
                return true;
            } else {
                return false;
            }
        });

        groups.removeIf((Group group) -> {
            if (group.getName().equals(name)) {
                return true;
            } else {
                return false;
            }
        });
    }

    public void deleteStudent(String name) {
        Student st = getStudent(name);
        if (st != null) {
            students.remove(st);
        }
    }

}
