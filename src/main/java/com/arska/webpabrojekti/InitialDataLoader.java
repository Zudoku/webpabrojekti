/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arska.webpabrojekti;

import com.arska.webpabrojekti.domain.Toimittaja;
import com.arska.webpabrojekti.domain.Uutinen;
import com.arska.webpabrojekti.domain.UutisKategoria;
import com.arska.webpabrojekti.repository.KategoriaRepository;
import com.arska.webpabrojekti.repository.ToimittajaRepository;
import com.arska.webpabrojekti.repository.UutisRepository;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 * Created Dec 9, 2017
 * @author arska
 */
@Component
public class InitialDataLoader implements ApplicationRunner {
    
    @Autowired
    private KategoriaRepository kategoriaRepository;
    
    @Autowired
    private ToimittajaRepository toimittajaRepository;
    
    @Autowired
    private UutisRepository uutisRepository;
    
    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        setUpKategoria();
        setUpToimittaja();
        setUpUutinen();
    }
    
    private void setUpKategoria(){
        kategoriaRepository.save(new UutisKategoria(true, "Urheilu"));
        kategoriaRepository.save(new UutisKategoria(true, "Viihde"));
        kategoriaRepository.save(new UutisKategoria(true, "Terveys"));
        kategoriaRepository.save(new UutisKategoria(true, "Sosiaalinen media"));
        kategoriaRepository.save(new UutisKategoria(false, "Politiikka"));
    }

    private void setUpUutinen() throws IOException, URISyntaxException {
        URL path = resourceLoader.getClassLoader().getResource("news.jpg");
        File fi = new File(path.toURI());
        byte[] newsPicData = Files.readAllBytes(fi.toPath());
        
        Uutinen u = new Uutinen("Suomalainen jalkapalloilija sai kultaa", "Suomalainen jalkapalloilija Tero teronen on voittanut EM-kultaa.", 
                "Suomalainen jalkapalloilija Tero teronen on voittanut EM-kultaa. Hän ei vastannut haastattelupyyntöihimme.", 
                System.currentTimeMillis(), System.currentTimeMillis(), newsPicData, 0);
        
        uutisRepository.save(u);
    }

    private void setUpToimittaja() {
        toimittajaRepository.save(new Toimittaja("Eric Clapman"));
        toimittajaRepository.save(new Toimittaja("Andrew Broze"));
        toimittajaRepository.save(new Toimittaja("Alex Hobson"));
        toimittajaRepository.save(new Toimittaja("Eugene Cam"));
        toimittajaRepository.save(new Toimittaja("Aluna George"));
    }
}
