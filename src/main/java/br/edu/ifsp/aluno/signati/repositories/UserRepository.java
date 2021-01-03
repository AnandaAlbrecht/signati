package br.edu.ifsp.aluno.signati.repositories;

import br.edu.ifsp.aluno.signati.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findUserByEmail(String email);

}
