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
  private AuthorDTO author;
  private List<SignatureDTO> signatures;



  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static final class AuthorDTO {

    private Integer id;
    private String name;
    private String email;

    public static AuthorDTO toDTO(User author) {

      return AuthorDTO.builder()
          .id(author.getId())
          .name(author.getName())
          .email(author.getEmail())
          .build();

    }
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static final class SignatureDTO {

    private Integer id;
    private AuthorDTO author;
    private LocalDateTime timestamp;

    public static SignatureDTO toDTO(Signature signature) {

      return SignatureDTO.builder()
          .id(signature.getId())
          .author(AuthorDTO.toDTO(signature.getAuthor()))
          .timestamp(signature.getTimestamp())
          .build();
    }
  }

}
