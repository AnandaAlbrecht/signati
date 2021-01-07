package br.edu.ifsp.aluno.signati.controllers;

import br.edu.ifsp.aluno.signati.dto.signature.PostSignatureDTO;
import br.edu.ifsp.aluno.signati.services.PetitionService;
import br.edu.ifsp.aluno.signati.services.SignatureService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("petitions/{petitionId}/signature")
@RequiredArgsConstructor
public class SignatureController {

  private final SignatureService signatureService;
  private final PetitionService petitionService;

  @PostMapping
  public RedirectView postSignature(@PathVariable Integer petitionId,
      @Valid @ModelAttribute PostSignatureDTO postSignatureDTO, BindingResult result) {

    signatureService.postSignature(petitionId, postSignatureDTO);
    return new RedirectView("/petitions/"+petitionId);

  }
}
