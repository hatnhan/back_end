/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifisolution.controller;

import com.ifisolution.entity.Accounts;
import com.ifisolution.repository.AccountsRepository;
import com.ifisolution.repository.CustomerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AccountsController {

        @Autowired
        private AccountsRepository accountsRepository;
        @Autowired
        private CustomerRepository customerRepository;

        @GetMapping("accounts")
        public List<Accounts> getAll() {
                return accountsRepository.findAll();
        }

        @GetMapping("accounts/{id}")
        public Accounts getById(@PathVariable("id") Integer id) {
                return accountsRepository.findOne(id);
        }

        @PostMapping("accounts/authenticated")
        public boolean getAuthenticated(@RequestBody Accounts accounts) {
                List<Accounts> list = this.getAll();
                for (Accounts a : list) {
                        if (accounts.getUsername() == a.getUsername() && accounts.getPassword() == a.getPassword()) {
                                return true;
                        }
                }
                return false;
        }

        @PostMapping(value = "accounts", produces = "text/plain;charset=UTF-8")
        public String create(@RequestBody Accounts a) {
                if (a.getUsername() == null || a.getPassword() == null || a.getCustomers().getCustomerId() == null) {
                        return "Can not add";
                }
                if (customerRepository.findOne(a.getCustomers().getCustomerId()) == null) {
                        return "Not found customerId";
                }
                accountsRepository.save(a);
                return "Success";
        }

        @PutMapping("accounts")
        public String update(@RequestBody Accounts a) {
                if (a.getAccountId() == null || a.getUsername() == null || a.getPassword() == null || a.getCustomers().getCustomerId() == null) {
                        return "Can not add";
                }
                if (accountsRepository.findOne(a.getAccountId()) == null) {
                        return "Not found Accounts id";
                }
                if (customerRepository.findOne(a.getCustomers().getCustomerId()) == null) {
                        return "Not found customerId";
                }
                accountsRepository.save(a);
                return "Success";
        }

        @DeleteMapping("accounts/{id}")
        public String delete(@PathVariable(value = "id") Integer id) {
                Accounts accounts = accountsRepository.findOne(id);

                if (accounts == null) {
                        return "Not found";
                }

                accountsRepository.delete(accounts);
                return "Success";
        }
}
