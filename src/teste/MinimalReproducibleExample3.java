package teste;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MinimalReproducibleExample3 {

	static String jdbcURL = "jdbc:derby:acustraprograma";
	static Connection connection;

	public static void main(String[] args) {

		
		// MinimalReproducibleExample3 minimal = new MinimalReproducibleExample3();
		// minimal.inserirValorEmEntidade();
		
	}	
	
	
	void inserirValorEmEntidade() 
	{
		MinimalReproducibleExample3 minimal = new MinimalReproducibleExample3();
		
		minimal.createTableIfDoesnotExist();
		try {
			String sql = "INSERT into Entity (tipoRegistro) values (?)";

			PreparedStatement stmt = getConnection().prepareStatement(sql);

			String numero = "123456789012";
			
			System.out.println("numero: " +  numero);
			
			stmt.setBigDecimal(1, new BigDecimal(numero));
			stmt.execute();
			stmt.close();

			getConnection().close();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		minimal.selectFromTable();
	}
	

	void selectFromTable() {
		try {
			String query = "SELECT * FROM ENTITY";

			PreparedStatement stmt = getConnection().prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getBigDecimal("tipoRegistro"));
			}
			stmt.close();

			System.out.println(stmt.toString());

			getConnection().close();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}	
	

	void createTableIfDoesnotExist() {
		try {
			String tableName = "ENTITY";
			String query = "SELECT TRUE FROM SYS.SYSTABLES WHERE TABLENAME = " + "? AND TABLETYPE = 'T'";

			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.setString(1, tableName);
			ResultSet rs = ps.executeQuery();

			if (!(rs.next() && rs.getBoolean(1))) {

				connection = DriverManager.getConnection(jdbcURL);
				String sql = "CREATE TABLE " + tableName + "(tipoRegistro DECIMAL(30,0))";

				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.execute();
				stmt.close();

				connection.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	void deleteTable() {
		try {
			String query = "DROP TABLE ENTITY";

			PreparedStatement stmt = getConnection().prepareStatement(query);

			stmt.execute();
			stmt.close();

			getConnection().close();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	static Connection getConnection() {
		Connection connection = null;
		try {
			return DriverManager.getConnection(jdbcURL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
