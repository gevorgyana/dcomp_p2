package DatabaseConnection;

import University.Group;
import University.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UniversityDatabaseConnection {

    private static final String jdbcDriverName = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://127.0.0.1:3306/?user=root&password=538734962318&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "538734962318";
    private static Logger logger = Logger.getLogger(UniversityDatabaseConnection.class.getName());

    public static ArrayList<Student> getAllStudents() {
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM university.students");
            ArrayList<Student> students = new ArrayList<>();
            while (rs.next()) {
                Student student = new Student(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getInt(5));
                students.add(student);
            }
            connection.close();
            return students;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public static Student getStudentsByID(int id) {
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            String sqlQuery = "SELECT * FROM university.students " +
                    "WHERE id=?;";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Student student = null;
            while (rs.next()) {
                student = new Student(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getInt(5));
            }
            connection.close();
            return student;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public static ArrayList<Student> getStudentsByFullName(String firstName, String lastName) {
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            String sqlQuery = "SELECT * FROM university.students " +
                    "WHERE firstName=? AND lastName=?;";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Student> students = new ArrayList<>();
            while (rs.next()) {
                Student student = new Student(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getInt(5));
                students.add(student);
            }
            connection.close();
            return students;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public static ArrayList<Student> getStudentsByGroupID(int groupID) {
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            String sqlQuery = "SELECT * FROM university.students " +
                    "WHERE groupID=?;";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setInt(1, groupID);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Student> students = new ArrayList<>();
            while (rs.next()) {
                Student student = new Student(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getInt(5));
                students.add(student);
            }
            connection.close();
            return students;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public static ArrayList<Student> getStudentsByGroupName(String groupName) {
        Group group = getGroupByName(groupName);
        if (group == null) {
            return null;
        } else {
            return getStudentsByGroupID(group.getGroupID());
        }
    }

    public static ArrayList<Student> getStudentsByAgeGreaterThan(int age) {
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            String sqlQuery = "SELECT * FROM university.students " +
                    "WHERE age>?;";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setInt(1, age);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Student> students = new ArrayList<>();
            while (rs.next()) {
                Student student = new Student(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getInt(5));
                students.add(student);
            }
            connection.close();
            return students;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public static Group getGroupByID(int id) {
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            String sqlQuery = "SELECT * FROM university.groups " +
                    "WHERE id=?;";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Group group = null;
            while (rs.next()) {
                group = new Group(rs.getInt(1), rs.getString(2), rs.getInt(3));
            }
            connection.close();
            return group;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public static Group getGroupByName(String name) {
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            String sqlQuery = "SELECT * FROM university.groups " +
                    "WHERE name=?;";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            Group group = null;
            while (rs.next()) {
                group = new Group(rs.getInt(1), rs.getString(2), rs.getInt(3));
            }
            connection.close();
            return group;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public static ArrayList<Group> getAllGroups() {
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM university.groups;");
            ArrayList<Group> groups = new ArrayList<>();
            while (rs.next()) {
                Group group = new Group(rs.getInt(1), rs.getString(2), rs.getInt(3));
                groups.add(group);
            }
            connection.close();
            return groups;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public static ArrayList<Group> getGroupByCourse(int course) {
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            String sqlQuery = "SELECT * FROM university.groups WHERE course=?;";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setInt(1, course);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Group> groups = new ArrayList<>();
            while (rs.next()) {
                Group group = new Group(rs.getInt(1), rs.getString(2), rs.getInt(3));
                groups.add(group);
            }
            connection.close();
            return groups;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public static final boolean addGroup(String name, int course) {
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url);
            String sqlQuery = "INSERT INTO university.groups(name,course) VALUES(?,?);";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, name);
            stmt.setInt(2, course);
            stmt.execute();
            connection.close();
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return false;
        }
    }

    private static boolean insertStudent(int groupID, String firstName, String lastName, int age) {
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url);
            String sqlQuery = "INSERT INTO university.students(firstName,lastName,age,groupID) VALUES(?,?,?,?);";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setInt(3, age);
            stmt.setInt(4, groupID);
            stmt.execute();
            connection.close();
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return false;
        }
    }

    public static final boolean addStudentToGroup(String groupName, String firstName, String lastName, int age) {
        Group group = getGroupByName(groupName);
        if (group == null) {
            return false;
        } else {
            return insertStudent(group.getGroupID(), firstName, lastName, age);
        }
    }

    public static final boolean addStudentToGroup(int groupID, String firstName, String lastName, int age) {
        Group group = getGroupByID(groupID);
        if (group == null) {
            return false;
        } else {
            return insertStudent(group.getGroupID(), firstName, lastName, age);
        }
    }

    public static final boolean deleteStudentByID(int id) {
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            String sqlQuery = "DELETE FROM university.students WHERE id=?;";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setInt(1, id);
            stmt.execute();
            connection.close();
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return false;
        }
    }

    public static final boolean deleteStudentByFullName(String firstName, String lastName) {
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            String sqlQuery = "DELETE FROM university.students WHERE firstName=? AND lastName=?;";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.execute();
            connection.close();
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return false;
        }
    }

    public static final boolean deleteGroupByID(int id) {
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            String sqlQuery = "DELETE FROM university.groups WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setInt(1, id);
            stmt.execute();
            sqlQuery = "DELETE FROM university.students WHERE groupID=?;";
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setInt(1, id);
            stmt.execute();
            connection.close();
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return false;
        }
    }

    public static final boolean deleteGroupByName(String name) {
        Group group = getGroupByName(name);
        if (group == null) {
            return false;
        } else {
            try {
                Class.forName(jdbcDriverName);
                Connection connection = DriverManager.getConnection(url, user, password);
                String sqlQuery = "DELETE FROM university.groups WHERE name=?";
                PreparedStatement stmt = connection.prepareStatement(sqlQuery);
                stmt.setString(1, name);
                stmt.execute();
                sqlQuery = "DELETE FROM university.students WHERE groupID=?;";
                stmt = connection.prepareStatement(sqlQuery);
                stmt.setInt(1, group.getGroupID());
                stmt.execute();
                connection.close();
                return true;
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage());
                return false;
            }
        }
    }

    public static final boolean changeGroupForStudent(int newGroupID, int studentID) {
        Group group = getGroupByID(newGroupID);
        if (group == null) {
            return false;
        } else {
            try {
                Class.forName(jdbcDriverName);
                Connection connection = DriverManager.getConnection(url, user, password);
                String sqlQuery = "UPDATE university.students " +
                        "SET groupID=? " +
                        "WHERE id=?";
                PreparedStatement stmt = connection.prepareStatement(sqlQuery);
                stmt.setInt(1, newGroupID);
                stmt.setInt(2, studentID);
                stmt.execute();
                connection.close();
                return true;
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage());
                return false;
            }
        }
    }

}
