package br.edu.ifsp.aluno.signati.dto.petition;

import br.edu.ifsp.aluno.signati.models.Petition;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PetitionPostDTO {

  @NotEmpty
  private String title;

  @NotEmpty
  private String content;

  @NotEmpty
  @Email
  private String author;

  public Petition toPetition() {

    return Petition.builder()
        .title(this.title)
        .content(this.content)
        .author(this.author)
        .build();
  }
}
