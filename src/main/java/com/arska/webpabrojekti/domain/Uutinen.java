/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arska.webpabrojekti.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Created Dec 3, 2017
 * @author arska
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Uutinen extends AbstractPersistable<Long> {
    private String otsikko;
    private String ingressi;
    private String sisalto;
    private long created;
    private long updated;
    @Lob
    private byte[] kuva;
    private long visits;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private List<UutisKategoria> kategoriat;
    @ManyToMany
    private List<Toimittaja> toimittajat;
    
    public String getTimeStamp(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy, HH:mm");    
        Date createdDate = new Date(created);
        Date updatedDate = new Date(updated);
        return "Julkaistu: " + sdf.format(createdDate);
    }
    
    public String getModifiedTimeStamp(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy, HH:mm");    

        Date updatedDate = new Date(updated);
        return " Muokattu viimeksi: " + sdf.format(updatedDate);
    }

    public String getToimittajatString(){
        String result = "Kirjoittanut: ";
        for(Toimittaja toimittaja : toimittajat){
            result += ( toimittaja.nimi() + " ");
        }

        return result;
    }

    public String getKategoriatString(){
        String result = "Kategoriat: ";
        for(UutisKategoria kategoria : kategoriat){
            result += ( kategoria.teksti() + " ");
        }

        return result;
    }
    
    

}
