package model.ValidacoesDetailConveniado.NovoCadastro;

import model.ValidacoesDetailConveniado.ModeloDeValidacaoTextField;

public class CoberturaOcorrenciaValidacao extends TextFieldEValido {

	/*
	 * Campo 2.07: Cobertura/Ocorrência Gerencial CHAR Tamanho Mínimo: 2 caracteres
	 * Tamanho Máximo: 2 caracteres Nota 09
	 */
	@Override
	public boolean eValido() {
		ModeloDeValidacaoTextField coberturaOcorrenciaModelo = new ModeloDeValidacaoTextField(
				ModeloDeValidacaoTextField.Regex.Numerico, 
				super.getNovoCadastro().getCoberturaOcorrenciaTextField(),
				"Cobertura de Ocorrência", 1, 2);

		return coberturaOcorrenciaModelo.eValido();		
	}

}
