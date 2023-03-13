package com.yety.project.request.controller;

import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yety.project.request.comun.OperationsHTML;
import com.yety.project.request.comun.RequestDIAN;
import com.yety.project.request.comun.RequestRUES;



/**
 * @author Yesid Rangel
 *
 */
@RestController
@RequestMapping("/consultarues/")
public class requestRUEScontroller {
	

	/**
	 * @return
	 * @throws Exception
	 */
	@GetMapping("nit/")
    public String paginaComun() throws Exception {
		String url = "https://muisca.dian.gov.co/WebRutMuisca/DefConsultaEstadoRUT.faces";
		
		// RequestDIAN requestDIAN = new RequestDIAN();
		// System.out.println("#############################obtenerHtmlDIANgpt#######################");
		// requestDIAN.obtenerHtmlDIANgpt();
		// System.out.println("########################obtenerHtmlDIANsonic###########################");
		// requestDIAN.obtenerHtmlDIANsonic();
		//requestDIAN.requesDIANokHttp();
		
		String respuesta = RequestDIAN.obtenerHtmlDIANrut("1049373081", url);
		OperationsHTML opHTML = new OperationsHTML();
		
		Document documento = opHTML.parserToHTML(respuesta);
		
		Boolean juridica = opHTML.esJuridica(documento, "td.tipoFilaNormalVerde");
		
		System.out.println(juridica);
		
		return respuesta;
    }

}
