package karoline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Styrke extends Ovelser {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	 static final String DB_URL = "jdbc:mysql://mysql.stud.ntnu.no/ingrivho_nobe_db";
	
	 static final String USER = "ingrivho_nobe";
	 static final String PASS = "1234";		 
	 
	 Connection conn = null;
	
	
	public void connect() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		}
		catch (SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
		}
		catch(Exception ex){
			System.out.println("Tilkobling mislyktes");
		}
	}
	
	Statement stmt = null;
	ResultSet rs = null;

	
	public void insettStyrkeøvelse()  {
		try {
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("Muskelgruppe: ");
			String muskelgruppe = scanner.nextLine();
			
			scanner.close();
			
			String query = "INSERT INTO Styrke (muskelgruppe)"
					+ "VALUES ('" + muskelgruppe + "')";
			
			System.out.println("Øvelse lagt til i ingrivho_nobe.Styrke.");
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			
		}
		catch (SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
		}
	}
}

