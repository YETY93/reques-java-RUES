package com.yety.project.request.controller;

import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yety.project.request.comun.OperationsHTML;
import com.yety.project.request.comun.RequestDIAN;
import com.yety.project.request.comun.dto.DataTerceroDianDto;



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
	@GetMapping("nit/{numberIdent}")
    public DataTerceroDianDto searchData(@PathVariable String numberIdent ) throws Exception {
		String url = "https://muisca.dian.gov.co/WebRutMuisca/DefConsultaEstadoRUT.faces";
		
		String respuesta = RequestDIAN.obtenerHtmlDIANrut(numberIdent , url);
		OperationsHTML opHTML = new OperationsHTML();
		
		DataTerceroDianDto dataTerceroDianDto = opHTML.searchDataThirdCustomer(respuesta);
		// Return DTO
		// return Response.ok(dto, MediaType.APPLICATION_JSON).build();
		return dataTerceroDianDto;
    }

}
