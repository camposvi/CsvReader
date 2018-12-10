package csvReader;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;

public class Connect {
	private String url;
	private String user;
	private String password;

	public Connection connectDB() throws SQLException {
		url = "jdbc:postgresql://localhost:5432/asap";
		user = "postgres";
		password = "Vicmps270100";

		return DriverManager.getConnection(url, user, password);

	}

	Connect() {

		try {
			Class.forName("org.postgresql.Driver");
			dropTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public long updateTable(String directory) {
		long insertedRows = 0;
		try {
			Connection con = connectDB();

			insertedRows = new CopyManager((BaseConnection) con).copyIn(
					"COPY testing FROM STDIN DELIMITER ';' CSV HEADER", new BufferedReader(new FileReader(directory)));

			System.out.printf("%d row(s) inserted%n", insertedRows);
			JOptionPane.showMessageDialog(null,(insertedRows+ " row(s) inserted") );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return insertedRows;

	}

	public long dropTable() {
		String SQL = "DELETE FROM testing";
		long affectedRows = 0;
		try (Connection con = connectDB(); PreparedStatement pstmt = con.prepareStatement(SQL)) {
			affectedRows = pstmt.executeUpdate();
			System.out.printf("%d row(s) affected%n", affectedRows);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return affectedRows;
	}
}
