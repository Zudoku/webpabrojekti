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
import java.util.*;

import com.google.common.io.ByteStreams;
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
    private UutisKategoria viihde;
    private UutisKategoria terveys;
    private UutisKategoria politiikka;
    private UutisKategoria some;
    
    private Random r = new Random();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        setUpKategoria();
        setUpToimittaja();
        setUpUutinen();
    }
    
    private void setUpKategoria(){
        urheilu = new UutisKategoria(true, "Urheilu", new ArrayList<>());
        terveys = new UutisKategoria(true, "Terveys", new ArrayList<>());
        some = new UutisKategoria(false, "Sosiaalinen media", new ArrayList<>());
        viihde = new UutisKategoria(false, "Viihde", new ArrayList<>());
        politiikka = new UutisKategoria(false, "Politiikka", new ArrayList<>());
        
        kategoriaRepository.save(urheilu);
        kategoriaRepository.save(terveys);
        kategoriaRepository.save(viihde);
        kategoriaRepository.save(some);
        kategoriaRepository.save(politiikka);
    }

    private void setUpUutinen() throws IOException, URISyntaxException {
        byte[] newsPicData = ByteStreams.toByteArray(resourceLoader.getClassLoader().getResourceAsStream("news.jpg"));
        
        setUpUrheilu(newsPicData);
        setUpTerveys(newsPicData);
    }

    private void setUpTerveys(byte[] newsPicData) {
        Uutinen u = new Uutinen("2 Niksiä flunssaan", "Asiantuntijan vinkit.",
                "1. Lepo. 2. Uni.",
                System.currentTimeMillis() - (r.nextInt(5) * 1000 * 60 * 60 * 24), System.currentTimeMillis(), newsPicData, r.nextInt(200),
                Collections.singletonList(terveys), getRandomToimittaja());

        uutisRepository.save(u);

        u = new Uutinen("Syöpään on löydetty uusi hoito", "Ruotsissa asiantuntijat ovat löytäneet uuden hoidon ihosyöpään.",
                "Ruotsissa Tukholman yliopistollisen sairaalan tutkijaryhmä on saanut positiivisia tuloksia hoitaessaan ihosyöpää uusilla tavoin.",
                System.currentTimeMillis() - (r.nextInt(5) * 1000 * 60 * 60 * 24), System.currentTimeMillis(), newsPicData, r.nextInt(200),
                Collections.singletonList(terveys), getRandomToimittaja());

        uutisRepository.save(u);

        u = new Uutinen("Vain kolmas-osa suomalaisista osaisi elvyttää", "Hätätilanteessa elvytys on arvokas taito.",
                "Suomalaisista vain kolmas osa osaisi elvyttää arvioi Suomen terveysliitto ST.",
                System.currentTimeMillis() - (r.nextInt(5) * 1000 * 60 * 60 * 24), System.currentTimeMillis(), newsPicData, r.nextInt(200),
                Collections.singletonList(terveys), getRandomToimittaja());

        uutisRepository.save(u);

        u = new Uutinen("Stressin oireet ovat muuttumassa", "Uusi tutkimus osoittaa.",
                "Stressin oireet ovat muuttuneet viime vuosikymmenen aikana toteaa Suomen terveysliitto ST.",
                System.currentTimeMillis() - (r.nextInt(5) * 1000 * 60 * 60 * 24), System.currentTimeMillis(), newsPicData, r.nextInt(200),
                Collections.singletonList(terveys), getRandomToimittaja());

        uutisRepository.save(u);

        u = new Uutinen("Opiskelijoiden univaje johtaa stressiin", "Monet nuoret kokevat kärsivänsä univajeesta.",
                "Monet nuoret kokevat kärsivänsä univajeesta. Tohtori T.T Suomen terveysliitosta suosittelee nukkumaan tarpeeksi.",
                System.currentTimeMillis() - (r.nextInt(5) * 1000 * 60 * 60 * 24), System.currentTimeMillis(), newsPicData, r.nextInt(200),
                Collections.singletonList(terveys), getRandomToimittaja());

        uutisRepository.save(u);

        u = new Uutinen("30 minuuttia terveellistä liikuntaa päivässä tuo noin 5 vuotta lisää elinaikaa", "Asiantuntijat suosittelevat liikkumaan ainakin 30 minuuttia päivässä.",
                "Asiantuntijat suosittelevat liikkumaan ainakin 30 minuuttia päivässä.",
                System.currentTimeMillis() - (r.nextInt(5) * 1000 * 60 * 60 * 24), System.currentTimeMillis(), newsPicData, r.nextInt(200),
                Collections.singletonList(terveys), getRandomToimittaja());

        uutisRepository.save(u);
    }

    private void setUpUrheilu(byte[] newsPicData){
        Uutinen u = new Uutinen("Suomalainen jalkapalloilija sai kultaa", "Suomalainen jalkapalloilija Tero teronen on voittanut Lontoon kisoissa kultaa.",
                "Suomalainen jalkapalloilija Tero teronen on voittanut vuoden 2017 Lontoon talvikisoissa. Hän on osa UNI-spört joukkuetta. Hän ei vastannut haastattelupyyntöihimme.",
                System.currentTimeMillis() - (r.nextInt(5) * 1000 * 60 * 60 * 24), System.currentTimeMillis(), newsPicData, r.nextInt(200),
                Collections.singletonList(urheilu), getRandomToimittaja());

        uutisRepository.save(u);

        u = new Uutinen("Suomalainen urheilu kiinnostaa yhä useamipia ulkomaalaisia", "Etenkin pohjoismaissa Suomalaisen urheilun suosio on kasvanut",
                "Suomalainen urheilu kiinnostaa yhä useampia ulkomaalaisia, tiedottaa Euroopan urheiluliitto EUSPORT. Etenkin pohjoismainen urheilu näyttää olevan nousussa.",
                System.currentTimeMillis() - (r.nextInt(5) * 1000 * 60 * 60 * 24), System.currentTimeMillis(), newsPicData, r.nextInt(200),
                Collections.singletonList(urheilu), getRandomToimittaja());

        uutisRepository.save(u);

        u = new Uutinen("Venäjällä suuri doping-skandaali", "Kolme miesten olympiamitalistia on jäänyt kiinni dopingista.",
                "Venäjällä 3 miesten pyöräilyn olympiamitalistia vuoden 2015 kesäkisojen pyöräilyssä, on jäänyt kiinni kiellettyjen aineiden käytöstä. Asia huomattiin vuosittaisissa uusinta testeissä.",
                System.currentTimeMillis() - (r.nextInt(5) * 1000 * 60 * 60 * 24), System.currentTimeMillis(), newsPicData, r.nextInt(200),
                Collections.singletonList(urheilu), getRandomToimittaja());

        uutisRepository.save(u);

        u = new Uutinen("Onko E-urheilu urheilua?", "Keskustelupalstojen puheenaihe on ollut valtamediassa suuresti esillä.",
                "Keskustelupalstojen puheenaihe on ollut valtamediassa suuresti esillä. Mitä urheilu oikein on? Urheiluksi lasketaan usein myös Golf tai Pokeri.",
                System.currentTimeMillis() - (r.nextInt(5) * 1000 * 60 * 60 * 24), System.currentTimeMillis(), newsPicData, r.nextInt(200),
                Collections.singletonList(urheilu), getRandomToimittaja());

        uutisRepository.save(u);

        u = new Uutinen("3 Asiaa jotka vain urheilijat ymmärtävät", "Klikkaa selvittääksesi oletko urheilija!",
                "1. Urheilun jälkeen maistuu ruoka. 2. Aina ei vain jaksa. 3. Urheilua ei lasketa ellet ota kuvaa someen",
                System.currentTimeMillis() - (r.nextInt(5) * 1000 * 60 * 60 * 24), System.currentTimeMillis(), newsPicData, r.nextInt(200),
                Arrays.asList(urheilu, some), getRandomToimittaja());

        uutisRepository.save(u);

        u = new Uutinen("Oletko aina urheillut väärin?", "Asiantuntija kertoo kuinka ammattilaiset urheilevat.",
                "Ammattilaiset urheilevat säännöllisesti ja rutiininomaisesti.",
                System.currentTimeMillis() - (r.nextInt(5) * 1000 * 60 * 60 * 24), System.currentTimeMillis(), newsPicData, r.nextInt(200),
                Collections.singletonList(urheilu), getRandomToimittaja());

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
