package br.edu.ifsp.aluno.signati.controllers;

import br.edu.ifsp.aluno.signati.dto.petition.GetPetitionDTO;
import br.edu.ifsp.aluno.signati.dto.petition.PetitionPostDTO;
import br.edu.ifsp.aluno.signati.dto.signature.PostSignatureDTO;
import br.edu.ifsp.aluno.signati.services.PetitionService;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
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

  @GetMapping("/{petitionId}/edit")
  public ModelAndView getEdit(@PathVariable Integer petitionId) {

    var dto = this.petitionService.getPetitionDTOById(petitionId);

    var mv = new ModelAndView("edit");
    mv.addObject("petition", dto);
    mv.addObject("petitionPostDTO", dto);

    return mv;

  }

  @PostMapping("/{petitionId}/edit")
  public RedirectView edit(@PathVariable Integer petitionId,
      @ModelAttribute PetitionPostDTO petitionPut) {

    this.petitionService.updatePetition(petitionId, petitionPut);
    return new RedirectView("/petitions/" + petitionId);
  }

  @PostMapping("/{petitionId}/delete")
  public RedirectView delete(@PathVariable Integer petitionId) {
    var mv = new RedirectView("/");

    this.petitionService.deletePetition(petitionId);

    return mv;
  }

  @ExceptionHandler(Exception.class)
  private RedirectView petitionError(EmptyResultDataAccessException ex,
      HttpServletRequest request,
      HttpServletResponse response)
      throws IOException {

    RedirectView rw = new RedirectView("/");
    rw.setStatusCode(HttpStatus.MOVED_PERMANENTLY); // you might not need this
    FlashMap outputFlashMap = RequestContextUtils.getOutputFlashMap(request);
    if (Objects.nonNull(outputFlashMap)) {
      outputFlashMap.put("myAttribute", true);
      outputFlashMap.put("message", true);
      outputFlashMap.put("error", true);
    }
    return rw;
  }

}
