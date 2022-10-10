package model.ValidacoesDetailConveniado.NovoCadastro;

import model.ValidacoesDetailConveniado.ModeloDeValidacaoTextField;

public class CpfValidacao extends TextFieldEValido {	
	
	/*
	 * Campo 2.11: CPF/CNPJ CPF CHAR / CNPJ CHAR TextField: CPF_CNPJ (?) Nota
	 * Referência: 10 CPF - Tamanho Mínimo: 11 caracteres// Tamanho Máximo: 11
	 * caracteres CNPJ - Tamanho Mínimo: 12 caracteres// Tamanho Máximo: 12
	 * caracteres
	 */
	@Override
	public boolean eValido() {		
		ModeloDeValidacaoTextField cpfCNPJModelo = new ModeloDeValidacaoTextField(
				super.getNovoCadastro().getCpfCnpjTextField(), "CPF/CNPJ");
		cpfCNPJModelo.adicionarRegexDeValidacao(ModeloDeValidacaoTextField.Regex.Numerico);		
		
		cpfCNPJModelo.setTamanhoMinimo(11);
		cpfCNPJModelo.setTamanhoMaximo(11);			
		
		return cpfCNPJModelo.eValido();
	}

}
