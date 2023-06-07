package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Random;

@Controller
public class DiceController {

    @GetMapping("/roll-dice")
    public String diceRoller(){
        return "/dice/dice-selector";
    }

    @GetMapping("/roll-dice/{n}")
    public String diceRevealer(@PathVariable int n, Model model){
        Random random = new Random();
        int rando = random.nextInt(6) + 1;

        model.addAttribute("guess", n);
        model.addAttribute("randomNum", rando);
        return "/dice/dice-revealer";
    }
}
