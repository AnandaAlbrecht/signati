package br.edu.ifsp.aluno.signati.controllers;

import br.edu.ifsp.aluno.signati.dto.signature.PostSignatureDTO;
import br.edu.ifsp.aluno.signati.services.SignatureService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("petitions/{petitionId}/signature")
@RequiredArgsConstructor
public class SignatureController {

  private final SignatureService signatureService;

  @PostMapping
  public RedirectView postSignature(@PathVariable Integer petitionId,
      @Valid @ModelAttribute PostSignatureDTO postSignatureDTO, BindingResult result) {

    signatureService.postSignature(petitionId, postSignatureDTO);
    return new RedirectView("/petitions/" + petitionId);

  }

  @PostMapping("/{signatureId}/delete")
  public RedirectView delete(@PathVariable Integer petitionId, @PathVariable Integer signatureId) {
    var rv = new RedirectView("/petitions/" + petitionId);

    this.signatureService.deleteById(signatureId);

    return rv;
  }


}
