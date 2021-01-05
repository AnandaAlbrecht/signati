package br.edu.ifsp.aluno.signati.repositories;

import br.edu.ifsp.aluno.signati.models.Signature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignatureRepository extends JpaRepository<Signature, Integer> {
}
