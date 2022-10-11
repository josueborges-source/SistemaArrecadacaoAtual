package view.TelasCadastro;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import dao.DetailConveniadoDAO;
import dao.EnvioDAO;
import dao.FooterDAO;
import dao.HeaderDAO;
import model.DetailConveniado;
import model.DetailConveniado.CPF_CNPJ;
import model.Envio;
import model.Footer;
import model.Header;

import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;

public class EnviarRemessaCadastro {

	public JPanel retornaPainel() {	
		
		JPanel enviarCadastroPanel = new JPanel();
		
		JButton carregarInformacoesBotao = new JButton("Carregar Informações");
		carregarInformacoesBotao.setBounds(10, 11, 910, 32);
		enviarCadastroPanel.add(carregarInformacoesBotao);		
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 54, 910, 376);
		enviarCadastroPanel.add(textArea);
		
		JButton gerarArquivoParaRemessaBotao = new JButton("Gerar arquivo para Remessa");
		
		gerarArquivoParaRemessaBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {							
				
				///  Parte 1: Salva envio no banco
				
				Envio envio = new Envio();
				
				//Cabeçalho e Rodapé
				Header header = new Header();
				Footer footer = new Footer();				
				
				
				///Cabeçalho
				header.setNumeroSequencialEnvio(new HeaderDAO().SelecionarMaiorSequencialEnvio() + 1);
				header.setDataEnvio(Calendar.getInstance());			
				envio.setHeader(header);				
				

				///Corpo Do Envio
				List<DetailConveniado> listaDeDetail = new DetailConveniadoDAO().resgatarLista();
				
				for (DetailConveniado detailConveniado : listaDeDetail) 
				{
					envio.addDetailConveniado(detailConveniado);
				}
				
				
				///Rodapé
				footer.setEnvio(envio);				
				envio.setFooter(footer);
				
				new EnvioDAO().adiciona(envio);
				
				///  Parte 2: Cria arquivo de texto exportação
				
							
			}

			private Object espacoEmBranco(int numero) {
				
				String espaçoEmBranco = "";
				for(int i = 0; i < numero; i++) 
				{
					espaçoEmBranco += " ";
				}
				return espaçoEmBranco;
			}
		});
		gerarArquivoParaRemessaBotao.setBounds(10, 441, 910, 42);
		enviarCadastroPanel.add(gerarArquivoParaRemessaBotao);
			
		
		return enviarCadastroPanel;		
	}
}
