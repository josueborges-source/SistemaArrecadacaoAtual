package model;

import java.util.Date;

public class DetailConcessionaria {
	
	private Integer id;
	private Integer identificacaoRegistro; /* FIXO */
	private Integer codigoUnidadeConsumidora; /* Máximo 13 caracteres, com zeros à esquerda */
	private Integer valorLancamento; /* Máximo 9 caracteres, com zeros à esquerda, sem virgura ou ponto para as casas decimais (ex.: R$5,00 = 000000500)*/
	private Date dataLancamento; /* Ver no arquivo "Layout completo - Convenio Arrecadacao Terceiros.pdf", nota 15 */
	private String informativoRegistro; /* Ver no arquivo "Layout completo - Convenio Arrecadacao Terceiros.pdf", nota 16 */
	private String codigoContaGerencial = "11307SC1"; /* FIXO */
	private Integer identificacaoClienteConveniado;
	private Integer codigoOrigemFatura; /* Ver no arquivo "Layout completo - Convenio Arrecadacao Terceiros.pdf", nota 18 */
	private Integer numeroFatura; /* Ver no arquivo "Layout completo - Convenio Arrecadacao Terceiros.pdf", nota 19 */
	private Date dataVencimentoBaixa; /* Ver no arquivo "Layout completo - Convenio Arrecadacao Terceiros.pdf", nota 20 */
	private Integer valorBasedeCalculo; /* Ver no arquivo "Layout completo - Convenio Arrecadacao Terceiros.pdf", nota 21 */
	private Integer numeroSequencialRegistro; /* evoluir de 1 em 1, a cada registro gerado no arquivo */		
	
	private String espacoBrancoTrintaEDois = "                                "; /* espaco em branco tamanho 32 */

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getIdentificacaoRegistro() {
		return identificacaoRegistro;
	}
	
	public void setIdentificacaoRegistro(Integer identificacaoRegistro) {
		this.identificacaoRegistro = identificacaoRegistro;
	}
	
	public Integer getCodigoUnidadeConsumidora() {
		return codigoUnidadeConsumidora;
	}
	
	public void setCodigoUnidadeConsumidora(Integer codigoUnidadeConsumidora) {
		this.codigoUnidadeConsumidora = codigoUnidadeConsumidora;
	}
	
	public Integer getValorLancamento() {
		return valorLancamento;
	}
	
	public void setValorLancamento(Integer valorLancamento) {
		this.valorLancamento = valorLancamento;
	}
	
	public Date getDataLancamento() {
		return dataLancamento;
	}
	
	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	
	public String getInformativoRegistro() {
		return informativoRegistro;
	}
	
	public void setInformativoRegistro(String informativoRegistro) {
		this.informativoRegistro = informativoRegistro;
	}
	
	public Integer getIdentificacaoClienteConveniado() {
		return identificacaoClienteConveniado;
	}
	
	public void setIdentificacaoClienteConveniado(Integer identificacaoClienteConveniado) {
		this.identificacaoClienteConveniado = identificacaoClienteConveniado;
	}
	
	public Integer getCodOrigemFatura() {
		return codigoOrigemFatura;
	}
	
	public void setCodOrigemFatura(Integer codigoOrigemFatura) {
		this.codigoOrigemFatura = codigoOrigemFatura;
	}
	
	public Integer getNumeroFatura() {
		return numeroFatura;
	}

	public void setNumeroFatura(Integer numeroFatura) {
		this.numeroFatura = numeroFatura;
	}

	public Date getDataVencimentoBaixa() {
		return dataVencimentoBaixa;
	}

	public void setDataVencimentoBaixa(Date dataVencimentoBaixa) {
		this.dataVencimentoBaixa = dataVencimentoBaixa;
	}

	public Integer getValorBasedeCalculo() {
		return valorBasedeCalculo;
	}

	public void setValorBasedeCalculo(Integer valorBasedeCalculo) {
		this.valorBasedeCalculo = valorBasedeCalculo;
	}

	public Integer getNumeroSequencialRegistro() {
		return numeroSequencialRegistro;
	}

	public void setNumeroSequencialRegistro(Integer numeroSequencialRegistro) {
		this.numeroSequencialRegistro = numeroSequencialRegistro;
	}

	public String getEspacoBrancoTrintaEDois() {
		return espacoBrancoTrintaEDois;
	}

	public void setEspacoBrancoTrintaEDois(String espacoBrancoTrintaEDois) {
		this.espacoBrancoTrintaEDois = espacoBrancoTrintaEDois;
	}

	public String getCodigoContaGerencial() {
		return codigoContaGerencial;
	}

	public void setCodigoContaGerencial(String codigoContaGerencial) {
		this.codigoContaGerencial = codigoContaGerencial;
	}

	public Integer getCodigoOrigemFatura() {
		return codigoOrigemFatura;
	}

	public void setCodigoOrigemFatura(Integer codigoOrigemFatura) {
		this.codigoOrigemFatura = codigoOrigemFatura;
	}
}
