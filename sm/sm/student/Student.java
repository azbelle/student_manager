package sm.student;


import sm.manager.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Student {
    protected String firstName, lastName = "";
    protected int studentID = 0;
    protected float GPA = 0.0f;
    protected int status = RESIDENT;
    protected String mentor = "";
    protected int creditHours = 0;

    // default constructor
    protected Student(){}

    protected Student(String firstName, String lastName, int studentID, float GPA, int status, String mentor) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentID = studentID;
        this.GPA = GPA;
        this.status = status;
        this.mentor = mentor;
    }

    public static final int RESIDENT = 1;
    public static final int NON_RESIDENT = 0;


    /*public void prepareAddStatement(PreparedStatement ps) throws SQLException {
       ps.setInt(1, studentID);
       ps.setString(2, firstName);
       ps.setString(3, lastName);
       ps.setFloat(4, GPA);
       ps.setInt(5, status);
       ps.setString(6, mentor);
    }*/

    public void prepareAddOrUpdateStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, firstName);
        ps.setString(2, lastName);
        ps.setFloat(3, GPA);
        ps.setInt(4, status);
        ps.setString(5, mentor);
    }

    public abstract void add();

    public void delete(){
        String query = "delete from student where studentID = ?";
        DBConnection.instance().executeDeleteQuery(query, studentID);
    }

    public void query() {
        String query = "select * from student where studentID = ?";
        DBConnection.instance().executeSelectQuery(query, this);
        System.out.println(this);
    }

    public abstract void update();

    public void populate(ResultSet rs) throws SQLException {
        this.firstName = rs.getString("firstName");
        this.lastName = rs.getString("lastName");
        this.studentID = rs.getInt("studentID");
        this.GPA = rs.getFloat("gpa");
        this.status = rs.getShort("status");
        this.mentor = rs.getString("mentor");
    }

    public abstract double calculateTuition();

    public String getMentor() {
        return mentor;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getGPA() {
        return GPA;
    }

    public void setGPA(float GPA) {
        this.GPA = GPA;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
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

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }


    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", studentID=" + studentID +
                ", GPA=" + GPA +
                ", status=" + status +
                ", mentor='" + mentor + '\'' +
                ", creditHours=" + creditHours +
                '}';
    }
}
