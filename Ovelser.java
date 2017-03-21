package karoline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class Ovelser {
	
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
	
	public void sporring(){
		try {
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM Øvelser";
			if (stmt.execute(query)) {
				rs = stmt.getResultSet();
			}
			
			while (rs.next()) {
				String kolonne1 = rs.getString(1);
				String kolonne2 = rs.getString(2);
				System.out.println(kolonne1 + " - " + kolonne2);
			}
		}
		catch (SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
		}
	}
	
	public void insettØvelse()  {
		try {
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("Navn: ");
			String navn = scanner.nextLine();
			System.out.println("Beskrivelse: ");
			String beskrivelse = scanner.nextLine();
			System.out.println("Belastning: ");
			String belastning = scanner.nextLine();
			System.out.println("Repetisjoner: ");
			String repetisjoner = scanner.nextLine();
			System.out.println("Sett: ");
			String sett = scanner.nextLine();
			
			scanner.close();
			
			String query = "INSERT INTO Øvelser (navn, beskrivelse, belastning, repetisjoner, sett)"
					+ "VALUES ('" + navn + "', '" + beskrivelse + "', '" + belastning + "', '" + 
					repetisjoner + "', '" + sett + "')";
			System.out.println("Øvelse lagt til i ingrivho_nobe.Øvelser.");
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		}
		catch (SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
		}
	}
	
	public static void main(String[] args) {
		Ovelser test = new Ovelser();
		try {
			test.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		test.sporring();
		test.insettØvelse();
	}
	

}