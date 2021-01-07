package br.edu.ifsp.aluno.signati.controllers;

import br.edu.ifsp.aluno.signati.services.PetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final PetitionService petitionService;

    @GetMapping
    public ModelAndView helloController(){

        var mv = new ModelAndView("home");
        mv.addObject("petitions", petitionService.getAllPetitionsByQuery(null));

        return mv;
    }
}
