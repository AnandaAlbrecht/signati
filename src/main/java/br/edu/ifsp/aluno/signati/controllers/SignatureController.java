package br.edu.ifsp.aluno.signati.controllers;

import br.edu.ifsp.aluno.signati.dto.signature.PostSignatureDTO;
import br.edu.ifsp.aluno.signati.services.SignatureService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("petition/{petitionId}/signature")
@RequiredArgsConstructor
public class SignatureController {
//petition/{petitionId}/signature
  private final SignatureService signatureService;

  @PostMapping
  public ModelAndView postSignature(@PathVariable Integer petitionId, @Valid @ModelAttribute PostSignatureDTO postSignatureDTO, BindingResult result){

      var signature = signatureService.postSignature(petitionId, postSignatureDTO);
      var mv = new ModelAndView("petition");
      mv.addObject("petition", signature.getPetition().toDTO());
      mv.addObject("postSignatureDTO", new PostSignatureDTO());
      return mv;

  }
}
