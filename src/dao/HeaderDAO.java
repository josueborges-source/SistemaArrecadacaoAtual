package dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import model.DetailConveniado;
import model.Header;

import java.sql.Date;

public class HeaderDAO 
{

	static HeaderDAO dao = new HeaderDAO();

	Connection connection;

	public HeaderDAO() 
	{		
		connection = ConnectionFactory.getConnection();
	}

	public static void main(String[] args) throws SQLException {		
		dao.CriarTabelaHeader();
		//dao.ExcluirTabelaHeader();		
	}

	// Create
	public void adiciona(Header header) {

		try {

			String sql = "INSERT into header (identificacaoRegistro, contrato, codConcessionaria,"
					+ "dataEnvio, siglaMoeda, numeroSequencialEnvio, motivosRecusa , "
					+ "nomeClienteContratante, tipoArquivo, numeroSequencialRegistro)"
					+ " values (?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, header.getIdentificacaoRegistro().intValue());
			stmt.setString(2, header.getContrato());
			stmt.setInt(3, header.getCodigoConcessionaria().intValue());
			stmt.setDate(4, new Date(header.getDataEnvio().getTimeInMillis()));

			stmt.setString(5, header.getSiglaMoeda());
			stmt.setInt(6, header.getNumeroSequencialEnvio().intValue());
			stmt.setInt(7, header.getMotivoRecusa().intValue());
			stmt.setString(8, header.getNomeClienteContratante());
			stmt.setInt(9, header.getTipoArquivo().intValue());
			stmt.setInt(10, header.getNumeroSequencialRegistro().intValue());

			stmt.execute();
			stmt.close();

			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Read - Resgatar Lista
	public List<DetailConveniado> ResgatarLista() {

		List<DetailConveniado> detailConveniadoLista = new ArrayList<DetailConveniado>();

		try {

			PreparedStatement stmt = ConnectionFactory.getConnection()
					.prepareStatement("Select * From Header");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				DetailConveniado detailConveniado = new DetailConveniado();

				detailConveniado.setCoberturaOcorrencia(Integer.valueOf(rs.getString("coberturaOcorrencia")));
				detailConveniado.setComplementoCNPJ(Integer.valueOf(rs.getString("cnpjCliente")));
				detailConveniado
						.setNumeroSequencialRegistro(Integer.valueOf(rs.getString("numeroSequencialDoRegistro")));
				detailConveniado.setValorLancamento(Integer.valueOf(rs.getString("valorLancamento")));
				detailConveniado.setComandoMovimento(rs.getString("comandoMovimento"));

				Calendar mesVigenciaCalendar;
				SimpleDateFormat sdf;

				String mesVigencia = rs.getString("mesVigencia");
				mesVigenciaCalendar = Calendar.getInstance();
				sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

				try {
					mesVigenciaCalendar.setTime(sdf.parse(mesVigencia));
				} catch (ParseException e) {
					e.printStackTrace();
				}

				detailConveniado.setMesVigencia(mesVigenciaCalendar);

				String mesFimVigencia = rs.getString("mesFimVigencia");

				try {
					mesVigenciaCalendar.setTime(sdf.parse(mesFimVigencia));
				} catch (ParseException e) {
					e.printStackTrace();
				}

				String dataGeracaoServico = rs.getString("dataGeracaoRegistro");
				String cpfCliente = rs.getString("cpfCliente");
				String cnpjCliente = rs.getString("cnpjCliente");
				String codUnidadeConsumidora = rs.getString("codigoUnidadeConsumidora");
				Long codUnidadeConsumidoraToLong = Long.valueOf(codUnidadeConsumidora);
				String coberturaOcorrencia = rs.getString("coberturaOcorrencia");
				BigInteger coberturaToInt = BigInteger.valueOf(Long.parseLong(coberturaOcorrencia));
				String numeroSequencial = rs.getString("numeroSequencialDoRegistro");
				Integer numeroSequencialToInt = Integer.valueOf(numeroSequencial);

				try {
					mesVigenciaCalendar.setTime(sdf.parse(dataGeracaoServico));
				} catch (ParseException e) {
					e.printStackTrace();
				}

				detailConveniado.setMesFimVigencia(mesVigenciaCalendar);
				detailConveniado.setMesFimVigencia(mesVigenciaCalendar);

				detailConveniado.setDataGeracaoRegistro(mesVigenciaCalendar);
				detailConveniado.setComplementoCNPJ(rs.getInt("complementoCNPJ"));

				detailConveniado.setCodigoUnidadeConsumidora(codUnidadeConsumidoraToLong);
				detailConveniado.setDescricaoCoberturaOcorrencia(coberturaToInt);
				detailConveniado.setNumeroSequencialRegistro(numeroSequencialToInt);

				if (cpfCliente.isEmpty()) {
					detailConveniado.setCnpjCliente(new BigDecimal(cpfCliente));
				} else {
					detailConveniado.setCpfCliente(new BigDecimal(cnpjCliente));
				}

				detailConveniadoLista.add(detailConveniado);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return detailConveniadoLista;
	}

	// Update Header
	public void Update(Header header) {
			
		String sql = "UPDATE Header set contrato=?, codigoConcessionaria=?,"
				+ "dataEnvio=?, siglaMoeda=?, numeroSequencialEnvio=?,"
				+ "motivoRecusa=?, nomeClienteContratante=?, espacoBrancoQuarenta=?,"
				+ "tipoArquivo=?, numeroSequencialRegistro=? where id=?";

		PreparedStatement stmt;
		try {			
			stmt = ConnectionFactory.getConnection().prepareStatement(sql);

			stmt.setString(1, header.getContrato());
			stmt.setInt(2, header.getCodigoConcessionaria());
			stmt.setDate(3, new Date(header.getDataEnvio().getTimeInMillis()));
			stmt.setString(4, header.getSiglaMoeda());
			stmt.setInt(5, header.getNumeroSequencialEnvio());
			stmt.setInt(6, header.getMotivoRecusa());
			stmt.setString(7, header.getNomeClienteContratante());
			stmt.setString(8, header.getEspacoBrancoQuarenta());
			stmt.setInt(9, header.getTipoArquivo());
			stmt.setInt(10, header.getNumeroSequencialRegistro());
			stmt.setInt(11, header.getId());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Delete - Header
	public void Delete(Header header) throws SQLException 
	{
		String sql = "DELETE FROM header WHERE id=?";

		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setInt(1, header.getId());

		stmt.execute();
		stmt.close();

		connection.close();
	
	}

	
	public void CriarTabelaHeader() throws SQLException {
		
		String sql = "CREATE TABLE header (identificacaoRegistro VARCHAR(1), "
				+ "contrato VARCHAR(56) DEFAULT '01000000002022908203774000AC1',"
				+ "codConcessionaria VARCHAR(4) DEFAULT '0001', "
				+ "dataEnvio DATE, siglaMoeda VARCHAR(6) DEFAULT 'R$', numeroSequencialEnvio INT, "
				+ "motivosRecusa VARCHAR(2), nomeClienteContratante VARCHAR(20) DEFAULT 'ASSOCIACAO CULTURAL', "
				+ "tipoArquivo VARCHAR(1), numeroSequencialRegistro INT NOT NULL PRIMARY KEY )";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt.execute();
		stmt.close();
		connection.close();
	}
	
	public void ExcluirTabelaHeader() throws SQLException {
		String sql = "DROP TABLE header";

		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.execute();
		stmt.close();
		connection.close();
	}
	
	public Integer SelecionarMaiorID() throws SQLException {
		
		Integer id = 0;
		
		String sql = "select max(id) from TABLE header";

		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		
		if (rs.next()) {
			id = rs.getInt("max_id") + 1;
		}
		return id;
	}




}
