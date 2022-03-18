package tn.esprit.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.auth.entity.Livre;
import tn.esprit.auth.model.Response;
import tn.esprit.auth.model.ResponseService;
import tn.esprit.auth.repository.LivreRepository;

@Service
@Transactional
public class LivreService {

	@Autowired
	private LivreRepository livreRepo;

	public <S extends Livre> S save(S livre) {
		return livreRepo.save(livre);
	}

	public Response<Livre> update(Livre livre, Long id) {
		Boolean exist = existsById(id);
		if (exist) {
			livre.setReference(id);
			livreRepo.save(livre);
			return new ResponseService<Livre>().getSuccessResponse(livre);
		} else
			return new ResponseService<Livre>().getFailedResponse("This book does not exist ...");

	}

	public Page<Livre> findAll(Pageable pageable) {
		return livreRepo.findAll(pageable);
	}

	public List<Livre> findAll() {
		return livreRepo.findAll();
	}
	
	public List<Livre> findAllByUserId(Long id) {
		return livreRepo.findAllByUsers_Id(id);
	}

	public Response<Livre> findById(Long ref) {
		if (existsById(ref))
			return new ResponseService<Livre>().getSuccessResponse(livreRepo.findById(ref).get());
		else
			return new ResponseService<Livre>().getFailedResponse("This book does not exist ...");
	}

	public boolean existsById(Long id) {
		return livreRepo.existsById(id);
	}

	public long count() {
		return livreRepo.count();
	}

	public Response<Boolean> deleteById(Long id) {
		if (existsById(id)) {
			livreRepo.deleteById(id);
			return new ResponseService<Boolean>().getSuccessResponse(true);
		} else
			return new ResponseService<Boolean>().getFailedResponse("this book does not exist ...");
	}

}