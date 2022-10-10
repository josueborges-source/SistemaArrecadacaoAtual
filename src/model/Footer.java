package model;

public class Footer {
	
	private int identificacaoRegistro = 9; /* Fixo */
	private int valorTotaldosLancamentos; /* Máximo 11 caracteres, com zeros à esquerda, sem virgura ou ponto para as casas decimais (ex.: R$5,00 = 00000000500)*/
	private String espacoBranco = "                                                                                                                                    "; /* espaço em branco tamanho 132 */
	private int numeroSequencialRegistro;		
	
	private Envio envio;
	
/*
CREATE TABLE 'footer' (
'identificacaoRegistro' INT,
'valorTotaldosLancamentos' INT,
'espacoBranco' VARCHAR(45),
'numeroSequencialRegistro' INT);
 */
	
	public Envio getEnvio() {
		return envio;
	}
	public void setEnvio(Envio envio) {
		this.envio = envio;
	}
	
	public int getIdentificacaoRegistro() {
		return identificacaoRegistro;
	}
	public void setIdentificacaoRegistro(int identificacaoRegistro) {
		this.identificacaoRegistro = identificacaoRegistro;
	}
	public int getValorTotaldosLancamentos() {
		return valorTotaldosLancamentos;
	}
	public void setValorTotaldosLancamentos(int valorTotaldosLancamentos) {
		this.valorTotaldosLancamentos = valorTotaldosLancamentos;
	}
	public int getNumeroSequencialRegistro() {
		return numeroSequencialRegistro;
	}
	public void setNumeroSequencialRegistro(int numeroSequencialRegistro) {
		this.numeroSequencialRegistro = numeroSequencialRegistro;
	}
	public String getEspacoBranco() {
		return espacoBranco;
	}
	public void setEspacoBranco(String espacoBranco) {
		this.espacoBranco = espacoBranco;
	}
}
