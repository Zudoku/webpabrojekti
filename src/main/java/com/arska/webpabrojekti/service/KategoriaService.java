/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arska.webpabrojekti.service;

import com.arska.webpabrojekti.domain.Uutinen;
import com.arska.webpabrojekti.domain.UutisKategoria;
import com.arska.webpabrojekti.repository.KategoriaRepository;
import com.arska.webpabrojekti.repository.UutisRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created Dec 9, 2017
 * @author arska
 */
@Service
public class KategoriaService {
    
    @Autowired
    private KategoriaRepository kategoriaRepository;
    
    @Autowired
    private UutisRepository uutisRepository;
    
    public List<UutisKategoria> getPublicCategories(){
        return kategoriaRepository.findByJulkisuus(true);
    }
    
    public List<UutisKategoria> getAllCategories(){
        return kategoriaRepository.findAll();
    }
    
    public UutisKategoria getKategoria(long id){
        Optional<UutisKategoria> kategoria = kategoriaRepository.findById(id);
        if(kategoria.isPresent()){
            return kategoria.get();
        }
        return null;
    }
    
    public UutisKategoria alustaKategoria(String teksti, boolean julkisuus){
        UutisKategoria kategoria = new UutisKategoria(julkisuus, teksti, new ArrayList<>());
        return kategoriaRepository.save(kategoria);
    }
    
    public void poistaKategoriaJaSiihenLiittyvatUutiset(long id) {
        kategoriaRepository.deleteById(id);
    }
    
    public void paivitaKategoria(long id, boolean julkisuus, String teksti){
        UutisKategoria kategoria = getKategoria(id);
        if(kategoria != null){
            kategoria.setJulkisuus(julkisuus);
            kategoria.setTeksti(teksti);
            kategoriaRepository.save(kategoria);
        }
    }
    
    public List<Uutinen> getKatsotuimmat(long id, boolean limitToFive){
        UutisKategoria kategoria = getKategoria(id);
        if(kategoria != null){
            if(limitToFive){
                return uutisRepository.findFirst5ByKategoriatContaining(kategoria, Sort.by(Sort.Direction.DESC, "visits"));
            } else {
                return uutisRepository.findByKategoriatContaining(kategoria, Sort.by(Sort.Direction.DESC, "visits"));
            }
        }
        
        return new ArrayList<>();
    }
    
    public List<Uutinen> getUusimmat(long id, boolean limitToFive){
        UutisKategoria kategoria = getKategoria(id);
        if(kategoria != null){
            if(limitToFive){
                return uutisRepository.findFirst5ByKategoriatContaining(kategoria, Sort.by(Sort.Direction.DESC, "created"));
            } else {
                return uutisRepository.findByKategoriatContaining(kategoria, Sort.by(Sort.Direction.DESC, "created"));
            }
        }
        
        return new ArrayList<>();
    }

}
