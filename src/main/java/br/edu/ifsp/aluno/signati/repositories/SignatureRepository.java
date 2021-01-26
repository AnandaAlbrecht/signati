package br.edu.ifsp.aluno.signati.repositories;

import br.edu.ifsp.aluno.signati.models.Petition;
import br.edu.ifsp.aluno.signati.models.Signature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignatureRepository extends JpaRepository<Signature, Integer> {


}
