package br.edu.ifsp.aluno.signati.models;

import br.edu.ifsp.aluno.signati.dto.petition.GetPetitionDTO;
import br.edu.ifsp.aluno.signati.dto.petition.GetPetitionDTO.SignatureDTO;
import br.edu.ifsp.aluno.signati.dto.petition.PetitionPostDTO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

  @Column(length = 4048)
  private String content;

  private String author;

  @OneToMany(mappedBy = "petition", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  private List<Signature> signatures;

  public GetPetitionDTO toDTO() {

    return GetPetitionDTO.builder()
        .id(this.getId())
        .author(this.author)
        .title(this.getTitle())
        .content(this.getContent())
        .signatures(Optional.ofNullable(this.getSignatures())
            .orElse(List.of())
            .stream()
            .map(SignatureDTO::toDTO)
            .collect(Collectors.toList()))
        .build();
  }

  public void updatePetition(PetitionPostDTO dto) {

    this.setContent(dto.getContent());
    this.setTitle(dto.getTitle());
  }

}
