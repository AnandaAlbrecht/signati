package br.edu.ifsp.aluno.signati.controllers;

import br.edu.ifsp.aluno.signati.dto.user.UserGetAllDTO;
import br.edu.ifsp.aluno.signati.dto.user.UserPostDTO;
import br.edu.ifsp.aluno.signati.dto.user.UserLoginDTO;
import br.edu.ifsp.aluno.signati.models.User;
import br.edu.ifsp.aluno.signati.services.UserService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated
public class UserController {

  private final UserService userService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public User PostCreateUser(@RequestBody @Valid UserPostDTO userDto) {

    return this.userService.createUser(userDto);
  }

  @PostMapping("/login")
  @ResponseBody
  public User postUserLogin(@RequestBody @Valid UserLoginDTO login) {

    return this.userService.findUserByLogin(login);
  }

  @GetMapping
  @ResponseBody
  public List<UserGetAllDTO> getAllUsers(){

    return this.userService.getAllUsers();
  }

}
