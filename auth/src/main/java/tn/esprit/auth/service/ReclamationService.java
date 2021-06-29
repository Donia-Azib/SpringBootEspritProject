package tn.esprit.auth.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tn.esprit.auth.entity.Commande;
import tn.esprit.auth.entity.Reclamation;
import tn.esprit.auth.model.Response;
import tn.esprit.auth.model.ResponseService;
import tn.esprit.auth.repository.CommandeRepository;
import tn.esprit.auth.repository.ReclamationRepository;

@Service
@Transactional
public class ReclamationService {

	
	@Autowired
	private ReclamationRepository ReclamationRepo;
	
	@Autowired
	private CommandeRepository CommandeRepo;
	
	public <S extends Reclamation> S save(S reclamation) {
		return ReclamationRepo.save(reclamation);
		
	}

	public Response<Reclamation> update(Reclamation reclamation, Long id) {
		Boolean exist = existsById(id);
		if (exist) {
			reclamation.setReference(id);
			ReclamationRepo.save(reclamation);
			return new ResponseService<Reclamation>().getSuccessResponse(reclamation);
		} else
			return new ResponseService<Reclamation>().getFailedResponse("This reclamation does not exist ...");

	}

	public Page<Reclamation> findAll(Pageable pageable) {
		return ReclamationRepo.findAll(pageable);
	}

	public List<Reclamation> findAll() {
		return ReclamationRepo.findAll();
	}

	public Response<Reclamation> findById(Long ref) {
		if (existsById(ref))
			return new ResponseService<Reclamation>().getSuccessResponse(ReclamationRepo.findById(ref).get());
		else
			return new ResponseService<Reclamation>().getFailedResponse("This Reclamation does not exist ...");
	}

	public boolean existsById(Long id) {
		return ReclamationRepo.existsById(id);
	}

	public long count() {
		return ReclamationRepo.count();
	}

	public Response<Boolean> deleteById(Long id) {
		if (existsById(id)) {
			ReclamationRepo.deleteById(id);
			return new ResponseService<Boolean>().getSuccessResponse(true);
		} else
			return new ResponseService<Boolean>().getFailedResponse("this Reclamation does not exist ...");
	}
}
