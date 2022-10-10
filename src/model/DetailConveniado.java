package model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;

public class DetailConveniado {	

	////// ID ? ///////
	private Integer id;
		
	/* FIXO */
	private final Integer tipoRegistro = 2; 	

	// Máximo de 13 caracteres, com zeros à esquerda
	private Long codigoUnidadeConsumidora; 	
	/* 
	 Máximo 9 caracteres, com zeros à esquerda, sem virgura ou ponto para as casas
	 decimais (ex.: R$5,00 = 000000500)*/
	private Integer valorLancamento; 
		
	// Data atual
	private Calendar dataGeracaoRegistro; 

	// Comando de Movimento
	private String comandoMovimento;
	
	// Ver no arquivo "Layout completo - Convenio Arrecadacao Terceiros.pdf", nota 07
	private final String codigoContaGerencial = "11307AC1"; /* FIXO */

	// Tamanho 2
	private Integer coberturaOcorrencia;	

	// Ver no arquivo Layout completo - Convenio Arrecadacao Terceiros.pdf", nota 09
	private BigInteger descricaoCoberturaOcorrencia; 	
	
	// Máximo 11, com zeros a esquerda
	private BigDecimal cpfCliente; 
	
	// Máximo 12, com zeros a esquerda. Complemento do campo 2.14
	private BigDecimal cnpjCliente; 

	// Máximo 2, com zeros a esquerda
	private Integer complementoCNPJ; 

	// Mês inicio da cobranca, IMPORTANTE: sempre utilizar o mês seguinte
	private Calendar mesVigencia; 
	
	// Fixo zeros 00000000 (8 zeros)
	private Calendar mesFimVigencia; 
	
	// Mes Fim Vigencia Boolean
	private Boolean MesFimVigenciaAtivado = false;

	// Espaco em branco de tamanho 2
	private String espacoBrancoDois = "  "; 
	// Espaco em branco de tamanho 13
	private String espacoBrancoTreze = "             "; 
	// Espaco em branco de tamanho 10
	private String espacoBrancoDez = "          "; 	

	// Evoluir de 1 em 1, a cada registro gerado no arquivo - Máximo 6
	private Integer numeroSequencialRegistro; 
	
	// CNPJ ou CPF
	private CPF_CNPJ cpfCnpj = CPF_CNPJ.CPF;
	
	protected CPF_CNPJ cpfCNPJEnum;
	
	private boolean enviado = false;

	
	

	// Retorna Query - DetailConveniado
	public String retornaQuery() {
		
		String query = "CREATE TABLE DetailConveniado(tipoRegistro INT,  codigoUnidadeConsumidora BIGINT,"
				+ "valorLancamento INT, dataGeracaoRegistro DATE, comandoMovimento VARCHAR(255),"
				+ "codigoContaGerencial VARCHAR(255), coberturaOcorrencia INT, descricaoCoberturaOcorrencia DECIMAL(30,0),"
				+ "cpfCliente BIGINT, cnpjCliente BIGINT, cpf_CNPJ ENUM('CPF','CNPJ'),  mesVigencia DATE, mesFimVigencia DATE, complementoCNPJ INT,"
				+ "numeroSequencialDoRegistro INT)";
		
		return query;
	}	
	
	// CPF CNPJ - Enum Opcoes
	public enum CPF_CNPJ {
		CPF, CNPJ
	}		
	
	public void setCPFCNPJFromString(String cpfOuCNPJ) 
	{		
		switch (cpfOuCNPJ) {
		case "cpf":
			cpfCnpj= CPF_CNPJ.CPF;
		case "cnpj":
			cpfCnpj= CPF_CNPJ.CNPJ;		
		}		
	}
	
	//// GETTERS e SETTERS		
	public boolean isEnviado() {
		return enviado;
	}

	public void setEnviado(boolean enviado) {
		this.enviado = enviado;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public CPF_CNPJ getCpfCnpj() {
		return cpfCnpj;
	}
	
	public void setCpfCnpj(CPF_CNPJ cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Calendar getDataGeracaoRegistro() {
		return dataGeracaoRegistro;
	}

	public void setDataGeracaoRegistro(Calendar dataGeracaoRegistro) {
		this.dataGeracaoRegistro = dataGeracaoRegistro;
	}

	public void setNumeroSequencialRegistro(int numeroSequencialRegistro) {
		this.numeroSequencialRegistro = numeroSequencialRegistro;
	}

	public void setCoberturaOcorrencia(Integer coberturaOcorrencia) {
		this.coberturaOcorrencia = coberturaOcorrencia;
	}

	public int getTipoRegistro() {
		return tipoRegistro;
	}

	public String getEspacoBrancoDois() {
		return espacoBrancoDois;
	}

	public void setEspacoBrancoDois(String espacoBrancoDois) {
		this.espacoBrancoDois = espacoBrancoDois;
	}

	public String getEspacoBrancoTreze() {
		return espacoBrancoTreze;
	}

	public void setEspacoBrancoTreze(String espacoBrancoTreze) {
		this.espacoBrancoTreze = espacoBrancoTreze;
	}

	public String getEspacoBrancoDez() {
		return espacoBrancoDez;
	}

	public void setEspacoBrancoDez(String espacoBrancoDez) {
		this.espacoBrancoDez = espacoBrancoDez;
	}

	public String getCodigoContaGerencial() {
		return codigoContaGerencial;
	}

	public Integer getValorLancamento() {
		return valorLancamento;
	}

	public void setValorLancamento(Integer valorLancamento) {
		this.valorLancamento = valorLancamento;
	}

	public String getComandoMovimento() {
		return comandoMovimento;
	}

	public void setComandoMovimento(String comandoMovimento) {
		this.comandoMovimento = comandoMovimento;
	}

	public int getCoberturaOcorrencia() {
		return coberturaOcorrencia;
	}

	public void setCoberturaOcorrencia(int coberturaOcorrencia) {
		this.coberturaOcorrencia = coberturaOcorrencia;
	}

	public BigInteger getDescricaoCoberturaOcorrencia() {
		return descricaoCoberturaOcorrencia;
	}

	public void setDescricaoCoberturaOcorrencia(BigInteger descricaoCoberturaOcorrencia) {
		this.descricaoCoberturaOcorrencia = descricaoCoberturaOcorrencia;
	}

	public BigDecimal getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(BigDecimal cpfCliente) {
		System.out.println("SET cpfCliente: " + cpfCliente);
		this.cpfCliente = cpfCliente;
	}

	public BigDecimal getCnpjCliente() {
		return cnpjCliente;
	}

	public void setCnpjCliente(BigDecimal cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}

	public int getComplementoCNPJ() {
		return complementoCNPJ;
	}

	public void setComplementoCNPJ(Integer complementoCNPJ) {
		this.complementoCNPJ = complementoCNPJ;
	}

	public Calendar getMesVigencia() {
		return mesVigencia;
	}

	public void setMesVigencia(Calendar mesVigencia) {
		this.mesVigencia = mesVigencia;
	}

	public Calendar getMesFimVigencia() {
		return mesFimVigencia;
	}

	public void setMesFimVigencia(Calendar mesFimVigencia) {
		this.mesFimVigencia = mesFimVigencia;
	}

	public Integer getNumeroSequencialRegistro() {
		return numeroSequencialRegistro;
	}

	public void setNumeroSequencialRegistro(Integer numeroSequencialRegistro) {
		this.numeroSequencialRegistro = numeroSequencialRegistro;
	}

	public Long getCodigoUnidadeConsumidora() {
		return codigoUnidadeConsumidora;
	}

	public void setCodigoUnidadeConsumidora(Long codigoUnidadeConsumidora) {
		this.codigoUnidadeConsumidora = codigoUnidadeConsumidora;
	}

	public Boolean getMesFimVigenciaAtivado() {
		return MesFimVigenciaAtivado;
	}
	
	public void setMesFimVigenciaAtivado(Boolean mesFimVigenciaAtivado) {
		MesFimVigenciaAtivado = mesFimVigenciaAtivado;
	}
	
	public String toString() {
		return "Detail_conveniado [tipoRegistro=" + tipoRegistro + ", codigoUnidadeConsumidora=" + codigoUnidadeConsumidora
				+ ", valorLancamento=" + valorLancamento + ", dataGeracaoRegistro=" + dataGeracaoRegistro
				+ ", comandoMovimento=" + comandoMovimento + ", codigoContaGerencial=" + codigoContaGerencial
				+ ", coberturaOcorrencia=" + coberturaOcorrencia + ", descricaoCoberturaOcorrencia="
				+ descricaoCoberturaOcorrencia + ", cpfCliente=" + cpfCliente + ", cnpjCliente=" + cnpjCliente
				+ ", mesVigencia=" + mesVigencia + ", mesFimVigencia=" + mesFimVigencia + ", complementoCNPJ="
				+ complementoCNPJ + ", espacoBrancoDois=" + espacoBrancoDois + ", espacoBrancoTreze=" + espacoBrancoTreze
				+ ", espacoBrancoDez=" + espacoBrancoDez + ", numeroSequencialRegistro=" + numeroSequencialRegistro
				+ "]";
	}
	
	public String retornarParaFormatoTexto() {
		
		StringBuilder formatoTexto = new StringBuilder();		
		
		return formatoTexto.toString();		
	}
	
}
