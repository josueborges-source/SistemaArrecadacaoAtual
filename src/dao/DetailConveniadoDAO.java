package dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
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

public class DetailConveniadoDAO {

	Connection connection;	
	/*
	 cd C:\Users\josue\eclipse-workspace\SistemaDeArrecadacaoDeTerceiros\
	 SistemaDeArrecadacaoDeTerceiros\libs
	 java -jar derbyrun.jar ij
	 
	 connect
	 'jdbc:derby:C:/Users/josue/eclipse-workspace/SistemaDeArrecadacaoDeTerceiros/
	 SistemaDeArrecadacaoDeTerceiros/acustraprograma';
	  
	 show tables;
	 select * from DetailConveniado;
	 */
	public DetailConveniadoDAO() {
		connection = ConnectionFactory.getConnection();
	}

	public static void main(String[] args) 
	{		
		new DetailConveniadoDAO().TestesTabelas();		
		
	}

	public void TestesTabelas() {

		DetailConveniadoDAO detailConveniadoDAO = new DetailConveniadoDAO();

		detailConveniadoDAO.DroparTabela();
		detailConveniadoDAO.CriarTabela();
		detailConveniadoDAO.InsercaoTeste();

		List<DetailConveniado> listaDeDetail = detailConveniadoDAO.resgatarLista();

		for (DetailConveniado detailConveniado : listaDeDetail) {
			System.out.println(detailConveniado);
		}
	}
	

	public void CriarTabela() 
	{
		String query = 
				"CREATE TABLE DetailConveniado(" + "tipoRegistro INTEGER," + "codigoUnidadeConsumidora BIGINT,"
				+ "valorLancamento INTEGER," + "dataGeracaoRegistro DATE," + "comandoMovimento VARCHAR(255),"
				+ "codigoContaGerencial VARCHAR(255)," + "coberturaOcorrencia INTEGER,"
				+ "descricaoCoberturaOcorrencia DECIMAL(30,0),"
				+ "cpfCNPJ varchar(4) NOT NULL CHECK (cpfCNPJ IN('CPF', 'CNPJ'))," + "cpfCliente BIGINT,"
				+ "cnpjCliente BIGINT," + "complementoCNPJ INTEGER," + "mesVigencia DATE," + "mesFimVigencia DATE,"
				+ "numeroSequencialDoRegistro INTEGER)";

		try {
			connection = ConnectionFactory.getConnection();

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.execute();
			stmt.close();

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void DroparTabela() {

		System.out.println("Dropando tabela");
		try {
			String query = "DROP TABLE DetailConveniado";

			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(query);

			stmt.execute();
			stmt.close();

			connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void InsercaoTeste() {

		System.out.println("Inserindo Teste");
		try {
			String queryInsert = "INSERT into DetailConveniado ( tipoRegistro, codigoUnidadeConsumidora, valorLancamento,  dataGeracaoRegistro,"
					+ "comandoMovimento, codigoContaGerencial, coberturaOcorrencia, descricaoCoberturaOcorrencia, cpfCNPJ,cpfCliente,"
					+ "cnpjCliente, complementoCNPJ, mesVigencia, mesFimVigencia, numeroSequencialDoRegistro) "
					+ "values ( 1,  1,  1,  '1988-02-23' , 'XPTO','ContaXYZ',  1,  09876543211, 'CPF', 1234567890, "
					+ "1234567890, 02,'1988-02-23', '1988-02-23' , 123)";

			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(queryInsert);

			stmt.execute();
			stmt.close();

			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void Salvar(DetailConveniado detailConveniado) {

		System.out.println("Salvando detail conveniado");
		try {
			String sql = "INSERT into DetailConveniado ("
					+ "tipoRegistro, codigoUnidadeConsumidora, valorLancamento, dataGeracaoRegistro, "
					+ "comandoMovimento, codigoContaGerencial,coberturaOcorrencia, descricaoCoberturaOcorrencia,"
					+ "cpfCNPJ, cpfCliente, cnpjCliente, complementoCNPJ, mesVigencia,"
					+ "mesFimVigencia, numeroSequencialDoRegistro) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);

			stmt.setInt(1, detailConveniado.getTipoRegistro());
			stmt.setBigDecimal(2, new BigDecimal(detailConveniado.getCodigoUnidadeConsumidora().intValue()));
			stmt.setInt(3, detailConveniado.getValorLancamento());
			stmt.setDate(4, new Date(detailConveniado.getDataGeracaoRegistro().getTimeInMillis()));
			stmt.setString(5, detailConveniado.getComandoMovimento());

			stmt.setString(6, detailConveniado.getCodigoContaGerencial());
			stmt.setInt(7, detailConveniado.getCoberturaOcorrencia());
			stmt.setBigDecimal(8, new BigDecimal(detailConveniado.getDescricaoCoberturaOcorrencia()));
			stmt.setString(9, detailConveniado.getCpfCnpj().name());

			if (detailConveniado.getCpfCnpj() == DetailConveniado.CPF_CNPJ.CPF) {
				stmt.setBigDecimal(10, detailConveniado.getCpfCliente());
				stmt.setBigDecimal(11, null);
				stmt.setInt(12, Integer.valueOf(0));
			} else {
				stmt.setBigDecimal(10, null);
				stmt.setBigDecimal(11, detailConveniado.getCnpjCliente());
				stmt.setInt(12, detailConveniado.getComplementoCNPJ());
			}

			stmt.setDate(13, new Date(detailConveniado.getMesVigencia().getTimeInMillis()));

			if (detailConveniado.getMesFimVigenciaAtivado()) {
				stmt.setDate(14, new Date(detailConveniado.getMesFimVigencia().getTimeInMillis()));
			} else {
				stmt.setDate(14, null);
			}

			stmt.setInt(15, detailConveniado.getNumeroSequencialRegistro());

			stmt.execute();
			stmt.close();

			connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public List<DetailConveniado> resgatarLista() {

		// System.out.println("Retornando lista");
		List<DetailConveniado> detailConveniadoLista = new ArrayList<DetailConveniado>();
		try {

			PreparedStatement stmt = ConnectionFactory.getConnection()
					.prepareStatement("select * from detailConveniado");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				DetailConveniado detailConveniado = new DetailConveniado();

				detailConveniado.setCoberturaOcorrencia(Integer.valueOf(rs.getString("coberturaOcorrencia")));
				
				
				
				detailConveniado
						.setNumeroSequencialRegistro(Integer.valueOf(rs.getString("numeroSequencialDoRegistro")));
				detailConveniado.setValorLancamento(Integer.valueOf(rs.getString("valorLancamento")));
				detailConveniado.setComandoMovimento(rs.getString("comandoMovimento"));

				String mesVigencia = rs.getString("mesVigencia");

				// Mes Vigencia
				Calendar mesVigenciaCalendar = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

				try {
					mesVigenciaCalendar.setTime(sdf.parse(mesVigencia));
				} catch (ParseException e) {
					e.printStackTrace();
				}

				detailConveniado.setMesVigencia(mesVigenciaCalendar);

				
				
				// Mes Fim Vigencia
				String mesFimVigencia = rs.getString("mesFimVigencia");
				if(mesFimVigencia!=null) {
				System.out.println(mesFimVigencia);
				try {
					mesVigenciaCalendar.setTime(sdf.parse(mesFimVigencia));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				}

				detailConveniado.setMesFimVigencia(mesVigenciaCalendar);

				// Mes Data Geracao Registro
				String dataGeracaoServico = rs.getString("dataGeracaoRegistro");
				try {
					mesVigenciaCalendar.setTime(sdf.parse(dataGeracaoServico));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				detailConveniado.setDataGeracaoRegistro(mesVigenciaCalendar);

				String cpfCliente = rs.getString("cpfCliente");
				String cnpjCliente = rs.getString("cnpjCliente");
				
				System.out.println(cpfCliente);
				System.out.println(cnpjCliente);

				if (cpfCliente != null) {
					detailConveniado.setCnpjCliente(new BigDecimal(cpfCliente));
				} else {
					detailConveniado.setComplementoCNPJ(rs.getInt("complementoCNPJ"));					
					detailConveniado.setCpfCliente(new BigDecimal(cnpjCliente));
				}

				String codigoUnidadeConsumidora = rs.getString("codigoUnidadeConsumidora");
				Long codigoUnidadeConsumidoraToLong = Long.valueOf(codigoUnidadeConsumidora);
				detailConveniado.setCodigoUnidadeConsumidora(codigoUnidadeConsumidoraToLong);

				String coberturaOcorrencia = rs.getString("coberturaOcorrencia");
				BigInteger coberturaToInt = BigInteger.valueOf(Long.parseLong(coberturaOcorrencia));
				detailConveniado.setDescricaoCoberturaOcorrencia(coberturaToInt);

				String numeroSequencial = rs.getString("numeroSequencialDoRegistro");
				Integer numeroSequencialToInt = Integer.valueOf(numeroSequencial);
				detailConveniado.setNumeroSequencialRegistro(numeroSequencialToInt);

				detailConveniadoLista.add(detailConveniado);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return detailConveniadoLista;
	}
}
