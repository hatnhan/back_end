/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifisolution.controller;

import com.ifisolution.entity.TransactionTypes;
import com.ifisolution.repository.TransactionTypesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author johnny
 */
@RestController
@RequestMapping("api")
public class TransactionTypesController {
        @Autowired
        private TransactionTypesRepository ttr;
        
        @GetMapping("transactiontypes")
        public List<TransactionTypes> getAll(){
                return ttr.findAll();
        }
        
        @GetMapping("transactiontypes/{id}")
        public TransactionTypes getById(@PathVariable("id") Integer id){
                return ttr.findOne(id);
        }
        
        @PostMapping("transactiontypes")
        public String create(@RequestBody TransactionTypes tt) {
                ttr.save(tt);
                return "Success";
        }
        
        
        @DeleteMapping("transactiontypes/{id}")
        public String delete(@PathVariable(value = "id") Integer id) {
                TransactionTypes tt = ttr.findOne(id);
                if (tt == null) {
                        return "Not found";
                }

                ttr.delete(tt);
                return "Success";
        }
}
