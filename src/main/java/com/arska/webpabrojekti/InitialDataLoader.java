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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
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
    
    
    private UutisKategoria urheilu;
    
    private Random r = new Random();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        setUpKategoria();
        setUpToimittaja();
        setUpUutinen();
    }
    
    private void setUpKategoria(){
        urheilu = new UutisKategoria(true, "Urheilu", new ArrayList<>());
        kategoriaRepository.save(urheilu);
        kategoriaRepository.save(new UutisKategoria(true, "Viihde", new ArrayList<>()));
        kategoriaRepository.save(new UutisKategoria(true, "Terveys", new ArrayList<>()));
        kategoriaRepository.save(new UutisKategoria(true, "Sosiaalinen media", new ArrayList<>()));
        kategoriaRepository.save(new UutisKategoria(false, "Politiikka", new ArrayList<>()));
    }

    private void setUpUutinen() throws IOException, URISyntaxException {
        URL path = resourceLoader.getClassLoader().getResource("news.jpg");
        File fi = new File(path.toURI());
        byte[] newsPicData = Files.readAllBytes(fi.toPath());
        
        
        
        Uutinen u = new Uutinen("Suomalainen jalkapalloilija sai kultaa", "Suomalainen jalkapalloilija Tero teronen on voittanut EM-kultaa.", 
                "Suomalainen jalkapalloilija Tero teronen on voittanut EM-kultaa. Hän ei vastannut haastattelupyyntöihimme.", 
                System.currentTimeMillis(), System.currentTimeMillis(), newsPicData, r.nextInt(200), Arrays.asList(urheilu), getRandomToimittaja());
        
        uutisRepository.save(u);
    }
    
    public List<Toimittaja> getRandomToimittaja(){
        return Arrays.asList(toimittajaRepository.findAll().get(r.nextInt(toimittajaRepository.findAll().size())));
    }

    private void setUpToimittaja() {
        toimittajaRepository.save(new Toimittaja("Eric Clapman", new ArrayList<>()));
        toimittajaRepository.save(new Toimittaja("Andrew Broze", new ArrayList<>()));
        toimittajaRepository.save(new Toimittaja("Alex Hobson", new ArrayList<>()));
        toimittajaRepository.save(new Toimittaja("Eugene Cam", new ArrayList<>()));
        toimittajaRepository.save(new Toimittaja("Aluna George", new ArrayList<>()));
    }
}
