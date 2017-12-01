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
import javax.persistence.Lob;
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
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p")})
public class Products implements Serializable {

        private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "product_id")
        private Integer productId;
        @Size(max = 200)
        @Column(name = "product_name")
        private String productName;
        @Size(max = 255)
        @Column(name = "image")
        private String image;
        @Column(name = "amount")
        private Integer amount;
        @Lob
        @Size(max = 65535)
        @Column(name = "description")
        private String description;
        @Size(max = 200)
        @Column(name = "brand_name")
        private String brandName;
        @OneToMany(mappedBy = "products")
        @JsonIgnore
        private Collection<OrderItems> orderItemsCollection;

        public Products() {
        }

        public Products(Integer productId) {
                this.productId = productId;
        }

        public Integer getProductId() {
                return productId;
        }

        public void setProductId(Integer productId) {
                this.productId = productId;
        }

        public String getProductName() {
                return productName;
        }

        public void setProductName(String productName) {
                this.productName = productName;
        }

        public String getImage() {
                return image;
        }

        public void setImage(String image) {
                this.image = image;
        }

        public Integer getAmount() {
                return amount;
        }

        public void setAmount(Integer amount) {
                this.amount = amount;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public String getBrandName() {
                return brandName;
        }

        public void setBrandName(String brandName) {
                this.brandName = brandName;
        }

        public Collection<OrderItems> getOrderItemsCollection() {
                return orderItemsCollection;
        }

        public void setOrderItemsCollection(Collection<OrderItems> orderItemsCollection) {
                this.orderItemsCollection = orderItemsCollection;
        }

        @Override
        public int hashCode() {
                int hash = 0;
                hash += (productId != null ? productId.hashCode() : 0);
                return hash;
        }

        @Override
        public boolean equals(Object object) {
                // TODO: Warning - this method won't work in the case the id fields are not set
                if (!(object instanceof Products)) {
                        return false;
                }
                Products other = (Products) object;
                if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
                        return false;
                }
                return true;
        }

        @Override
        public String toString() {
                return "com.ifisolution.entity.Products[ productId=" + productId + " ]";
        }
        
}
