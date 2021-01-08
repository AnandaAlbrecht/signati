package br.edu.ifsp.aluno.signati.controllers;

import br.edu.ifsp.aluno.signati.dto.petition.GetPetitionDTO;
import br.edu.ifsp.aluno.signati.dto.petition.PetitionPostDTO;
import br.edu.ifsp.aluno.signati.dto.signature.PostSignatureDTO;
import br.edu.ifsp.aluno.signati.services.PetitionService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/petitions")
@RequiredArgsConstructor
public class PetitionController {

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


}
