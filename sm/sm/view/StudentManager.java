package sm.view;


public class StudentManager {
    // TODO this should be the GUI
    public static void main(String... args){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        }
    }

}
