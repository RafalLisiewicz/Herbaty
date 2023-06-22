package edp.herbaty.herbaty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBControl {

    public void createTable() {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:comments.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE COMMENT " +
                    "(TYPE           TEXT    NOT NULL," +
                    " BODY            Text     NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
        }
    }

    public void addComment(String type, String comment) {
        Connection c;
        Statement stmt;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:comments.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "INSERT INTO COMMENT (TYPE,BODY) " +
                    "VALUES (\"" + type + "\", \"" + comment + "\");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Records created successfully");
    }

    public ArrayList<String> getComments(String type) {
        Connection c;
        Statement stmt;
        ArrayList<String> ret = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:comments.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT BODY FROM COMMENT WHERE TYPE LIKE \"" + type + "\";");
            while (rs.next()) {
                ret.add(rs.getString("BODY"));
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return ret;
    }

    public void deleteComment(String body) {
        Connection c;
        Statement stmt;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:comments.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "DELETE FROM COMMENT WHERE BODY LIKE \"" + body + "\";";
            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
