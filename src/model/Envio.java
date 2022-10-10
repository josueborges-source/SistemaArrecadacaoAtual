package model;

import java.util.ArrayList;
import java.util.List;

public class Envio 
{
	private Header header = new Header();
	
	private List<DetailConveniado> detailConveniadoLista = new ArrayList<DetailConveniado>();
	private Footer footer = new Footer(); 
	
	public Header getHeader() {
		return header;
	}
	
	public void setHeader(Header header) {
		this.header = header;
	}	
	
	public void addDetailConveniado(DetailConveniado detailConveniado) 
	{
		this.detailConveniadoLista.add(detailConveniado);
	}
	
	public void removeDetailConveniado(DetailConveniado detailConveniado) 
	{
		this.detailConveniadoLista.remove(detailConveniado);
	}
	
	public Footer getFooter() {
		return footer;
	}
	
	public void setFooter(Footer footer) {
		this.footer = footer;
	}
	

}
