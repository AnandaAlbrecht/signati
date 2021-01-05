package br.edu.ifsp.aluno.signati.dto.petition;

import br.edu.ifsp.aluno.signati.models.Petition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PetitionPostDTO {

  private String title;
  private String content;
  private Integer authorId;

  public Petition toPetition() {
    return Petition.builder()
        .title(this.title)
        .content(this.content)
        .build();
  }
}
