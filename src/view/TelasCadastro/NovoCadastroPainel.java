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
import model.ValidacoesDetailConveniado.ValidacaoDeTodosCamposNovoCadastro;
import model.ValidacoesDetailConveniado.NovoCadastro.TextFieldEValido;
import util.Util;

public class NovoCadastroPainel {

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

	/**
	 * @wbp.parser.entryPoint
	 */
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
			}
		});

		NovoCadastroPainel painelAtual = this;

		
		salvarCadastroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DetailConveniadoDAO detailDAO = new DetailConveniadoDAO();

				// DETAIL CONVENIADO MODELO
				DetailConveniado detailConveniado = new DetailConveniado();				

				TextFieldEValido.setNovoCadastro(painelAtual);

				boolean todosCamposSaoValidos = new ValidacaoDeTodosCamposNovoCadastro()
						.TodosCamposDoPainelSaoValidos();

				if (todosCamposSaoValidos) {

					///Set Codigo Unidade					
					detailConveniado
							.setCodigoUnidadeConsumidora(Long.valueOf(getUnidadeConsumidoraTextField().getText()));
					
					///Set Comando de Movimento					
					detailConveniado.setComandoMovimento(getComandoDeMovimentoTextField().getText());
					
					///Set Valor Lancamento					
					detailConveniado.setValorLancamento(Integer.valueOf(getValorDoacaoTextField().getText()));
					
					///Set Cobertura Ocorrencia					
					detailConveniado
							.setCoberturaOcorrencia(Integer.valueOf(getCoberturaOcorrenciaTextField().getText()));
					
					///Set DescricaoCobertura Sequencial					
					detailConveniado.setDescricaoCoberturaOcorrencia(
							new BigInteger(getDescricaoOcorrenciaTextField().getText()));

					///Set Numero Sequencial					
					String registroNumero = registroTextField.getText();
					detailConveniado.setNumeroSequencialRegistro(Integer.valueOf(registroNumero));

					////Set CPF e CNPJ					
					boolean cpfAtivado = cpf_rdnButton.isSelected();

					if (cpfAtivado) {
						detailConveniado
								.setCpfCliente(BigDecimal.valueOf(Long.valueOf(getCpfCnpjTextField().getText())));
					} else {
						detailConveniado
								.setCnpjCliente(BigDecimal.valueOf(Long.valueOf(getCpfCnpjTextField().getText())));
					}

					/// Campo Mês Vigência: detailConveniado.setMesVigencia					
					Date data = dateChooserInicio.getDate();

					Calendar calendar = Calendar.getInstance();
					calendar.setTime(data);

					calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
					detailConveniado.setMesVigencia(calendar);

					
					/// Campo Data Geração do Registro: detailConveniado.setDataGeracaoRegistro					
					String dataDeCadastro = dataAtualDeCadastroText.getText();

					System.out.println(dataDeCadastro);

					dataDeCadastro = Util.SomenteNumeros(dataDeCadastro);
					detailConveniado.setDataGeracaoRegistro(Calendar.getInstance());
					
					/// Set Mês de Fim da Vigência					
					if (chckbxTmpContrato.isSelected()) {

						data = dateChooserFim.getDate();

						calendar.setTime(data);
						calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);

						detailConveniado.setMesFimVigencia(calendar);
					}
					detailDAO.Salvar(detailConveniado);
				}
			}
		});

		return novoCadastro;
	}

	public void inicializarComponentes() {
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
