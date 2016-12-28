package sm.student;


import sm.manager.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PartTime extends Student {
    private String company = "";

    public PartTime(){
        super();
    }

    public PartTime(String firstName, String lastName, int studentID, float GPA, int status, String mentor, String company) {
        super(firstName, lastName, studentID, GPA, status, mentor);
        this.company = company;
    }

    @Override
    public void add(){
        String query = "insert into student('firstName','lastName','gpa','status','mentor','company', 'studentID') values (?,?,?,?,?,?,?)";
        DBConnection.instance().executeAddOrUpdateQuery(query, this);
    }

    @Override
    public void prepareAddOrUpdateStatement(PreparedStatement ps) throws SQLException {
        super.prepareAddOrUpdateStatement(ps);
        ps.setString(6, company);
        ps.setInt(7, studentID);
    }

    @Override
    public void populate(ResultSet rs) throws SQLException {
        super.populate(rs);
        this.company = rs.getString("company");
    }

    @Override
    public void update() {
        String query = "update student set firstName = ?, lastName = ?, gpa = ?, status = ?, mentor = ?, company = ? where studentID = ?";
        DBConnection.instance().executeAddOrUpdateQuery(query, this);
    }

    @Override
    public double calculateTuition() {
        if(status == RESIDENT){
            return creditHours * 250;
        }
        else{
            return creditHours * 450;
        }
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "PartTime{" +
                "company='" + company + '\'' +
                "} " + super.toString();
    }
}
