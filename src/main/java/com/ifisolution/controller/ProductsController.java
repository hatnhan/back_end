/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifisolution.controller;

import com.ifisolution.entity.Products;
import com.ifisolution.repository.ProductsRepository;
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
public class ProductsController {

        @Autowired
        private ProductsRepository productsRepository;

        @GetMapping("products")
        public List<Products> getAll() {
                return productsRepository.findAll();
        }

        @GetMapping("products/{id}")
        public Products getById(@PathVariable("id") Integer id) {
                return productsRepository.findOne(id);
        }

       @PostMapping(value = "products", produces = "text/plain;charset=UTF-8")
        public String create(@RequestBody Products products) {
                if(products.getProductName()== null || products.getImage()== null || products.getAmount()== null || products.getBrandName()== null)
                        return "Can not add";
                productsRepository.save(products);
                return "Success";
        }

        @PutMapping(value = "products", produces = "text/plain;charset=UTF-8")
        public String update(@RequestBody Products products) {
                if(products.getProductId() == null || products.getProductName()== null || products.getImage()== null || products.getAmount()== null || products.getBrandName()== null)
                        return "Can not add";
                if (getById(products.getProductId()) == null) {
                        return "Not Found ID";
                }
                productsRepository.save(products);
                return "Success";
        }
        
        @DeleteMapping("products/{id}")
        public String delete(@PathVariable(value = "id") Integer id) {
                Products p = productsRepository.findOne(id);
                if (p == null) {
                        return "Not found";
                }

                productsRepository.delete(p);
                return "Success";
        }
}
