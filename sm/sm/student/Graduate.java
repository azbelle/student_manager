package sm.student;

import sm.manager.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Graduate extends Student {

    private String thesisTitle, thesisAdvisor = "";

    public Graduate(){
        super();
    }

    public Graduate(String firstName, String lastName, int studentID, float GPA, int status, String mentor, String thesisTitle, String thesisAdvisor) {
        super(firstName, lastName, studentID, GPA, status, mentor);
        this.thesisTitle = thesisTitle;
        this.thesisAdvisor = thesisAdvisor;
    }


    @Override
    public void add(){
        String query = "insert into student('firstName','lastName','gpa','status','mentor','thesisTitle','thesisAdvisor', 'studentID') values (?,?,?,?,?,?,?,?)";
        DBConnection.instance().executeAddOrUpdateQuery(query, this);
    }

    @Override
    public void prepareAddOrUpdateStatement(PreparedStatement ps) throws SQLException {
        super.prepareAddOrUpdateStatement(ps);
        ps.setString(6, thesisTitle);
        ps.setString(7, thesisAdvisor);
        ps.setInt(8, studentID);
    }

    @Override
    public void populate(ResultSet rs) throws SQLException {
        super.populate(rs);
        this.thesisAdvisor = rs.getString("thesisAdvisor");
        this.thesisTitle = rs.getString("thesisTitle");
    }

    @Override
    public void update() {
        String query = "update student set firstName = ?, lastName = ?, gpa = ?, status = ?, mentor = ?, thesisTitle = ?, thesisAdvisor = ? where studentID = ?";
        DBConnection.instance().executeAddOrUpdateQuery(query, this);
    }

    @Override
    public double calculateTuition() {
        if(status == RESIDENT){
            return creditHours * 300;
        }
        else{
            return creditHours * 350;
        }
    }

    public String getThesisTitle() {
        return thesisTitle;
    }

    public void setThesisTitle(String thesisTitle) {
        this.thesisTitle = thesisTitle;
    }

    public String getThesisAdvisor() {
        return thesisAdvisor;
    }

    public void setThesisAdvisor(String thesisAdvisor) {
        this.thesisAdvisor = thesisAdvisor;
    }


    @Override
    public String toString() {
        return "Graduate{" +
                "thesisTitle='" + thesisTitle + '\'' +
                ", thesisAdvisor='" + thesisAdvisor + '\'' +
                "} " + super.toString();
    }
}
