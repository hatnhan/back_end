/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifisolution.repository;

import com.ifisolution.entity.Invoices;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author johnny
 */
public interface InvoicesRepository extends JpaRepository<Invoices, Integer>{
        
}
