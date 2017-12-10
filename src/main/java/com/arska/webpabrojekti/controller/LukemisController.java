/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arska.webpabrojekti.controller;

import com.arska.webpabrojekti.domain.Uutinen;
import com.arska.webpabrojekti.service.KategoriaService;
import com.arska.webpabrojekti.service.UutisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created Dec 6, 2017
 * @author arska
 */
@Controller
public class LukemisController {
    
    @Autowired
    private KategoriaService kategoriaService;
    
    @Autowired
    private UutisService uutisService;

    @RequestMapping("/")
    public String handleDefault(Model model, @RequestParam(required=false, defaultValue="0") int page) {
        model.addAttribute("julkisetKategoriat", kategoriaService.getPublicCategories());
        model.addAttribute("uutiset", uutisService.getUutiset(page));
        model.addAttribute("kategoriateksti", "Kaikki");
        model.addAttribute("uusimmat_uutiset",  uutisService.getUusimmat5());
        model.addAttribute("katsotuimmat_uutiset",  uutisService.getKatsotuimmat5());
        
        
        return "public/index";
    }
    
    @GetMapping("/uutinen/{id}")
    public String handleGetUutinen(Model model, @PathVariable long id) {
        uutisService.katseleUutinen(id);
        if(uutisService.getUutinen(id) == null){
            return "redirect:/";
        }
        
        model.addAttribute("julkisetKategoriat", kategoriaService.getPublicCategories());
        model.addAttribute("uutinen", uutisService.getUutinen(id));
        model.addAttribute("kategoriateksti", "Kaikki");
        model.addAttribute("uusimmat_uutiset",  uutisService.getUusimmat5());
        model.addAttribute("katsotuimmat_uutiset",  uutisService.getKatsotuimmat5());
        
        return "public/uutinen";
    }
    
    @GetMapping("/kategoria/{id}")
    public String handleGetKategoria(Model model, @PathVariable long id) {
        if(kategoriaService.getKategoria(id) == null){
            return "redirect:/";
        }
        
        model.addAttribute("julkisetKategoriat", kategoriaService.getPublicCategories());
        model.addAttribute("uutiset", kategoriaService.getUusimmat(id, true));
        model.addAttribute("kategoriateksti", kategoriaService.getKategoria(id).getTeksti());
        model.addAttribute("uusimmat_uutiset",  kategoriaService.getUusimmat(id, true));
        model.addAttribute("katsotuimmat_uutiset",  kategoriaService.getKatsotuimmat(id, true));
        
        
        return "public/index";
    }
    
    @GetMapping(path = "/uutiskuva/{id}", produces = "image/jpg")
    @ResponseBody
    public byte[] handleGetUutiskuva(Model model, @PathVariable long id) {
        Uutinen uutinen = uutisService.getUutinen(id);
        if(uutinen != null){
            return uutinen.getKuva();
        }
        return new byte[0];
    }
    
}
