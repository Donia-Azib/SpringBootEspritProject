package tn.esprit.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.auth.service.PromoService;



@RestController
@RequestMapping("/promo")
public class PromoController {
	
	@Autowired
	private PromoService PromoSer;

	@Autowired
	public PromoController(PromoService PromoSer) {
		this.PromoSer = PromoSer;
	}

}
