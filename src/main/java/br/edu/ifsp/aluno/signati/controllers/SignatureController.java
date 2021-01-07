package br.edu.ifsp.aluno.signati.controllers;

import br.edu.ifsp.aluno.signati.dto.signature.PostSignatureDTO;
import br.edu.ifsp.aluno.signati.services.SignatureService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("petition/{petitionId}/signature")
@RequiredArgsConstructor
public class SignatureController {
//petition/{petitionId}/signature
  private final SignatureService signatureService;

  @PostMapping
  @ResponseStatus(HttpStatus.ACCEPTED)
  public ModelAndView postSignature(@PathVariable Integer petitionId, @Valid @RequestBody PostSignatureDTO postSignatureDTO, BindingResult result){

      signatureService.postSignature(petitionId, postSignatureDTO);
      var mv = new ModelAndView("petition");
      mv.addObject("petitionId", petitionId);
      return mv;

  }
}
