package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Footer;

public class FooterDAO {

	private Connection connection = ConnectionFactory.getConnection();

	public static void main(String[] args) {
		
		new FooterDAO().CriarTabelaFooter();
		//new FooterDAO().TestesTabelas();	
		
	}
	
	
	public void TestesTabelas() {
		
		FooterDAO footerDAO = new FooterDAO();

		try {
			footerDAO.CriarTabelaFooter();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			Footer f = new Footer();
			f.setEspacoBranco("       ");
			f.setIdentificacaoRegistro(123456);
			f.setNumeroSequencialRegistro(98797987);
			f.setValorTotaldosLancamentos(1000);

			footerDAO.adiciona(f);
		}

		footerDAO.DroparTabelaFooter();
		
	}

	public Connection getConnection() {
		Connection connection = null;
		try {
			return DriverManager.getConnection("jdbc:derby:acustraprograma");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}


	// Update Footer
	public void Update(Footer footer) {

		String sql = "UPDATE TABLE footer ( identificacaoRegistro INT, valorTotaldosLancamentos INT,"
				+ "espacoBranco VARCHAR(45), numeroSequencialRegistro INT)";

		try {
			connection = getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.execute();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Adicionar Objeto Footer
	public void adiciona(Footer footer) {

		try {
			String sql = "INSERT INTO footer (identificacaoRegistro, valorTotaldosLancamentos, "
					+ "espacoBranco, numeroSequencialRegistro) VALUES (?,?,?,?)";

			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, footer.getIdentificacaoRegistro());
			stmt.setInt(2, footer.getValorTotaldosLancamentos());
			stmt.setString(3, footer.getEspacoBranco());
			stmt.setInt(4, footer.getNumeroSequencialRegistro());

			stmt.execute();
			stmt.close();
			connection.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Dropar Tabela Footer
	public void DroparTabelaFooter() {

		try {
			String sql = "DROP TABLE footer";
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.execute();
			stmt.close();
			connection.close();
			System.out.println("Tabela footer deletada!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Read - Select
	public List<Footer> ResgatarLista() {

		List<Footer> listaFooter = new ArrayList<Footer>();

		try {
			PreparedStatement stmt = getConnection().prepareStatement("Select * From Footer");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Footer footer = new Footer();
				footer.setEspacoBranco(rs.getString("espacoBranco"));
				footer.setIdentificacaoRegistro(rs.getInt("identificacaoRegistro"));
				footer.setNumeroSequencialRegistro(rs.getInt("numeroSequencialRegistro"));
				footer.setValorTotaldosLancamentos(rs.getInt("valorTotaldosLancamentos"));

				listaFooter.add(footer);
			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaFooter;
	}

	// Criar Tabela Footer
	public void CriarTabelaFooter() 
	{		
		String sql = 
				"CREATE TABLE footer (identificacaoRegistro INT, valorTotaldosLancamentos INT," +
				"espacoBranco VARCHAR(45), numeroSequencialRegistro INT)";		
		try {
			connection = getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.execute();
			stmt.close();
			connection.close();			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Delete Table
	public void ExcluirTabelaHeader() throws SQLException 
	{		
		String sql = "DROP TABLE footer";

		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.execute();
		stmt.close();
		connection.close();
	}
}
