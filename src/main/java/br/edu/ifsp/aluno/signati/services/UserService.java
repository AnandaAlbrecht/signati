package br.edu.ifsp.aluno.signati.services;

import br.edu.ifsp.aluno.signati.dto.user.UserGetAllDTO;
import br.edu.ifsp.aluno.signati.dto.user.UserPostDTO;
import br.edu.ifsp.aluno.signati.dto.user.UserLoginDTO;
import br.edu.ifsp.aluno.signati.models.User;
import br.edu.ifsp.aluno.signati.repositories.UserRepository;
import java.security.PublicKey;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder encoder;

  public User createUser(UserPostDTO dto) {

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
        .orElseThrow(this.userNotFound());
  }

  public User findUserById(Integer id) {

    return this.userRepository.findById(id)
        .orElseThrow(this.userNotFound());
  }

  public List<UserGetAllDTO> getAllUsers() {

    return this.userRepository.findAll().stream()
        .map(UserGetAllDTO::toDTO)
        .collect(Collectors.toList());
  }

  private Supplier userNotFound() {
    return () -> new RuntimeException("User not found");
  }
}
