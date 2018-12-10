package csvReader;

import java.sql.DriverManager;

import javax.swing.JOptionPane;

import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;


import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;

public class Connection {
	private String url;
	private String user;
	private String password;

	Connect(String directory) {
		
		url = "jdbc:postgresql://localhost:5432/asap";
		user = "postgres";
		password = "Vicmps270100";
		
		try {
			Class.forName("org.postgresql.Driver");
			
			Connection con = DriverManager.getConnection(url, user, password);
			long rowsInserted = new CopyManager((BaseConnection) con)
					.copyIn("COPY testing FROM STDIN DELIMITER ';' CSV HEADER", new BufferedReader(
							new FileReader(directory)));
			System.out.printf("%d row(s) inserted%n", rowsInserted);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
