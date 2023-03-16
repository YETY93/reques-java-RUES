package com.yety.project.request.comun;

import java.util.Objects;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.yety.project.request.comun.constants.IContsRequestDIAN;
import com.yety.project.request.comun.dto.DataTerceroDianDto;
import com.yety.project.request.comun.mapper.DatosADataTerceroDianDto;

import lombok.Getter;
import lombok.Setter;

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
	public DataTerceroDianDto searchDataThirdCustomer(String paginaHTML) throws Exception {
		if (Objects.isNull(paginaHTML)) {
			return DatosADataTerceroDianDto.terceroNaturalNoEncontrado();
		}
		
		DataTerceroDianDto dataTerceroDianDto;
		Document document = this.parserToHTML(paginaHTML);
		Boolean juridica = this.isJuridica(document, paginaHTML);
		
		if (juridica) {
			Element element = document.select(IContsRequestDIAN.HTML_ID_JURIDICA_TAG).first();
			String thirdCustomer = element.text();
			dataTerceroDianDto = DatosADataTerceroDianDto.crearTerceroJuridica(thirdCustomer);
			
		}else {
			String name;
			String otherName;
			String lastName;
			String otherLastName;

			try {
				Element elementName = document.select(IContsRequestDIAN.HTML_ID_PRIMER_NOMBRE_TAG).first();
				Element elementOtherName = document.select(IContsRequestDIAN.HTML_ID_OTRO_NOMBRE_TAG).first();
				Element elementLastname = document.select(IContsRequestDIAN.HTML_ID_PRIMER_APELLIDO_TAG).first();
				Element elementOtherLastname= document.select(IContsRequestDIAN.HTML_ID_OTRO_APELLIDO_TAG).first();
				
				name = elementName.text();
				otherName = elementOtherName.text();
				lastName = elementLastname.text();
				otherLastName= elementOtherLastname.text();
				
				dataTerceroDianDto = DatosADataTerceroDianDto.crearTerceroNatural(name, otherName, lastName, otherLastName);
				
			} catch (Exception e) {
				
				dataTerceroDianDto = DatosADataTerceroDianDto.terceroNaturalNoEncontrado();
				
			}			
		}
		
		return dataTerceroDianDto;
	}
	
	

}
