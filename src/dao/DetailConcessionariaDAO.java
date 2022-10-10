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

public class DetailConcessionariaDAO {

	Connection connection;

	public static void main(String[] args) {
		new DetailConcessionariaDAO().TestesTabelas();
	}

	public DetailConcessionariaDAO() {
		connection = ConnectionFactory.getConnection();
	}

	public void TestesTabelas() {

		DetailConcessionariaDAO detailConcessionariaDAO = new DetailConcessionariaDAO();

		detailConcessionariaDAO.DroparTabela();
		detailConcessionariaDAO.CriarTabela();
		detailConcessionariaDAO.InsercaoTeste();

		List<DetailConveniado> listaDeDetail = detailConcessionariaDAO.ResgatarLista();

		for (DetailConveniado detailConveniado : listaDeDetail) {
			System.out.println(detailConveniado);
		}
	}

	public void CriarTabela() {
		String query = "CREATE TABLE DetailConcessionaria(" + "id INTEGER," + "identificacaoRegistro BIGINT,"
				+ "codigoUnidadeConsumidora BIGINT" + "valorLancamento INTEGER," + "dataLancamento DATE,"
				+ "informativoRegistro VARCHAR(255)," + "codigoContaGerencial VARCHAR(255),"
				+ "coberturaOcorrencia INTEGER," + "descricaoCoberturaOcorrencia DECIMAL(30,0),"
				+ "cpfCNPJ varchar(4) NOT NULL CHECK " + "(cpfCNPJ IN('CPF', 'CNPJ'))," + "cpfCliente BIGINT,"
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
		try {
			String queryInsert = "INSERT into DetailConcessionaria ( identificacaoRegistro, codigoUnidadeConsumidora,"
					+ " valorLancamento,  dataGeracaoRegistro, comandoMovimento, codigoContaGerencial,"
					+ " coberturaOcorrencia, descricaoCoberturaOcorrencia, cpfCNPJ,cpfCliente,"
					+ "cnpjCliente, complementoCNPJ, mesVigencia, mesFimVigencia," + " numeroSequencialDoRegistro) "
					+ "values ( 1,  1,  1,  '1988-02-23' , 'XPTO','ContaXYZ',  1,  09876543211, "
					+ "'CPF', 1234567890, 1234567890, 02,'1988-02-23', '1988-02-23' , 123)";

			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(queryInsert);

			stmt.execute();
			stmt.close();

			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	// CREATE //
	public void Salvar(DetailConveniado detailConveniado) {

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

			if (detailConveniado.getCpfCnpj() == DetailConveniado.CPF_CNPJ.CPF) {
				stmt.setString(9, detailConveniado.getCpfCnpj().name());
				stmt.setBigDecimal(10, detailConveniado.getCpfCliente());
				stmt.setBigDecimal(11, null);
				stmt.setInt(12, Integer.valueOf(0));
			} else {
				stmt.setString(9, detailConveniado.getCpfCnpj().name());
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

	// READ //
	public List<DetailConveniado> ResgatarLista() {

		List<DetailConveniado> detailConveniadoLista = new ArrayList<DetailConveniado>();

		try {

			PreparedStatement stmt = ConnectionFactory.getConnection()
					.prepareStatement("select * from detailConveniado");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				DetailConveniado detailConveniado = new DetailConveniado();

				detailConveniado.setCoberturaOcorrencia(Integer.valueOf(rs.getString("coberturaOcorrencia")));
				detailConveniado.setComplementoCNPJ(Integer.valueOf(rs.getString("cnpjCliente")));
				detailConveniado
						.setNumeroSequencialRegistro(Integer.valueOf(rs.getString("numeroSequencialDoRegistro")));

				detailConveniado.setValorLancamento(Integer.valueOf(rs.getString("valorLancamento")));
				detailConveniado.setComandoMovimento(rs.getString("comandoMovimento"));

				String mesVigencia = rs.getString("mesVigencia");

				Calendar mesVigenciaCalendar = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

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

				detailConveniado.setMesFimVigencia(mesVigenciaCalendar);

				String dataGeracaoServico = rs.getString("dataGeracaoRegistro");
				try {
					mesVigenciaCalendar.setTime(sdf.parse(dataGeracaoServico));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				detailConveniado.setMesFimVigencia(mesVigenciaCalendar);

				detailConveniado.setDataGeracaoRegistro(mesVigenciaCalendar);
				detailConveniado.setComplementoCNPJ(rs.getInt("complementoCNPJ"));

				String cpfCliente = rs.getString("cpfCliente");
				String cnpjCliente = rs.getString("cnpjCliente");

				if (cpfCliente.isEmpty()) {
					detailConveniado.setCnpjCliente(new BigDecimal(cpfCliente));
				} else {
					detailConveniado.setCpfCliente(new BigDecimal(cnpjCliente));
				}

				String codUnidadeConsumidora = rs.getString("codigoUnidadeConsumidora");
				Long codUnidadeConsumidoraToLong = Long.valueOf(codUnidadeConsumidora);
				detailConveniado.setCodigoUnidadeConsumidora(codUnidadeConsumidoraToLong);

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

	// UPDATE //
	public void Alterar(DetailConveniado detailConveniado) {

		try {

			String sql = "UPDATE DetailConveniado set " + "tipoRegistro=?," + "codigoUnidadeConsumidora=?,"
					+ "valorLancamento=?," + "dataGeracaoRegistro=?," + "comandoMovimento=?,"
					+ "codigoContaGerencial=?," + "coberturaOcorrencia=?," + "descricaoCoberturaOcorrencia=?,"
					+ "cpfCNPJ=?," + "cpfCliente=?," + "cnpjCliente=?," + "complementoCNPJ=?," + "mesVigencia=?,"
					+ "mesFimVigencia=?," + "numeroSequencialDoRegistro=? where id=?)";

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

			stmt.setInt(16, detailConveniado.getId());

			stmt.execute();
			stmt.close();

			connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	// DELETE //
	public void Deletar(DetailConveniado detailConveniado) {

		try {

			String sql = "DELETE DetailConveniado where id=?";

			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);

			stmt.setInt(1, detailConveniado.getId());

			stmt.execute();
			stmt.close();

			connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
