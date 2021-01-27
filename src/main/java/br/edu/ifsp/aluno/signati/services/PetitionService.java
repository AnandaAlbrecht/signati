package br.edu.ifsp.aluno.signati.services;

import br.edu.ifsp.aluno.signati.dto.petition.GetPetitionDTO;
import br.edu.ifsp.aluno.signati.dto.petition.PetitionPostDTO;
import br.edu.ifsp.aluno.signati.models.Petition;
import br.edu.ifsp.aluno.signati.models.Signature;
import br.edu.ifsp.aluno.signati.repositories.PetitionRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetitionService {

  private final PetitionRepository petitionRepository;
  private final UserService userService;

  public GetPetitionDTO postPetition(PetitionPostDTO postDTO) {

    var petition = postDTO.toPetition();

    return this.petitionRepository.save(petition).toDTO();
  }

  public GetPetitionDTO getPetitionDTOById(Integer petitionId) {

    return Optional.ofNullable(this.findPetitionById(petitionId))
        .map(Petition::toDTO).orElse(null);
  }

  public List<GetPetitionDTO> getAllPetitionsByQuery(String titleOrContent) {

    var petitions = Objects.nonNull(titleOrContent) ?
        this.petitionRepository.findAllByTitleOrContent(titleOrContent) :
        this.petitionRepository.findAll();

    return petitions.stream().map(Petition::toDTO).collect(Collectors.toList());
  }

  public Petition findPetitionById(Integer petitionId) {

    return this.petitionRepository.findById(petitionId).orElseThrow(RuntimeException::new);
  }


  public void deletePetition(Integer petitionId) {
    this.petitionRepository.deleteById(petitionId);
  }

  public void updatePetition(Integer petitionId, PetitionPostDTO petitionPut) {

    var petition = this.findPetitionById(petitionId);
    petition.updatePetition(petitionPut);

    this.petitionRepository.save(petition);
  }

  public void removeSignature(Signature signature){

  }
}
