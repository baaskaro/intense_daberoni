package karoline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class Resultat {
	
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
			
			String query = "SELECT * FROM Resultat";
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
	
	public void insettResultat()  {
		try {
				Scanner scanner = new Scanner(System.in);
				System.out.println("ResultatID: ");
				String resultatID= scanner.nextLine();
				System.out.println("Beste resultat ");
				String besteresultat = scanner.nextLine();
				System.out.println("Logg");
				String logg = scanner.nextLine();
				System.out.println("TreningsID: ");
				String treningsID = scanner.nextLine();
				System.out.println("Navn: ");
				String Navn = scanner.nextLine();
				scanner.close();
				
				String query = "INSERT INTO Resultat (resultatID, beste_resultat, logg, treningsID, navn) "
						+ "VALUES ('" + resultatID + "', '" + besteresultat + "', '" + logg + "', '" + treningsID + "', '" + Navn + "')";
				stmt = conn.createStatement();
				stmt.executeUpdate(query);
				
				System.out.println("Resultat lagt til i ingrivho_nobe.Resultat.");
		}
			
		
		catch (SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
		}
	}
	
	public static void main(String[] args) {
		Resultat test = new Resultat();
		try {
			test.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		test.sporring();
		test.insettResultat();
	}
	

}
