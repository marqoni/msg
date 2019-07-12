package database.sqlite;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public final class SQLite {
 
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:ttp.db";
            Connection conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
                System.out.println("Connected to the database");
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
                               
                String sql = "SELECT TRIPS.DRIVER_ID, TRIPS.START_TS, TRIPS.END_TS, TRIPS.TRIP_ID, POINTS.X, POINTS.Y, POINTS.TS\r\n" + 
                		"FROM TRIPS\r\n" + 
                		"INNER JOIN POINTS ON TRIPS.TRIP_ID=POINTS.TRIP_ID\r\n" + 
                		"order by POINTS.TS asc;";
                
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql);
                
                while (rs.next()) {
                    System.out.println(rs.getString("DRIVER_ID") +  "\t" + 
                    				   rs.getString("TRIP_ID") + "\t" +
                                       rs.getString("START_TS") + "\t" +
                                       rs.getString("END_TS") + "\t" +
                                       rs.getString("X") + "\t" +
                                       rs.getString("Y") + "\t" +
                                       rs.getString("TS"));
                }
//                System.out.println(rs);
                
                
                conn.close();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}



