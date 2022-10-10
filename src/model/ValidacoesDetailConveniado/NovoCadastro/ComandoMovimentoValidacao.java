package model.ValidacoesDetailConveniado.NovoCadastro;

import model.ValidacoesDetailConveniado.ModeloDeValidacaoTextField;

public class ComandoMovimentoValidacao extends TextFieldEValido {

	/*
	 * Campo 2.05: Comando de Movimento CHAR TextField:
	 * codigoComandoMovimentoJTextField Nota 07 Tamanho Mínimo: 2 Tamanho Máximo: //
	 * 2 caracteres
	 */
	@Override
	public boolean eValido() {
		
		ModeloDeValidacaoTextField comandoDoMovimentoModelo = new ModeloDeValidacaoTextField(
				ModeloDeValidacaoTextField.Regex.Numerico, 
				super.getNovoCadastro().getComandoDeMovimentoTextField(), "Comando de Movimento",
				1, 2);

		return comandoDoMovimentoModelo.eValido();
	}

}
