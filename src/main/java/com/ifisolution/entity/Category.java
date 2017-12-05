/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifisolution.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author johnny
 */
@Entity
@Table(name = "category")
@NamedQueries({
        @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")})
public class Category implements Serializable {

        private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "category_id")
        private Integer categoryId;
        @Size(max = 200)
        @Column(name = "name")
        private String name;
        @Column(name = "status")
        private Short status;
        @OneToMany(mappedBy = "category")
        @JsonIgnore
        private Collection<Products> productsCollection;

        public Category() {
        }

        public Category(Integer categoryId) {
                this.categoryId = categoryId;
        }

        public Integer getCategoryId() {
                return categoryId;
        }

        public void setCategoryId(Integer categoryId) {
                this.categoryId = categoryId;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Short getStatus() {
                return status;
        }

        public void setStatus(Short status) {
                this.status = status;
        }

        public Collection<Products> getProductsCollection() {
                return productsCollection;
        }

        public void setProductsCollection(Collection<Products> productsCollection) {
                this.productsCollection = productsCollection;
        }

        @Override
        public int hashCode() {
                int hash = 0;
                hash += (categoryId != null ? categoryId.hashCode() : 0);
                return hash;
        }

        @Override
        public boolean equals(Object object) {
                // TODO: Warning - this method won't work in the case the id fields are not set
                if (!(object instanceof Category)) {
                        return false;
                }
                Category other = (Category) object;
                if ((this.categoryId == null && other.categoryId != null) || (this.categoryId != null && !this.categoryId.equals(other.categoryId))) {
                        return false;
                }
                return true;
        }

        @Override
        public String toString() {
                return "com.ifisolution.entity.Category[ categoryId=" + categoryId + " ]";
        }
        
}
