/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arska.webpabrojekti.controller.toimittaja;

import static com.arska.webpabrojekti.controller.toimittaja.MuokkausController.TOIMITTAJAID;
import static com.arska.webpabrojekti.controller.toimittaja.MuokkausController.needLogin;
import com.arska.webpabrojekti.domain.Uutinen;
import com.arska.webpabrojekti.service.ToimittajaService;
import com.arska.webpabrojekti.service.UutisService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created Dec 9, 2017
 * @author arska
 */
@Controller
public class UutisController {
    
    @Autowired
    private ToimittajaService toimittajaService;
    
    @Autowired
    private UutisService uutisService;
    
    @GetMapping("/toimittaja/uutiset")
    public String handleGetKategoriat(Model model, HttpSession session) {
        if (needLogin(session) || toimittajaService.getToimittaja((long) session.getAttribute(TOIMITTAJAID)) == null) {
            return "redirect:/toimittaja";
        } else {
            long sessionToimittajaID = (long) session.getAttribute(TOIMITTAJAID);
            model.addAttribute("toimittaja", toimittajaService.getToimittaja(sessionToimittajaID));
            model.addAttribute("uutiset", uutisService.getAllUutinen());
            return "editor/uutiset";
        }
    }
}
