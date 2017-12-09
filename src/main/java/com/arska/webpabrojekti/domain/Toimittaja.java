/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arska.webpabrojekti.domain;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Created Dec 6, 2017
 * @author arska
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Toimittaja extends AbstractPersistable<Long> {
    private String nimi;
}
