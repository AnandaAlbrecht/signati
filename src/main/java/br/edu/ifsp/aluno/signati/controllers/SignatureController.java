package br.edu.ifsp.aluno.signati.controllers;

import br.edu.ifsp.aluno.signati.dto.signature.PostSignatureDTO;
import br.edu.ifsp.aluno.signati.repositories.PetitionRepository;
import br.edu.ifsp.aluno.signati.repositories.SignatureRepository;
import br.edu.ifsp.aluno.signati.services.PetitionService;
import br.edu.ifsp.aluno.signati.services.SignatureService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

  @Autowired
  private SignatureRepository signatureRepository;

  @PostMapping
  public RedirectView postSignature(@PathVariable Integer petitionId,
      @Valid @ModelAttribute PostSignatureDTO postSignatureDTO, BindingResult result) {

    signatureService.postSignature(petitionId, postSignatureDTO);
    return new RedirectView("/petitions/"+petitionId);

  }

  @GetMapping("/{signatureId}")
  public ModelAndView delete(@PathVariable Integer petitionId,@PathVariable Integer signatureId) {
    var mv = new ModelAndView("redirect:/petitions/" + petitionId);


    try {
      this.signatureRepository.deleteById(signatureId);
      mv.addObject("message", "Assinatura excluída com sucesso!");
      mv.addObject("error", false);
    }
    catch (EmptyResultDataAccessException e) {
      System.out.println(e);
      mv.addObject("message", "DELETE ERROR: Assinatura não encontrada!");
    }

    return mv;
  }


}
