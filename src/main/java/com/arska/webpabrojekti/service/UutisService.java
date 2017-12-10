/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arska.webpabrojekti.service;

import com.arska.webpabrojekti.domain.Toimittaja;
import com.arska.webpabrojekti.domain.Uutinen;
import com.arska.webpabrojekti.domain.UutisKategoria;
import com.arska.webpabrojekti.repository.UutisRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created Dec 9, 2017
 * @author arska
 */
@Service
public class UutisService {

    @Autowired
    private UutisRepository uutisRepository;
    
    public List<Uutinen> getAllUutinen(){
        return uutisRepository.findAll();
    }
    
    public Uutinen getUutinen(long id){
        Optional<Uutinen> uutinen = uutisRepository.findById(id);
        if(uutinen.isPresent()){
            return uutinen.get();
        }
        return null;
    }
    
    public void poistaUutinen(long id) {
        uutisRepository.deleteById(id);
    }
    
    public Uutinen alustaUutinen(String otsikko, String ingressi, String sisalto, byte[] kuva, UutisKategoria kategoria, Toimittaja toimittaja){
        Uutinen uutinen = new Uutinen(otsikko, ingressi, sisalto, System.currentTimeMillis(), System.currentTimeMillis(), kuva, 0, Arrays.asList(kategoria), Arrays.asList(toimittaja));
        return uutisRepository.save(uutinen);
    }
    
    public void paivitaUutinen(long id, String otsikko, String ingressi, String sisalto, byte[] kuva, Toimittaja toimittaja){
        Uutinen uutinen = getUutinen(id);
        if(uutinen != null){
            uutinen.setOtsikko(otsikko);
            uutinen.setIngressi(ingressi);
            uutinen.setSisalto(sisalto);
            uutinen.setKuva(kuva);
            uutinen.setUpdated(System.currentTimeMillis());
            
            uutisRepository.save(uutinen);
        }
    }
    
    public void katseleUutinen(long id){
        Uutinen uutinen = getUutinen(id);
        if(uutinen != null){
            uutinen.setVisits(uutinen.getVisits() +1);
            uutisRepository.save(uutinen);
        }
    }
    
    public Page<Uutinen> getUutiset(int page){
        if(page < 0){
            return null;
        }
        return uutisRepository.findAll(PageRequest.of(page, 5, Sort.Direction.DESC, "visits"));
    }
    
    public Page<Uutinen> getKatsotuimmat5(){
        return uutisRepository.findAll(PageRequest.of(0, 5, Sort.Direction.DESC, "visits"));
    }
    
    public Page<Uutinen> getUusimmat5(){
        Pageable p = PageRequest.of(0, 5, Sort.Direction.DESC, "created");
        return uutisRepository.findAll(p);
    }
}
