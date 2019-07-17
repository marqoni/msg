package database.sqlite;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import entity.TripInfo;
import entity.collections.TripInfoCollection;

public final class SQLite {

	private String dbName;

	public SQLite(String dbName) {
		super();
		this.dbName = "jdbc:sqlite:" + dbName.trim();
	}

	public TripInfoCollection getCheckpoints(String driverId) throws SQLException, ClassNotFoundException, NumberFormatException, ParseException {
		entity.collections.TripInfoCollection tic = new TripInfoCollection();
		Class.forName("org.sqlite.JDBC");
		String dbURL = dbName;
		Connection conn = DriverManager.getConnection(dbURL);
		if (conn != null) {
			System.out.println("Connected to the database");
			DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
			
			

			String sql = "SELECT POINTS.ID, TRIPS.DRIVER_ID, TRIPS.START_TS, TRIPS.END_TS, TRIPS.TRIP_ID, POINTS.X, POINTS.Y, POINTS.TS\r\n"
					+ "FROM TRIPS\r\n" + "INNER JOIN POINTS ON TRIPS.TRIP_ID=POINTS.TRIP_ID\r\n"
					+ "WHERE TRIPS.DRIVER_ID ='" + driverId + "' \r\n" + "order by POINTS.TS asc;";
			
			String sql2 = "SELECT MAX(X), MAX(Y) FROM POINTS";
			
			Statement stmt = conn.createStatement();
			ResultSet maxOffset = stmt.executeQuery(sql2);
			ResultSet rs = stmt.executeQuery(sql);
			DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss", Locale.ENGLISH);
			
			char alphabet = 'A';
			String previousRowTripId = rs.getString("TRIP_ID");			
			
			while (rs.next()) {	
				
				if(alphabet == 'X') {
					alphabet++;
				}
				
				if (rs.getString("TRIP_ID").equalsIgnoreCase(previousRowTripId) == false) {
						alphabet++;
					}				
										
				TripInfo  ti = new TripInfo(
						Double.parseDouble( rs.getString("ID")), 
						rs.getString("TRIP_ID"), 
						rs.getString("X"), 
						rs.getString("Y"), 
						format.parse(rs.getString("TS")),
						String.valueOf(alphabet)
						);
				
				System.out.println(rs.getString("DRIVER_ID") + "\t" + rs.getString("TRIP_ID") + "\t"
						+ rs.getString("START_TS") + "\t" + rs.getString("END_TS") + "\t" + rs.getString("X") + "\t"
						+ rs.getString("Y") + "\t" + rs.getString("TS"));
				tic.addTripCheckpoint(ti);	
				
				previousRowTripId = rs.getString("TRIP_ID");
							
			}
			
			alphabet = 'A';
			
			conn.close();
		}
		return tic;
	}
	
}
