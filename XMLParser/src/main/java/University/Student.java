package University;

public class Student {
    private Group group;
    private String name;
    private int age;

    public Student(Group group,String name,int age){
        this.group = group;
        this.name = name;
        this.age = age;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
