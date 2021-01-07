package br.edu.ifsp.aluno.signati.controllers;

import br.edu.ifsp.aluno.signati.dto.petition.GetPetitionDTO;
import br.edu.ifsp.aluno.signati.dto.petition.PetitionPostDTO;
import br.edu.ifsp.aluno.signati.services.PetitionService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/petitions")
@RequiredArgsConstructor
public class PetitionController {

  private final PetitionService petitionService;

  @PostMapping
  @ResponseBody
  public GetPetitionDTO postPetition(@Valid @RequestBody PetitionPostDTO petitionPost) {

    return this.petitionService.postPetition(petitionPost);
  }

  @GetMapping("/{petitionId}")
  public ModelAndView getPetition(@PathVariable Integer petitionId) {

    var dto = this.petitionService.getPetitionDTOById(petitionId);
    var mv = new ModelAndView("petition");
    mv.addObject("petition", dto);
    return mv;
  }

  @GetMapping
  @ResponseBody
  public List<GetPetitionDTO> getAllPetition(
      @RequestParam(value = "q", required = false) String query) {

    return this.petitionService.getAllPetitionsByQuery(query);
  }

}
