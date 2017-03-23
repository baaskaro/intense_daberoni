package karoline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class HentNotat {
	
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
	
	public void notat() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Vil du hente ut tidligere treningsnotater?");
		String svar = scanner.nextLine();
		scanner.close();
		if (svar.equals("Ja") || svar.equals("ja")) {
			HentUtNotat();
		} else {
			System.out.println("Ok.");
		}
	}
	
	public void HentUtNotat(){
		try {
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM Treningsøkt";
			if (stmt.execute(query)) {
				rs = stmt.getResultSet();
			}
			
			while (rs.next()) {
				String kolonne_notat = rs.getString(7);
				System.out.println(kolonne_notat);
			}
		}
		catch (SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
		}
	}
	
	public static void main(String[] args) {
		HentNotat test = new HentNotat();
		try {
			test.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		test.notat();
	}
}

