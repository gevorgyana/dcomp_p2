package Client;

import java.io.Serializable;

public enum QueryType implements Serializable {
    addStudent,
    deleteStudent,
    addGroup,
    deleteGroup,
    changeGroupForStudent,
    getStudentsInCurrentGroup,
    getGroups,
    getStudents
}
