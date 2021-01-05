package br.edu.ifsp.aluno.signati.repositories;

import br.edu.ifsp.aluno.signati.models.Petition;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PetitionRepository  extends JpaRepository<Petition, Integer> {


  @Query("SELECT p FROM Petition p WHERE p.content LIKE %?1% OR p.title LIKE %?1%")
  List<Petition> findAllByTitleOrContent(String titleOrContent);
}
