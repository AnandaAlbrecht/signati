package br.edu.ifsp.aluno.signati.services;

import br.edu.ifsp.aluno.signati.dto.signature.PostSignatureDTO;
import br.edu.ifsp.aluno.signati.models.Signature;
import br.edu.ifsp.aluno.signati.repositories.SignatureRepository;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignatureService {

  private final SignatureRepository signatureRepository;
  private final UserService userService;
  private final PetitionService petitionService;

  public void postSignature(Integer petitionId, PostSignatureDTO postSignatureDTO) {

    var signature = Signature.builder()
        .author(userService.findUserById(postSignatureDTO.getAuthorId()))
        .petition(petitionService.findPetitionById(petitionId))
        .build();

    signatureRepository.save(signature);
  }
}
