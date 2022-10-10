package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {


	private static String jdbcURL = "jdbc:derby:acustraprograma";

	/*
	 java -jar derbyrun.jar ij connect
	 'jdbc:derby:C:/Users/josue/eclipse-workspace/SistemaDeArrecadacaoDeTerceiros/
	 SistemaDeArrecadacaoDeTerceiros/acustraprograma'; show tables; select * from
	 DetailConveniado;
	 */
	public static Connection getConnection() {

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(jdbcURL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
