package sm.student;

import sm.manager.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Undergraduate extends Student {

    private String level = Level.freshman.name();

    public enum Level{
        freshman,
        sophomore,
        junior,
        senior
    };

    public Undergraduate(){
        super();
    }

    public Undergraduate(String firstName, String lastName, int studentID, float GPA, int status, String mentor, String level) {
        super(firstName, lastName, studentID, GPA, status, mentor);
        this.level = level;
    }

    @Override
    public void add(){
        String query = "insert into student('firstName','lastName','gpa','status','mentor','level','studentID') values (?,?,?,?,?,?,?)";
        DBConnection.instance().executeAddOrUpdateQuery(query, this);
    }

    @Override
    public void prepareAddOrUpdateStatement(PreparedStatement ps) throws SQLException {
        super.prepareAddOrUpdateStatement(ps);
        ps.setString(6, level);
        ps.setInt(7, studentID);
    }

    @Override
    public void populate(ResultSet rs) throws SQLException {
        super.populate(rs);
        this.level = rs.getString("level");
    }

    @Override
    public void update() {
        String query = "update student set firstName = ?, lastName = ?, gpa = ?, status = ?, mentor = ?, level = ? where studentID = ?";
        DBConnection.instance().executeAddOrUpdateQuery(query, this);
    }

    @Override
    public double calculateTuition() {
       if(status == RESIDENT){
           return creditHours * 200;
       }
       else{
           return creditHours * 400;
       }
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }


    @Override
    public String toString() {
        return "Undergraduate{" +
                "level=" + level +
                "} " + super.toString();
    }
}
