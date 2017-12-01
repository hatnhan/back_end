/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifisolution.repository;

import com.ifisolution.entity.Accounts;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author johnny
 */
@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Integer>{
        List<Accounts> findByCustomersCustomerId(Integer id);
        List<Accounts> findByUsername(String username);
}
