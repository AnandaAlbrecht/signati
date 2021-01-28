package br.edu.ifsp.aluno.signati.services;

import br.edu.ifsp.aluno.signati.dto.signature.PostSignatureDTO;
import br.edu.ifsp.aluno.signati.models.Signature;
import br.edu.ifsp.aluno.signati.repositories.SignatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignatureService {

  private final SignatureRepository signatureRepository;
  private final PetitionService petitionService;

  public void postSignature(Integer petitionId, PostSignatureDTO postSignatureDTO) {

    var signature = Signature.builder()
        .author(postSignatureDTO.getAuthor())
        .petition(petitionService.findPetitionById(petitionId))
        .build();

    signatureRepository.save(signature);
  }

  protected Signature findSignatureById(Integer signatureId) {

    return this.signatureRepository.findById(signatureId).orElse(null);
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void deleteById(Integer signatureId) {

    var signature = this.findSignatureById(signatureId);
    this.petitionService.findPetitionById(signature.getPetition().getId())
        .getSignatures()
        .remove(signature);
    this.signatureRepository.deleteById(signatureId);
  }
}
