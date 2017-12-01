/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifisolution.controller;

import com.ifisolution.entity.Orders;
import com.ifisolution.repository.CustomerRepository;
import com.ifisolution.repository.OrdersRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping("/api")
public class OrdersController {
        
        @Autowired
        private OrdersRepository or;
        @Autowired
        private CustomerRepository customerRepository;

        @GetMapping("orders")
        public List<Orders> getAll() {
                return or.findAll();
        }

        @GetMapping("orders/{id}")
        public Orders getById(@PathVariable("id") Integer id) {
                return or.findOne(id);
        }

        @PostMapping("orders")
        public String create(@RequestBody Orders orders) {
                if (customerRepository.findOne(orders.getCustomers().getCustomerId()) == null) {
                        return "Not found customerId";
                }
                or.save(orders);
                return "Success";
        }

        @PutMapping("orders/{id}")
        public String update(Orders orders) {
                if (or.findOne(orders.getOrderId()) == null) {
                        return "Not found id";
                }
                if (customerRepository.findOne(orders.getCustomers().getCustomerId()) == null) {
                        return "Not found customerId";
                }
                orders.setCustomers(orders.getCustomers());
                or.save(orders);
                return "Success";
        }

        @DeleteMapping("orders/{id}")
        public String delete(@PathVariable(value = "id") Integer id) {
                Orders accounts = or.findOne(id);
                if (accounts == null) {
                        return "Not found";
                }

                or.delete(accounts);
                return "Success";
        }
}
