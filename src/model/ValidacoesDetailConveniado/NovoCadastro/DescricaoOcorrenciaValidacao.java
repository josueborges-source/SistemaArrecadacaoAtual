package model.ValidacoesDetailConveniado.NovoCadastro;

import model.ValidacoesDetailConveniado.ModeloDeValidacaoTextField;

public class DescricaoOcorrenciaValidacao extends TextFieldEValido {

	/*
	 * Campo 2.08: Descrição da Cobertura/Ocorrencia descricaoCoberturaOcorrencia
	 * CHAR TextField: Descrição da Ocorrência Nota 09 Tamanho Mínimo: // Tamanho
	 * Máximo: 30 caracteres
	 */
	@Override
	public boolean eValido() {
		ModeloDeValidacaoTextField descricaoCoberturaOcorrenciaModelo = new ModeloDeValidacaoTextField(
				ModeloDeValidacaoTextField.Regex.Numerico, 
				super.getNovoCadastro().getDescricaoOcorrenciaTextField(),
				"Descricao De Ocorrencia", 0, 30);

		return descricaoCoberturaOcorrenciaModelo.eValido();
	}

}
