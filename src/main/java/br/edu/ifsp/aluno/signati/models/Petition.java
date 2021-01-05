package br.edu.ifsp.aluno.signati.models;

import br.edu.ifsp.aluno.signati.dto.petition.GetPetitionDTO;
import br.edu.ifsp.aluno.signati.dto.petition.GetPetitionDTO.AuthorDTO;
import br.edu.ifsp.aluno.signati.dto.petition.GetPetitionDTO.SignatureDTO;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Petition {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String title;

  private String content;

  @OneToOne
  private User author;

  @OneToMany(mappedBy = "petition", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Signature> signatures;

  public GetPetitionDTO toDTO() {

    return GetPetitionDTO.builder()
        .id(this.getId())
        .author(AuthorDTO.toDTO(this.getAuthor()))
        .title(this.getTitle())
        .content(this.getContent())
        .signatures(Optional.ofNullable(this.getSignatures())
            .orElse(List.of())
            .stream()
            .map(SignatureDTO::toDTO)
            .collect(Collectors.toList()))
        .build();
  }

}
