package sm.manager;

import sm.student.Student;

import java.lang.String;
import java.sql.*;
import java.sql.PreparedStatement;

/** Manage db connection */
public class DBConnection {

    private static DBConnection instance = new DBConnection();

    private Connection connection;

    private DBConnection(){
       init();
    }

    private void init(){} {

        try {
            Class.forName("com.mysql.jdbc.Driver" ).newInstance();
        } catch (Exception ex) {
            System.out.println("Failed to create com.mysql.jdbc.Driver instance");
            System.exit(1);
        }

        try {
            connection =
                    DriverManager.getConnection("jdbc:mysql://localhost/test?" +
                            "user=valentine&password=valentinethegreat");

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public static DBConnection instance(){
        return instance;
    }

    public Connection getConnection(){
       return connection;
    }


    public void executeAddOrUpdateQuery(String query, Student student){
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            student.prepareAddOrUpdateStatement(stmt);
            stmt.executeUpdate(query);
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { }
            }
        }
    }

    public void executeDeleteQuery(String query, int studentID){
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, studentID);
            stmt.executeUpdate(query);
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { }
            }
        }
    }


    public void executeSelectQuery(String query, Student student){
        ResultSet rs = null;
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, student.getStudentID());


            rs = stmt.executeQuery(query);
            student.populate(rs);
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore
            }
        }
    }
}
