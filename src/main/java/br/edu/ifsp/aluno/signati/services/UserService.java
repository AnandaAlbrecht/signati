package br.edu.ifsp.aluno.signati.services;

import br.edu.ifsp.aluno.signati.dto.UserCreationDTO;
import br.edu.ifsp.aluno.signati.dto.UserLoginDTO;
import br.edu.ifsp.aluno.signati.models.User;
import br.edu.ifsp.aluno.signati.repositories.UserRepository;
import com.fasterxml.jackson.databind.util.ClassUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder encoder;

  public User createUser(UserCreationDTO dto) {

    this.userRepository.findUserByEmail(dto.getEmail()).ifPresent(u -> {
      throw new RuntimeException(String.format("User %s", u.getEmail()));
    });

    var user = dto.toUser();
    user.setPassword(encoder.encode(user.getPassword()));

    return userRepository.save(user);
  }

  public User findUserByLogin(UserLoginDTO userLogin) {

    return this.userRepository
        .findUserByEmail(userLogin.getEmail())
        .filter(user -> this.encoder.matches(userLogin.getPassword(), user.getPassword()))
        .orElseThrow(() -> new RuntimeException("User not found"));
  }
}
