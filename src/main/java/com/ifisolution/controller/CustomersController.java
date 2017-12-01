/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifisolution.controller;

import com.ifisolution.entity.Customers;
import com.ifisolution.repository.AccountsRepository;
import com.ifisolution.repository.CustomerRepository;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author johnny
 */
@CrossOrigin
@RestController
@RequestMapping("api")
public class CustomersController {

        @Autowired
        private CustomerRepository customerRepository;
        @Autowired
        private AccountsRepository accountsRepository;

        JSONObject result = new JSONObject();

        @GetMapping("customers")
        public List<Customers> getAll() {
                return customerRepository.findAll();
        }

        @GetMapping("customers/{id}")
        public Customers getById(@PathVariable("id") Integer Id) {
                return customerRepository.findOne(Id);
        }

        @GetMapping("customers/findname")
        public List<Customers> getByName(@Param("name") String name) {
                return customerRepository.findByNameContaining(name);
        }

        @GetMapping("customers/findemail")
        public List<Customers> getByEmail(@Param("email") String email) {
                return customerRepository.findByEmailContaining(email);
        }

        @GetMapping("customers/findemailexists")
        public boolean getByEmailExists(@Param("email") String email) {
                if (customerRepository.findByEmail(email).isEmpty()) {
                        return false;
                }
                return true;
        }

        @PostMapping(value = "customers", produces = "text/plain;charset=UTF-8")
        public String create(@RequestBody Customers customers) {
                try {
                        if (this.checkError(customers)) {
                                customerRepository.save(customers);
                                result.put("code", "200");
                                result.put("message", "success");
                        }
                        return result.toString();
                } catch (Exception e) {
                        result.put("code", e);
                        result.put("message", "cannot add new ");
                        return result.toString();
                }
        }

        @PutMapping(value = "customers", produces = "text/plain;charset=UTF-8")
        public String update(@RequestBody Customers customers) {
                try {
                        if (customerRepository.findOne(customers.getCustomerId()) == null) {
                                result.put("code", "004");
                                result.put("message", "Not found ID");
                        } else {
                                customerRepository.save(customers);
                                result.put("code", "200");
                                result.put("message", "success");
                        }
                        return result.toString();
                } catch (Exception e) {
                        result.put("code", "000");
                        result.put("message", "cannot add new ");
                        return result.toString();
                }
        }

        @DeleteMapping("customers/{id}")
        public String delete(@PathVariable(value = "id") Integer id) {
                Customers customers = customerRepository.findOne(id);
                if (customers == null) {
                        result.put("code", "004");
                        result.put("message", "Not found ID");
                } else if (!accountsRepository.findByCustomersCustomerId(id).isEmpty()) {
                        result.put("code", "005");
                        result.put("message", "Cannot delete");
                } else {
                        customerRepository.delete(customers);
                        result.put("code", "200");
                        result.put("message", "success");
                }
                return result.toString();
        }

        private boolean checkError(Customers customers) {
                if (customers.getName()== null) {
                        result.put("code", "001");
                        result.put("message", "Name not exists");
                        return false;
                } else if (customers.getEmail() == null) {
                        result.put("code", "002");
                        result.put("message", "Email not exists");
                        return false;
                } else if (customerRepository.findByEmail(customers.getEmail()).isEmpty() == false) {
                        result.put("code", "003");
                        result.put("message", "Email exists");
                        return false;
                }
                return true;
        }
}
