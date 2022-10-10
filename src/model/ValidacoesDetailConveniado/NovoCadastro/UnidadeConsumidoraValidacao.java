package model.ValidacoesDetailConveniado.NovoCadastro;

import model.ValidacoesDetailConveniado.ModeloDeValidacaoTextField;

public class UnidadeConsumidoraValidacao extends TextFieldEValido {

	
	/*
	 * Campo 2.02: Código Unidade Consumidora TextField: Unidade Consumidora
	 * codigoUnidadeConsumidora NUM Tamanho Mínimo: 0 caracteres, Tamanho Máximo: 13
	 * caracteres Deve ser preenchido com zeros à esquerda do inteiro como formato
	 * original
	 */			
	@Override
	public boolean eValido() {
		
		ModeloDeValidacaoTextField unidadeConsumidoraModelo = new ModeloDeValidacaoTextField(
				ModeloDeValidacaoTextField.Regex.Numerico, 
				super.getNovoCadastro().getUnidadeConsumidoraTextField(), "Unidade Consumidora",
				0, 13);

		return unidadeConsumidoraModelo.eValido();
	}

}
