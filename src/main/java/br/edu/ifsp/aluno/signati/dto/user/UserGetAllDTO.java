package br.edu.ifsp.aluno.signati.dto.user;

import br.edu.ifsp.aluno.signati.models.Petition;
import br.edu.ifsp.aluno.signati.models.Signature;
import br.edu.ifsp.aluno.signati.models.User;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserGetAllDTO {

  private String name;
  private String email;
  private List<SignatureDTO> signatures;

  public static UserGetAllDTO toDTO(User user) {
    return UserGetAllDTO.builder()
        .email(user.getEmail())
        .name(user.getName())
        .signatures(SignatureDTO.signatureDTOList(user.getSignatures()))
        .build();
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static final class SignatureDTO {

    private String petition;

    public static List<SignatureDTO> signatureDTOList(List<Signature> signatures) {

      return signatures.stream().map(SignatureDTO::toDTO).collect(Collectors.toList());
    }

    private static SignatureDTO toDTO(Signature signature) {

      return SignatureDTO.builder()
          .petition(Optional.ofNullable(signature.getPetition())
              .map(Petition::getTitle).orElse(null))
          .build();
    }
  }
}
