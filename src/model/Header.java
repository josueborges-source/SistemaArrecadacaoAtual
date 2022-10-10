package model;

import java.util.Calendar;

public class Header {
	
	private Integer getId;		
	private Integer identificacaoRegistro = Integer.valueOf(1);
	private String contrato = "01000000002022908203774000AC1";
	private Integer codigoConcessionaria = Integer.valueOf(0001);
	private Calendar dataEnvio; /// A ser Preenchida
	private String siglaMoeda = "R$";
	private Integer numeroSequencialEnvio; /// A ser Preenchida
	private Integer motivoRecusa; /// A ser Preenchida
	private String nomeClienteContratante = "ASSOCIACAO CULTURAL";
	private String espacoBrancoQuarenta = "                                        "; /* espaco em branco tamanho 40 */
	private Integer tipoArquivo;
	private Integer numeroSequencialRegistro = Integer.valueOf(000001);
	
	private Envio envio;
	
	public Integer getId() {
		return getId;
	}

	public void setId(Integer getId) {
		this.getId = getId;
	}

	public void setMotivoRecusa(Integer motivoRecusa) {
		this.motivoRecusa = motivoRecusa;
	}

	public Integer getIdentificacaoRegistro() {
		return identificacaoRegistro;
	}

	public void setIdentificacaoRegistro(Integer identificacaoRegistro) {
		this.identificacaoRegistro = identificacaoRegistro;
	}

	public String getContrato() {
		return contrato;
	}

	public void setContrato(String contrato) {
		this.contrato = contrato;
	}

	public Integer getCodigoConcessionaria() {
		return codigoConcessionaria;
	}

	public void setCodigoConcessionaria(Integer codigoConcessionaria) {
		this.codigoConcessionaria = codigoConcessionaria;
	}

	public Calendar getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Calendar dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public String getSiglaMoeda() {
		return siglaMoeda;
	}

	public void setSiglaMoeda(String siglaMoeda) {
		this.siglaMoeda = siglaMoeda;
	}

	public Integer getNumeroSequencialEnvio() {
		return numeroSequencialEnvio;
	}

	public void setNumeroSequencialEnvio(Integer numeroSequencialEnvio) {
		this.numeroSequencialEnvio = numeroSequencialEnvio;
	}

	public Integer getMotivoRecusa() {
		return motivoRecusa;
	}

	public void setMotivosRecusa(Integer motivosRecusa) {
		this.motivoRecusa = motivosRecusa;
	}

	public Integer getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(Integer tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}

	public Integer getNumeroSequencialRegistro() {
		return numeroSequencialRegistro;
	}

	public void setNumeroSequencialRegistro(Integer numeroSequencialRegistro) {
		this.numeroSequencialRegistro = numeroSequencialRegistro;
	}

	public String getNomeClienteContratante() {
		return nomeClienteContratante;
	}

	public void setNomeClienteContratante(String nomeClienteContratante) {
		this.nomeClienteContratante = nomeClienteContratante;
	}

	public String getEspacoBrancoQuarenta() {
		return espacoBrancoQuarenta;
	}

	public void setEspacoBrancoQuarenta(String espacoBrancoQuarenta) {
		this.espacoBrancoQuarenta = espacoBrancoQuarenta;
	}

	public Envio getEnvio() {
		return envio;
	}

	public void setEnvio(Envio envio) {
		this.envio = envio;
	}
	
	/*
	public String HeaderDadosPlanos() {
		
		StringBuilder retorno = new StringBuilder();	
		
		Header headerASerPersistido = new Header();
		
		headerASerPersistido.setIdentificacaoRegistro(identificacaoRegistro);
		headerASerPersistido.setContrato(this.contrato);
		headerASerPersistido.setCodigoConcessionaria(this.codigoConcessionaria);
		headerASerPersistido.setDataEnvio(Calendar.getInstance());
		headerASerPersistido.S
		
		Calendar hoje = Calendar.getInstance();		
		
		
		hoje.get(Calendar.DAY_OF_MONTH);
		hoje.get(Calendar.MONTH);
		hoje.get(Calendar.YEAR);
		
		
		
		
		retorno.append(this.identificacaoRegistro);
		retorno.append(this.contrato);
		retorno.append(this.codigoConcessionaria);
		
		
		
		
		retorno.append(this.codigoConcessionaria);
				
		return retorno.toString();
	}
	*/
}
