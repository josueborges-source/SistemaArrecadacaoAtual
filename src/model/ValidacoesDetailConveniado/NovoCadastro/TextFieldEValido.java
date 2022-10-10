package model.ValidacoesDetailConveniado.NovoCadastro;

import view.TelasCadastro.NovoCadastroPainel;

public abstract class TextFieldEValido {
	
	static NovoCadastroPainel novoCadastro = new NovoCadastroPainel();	
	
	public abstract boolean eValido();

	public static NovoCadastroPainel getNovoCadastro() {
		return novoCadastro;
	}

	public static void setNovoCadastro(NovoCadastroPainel novoCadastro) {
		TextFieldEValido.novoCadastro = novoCadastro;
	}	

}
