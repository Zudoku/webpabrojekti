/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arska.webpabrojekti.controller.toimittaja;

import static com.arska.webpabrojekti.controller.toimittaja.MuokkausController.TOIMITTAJAID;
import static com.arska.webpabrojekti.controller.toimittaja.MuokkausController.needLogin;
import com.arska.webpabrojekti.service.KategoriaService;
import com.arska.webpabrojekti.service.ToimittajaService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created Dec 9, 2017
 * @author arska
 */
@Controller
public class KategoriaController {

    @Autowired
    private ToimittajaService toimittajaService;
    
    @Autowired
    private KategoriaService kategoriaService;
    
    
    @GetMapping("/toimittaja/kategoriat")
    public String handleGetKategoriat(Model model, HttpSession session) {
        if (needLogin(session) || toimittajaService.getToimittaja((long) session.getAttribute(TOIMITTAJAID)) == null) {
            return "redirect:/toimittaja";
        } else {
            long sessionToimittajaID = (long) session.getAttribute(TOIMITTAJAID);
            model.addAttribute("toimittaja", toimittajaService.getToimittaja(sessionToimittajaID));
            model.addAttribute("kategoriat", kategoriaService.getAllCategories());
            return "editor/kategoriat";
        }
    }
    
    @GetMapping("/toimittaja/kategoria/{id}")
    public String handleGetKategoria(Model model, HttpSession session, @PathVariable long id) {
        if (needLogin(session) || toimittajaService.getToimittaja((long) session.getAttribute(TOIMITTAJAID)) == null) {
            return "redirect:/toimittaja";
        } else {
            long sessionToimittajaID = (long) session.getAttribute(TOIMITTAJAID);
            model.addAttribute("toimittaja", toimittajaService.getToimittaja(sessionToimittajaID));
            model.addAttribute("kategoria", kategoriaService.getKategoria(id));
            return "editor/kategoria";
        }
    }
    
    @PostMapping("/toimittaja/kategoriat")
    public String handleNewKategoria(Model model, HttpSession session, @RequestParam String  teksti, @RequestParam(required=false) Boolean julkisuus) {
        if (needLogin(session) || toimittajaService.getToimittaja((long) session.getAttribute(TOIMITTAJAID)) == null) {
            return "redirect:/toimittaja";
        } else {
            boolean julkisuusValue = (julkisuus == null) ? false : julkisuus;
            kategoriaService.alustaKategoria(teksti, julkisuusValue);
            return "redirect:/toimittaja/kategoriat";
        }
    }
    
    @DeleteMapping("/toimittaja/kategoria/{id}")
    public String handleDeleteKategoria(Model model, HttpSession session, @PathVariable long id) {
        if (needLogin(session) || toimittajaService.getToimittaja((long) session.getAttribute(TOIMITTAJAID)) == null) {
            return "redirect:/toimittaja";
        } else {
            kategoriaService.poistaKategoria(id);
            return "redirect:/toimittaja/kategoriat";
        }
    }
    
    @PostMapping("/toimittaja/kategoria/{id}")
    public String handleUpdateKategoria(Model model, HttpSession session, @RequestParam String  teksti, 
            @RequestParam(required=false) Boolean julkisuus, @PathVariable long id) {
        if (needLogin(session) || toimittajaService.getToimittaja((long) session.getAttribute(TOIMITTAJAID)) == null) {
            return "redirect:/toimittaja";
        } else {
            boolean julkisuusValue = (julkisuus == null) ? false : julkisuus;
            kategoriaService.paivitaKategoria(id, julkisuusValue, teksti);
            return "redirect:/toimittaja/kategoriat";
        }
    }
    
    
    
}
