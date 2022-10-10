package model;

public class TesteDetailConveniado {

	public static void main(String[] args) {
		
		DetailConveniado dConv = new DetailConveniado()
		{					
			public String retornarParaFormatoTexto() 
			{
				StringBuilder formatoTexto = new StringBuilder();
				
				formatoTexto.append(this.getTipoRegistro());
				formatoTexto.append(this.getCodigoUnidadeConsumidora());					
				
				String codigoUnidadeConsumidora = 
						this.getCodigoUnidadeConsumidora().toString();
				
				if( codigoUnidadeConsumidora.length() < 8) 
				{
					formatoTexto.append(retornaZeros(8 - codigoUnidadeConsumidora.length()) 
							+ codigoUnidadeConsumidora);	
				}				
				
				return formatoTexto.toString();		
			}	
			
			public String retornaZeros(int qtdZeros) 
			{
				String zeros = "";
				
				for(int i = 0; i<qtdZeros; i++) {
					zeros +="0";
				}				
				return zeros;			
			}			
		};
		
		
		
		
		dConv.setCnpjCliente(null);
		dConv.setCoberturaOcorrencia(null);
		dConv.setCodigoUnidadeConsumidora(null);
		dConv.setComandoMovimento(null);
		dConv.setComplementoCNPJ(null);
		dConv.setCpfCliente(null);
		dConv.setCPFCNPJFromString(null);
		
		
		System.out.println(dConv.retornarParaFormatoTexto());
	}
	

}
