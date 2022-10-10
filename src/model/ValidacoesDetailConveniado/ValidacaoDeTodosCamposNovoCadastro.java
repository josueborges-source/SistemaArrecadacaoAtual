package model.ValidacoesDetailConveniado;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JRadioButton;

import model.ValidacoesDetailConveniado.NovoCadastro.CnpjValidacao;
import model.ValidacoesDetailConveniado.NovoCadastro.CoberturaOcorrenciaValidacao;
import model.ValidacoesDetailConveniado.NovoCadastro.ComandoMovimentoValidacao;
import model.ValidacoesDetailConveniado.NovoCadastro.CpfValidacao;
import model.ValidacoesDetailConveniado.NovoCadastro.DescricaoOcorrenciaValidacao;
import model.ValidacoesDetailConveniado.NovoCadastro.TextFieldEValido;
import model.ValidacoesDetailConveniado.NovoCadastro.UnidadeConsumidoraValidacao;
import model.ValidacoesDetailConveniado.NovoCadastro.ValorDoacaoValidacao;

public class ValidacaoDeTodosCamposNovoCadastro{	

	private List<TextFieldEValido> validacaoDosCampos = new ArrayList<TextFieldEValido>();

	public ValidacaoDeTodosCamposNovoCadastro() 
	{	
		validacaoDosCampos.add(new UnidadeConsumidoraValidacao());
		validacaoDosCampos.add(new ComandoMovimentoValidacao());
		validacaoDosCampos.add(new ValorDoacaoValidacao());
		validacaoDosCampos.add(new CoberturaOcorrenciaValidacao());
		validacaoDosCampos.add(new DescricaoOcorrenciaValidacao());
		
		JRadioButton botaoCPF = TextFieldEValido.getNovoCadastro().getCpf_rdnButton();
		
		if(botaoCPF.isSelected())
		{
			validacaoDosCampos.add(new CpfValidacao());
		}
		else{
			validacaoDosCampos.add(new CnpjValidacao());
		}
	}

	public boolean TodosCamposDoPainelSaoValidos() {
		
		boolean camposSaoValidos = true;
		for (TextFieldEValido textFieldEValido : validacaoDosCampos) 
		{			
			if(!textFieldEValido.eValido()) {
				camposSaoValidos = false;
			}
		}
		return camposSaoValidos;
	}

}
