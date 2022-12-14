package view.TelasCadastro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import dao.DetailConveniadoDAO;
import model.DetailConveniado;
import model.ValidacoesDetailConveniado.ModeloDeValidacaoTextField;
import model.ValidacoesDetailConveniado.ValidacaoDeTodosCamposNovoCadastro;
import util.Util;

public class NovoCadastroPainelBackup {

	final JPanel novoCadastro = new JPanel();

	final JLabel dataAtualDeCadastroText = new JLabel("17/07/2022");
	final JTextField cpfCnpjTextField = new JTextField();
	final JLabel registroLabel = new JLabel("Registro:");
	final JLabel cadastroLabel = new JLabel("Cadastro:");
	final JLabel unidadeConsumidoraTextField_1 = new JLabel("Unidade Consumidora:");
	final JTextField unidadeConsumidoraTextField = new JTextField();
	final JLabel inicioLabel = new JLabel("Inicio:");
	final JLabel valorDoacao = new JLabel("Valor Doação:");
	final JTextField valorDoacaoTextField = new JTextField();
	final JButton salvarCadastroButton = new JButton("Salvar Cadastro");
	final JTextField comandoDeMovimentoTextField = new JTextField();
	final JTextField descricaoOcorrenciaTextField = new JTextField();
	final JLabel descricaoDeOcorrenciaLabel = new JLabel("Descrição de Ocorrência:");
	final JTextField coberturaOcorrenciaTextField = new JTextField();

	final JLabel registroTextField = new JLabel("000001");
	final JDateChooser dateChooserInicio = new JDateChooser();
	final JDateChooser dateChooserFim = new JDateChooser();

	final JRadioButton cpf_rdnButton = new JRadioButton("CPF");
	final JRadioButton cnpj_rdnButton = new JRadioButton("CNPJ");

	final JLabel labelFim = new JLabel("Fim:");
	final JCheckBox chckbxTmpContrato = new JCheckBox("Tempo de contrato definido");

	Date dateAndTime = Calendar.getInstance().getTime();

	JLabel comandoDeMovimentoLabel = new JLabel("Comando de Movimento:");
	JLabel coberturaOcorrencia = new JLabel("Cobertura de Ocorrência");

	final ButtonGroup cpfCnpjButtonGroup = new ButtonGroup();

	public void popularCamposDoPainelParaTeste() {
		cpfCnpjTextField.setText("12345678901");
		unidadeConsumidoraTextField.setText("123456789012");
		valorDoacaoTextField.setText("123");
		comandoDeMovimentoTextField.setText("12");
		descricaoOcorrenciaTextField.setText("123456789012345678901234567890");
		coberturaOcorrenciaTextField.setText("12");
	}

	public JPanel retornaPainel() {

		inicializarComponentes();
		popularCamposDoPainelParaTeste();

		JPanel novoCadastro = new JPanel();

		novoCadastro.add(comandoDeMovimentoLabel);
		novoCadastro.add(cpfCnpjTextField);
		novoCadastro.add(registroLabel);
		novoCadastro.add(registroTextField);
		novoCadastro.add(cadastroLabel);
		novoCadastro.add(dataAtualDeCadastroText);
		novoCadastro.add(dateChooserInicio);
		novoCadastro.add(dateChooserFim);
		novoCadastro.add(cpf_rdnButton);
		novoCadastro.add(cnpj_rdnButton);
		novoCadastro.add(unidadeConsumidoraTextField_1);
		novoCadastro.add(labelFim);
		novoCadastro.add(comandoDeMovimentoTextField);
		novoCadastro.add(descricaoOcorrenciaTextField);
		novoCadastro.add(coberturaOcorrencia);
		novoCadastro.add(descricaoDeOcorrenciaLabel);
		novoCadastro.add(coberturaOcorrenciaTextField);
		novoCadastro.add(unidadeConsumidoraTextField);
		novoCadastro.add(inicioLabel);
		novoCadastro.add(valorDoacao);
		novoCadastro.add(valorDoacaoTextField);
		novoCadastro.add(chckbxTmpContrato);
		novoCadastro.add(salvarCadastroButton);

		cpfCnpjButtonGroup.add(cpf_rdnButton);
		cpfCnpjButtonGroup.add(cnpj_rdnButton);

		chckbxTmpContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean checkBoxcontratoTemporarioAtivado = chckbxTmpContrato.isSelected();

				dateChooserFim.setEnabled(checkBoxcontratoTemporarioAtivado);
				labelFim.setEnabled(checkBoxcontratoTemporarioAtivado);

				// System.out.println(checkBoxcontratoTemporarioAtivado);

			}
		});

		salvarCadastroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DetailConveniadoDAO detailDAO = new DetailConveniadoDAO();

				// DETAIL CONVENIADO MODELO
				DetailConveniado detailConveniado = new DetailConveniado();
				/// ArrayList<ModeloDeValidacaoTextField> camposDeValidacao = new
				/// ArrayList<ModeloDeValidacaoTextField>();

				boolean ultimoModeloEValido = true;

				boolean todosCamposSaoValidos = new ValidacaoDeTodosCamposNovoCadastro()
						.TodosCamposDoPainelSaoValidos();

				if (todosCamposSaoValidos) {
					detailConveniado.setCodigoUnidadeConsumidora(null);

					detailConveniado.setComandoMovimento(null);

					detailConveniado.setValorLancamento(null);

					detailConveniado.setCoberturaOcorrencia(null);

					detailConveniado.setDescricaoCoberturaOcorrencia(null);

					detailConveniado.setCpfCliente(null);

					detailConveniado.setCnpjCliente(null);

					String registroNumero = registroTextField.getText();
					detailConveniado.setNumeroSequencialRegistro(Integer.valueOf(registroNumero));

					Date data = dateChooserInicio.getDate();

					Calendar calendar = Calendar.getInstance();
					calendar.setTime(data);

					calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
					detailConveniado.setMesVigencia(calendar);

					if (chckbxTmpContrato.isSelected()) {

						data = dateChooserFim.getDate();

						calendar.setTime(data);

						calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);

						detailConveniado.setMesFimVigencia(calendar);
						/*
						 * System.out.println(detailConveniado.getMesVigencia().get(Calendar.
						 * DAY_OF_MONTH));
						 * System.out.println(detailConveniado.getMesVigencia().get(Calendar.MONTH));
						 * System.out.println(detailConveniado.getMesVigencia().get(Calendar.YEAR));
						 */
					}
					detailDAO.Salvar(detailConveniado);
				}
				/*
				 * Data Inicio Chooser
				 */

				/*
				 * System.out.println(detailConveniado.getMesVigencia().get(Calendar.
				 * DAY_OF_MONTH));
				 * System.out.println(detailConveniado.getMesVigencia().get(Calendar.MONTH));
				 * System.out.println(detailConveniado.getMesVigencia().get(Calendar.YEAR));
				 */

				

				/// Fim Implantação

				/*
				 * if (!errosNoPainel) { detailDAO.Salvar(detailConveniado); }
				 */

				/*
				 * Campo 2.01: Identificação do Tipo De Registro. tipoRegistro CHAR Tamanho
				 * Mínimo: 1 caracteres, Tamanho Máximo: 1 Caracteres - Fixo
				 */

				/*
				 * Campo 2.02: Código Unidade Consumidora TextField: Unidade Consumidora
				 * codigoUnidadeConsumidora NUM Tamanho Mínimo: 0 caracteres, Tamanho Máximo: 13
				 * caracteres Deve ser preenchido com zeros à esquerda do inteiro como formato
				 * original
				 */
				ModeloDeValidacaoTextField unidadeConsumidoraModelo = new ModeloDeValidacaoTextField(
						ModeloDeValidacaoTextField.Regex.Numerico, unidadeConsumidoraTextField, "Unidade Consumidora",
						0, 13);

				ultimoModeloEValido = unidadeConsumidoraModelo.eValido();

				if (ultimoModeloEValido) {
					detailConveniado.setCodigoUnidadeConsumidora(unidadeConsumidoraModelo.getConteudoToLong());
				}

				/*
				 * Campo 2.03: Valor de Lançamento TextField: Valor Doação valorLancamento NUM
				 * Tamanho Mínimo: 1 caractere, Tamanho Máximo: 9 caracteres Máximo de 1
				 * caractere com zeros a esquerda (ex: R5,00 = 000000500).
				 */

				ModeloDeValidacaoTextField valorDoacaoModelo = new ModeloDeValidacaoTextField(
						ModeloDeValidacaoTextField.Regex.Numerico, valorDoacaoTextField, "Valor Doacao", 1, 9);

				ultimoModeloEValido = valorDoacaoModelo.eValido();

				if (ultimoModeloEValido) {
					detailConveniado.setValorLancamento(valorDoacaoModelo.getConteudoToInteger());
				}

				/*
				 * Campo 2.04: Data de Geração de Registro dataGeracaoRegistro NUM Label:
				 * Cadastro Tamanho Mínimo: 8 caracteres, Tamanho Máximo: 8 caracteres Ver no
				 * arquivo “Layout Completo - Convenio - Arrecadacao Terceiros.pdf”, Nota 15.
				 */
				String dataDeCadastro = dataAtualDeCadastroText.getText();

				System.out.println(dataDeCadastro);

				dataDeCadastro = Util.SomenteNumeros(dataDeCadastro);
				detailConveniado.setDataGeracaoRegistro(Calendar.getInstance());

				/*
				 * Campo 2.05: Comando de Movimento CHAR TextField:
				 * codigoComandoMovimentoJTextField Nota 07 Tamanho Mínimo: 2 Tamanho Máximo: //
				 * 2 caracteres
				 */

				ModeloDeValidacaoTextField comandoDoMovimentoModelo = new ModeloDeValidacaoTextField(
						ModeloDeValidacaoTextField.Regex.Numerico, comandoDeMovimentoTextField, "Comando de Movimento",
						1, 2);

				ultimoModeloEValido = comandoDoMovimentoModelo.eValido();

				if (ultimoModeloEValido) {
					detailConveniado.setComandoMovimento(comandoDoMovimentoModelo.getConteudoTextField());
				}

				/*
				 * Campo 2.06: Código de Conta Gerencial CHAR TextField: Tamanho: 8 CHAR - Fixo
				 */

				/*
				 * Campo 2.07: Cobertura/Ocorrência Gerencial CHAR Tamanho Mínimo: 2 caracteres
				 * Tamanho Máximo: 2 caracteres Nota 09
				 */

				ModeloDeValidacaoTextField coberturaOcorrenciaModelo = new ModeloDeValidacaoTextField(
						ModeloDeValidacaoTextField.Regex.Numerico, coberturaOcorrenciaTextField,
						"Cobertura de Ocorrência", 1, 2);

				ultimoModeloEValido = coberturaOcorrenciaModelo.eValido();

				if (ultimoModeloEValido) {
					Integer integer = Integer.valueOf(coberturaOcorrenciaModelo.getConteudoTextField());
					System.out.println("Integer: " + integer);
					detailConveniado.setCoberturaOcorrencia(integer);
				}

				/*
				 * Campo 2.08: Descrição da Cobertura/Ocorrencia descricaoCoberturaOcorrencia
				 * CHAR TextField: Descrição da Ocorrência Nota 09 Tamanho Mínimo: // Tamanho
				 * Máximo: 30 caracteres
				 */
				ModeloDeValidacaoTextField descricaoCoberturaOcorrenciaModelo = new ModeloDeValidacaoTextField(
						ModeloDeValidacaoTextField.Regex.Numerico, descricaoOcorrenciaTextField,
						"Descricao De Ocorrencia", 0, 30);

				ultimoModeloEValido = descricaoCoberturaOcorrenciaModelo.eValido();

				if (ultimoModeloEValido) {
					detailConveniado.setDescricaoCoberturaOcorrencia(
							new BigInteger(descricaoCoberturaOcorrenciaModelo.getConteudoTextField()));
				}
				/*
				 * Campo 2.09: Desabilitado Tamanho: 10
				 */

				/*
				 * Campo 2.10: Desabilitado Tamanho: 6
				 */

				/*
				 * Campo 2.11: CPF/CNPJ CPF CHAR / CNPJ CHAR TextField: CPF_CNPJ (?) Nota
				 * Referência: 10 CPF - Tamanho Mínimo: 11 caracteres// Tamanho Máximo: 11
				 * caracteres CNPJ - Tamanho Mínimo: 12 caracteres// Tamanho Máximo: 12
				 * caracteres
				 */
				if (ultimoModeloEValido) {
					boolean cpfAtivado = cpf_rdnButton.isSelected();

					String cpfCnpj = Util.SomenteNumeros(cpfCnpjTextField.getText());
					// cpfCnpjTextField.setText(cpfCnpj);

					ModeloDeValidacaoTextField cpfCNPJModelo = new ModeloDeValidacaoTextField(cpfCnpjTextField,
							"CPF/CNPJ");
					cpfCNPJModelo.adicionarRegexDeValidacao(ModeloDeValidacaoTextField.Regex.Numerico);

					if (cpfAtivado) {
						cpfCNPJModelo.setTamanhoMinimo(11);
						cpfCNPJModelo.setTamanhoMaximo(11);
					} else {
						cpfCNPJModelo.setTamanhoMinimo(12);
						cpfCNPJModelo.setTamanhoMaximo(12);
					}

					ultimoModeloEValido = cpfCNPJModelo.eValido();

					/// Verificação de Formato Válido
					if (ultimoModeloEValido) {

						if (cpfAtivado) {
							detailConveniado.setCpfCnpj(DetailConveniado.CPF_CNPJ.CPF);

							String modeloTextField = cpfCNPJModelo.getTextField().getText();
							Long valor = Long.valueOf(modeloTextField);
							BigDecimal cpfValor = BigDecimal.valueOf(valor);

							detailConveniado.setCpfCliente(cpfValor);
						} else {
							detailConveniado.setCpfCnpj(DetailConveniado.CPF_CNPJ.CNPJ);

							String cnpj = cpfCNPJModelo.getTextField().getText();
							detailConveniado.setCnpjCliente(
									BigDecimal.valueOf(Long.getLong(cnpj.substring(0, cnpj.length() - 2))));

							Integer inteiro = Integer.valueOf(cnpj.substring(cnpj.length() - 2));

							detailConveniado.setComplementoCNPJ(inteiro);
						}
					}
				}

				/*
				 * Campo 2.12: Mês de inicio vigência. Início cobrança. Sempre utilizar o mês
				 * seguinte.
				 */

				/*
				 * Date data = dateChooserInicio.getDate();
				 * 
				 * Calendar calendar = Calendar.getInstance(); calendar.setTime(data);
				 * 
				 * calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
				 * 
				 * detailConveniado.setMesVigencia(calendar);
				 * 
				 * System.out.println(detailConveniado.getMesVigencia().get(Calendar.
				 * DAY_OF_MONTH));
				 * System.out.println(detailConveniado.getMesVigencia().get(Calendar.MONTH));
				 * System.out.println(detailConveniado.getMesVigencia().get(Calendar.YEAR));
				 */

				/*
				 * Integer dia = calendar.get(Calendar.DAY_OF_MONTH); Integer mes =
				 * calendar.get(Calendar.MONTH) + 1; Integer ano = calendar.get(Calendar.YEAR);
				 */
				/*
				 * String diaTexto; String mesTexto; String anoTexto;
				 * 
				 * if(dia < 10) { diaTexto = "0" + dia; } else { diaTexto = dia.toString(); }
				 * if(mes < 10) { mesTexto = "0" + dia; } else { mesTexto = mes.toString(); }
				 * anoTexto=ano.toString();
				 */
				/*
				 * System.out.println(dia +"" + mes + "" + ano); Integer mesAno =
				 * Integer.getInteger(mes + "" + ano); System.out.println(mesAno);
				 */

				/*
				 * Campo 2.13: Mês de fim vigência. Fim vigência.
				 */			

				/*
				 * Campo 2.14
				 */
				/*
				 * if (ultimoModeloEValido) { String registroNumero =
				 * registroTextField.getText();
				 * detailConveniado.setNumeroSequencialRegistro(Integer.valueOf(registroNumero))
				 * ; }
				 * 
				 * if (!errosNoPainel) { detailDAO.Salvar(detailConveniado); }
				 */
			}
		});

		return novoCadastro;
	}

	public void inicializarComponentes() {
		/// System.out.println("Init");
		labelFim.setEnabled(false);
		labelFim.setBounds(646, 351, 47, 14);
		cpfCnpjTextField.setBounds(178, 229, 588, 22);
		registroLabel.setBounds(423, 14, 46, 14);
		registroTextField.setBounds(498, 14, 46, 14);
		cadastroLabel.setBounds(25, 408, 53, 14);
		dataAtualDeCadastroText.setBounds(97, 408, 79, 14);
		dateChooserInicio.setBounds(443, 347, 108, 20);
		dateChooserFim.setBounds(675, 347, 108, 20);
		cpf_rdnButton.setBounds(25, 228, 53, 23);
		cpf_rdnButton.setSelected(true);
		cnpj_rdnButton.setBounds(80, 228, 53, 23);
		unidadeConsumidoraTextField_1.setBounds(26, 14, 123, 14);
		comandoDeMovimentoTextField.setBounds(178, 85, 216, 23);
		descricaoOcorrenciaTextField.setBounds(550, 155, 216, 23);
		coberturaOcorrencia.setBounds(26, 159, 123, 14);
		descricaoDeOcorrenciaLabel.setBounds(423, 159, 123, 14);
		coberturaOcorrenciaTextField.setBounds(178, 155, 216, 23);
		comandoDeMovimentoLabel.setBounds(26, 89, 123, 14);
		unidadeConsumidoraTextField.setBounds(178, 11, 216, 20);
		inicioLabel.setBounds(397, 351, 47, 14);
		valorDoacao.setBounds(423, 88, 79, 14);
		valorDoacaoTextField.setBounds(550, 85, 216, 20);
		chckbxTmpContrato.setBounds(25, 347, 223, 23);
		salvarCadastroButton.setBounds(25, 433, 872, 50);

		valorDoacaoTextField.setColumns(10);
		comandoDeMovimentoTextField.setColumns(10);
		descricaoOcorrenciaTextField.setColumns(10);
		cpfCnpjTextField.setColumns(10);
		coberturaOcorrenciaTextField.setColumns(10);
		unidadeConsumidoraTextField.setColumns(10);
	}

	public JTextField getCpfCnpjTextField() {
		return cpfCnpjTextField;
	}

	public JRadioButton getCpf_rdnButton() {
		return cpf_rdnButton;
	}

	public ButtonGroup getCpfCnpjButtonGroup() {
		return cpfCnpjButtonGroup;
	}

	public JRadioButton getCnpj_rdnButton() {
		return cnpj_rdnButton;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public JLabel getComandoDeMovimentoLabel() {
		return comandoDeMovimentoLabel;
	}

	public void setComandoDeMovimentoLabel(JLabel comandoDeMovimentoLabel) {
		this.comandoDeMovimentoLabel = comandoDeMovimentoLabel;
	}

	public JLabel getCoberturaOcorrencia() {
		return coberturaOcorrencia;
	}

	public void setCoberturaOcorrencia(JLabel coberturaOcorrencia) {
		this.coberturaOcorrencia = coberturaOcorrencia;
	}

	public JPanel getNovoCadastro() {
		return novoCadastro;
	}

	public JLabel getDataAtualDeCadastroText() {
		return dataAtualDeCadastroText;
	}

	public JLabel getRegistroLabel() {
		return registroLabel;
	}

	public JLabel getCadastroLabel() {
		return cadastroLabel;
	}

	public JLabel getUnidadeConsumidoraTextField_1() {
		return unidadeConsumidoraTextField_1;
	}

	public JTextField getUnidadeConsumidoraTextField() {
		return unidadeConsumidoraTextField;
	}

	public JLabel getInicioLabel() {
		return inicioLabel;
	}

	public JLabel getValorDoacao() {
		return valorDoacao;
	}

	public JTextField getValorDoacaoTextField() {
		return valorDoacaoTextField;
	}

	public JButton getSalvarCadastroButton() {
		return salvarCadastroButton;
	}

	public JTextField getComandoDeMovimentoTextField() {
		return comandoDeMovimentoTextField;
	}

	public JTextField getDescricaoOcorrenciaTextField() {
		return descricaoOcorrenciaTextField;
	}

	public JLabel getDescricaoDeOcorrenciaLabel() {
		return descricaoDeOcorrenciaLabel;
	}

	public JTextField getCoberturaOcorrenciaTextField() {
		return coberturaOcorrenciaTextField;
	}

	public JLabel getRegistroTextField() {
		return registroTextField;
	}

	public JDateChooser getDateChooserInicio() {
		return dateChooserInicio;
	}

	public JDateChooser getDateChooserFim() {
		return dateChooserFim;
	}

	public JLabel getLabelFim() {
		return labelFim;
	}

	public JCheckBox getChckbxTmpContrato() {
		return chckbxTmpContrato;
	}

}
