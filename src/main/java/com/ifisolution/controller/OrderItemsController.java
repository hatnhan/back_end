/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifisolution.controller;

import com.ifisolution.entity.OrderItems;
import com.ifisolution.repository.OrderItemsRepository;
import com.ifisolution.repository.OrdersRepository;
import com.ifisolution.repository.ProductsRepository;
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
@RequestMapping("api")
public class OrderItemsController {

        @Autowired
        private ProductsRepository pr;
        @Autowired
        private OrdersRepository or;
        @Autowired
        private OrderItemsRepository oir;

        @GetMapping("order_items")
        public List<OrderItems> getAll() {
                return oir.findAll();
        }

        @GetMapping("order_items/{id}")
        public OrderItems getById(@PathVariable("id") Integer id) {
                return oir.findOne(id);
        }

        @PostMapping("order_items")
        public String create(@RequestBody OrderItems orderItems) {
                
                if (or.findOne(orderItems.getOrders().getOrderId()) == null) {
                        return "Not found Order ID";
                }
                if (pr.findOne(orderItems.getProducts().getProductId()) == null) {
                        return "Not found Product ID";
                }
                oir.save(orderItems);
                return "Success";
        }

        @PutMapping("order_items")
        public String update(@RequestBody OrderItems orderItems) {
                if (oir.findOne(orderItems.getOrderItemId()) == null) {
                        return "Not found Order Items ID";
                }
                if (or.findOne(orderItems.getOrders().getOrderId()) == null) {
                        return "Not found Order ID";
                }
                if (pr.findOne(orderItems.getProducts().getProductId()) == null) {
                        return "Not found Product ID";
                }
                oir.save(orderItems);
                return "Success";
        }

        @DeleteMapping("order_items/{id}")
        public String delete(@PathVariable(value = "id") Integer id) {
                OrderItems accounts = oir.findOne(id);
                if (accounts == null) {
                        return "Not found";
                }

                oir.delete(accounts);
                return "Success";
        }
}
