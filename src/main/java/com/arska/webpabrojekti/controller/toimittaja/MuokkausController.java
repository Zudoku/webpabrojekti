/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arska.webpabrojekti.controller.toimittaja;

import com.arska.webpabrojekti.domain.Toimittaja;
import com.arska.webpabrojekti.service.ToimittajaService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created Dec 6, 2017
 * @author arska
 */
@Controller
public class MuokkausController {
    
    public final static String TOIMITTAJAID = "toimittajaid";
    
    @Autowired
    private ToimittajaService toimittajaService;
    
    
    @GetMapping("/toimittaja")
    public String handleDefault(Model model, HttpSession session) {
        if (needLogin(session, toimittajaService)) {
            return "editor/login";
        } else {
            long sessionToimittajaID = (long) session.getAttribute(TOIMITTAJAID);
            model.addAttribute("toimittaja", toimittajaService.getToimittaja(sessionToimittajaID));
            return "editor/index";
        }
    }
    
    @GetMapping("/logout")
    public String handleLogout(Model model, HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    
    @PostMapping("/kirjaudu")
    public String handleLogin(HttpSession session, @RequestParam String nimi) {
        Toimittaja toimittaja =  toimittajaService.alustaToimittaja(nimi);
        session.setAttribute(TOIMITTAJAID, toimittaja.getId());
        return "redirect:/toimittaja";
    }
    
    public static boolean needLogin(HttpSession session, ToimittajaService tservice){
        return (session == null || session.getAttribute(TOIMITTAJAID) == null || 
                !(session.getAttribute(TOIMITTAJAID) instanceof Long) || tservice.getToimittaja((long) session.getAttribute(TOIMITTAJAID)) == null );
    }
    

}
