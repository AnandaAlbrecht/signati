package br.edu.ifsp.aluno.signati.controllers;

import br.edu.ifsp.aluno.signati.dto.petition.GetPetitionDTO;
import br.edu.ifsp.aluno.signati.dto.petition.PetitionPostDTO;
import br.edu.ifsp.aluno.signati.dto.signature.PostSignatureDTO;
import br.edu.ifsp.aluno.signati.models.Petition;
import br.edu.ifsp.aluno.signati.repositories.PetitionRepository;
import br.edu.ifsp.aluno.signati.services.PetitionService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/petitions")
@RequiredArgsConstructor
public class PetitionController {

  @Autowired
  private PetitionRepository petitionRepository;

  private final PetitionService petitionService;

  @PostMapping
  @ResponseBody
  public RedirectView postPetition(@Valid @ModelAttribute PetitionPostDTO petitionPost,
      BindingResult result) {

    var petition = this.petitionService.postPetition(petitionPost);

    return new RedirectView("/petitions/" + petition.getId());
  }

  @GetMapping("/{petitionId}")
  public ModelAndView getPetition(@PathVariable Integer petitionId) {

    var dto = this.petitionService.getPetitionDTOById(petitionId);
    var mv = new ModelAndView("petition");
    mv.addObject("petition", dto);
    mv.addObject("postSignatureDTO", new PostSignatureDTO());
    return mv;
  }

  @GetMapping
  @ResponseBody
  public List<GetPetitionDTO> getAllPetition(
      @RequestParam(value = "q", required = false) String query) {

    return this.petitionService.getAllPetitionsByQuery(query);
  }

  @GetMapping("/new")
  public ModelAndView create() {
    var mv = new ModelAndView("new");
    mv.addObject("petitionPostDTO", new PetitionPostDTO());
    return mv;
  }

  @GetMapping("/{petitionId}/edit")
  public ModelAndView edit(@PathVariable Integer petitionId) {
    Optional<Petition> optional = this.petitionRepository.findById(petitionId);

    if (optional.isPresent()) {
      var dto = this.petitionService.getPetitionDTOById(petitionId);

      var mv = new ModelAndView("edit");
      mv.addObject("petition", dto);
      mv.addObject("petitionPostDTO", dto);

      return mv;

    }else {
      return this.petitionError("EDIT ERROR: Petição #" + petitionId + " não encontrada!");
    }
  }

  @GetMapping("/{petitionId}/delete")
  public ModelAndView delete(@PathVariable Integer petitionId) {
    var mv = new ModelAndView("redirect:/");


    try {
      this.petitionRepository.deleteById(petitionId);
      mv.addObject("message", "Petição #" + petitionId + " excluída com sucesso!");
      mv.addObject("error", false);
    }
    catch (EmptyResultDataAccessException e) {
      System.out.println(e);
      mv = this.petitionError("DELETE ERROR: Petição #" + petitionId + " não encontrada!");
    }

    return mv;
  }

  private ModelAndView petitionError(String msg) {
   var mv = new ModelAndView("redirect:/");
    mv.addObject("message", msg);
    mv.addObject("error", true);
    return mv;
  }

}
