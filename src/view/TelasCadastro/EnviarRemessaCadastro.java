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
				
				Envio envio = new Envio();				
				Footer footer = new Footer();
				Header header = new Header();
				
				footer.setEnvio(envio);
				envio.setFooter(footer);
				
				header.setEnvio(envio);
				envio.setHeader(header);
				
				FooterDAO footerDAO = new FooterDAO();				
				footerDAO.adiciona(footer);
				
				HeaderDAO headerDAO = new HeaderDAO();
				headerDAO.adiciona(header);
				
				EnvioDAO envioDAO = new EnvioDAO();					
				envioDAO.adiciona(envio);
				
				DetailConveniadoDAO detailDao = new DetailConveniadoDAO();				
				List<DetailConveniado>listaDetail = detailDao.resgatarLista();
				
				
				for(DetailConveniado detailConveniado : listaDetail) 
				{
					if(!detailConveniado.isEnviado()) {
					envio.addDetailConveniado(detailConveniado);
					}
				}				
				
				/*
				for(DetailConveniado detailConveniado : listaDetail) 
				{
					envio.addDetailConveniado(detailConveniado);
				}								
				*/
				
				
				//// ?
											
				
				StringBuilder saidaDeTexto = new StringBuilder();			
				
				for (DetailConveniado detailConveniado : listaDetail) 
				{
					saidaDeTexto.append("2");
					
					saidaDeTexto.append(detailConveniado.getCodigoUnidadeConsumidora());
					saidaDeTexto.append(detailConveniado.getValorLancamento());
					
					Date dataGeracaoRegistro = new Date(detailConveniado.getDataGeracaoRegistro().getTimeInMillis());					
					
					saidaDeTexto.append(dataGeracaoRegistro.getDay());					
					saidaDeTexto.append(dataGeracaoRegistro.getMonth());
					saidaDeTexto.append(dataGeracaoRegistro.getYear());					
					
				
					saidaDeTexto.append(detailConveniado.getComandoMovimento());
					saidaDeTexto.append(detailConveniado.getCodigoContaGerencial());
					saidaDeTexto.append(detailConveniado.getCoberturaOcorrencia());
					saidaDeTexto.append(detailConveniado.getDescricaoCoberturaOcorrencia());
					
					saidaDeTexto.append(espacoEmBranco(10));
					saidaDeTexto.append(espacoEmBranco(6));
					
					CPF_CNPJ cpfCnpj = detailConveniado.getCpfCnpj();
					
					if(cpfCnpj.equals(DetailConveniado.CPF_CNPJ.CPF)) {
					saidaDeTexto.append(detailConveniado.getCpfCliente());
					}
					else if(cpfCnpj.equals(DetailConveniado.CPF_CNPJ.CNPJ)) {
					saidaDeTexto.append(detailConveniado.getCnpjCliente());
					}					
					
					
					Calendar mesVigencia = detailConveniado.getMesVigencia();
					
					dataGeracaoRegistro = new Date(mesVigencia.getTimeInMillis());					
					
					saidaDeTexto.append(dataGeracaoRegistro.getDay());					
					saidaDeTexto.append(dataGeracaoRegistro.getMonth());
					saidaDeTexto.append(dataGeracaoRegistro.getYear());			
									
					mesVigencia = detailConveniado.getMesFimVigencia();
					/*
					saidaDeTexto.append(detailConveniado.getMesVigencia());					
					saidaDeTexto.append(detailConveniado.getMesFimVigencia());
					*/
					
					saidaDeTexto.append(dataGeracaoRegistro.getDay());					
					saidaDeTexto.append(dataGeracaoRegistro.getMonth());
					saidaDeTexto.append(dataGeracaoRegistro.getYear());	
					
					if(cpfCnpj.equals(DetailConveniado.CPF_CNPJ.CNPJ)) 
					{
					saidaDeTexto.append(detailConveniado.getComplementoCNPJ());
					}		
					else 
					{
					saidaDeTexto.append(espacoEmBranco(2));
					}
					saidaDeTexto.append(espacoEmBranco(2));
					saidaDeTexto.append(espacoEmBranco(13));
					saidaDeTexto.append(espacoEmBranco(10));					
					saidaDeTexto.append(detailConveniado.getNumeroSequencialRegistro());		
				}								
				System.out.println(saidaDeTexto);				
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
