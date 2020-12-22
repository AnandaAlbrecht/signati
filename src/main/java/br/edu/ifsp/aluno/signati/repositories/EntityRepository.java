package br.edu.ifsp.aluno.signati.repositories;

import br.edu.ifsp.aluno.signati.models.EntityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityRepository extends JpaRepository<EntityName, Long> {
}
