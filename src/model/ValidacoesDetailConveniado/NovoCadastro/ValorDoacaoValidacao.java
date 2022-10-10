package model.ValidacoesDetailConveniado.NovoCadastro;

import model.ValidacoesDetailConveniado.ModeloDeValidacaoTextField;

public class ValorDoacaoValidacao extends TextFieldEValido {

	/*
	 * Campo 2.03: Valor de Lançamento TextField: Valor Doação valorLancamento NUM
	 * Tamanho Mínimo: 1 caractere, Tamanho Máximo: 9 caracteres Máximo de 1
	 * caractere com zeros a esquerda (ex: R5,00 = 000000500).
	 */	
	@Override
	public boolean eValido() 
	{		
		ModeloDeValidacaoTextField valorDoacaoModelo = new ModeloDeValidacaoTextField(
				ModeloDeValidacaoTextField.Regex.Numerico, super.getNovoCadastro().getValorDoacaoTextField(),
				"Valor Doacao", 1, 9);

		return valorDoacaoModelo.eValido();		
	}

}
