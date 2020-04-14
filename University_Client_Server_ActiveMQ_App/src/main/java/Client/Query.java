package Client;

import java.io.Serializable;

public class Query implements Serializable {
    public QueryType queryType;
    public String studentFirstName, studentLastName, groupName;
    public int studentAge, studentID, groupID, newGroupID, groupCourse;
}
