package com.yety.project.request.comun;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.yety.project.request.comun.constants.IContsRequestDIAN;
import com.yety.project.request.comun.dto.DataTerceroDianDto;

/**
 * @author Yesid Rangel
 *
 */
public class OperationsHTML {
	
		
	    DataTerceroDianDto dataTerceroDianDto = new DataTerceroDianDto();
	
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
	public Boolean isJuridica(Document paginaHTML, String tagSearch) throws Exception {
		
		 // Busca la primera coincidencia con el id 
		Element element = paginaHTML.select(IContsRequestDIAN.HTML_ID_JURIDICA_TAG).first();
		
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
	 * @return 
	 * @throws Exception 
	 */
	public String searchDataThirdCustomer(String paginaHTML) throws Exception {
		
		Document document = this.parserToHTML(paginaHTML);
		Boolean juridica = this.isJuridica(document, paginaHTML);
		String thirdCustomer = null;
		
		if (juridica) {
			Element element = document.select(IContsRequestDIAN.HTML_ID_JURIDICA_TAG).first();
			thirdCustomer = element.text();
			System.out.println(thirdCustomer);
			
		}else {
			Element elementName = document.select(IContsRequestDIAN.HTML_ID_PRIMER_NOMBRE_TAG).first();
			Element elementOtherName = document.select(IContsRequestDIAN.HTML_ID_OTRO_NOMBRE_TAG).first();
			Element elementLastname = document.select(IContsRequestDIAN.HTML_ID_PRIMER_APELLIDO_TAG).first();
			Element elementOtherLastname= document.select(IContsRequestDIAN.HTML_ID_OTRO_APELLIDO_TAG).first();
			
			String name = elementName.text();
			String otherName = elementOtherName.text();
			String lastName = elementLastname.text();
			String otherLastName= elementOtherLastname.text();
			thirdCustomer =  name + " " + otherName + " " + lastName + " " + otherLastName;

			
		}
		
		
		return thirdCustomer;
	}
	
	

}
