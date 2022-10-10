package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Envio;
import model.Footer;

public class EnvioDAO 
{	
	private Connection connection = ConnectionFactory.getConnection();
	
	// Adicionar Objeto Footer
		public void adiciona(Envio envio) {			
			try {
			
				String sql = "INSERT INTO envio (header_id, ,footer_id) "
						+ "VALUES (?,?,?)";

				PreparedStatement stmt = connection.prepareStatement(sql);

				//stmt.setInt(1, envio);

				stmt.execute();
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
}
