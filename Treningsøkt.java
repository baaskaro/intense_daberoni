package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class main {
	
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
			
			String query = "SELECT * FROM Treningsøkt";
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
	
	public void insettTreningsØkt()  {
		try {
			System.out.println("Vil du trene inne eller ute?");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			if(input.equals("ute")){
				System.out.println("TreningsID: ");
				String treningsID = scanner.nextLine();
				System.out.println("Dato: ");
				String dato = scanner.nextLine();
				System.out.println("Tidspunkt: ");
				String tidspunkt = scanner.nextLine();
				System.out.println("Varighet: ");
				String varighet = scanner.nextLine();
				System.out.println("Personlig form: ");
				String personligForm = scanner.nextLine();
				System.out.println("Prestasjon: ");
				String prestasjon = scanner.nextLine();
				System.out.println("Notat: ");
				String notat = scanner.nextLine();
				System.out.println("Navn: ");
				String navn = scanner.nextLine();
			
					
				String query = "INSERT INTO Treningsøkt (treningsID, dato, tidspunkt, varighet, personligForm, prestasjon, notat, navn) "
						+ "VALUES ('" + treningsID + "', '" + dato + "', '" + tidspunkt + "', '" + varighet + "', '" + personligForm + "', '" + prestasjon 
						+ "', '" + notat + "', '" + navn + "')";
				stmt = conn.createStatement();
				stmt.executeUpdate(query);
				
				System.out.println("Værforhold: ");
				String vær = scanner.nextLine();
				System.out.println("Temperatur: ");
				String temperatur = scanner.nextLine();
				
				String ute = "INSERT INTO Innendørsaktivitet (treningsID, værforhold, temperatur) "
						+ "VALUES ('" + treningsID + "', '" + vær + "', '" + temperatur + "')";
				stmt = conn.createStatement();
				stmt.executeUpdate(ute);
				System.out.println("La til uteetrening");
			}
			
			else if(input.equals("inne")){
				System.out.println("TreningsID: ");
				String treningsID = scanner.nextLine();
				System.out.println("Dato: ");
				String dato = scanner.nextLine();
				System.out.println("Tidspunkt: ");
				String tidspunkt = scanner.nextLine();
				System.out.println("Varighet: ");
				String varighet = scanner.nextLine();
				System.out.println("Personlig form: ");
				String personligForm = scanner.nextLine();
				System.out.println("Prestasjon: ");
				String prestasjon = scanner.nextLine();
				System.out.println("Notat: ");
				String notat = scanner.nextLine();
				System.out.println("Navn: ");
				String navn = scanner.nextLine();
				
				
						
				String query = "INSERT INTO Treningsøkt (treningsID, dato, tidspunkt, varighet, personligForm, prestasjon, notat, navn) "
							+ "VALUES ('" + treningsID + "', '" + dato + "', '" + tidspunkt + "', '" + varighet + "', '" + personligForm + "', '" + prestasjon 
							+ "', '" + notat + "', '" + navn + "')";
				stmt = conn.createStatement();
				stmt.executeUpdate(query);
				
				System.out.println("Luft: ");
				String luft = scanner.nextLine();
				System.out.println("Antall tilskuere: ");
				String tilskuere = scanner.nextLine();
				
				String inne = "INSERT INTO Innendørsaktivitet (treningsID, luft, tilskuere) "
						+ "VALUES ('" + treningsID + "', '" + luft + "', '" + tilskuere + "')";
				stmt = conn.createStatement();
				stmt.executeUpdate(inne);
				System.out.println("La til innetrening");
			}
			else{
						System.out.println("Try once more");
					}
				
		
			
		}
		
		catch (SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
		}
	}
	
	public static void main(String[] args) {
		main test = new main();
		try {
			test.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		test.sporring();
		test.insettTreningsØkt();
	}
	

}
