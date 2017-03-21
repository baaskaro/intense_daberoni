package intense_daberoni;

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
			
			System.out.println("Vil du legge til type øvelse? Ja eller nei");
			String type1 = scanner.nextLine();
			if (type1.equals("Ja")) {
				System.out.println("Utholdenhet eller styrke?");
				String type2 = scanner.nextLine();
				if (type2.equals("Utholdenhet")) {
					try {
						
						Scanner scanner1 = new Scanner(System.in);
						System.out.println("Navn: ");
						String navn1 = scanner1.nextLine();
						System.out.println("Km: ");
						String km = scanner1.nextLine();
						System.out.println("Minutter: ");
						String minutter = scanner1.nextLine();
						
						scanner1.close();
						
						String query = "INSERT INTO Utholdenhet (navn, km, minutter)"
								+ "VALUES ('" + navn1 + "', '" + km + "', '" + minutter + "')";
						System.out.println("Øvelse lagt til i ingrivho_nobe.Utholdenhet.");
						stmt = conn.createStatement();
						stmt.executeUpdate(query);
					}
					catch (SQLException ex){
						System.out.println("SQLException: " + ex.getMessage());
					}
					
				} else if (type2.equals("Styrke")) {
					try {
						
						Scanner scanner2 = new Scanner(System.in);
						System.out.println("Navn: ");
						String navn2 = scanner2.nextLine();
						System.out.println("Muskelgruppe: ");
						String muskelgruppe = scanner2.nextLine();
						
						scanner2.close();
						
						String query = "INSERT INTO Styrke (navn, muskelgruppe)"
								+ "VALUES ('" + navn2 + "','" + muskelgruppe + "')";
						
						System.out.println("Øvelse lagt til i ingrivho_nobe.Styrke.");
						stmt = conn.createStatement();
						stmt.executeUpdate(query);
						
					}
					catch (SQLException ex){
						System.out.println("SQLException: " + ex.getMessage());
					}
					
				} else {
					System.out.println("Du må velge enten utholdenhet eller styrke.");
				}
				
			} else {
				System.out.println("Øvelse lagt til i ingrivho_nobe.Øvelser.");
			}
			
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
