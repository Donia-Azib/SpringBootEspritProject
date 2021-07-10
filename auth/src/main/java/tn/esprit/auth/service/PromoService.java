package tn.esprit.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.auth.repository.PromoRepository;



@Service
@Transactional
public class PromoService {
	
	@Autowired
	private PromoRepository promoRepo;

}
