/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifisolution.repository;

import com.ifisolution.entity.Customers;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author johnny
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customers, Integer>{
        List<Customers> findByNameContaining(String name);
        List<Customers> findByEmailContaining(String email); 
        List<Customers> findByAddressContaining(String address); 
        List<Customers> findByEmail(String email);
}
