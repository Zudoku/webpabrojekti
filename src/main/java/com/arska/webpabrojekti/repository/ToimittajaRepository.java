/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arska.webpabrojekti.repository;

import com.arska.webpabrojekti.domain.Toimittaja;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created Dec 9, 2017
 * @author arska
 */
public interface ToimittajaRepository extends JpaRepository<Toimittaja, Long> {
    Toimittaja findByNimi(String nimi);
}
