package tn.esprit.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.auth.entity.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long>{

}
