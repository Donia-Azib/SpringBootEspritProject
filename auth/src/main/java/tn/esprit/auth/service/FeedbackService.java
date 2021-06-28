package tn.esprit.auth.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.auth.entity.Feedback;
import tn.esprit.auth.entity.Livre;
import tn.esprit.auth.entity.Offre;
import tn.esprit.auth.model.Response;
import tn.esprit.auth.model.ResponseService;
import tn.esprit.auth.repository.FeedbackRepository;
import tn.esprit.auth.repository.LivreRepository;
import tn.esprit.auth.repository.OffreRepository;
import tn.esprit.auth.user.model.User;
import tn.esprit.auth.user.repository.UserRepository;

@Service
public class FeedbackService {

	@Autowired
	private LivreRepository livreRepo;

	@Autowired
	private FeedbackRepository feedbackRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private OffreRepository offreRepo;

	ArrayList<String> badWord = new ArrayList<>(Arrays.asList("bad", "awful", "asshole", "playboy", "spoiler",
			"spoiled", "bad", "mess", "terrible", "appalling", "dreadful", "frightful", "horrible", "crap", "holy crap",
			"douchewaffle", "dumass", "dumb ass", "assjacker", "a_s_s"));

//	---------------------------------------------------BOOK
//	--------------READ
	public List<Feedback> findAllFeebackOfABook(Long id) {
		List<Feedback> feedbacks = new ArrayList<>();
		if (livreRepo.existsById(id)) {

			feedbacks = feedbackRepo.findAllFeedbacksByLivreReference(id);
		}
		return feedbacks;
	}

//	--------------ADD	
	public Response<Boolean> addFeebacks(Long id, String userName, Feedback feedback) {
		if (livreRepo.existsById(id) && livreRepo.findById(id).get().isDisponibilite()) {
			if (!checkUserFeedBack(feedback.getCommentaire())) {
				User user = userRepo.findByUsername(userName).get();
				double total = 0, count = 0;
				Livre livre = livreRepo.findById(id).get();
				List<Feedback> oldFeedbacks = livre.getFeedbacks();
				oldFeedbacks.add(feedback);
				for (Feedback feedback2 : oldFeedbacks) {
					if (feedback2.getNote() != 0) {
						total += feedback2.getNote();
						count++;
					}
				}
				livre.setNote(total / count);

				livre.setFeedbacks(oldFeedbacks);
				livre.setNbComment(oldFeedbacks.size());

				feedback.setLivre(livre);
				feedback.setUser(user);
				feedbackRepo.save(feedback);

				livreRepo.save(livre);
				return new ResponseService<Boolean>().getSuccessResponse(true);
			} else
				return new ResponseService<Boolean>()
						.getFailedResponse("u're comment is rejected because he contain bad words ....! ");

		} else
			return new ResponseService<Boolean>().getFailedResponse("this book does not exist ...");
	}

	private boolean checkUserFeedBack(String comment) {
		boolean containBadWord = false;
		for (String bad : badWord) {
			if (comment.contains(bad)) {
				containBadWord = true;
				break;
			}
		}

		return containBadWord;
	}

//	--------------UPDATE
	public Response<Boolean> updateUserFeedback(Long id, String username, Feedback feedback) {
		if (livreRepo.existsById(id) && livreRepo.findById(id).get().isDisponibilite()) {
			Feedback feedback2 = feedbackRepo.findById(feedback.getId()).get();
			Livre livre = livreRepo.findById(id).get();
			if (feedback2.getUser().getUsername().equals(username)) {
				if (!feedback.getCommentaire().isEmpty() && !checkUserFeedBack(feedback.getCommentaire())) {
					feedback2.setCommentaire(feedback.getCommentaire());
					feedback2.setNote(
							(feedback.getNote() != feedback2.getNote()) && feedback.getNote() != 0 ? feedback.getNote()
									: feedback2.getNote());
					feedbackRepo.save(feedback2);

					livre.setFeedbacks(updateUserOldFeedback(livre.getFeedbacks(), feedback2));
					livreRepo.save(livre);

					User user = userRepo.findByUsername(username).get();
					user.setFeedbacks(updateUserOldFeedback(user.getFeedbacks(), feedback2));
					userRepo.save(user);

					return new ResponseService<Boolean>().getSuccessResponse(true);
				} else
					return new ResponseService<Boolean>()
							.getFailedResponse("u're comment is rejected because he contain bad words ....! ");

			} else
				return new ResponseService<Boolean>()
						.getFailedResponse("Sorry , this comment doesn't belong to you ...");
		} else
			return new ResponseService<Boolean>().getFailedResponse("this book does not exist ...");
	}

	private List<Feedback> updateUserOldFeedback(List<Feedback> feedbacks, Feedback userFeedback) {
		for (int i = 0; i < feedbacks.size(); i++) {
			if (feedbacks.get(i).getId() == userFeedback.getId())
				feedbacks.set(i, userFeedback);
		}
		return feedbacks;
	}

//	--------------DELETE
	public Response<Boolean> deleteAllFeedBackOfOneBook(Long id) {
		if (livreRepo.existsById(id)) {
			Livre livre = livreRepo.findById(id).get();
			livre.setFeedbacks(null);
			livre.setNote(0);
			livre.setNbComment(0);
			livreRepo.save(livre);
			feedbackRepo.deleteAll();

			return new ResponseService<Boolean>().getSuccessResponse(true);
		} else
			return new ResponseService<Boolean>().getFailedResponse("this book does not exist ...");
	}
	

	public Response<Boolean> deleteUserComment(Long livreId, String username, Feedback feedback) {
		if (livreRepo.existsById(livreId) && livreRepo.findById(livreId).get().isDisponibilite()) {
			Feedback feedback2 = feedbackRepo.findById(feedback.getId()).get();
			Livre livre = livreRepo.findById(livreId).get();
			User user = userRepo.findByUsername(username).get();
			if (feedback2 != null && feedback2.getUser().getUsername().equals(username)
					&& user.getFeedbacks().size() > 0) {
				System.out.println(".............exist "+feedbackRepo.existsByLivreReference(livreId));
				if (feedbackRepo.existsByLivreReference(livreId)) {
					livre.setFeedbacks(RemoveFeebackOfBook(feedback2.getId(), livre.getFeedbacks()));
					livre.setNbComment(livre.getFeedbacks().size());
					livreRepo.save(livre);

					user.setFeedbacks(RemoveFeebackOfBook(feedback2.getId(), user.getFeedbacks()));
					userRepo.save(user);

					feedbackRepo.deleteById(feedback2.getId());

					return new ResponseService<Boolean>().getSuccessResponse(true);
				} else
					return new ResponseService<Boolean>()
							.getFailedResponse("Sorry , this comment doesn't belong to this book ...");

			} else
				return new ResponseService<Boolean>()
						.getFailedResponse("Sorry , this comment doesn't belong to you ...");
		} else
			return new ResponseService<Boolean>().getFailedResponse("this book does not exist ...");
	}

	private List<Feedback> RemoveFeebackOfBook(Long ref, List<Feedback> feedbacks) {
		for (int i = 0; i < feedbacks.size(); i++) {
			if (feedbacks.get(i).getId() == ref) {
				System.out.println("----index of =" + i);
				feedbacks.remove(i);
			}
		}

		return feedbacks;
	}

//	---------------------------------------------------OFFER
//	--------------READ
	public List<Feedback> findAllFeebackOfAOffer(Long id) {
		List<Feedback> feedbacks = new ArrayList<>();
		if (offreRepo.existsById(id))
			feedbacks = feedbackRepo.findAllFeedbacksByOffreReference(id);

		return feedbacks;
	}

//--------------ADD	
	public Response<Boolean> addFeebacksInOffer(Long id, String userName, Feedback feedback) {
		if (offreRepo.existsById(id) && offreRepo.findById(id).get().isDiponibilite()) {
			if (!checkUserFeedBack(feedback.getCommentaire())) {
				User user = userRepo.findByUsername(userName).get();
				double total = 0, count = 0;
				Offre offre = offreRepo.findById(id).get();
				List<Feedback> oldFeedbacks = offre.getFeedbacks();
				oldFeedbacks.add(feedback);
				for (Feedback feedback2 : oldFeedbacks) {
					if (feedback2.getNote() != 0) {
						total += feedback2.getNote();
						count++;
					}
				}
				offre.setNote(total / count);

				offre.setFeedbacks(oldFeedbacks);
				offre.setNbComment(oldFeedbacks.size());

				feedback.setOffre(offre);
				feedback.setUser(user);
				feedbackRepo.save(feedback);

				offreRepo.save(offre);
				return new ResponseService<Boolean>().getSuccessResponse(true);
			} else
				return new ResponseService<Boolean>()
						.getFailedResponse("u're comment is rejected because he contain bad words ....! ");

		} else
			return new ResponseService<Boolean>().getFailedResponse("this Offer does not exist ...");
	}

//	--------------UPDATE
	public Response<Boolean> updateUserFeedbackOfAnOffer(Long id, String username, Feedback feedback) {
		if (offreRepo.existsById(id) && offreRepo.findById(id).get().isDiponibilite()) {
			Feedback feedback2 = feedbackRepo.findById(feedback.getId()).get();
			Offre offre = offreRepo.findById(id).get();
			if (feedback2.getUser().getUsername().equals(username)) {
				if (!feedback.getCommentaire().isEmpty() && !checkUserFeedBack(feedback.getCommentaire())) {
					feedback2.setCommentaire(feedback.getCommentaire());
					feedback2.setNote(
							(feedback.getNote() != feedback2.getNote()) && feedback.getNote() != 0 ? feedback.getNote()
									: feedback2.getNote());
					feedbackRepo.save(feedback2);

					offre.setFeedbacks(updateUserOldFeedback(offre.getFeedbacks(), feedback2));
					offreRepo.save(offre);

					User user = userRepo.findByUsername(username).get();
					user.setFeedbacks(updateUserOldFeedback(user.getFeedbacks(), feedback2));
					userRepo.save(user);

					return new ResponseService<Boolean>().getSuccessResponse(true);
				} else
					return new ResponseService<Boolean>()
							.getFailedResponse("u're comment is rejected because he contain bad words ....! ");

			} else
				return new ResponseService<Boolean>()
						.getFailedResponse("Sorry , this comment doesn't belong to you ...");
		} else
			return new ResponseService<Boolean>().getFailedResponse("this offer does not exist ...");
	}

//	--------------DELETE
	public Response<Boolean> deleteUserOfferComment(Long id, String username, Feedback feedback) {
		if (offreRepo.existsById(id) && offreRepo.findById(id).get().isDiponibilite()) {
			Feedback feedback2 = feedbackRepo.findById(feedback.getId()).get();
			Offre offre = offreRepo.findById(id).get();
			User user = userRepo.findByUsername(username).get();
			if (feedback2 != null && feedback2.getUser().getUsername().equals(username)
					&& user.getFeedbacks().size() > 0) {
				if (feedbackRepo.existsByOffreReference(id)) {
					offre.setFeedbacks(RemoveFeebackOfBook(feedback2.getId(), offre.getFeedbacks()));
					offre.setNbComment(offre.getFeedbacks().size());
					offreRepo.save(offre);

					user.setFeedbacks(RemoveFeebackOfBook(feedback2.getId(), user.getFeedbacks()));
					userRepo.save(user);

					feedbackRepo.deleteById(feedback2.getId());

					return new ResponseService<Boolean>().getSuccessResponse(true);
				} else
					return new ResponseService<Boolean>()
							.getFailedResponse("Sorry , this comment doesn't belong to the offer ...");

			} else
				return new ResponseService<Boolean>()
						.getFailedResponse("Sorry , this comment doesn't belong to you ...");
		} else
			return new ResponseService<Boolean>().getFailedResponse("this offer does not exist ...");
	}

}