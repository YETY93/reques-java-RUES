package com.yety.project.request.comun;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Yesid Rangel
 *
 */
public class OperationsHTML {
	
	/**
	 * @param paginaHTML
	 * @return
	 */
	public Document parserToHTML(String datosHTML) {
		
		Document document = Jsoup.parseBodyFragment(datosHTML);
		return document;
		
	}
	
	/**
	 * @param paginaHTML
	 * @return
	 */
	public Boolean esJuridica(Document paginaHTML, String tagSearch) throws Exception {
		
		 // Busca la primera coincidencia con el id 
		Element element = paginaHTML.select("#vistaConsultaEstadoRUT\\:formConsultaEstadoRUT\\:razonSocial").first();
		
		 // optiene el valor de la etiqueta 
		String valorSpam;
		Boolean esJuridico;
		try {
			valorSpam = element.text();
			return esJuridico = true;
			
		} catch (Exception e) {
			return esJuridico = false;
		}
	}
	
	/**
	 * @param paginaHTML
	 */
	public void buscarElemntoHTML(Document paginaHTML) {
		
		
	}
	
	

}
