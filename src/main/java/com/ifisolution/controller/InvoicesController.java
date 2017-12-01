/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifisolution.controller;

import com.ifisolution.entity.Invoices;
import com.ifisolution.repository.InvoicesRepository;
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
@RequestMapping("/api")
public class InvoicesController {

        @Autowired
        private InvoicesRepository ir;

        @GetMapping("invoices")
        public List<Invoices> getAll() {
                return ir.findAll();
        }

        @GetMapping("invoices/{id}")
        public Invoices getById(@PathVariable("id") Integer id) {
                return ir.findOne(id);
        }

        @PostMapping("invoices")
        public boolean create(@RequestBody Invoices invoices) {
                ir.save(invoices);
                return true;
        }

        @DeleteMapping("invoices/{id}")
        public boolean delete(@PathVariable(value = "id") Integer id) {
                Invoices tt = ir.findOne(id);
                if (tt == null) {
                        return false;
                }

                ir.delete(tt);
                return true;
        }
}
