/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifisolution.repository;

import com.ifisolution.entity.Products;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author johnny
 */
public interface ProductsRepository extends JpaRepository<Products, Integer>{
        List<Products> findByCategoryCategoryId(Integer id);
}
