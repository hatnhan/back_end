/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifisolution.controller;

import com.ifisolution.entity.Category;
import com.ifisolution.repository.CategoryRepository;
import com.ifisolution.repository.ProductsRepository;
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
public class CategoryController {
        
        @Autowired
        private CategoryRepository categoryRepository;
        @Autowired
        private ProductsRepository productsRepository;

        JSONObject result = new JSONObject();

        @GetMapping("category")
        public List<Category> getAll() {
                return categoryRepository.findAll();
        }

        @GetMapping("category/{id}")
        public Category getById(@PathVariable("id") Integer Id) {
                return categoryRepository.findOne(Id);
        }

//        @GetMapping("category/findname")
//        public List<Category> getByName(@Param("name") String name) {
//                return categoryRepository.findByNameContaining(name);
//        }

        @PostMapping(value = "category", produces = "text/plain;charset=UTF-8")
        public String create(@RequestBody Category category) {
                try {
                        if (this.checkError(category)) {
                                categoryRepository.save(category);
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

        @PutMapping(value = "category", produces = "text/plain;charset=UTF-8")
        public String update(@RequestBody Category category) {
                try {
                        if (categoryRepository.findOne(category.getCategoryId()) == null) {
                                result.put("code", "004");
                                result.put("message", "Not found ID");
                        } else {
                                categoryRepository.save(category);
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

        @DeleteMapping("category/{id}")
        public String delete(@PathVariable(value = "id") Integer id) {
                Category category = categoryRepository.findOne(id);
                if (category == null) {
                        result.put("code", "004");
                        result.put("message", "Not found ID");
                } else if (!productsRepository.findByCategoryCategoryId(id).isEmpty()) {
                        result.put("code", "005");
                        result.put("message", "Cannot delete");
                } else {
                        categoryRepository.delete(category);
                        result.put("code", "200");
                        result.put("message", "success");
                }
                return result.toString();
        }

        private boolean checkError(Category category) {
                if (category.getName()== null) {
                        result.put("code", "001");
                        result.put("message", "Name not exists");
                        return false;
                } 
                return true;
        }
}
