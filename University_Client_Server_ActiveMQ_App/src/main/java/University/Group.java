package University;

import java.io.Serializable;

public class Group implements Serializable {
    private int groupID;
    private String name;
    private int course;

    public Group() {
        groupID = course = -1;
        name = "";
    }

    public Group(int groupID, String name, int course) {
        this.groupID = groupID;
        this.name = name;
        this.course = course;
    }

    public int getGroupID() {
        return groupID;
    }

    @Override
    public String toString(){
        return String.format("Group %s , id : %d, course : %d",name,groupID,course);
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }
}
