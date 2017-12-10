/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arska.webpabrojekti.repository;

import com.arska.webpabrojekti.domain.Uutinen;
import com.arska.webpabrojekti.domain.UutisKategoria;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created Dec 9, 2017
 * @author arska
 */
public interface UutisRepository extends JpaRepository<Uutinen, Long> {
    List<Uutinen> findByKategoriatContaining(UutisKategoria kategoria, Pageable page);
    List<Uutinen> findByKategoriatContaining(UutisKategoria kategoria);
    List<Uutinen> findFirst5ByKategoriatContaining(UutisKategoria kategoria, Sort sort);
}
