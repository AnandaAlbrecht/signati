package br.edu.ifsp.aluno.signati.dto.user;

import br.edu.ifsp.aluno.signati.models.User;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPostDTO {

  @NotEmpty
  private String name;
  @NotEmpty
  @Email
  private String email;
  @NotEmpty
  private String password;

  public User toUser() {

    return User.builder()
        .email(this.email)
        .name(this.name)
        .password(this.password)
        .build();
  }
}
