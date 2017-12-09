/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arska.webpabrojekti.service;

import com.arska.webpabrojekti.domain.Toimittaja;
import com.arska.webpabrojekti.repository.ToimittajaRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created Dec 9, 2017
 * @author arska
 */
@Service
public class ToimittajaService {

    @Autowired
    private ToimittajaRepository toimittajaRepository;
    
    public Toimittaja alustaToimittaja(String nimi){
        if (toimittajaRepository.findByNimi(nimi) != null) {
            return toimittajaRepository.findByNimi(nimi);
        } else {
            Toimittaja t = new Toimittaja(nimi);
            return toimittajaRepository.save(t);
        }
        
    }
    
    public Toimittaja getToimittaja(long id){
        Optional<Toimittaja> toimittaja = toimittajaRepository.findById(id);
        if(toimittaja.isPresent()){
            return toimittaja.get();
        }
        return null;
    }
}
