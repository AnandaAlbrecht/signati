package br.edu.ifsp.aluno.signati.dto.petition;

import br.edu.ifsp.aluno.signati.models.Petition;
import br.edu.ifsp.aluno.signati.models.Signature;
import br.edu.ifsp.aluno.signati.models.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPetitionDTO {

  private Integer id;
  private String title;
  private String content;
  private String author;
  private List<SignatureDTO> signatures;



  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static final class SignatureDTO {

    private Integer id;
    private String author;

    public static SignatureDTO toDTO(Signature signature) {

      return SignatureDTO.builder()
          .id(signature.getId())
          .author(signature.getAuthor())
          .build();
    }
  }

}
