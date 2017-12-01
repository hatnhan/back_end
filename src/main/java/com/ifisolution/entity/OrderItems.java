/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifisolution.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author johnny
 */
@Entity
@Table(name = "order_items")
@NamedQueries({
        @NamedQuery(name = "OrderItems.findAll", query = "SELECT o FROM OrderItems o")})
public class OrderItems implements Serializable {

        private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "order_item_id")
        private Integer orderItemId;
        @Basic(optional = false)
        @NotNull
        @Column(name = "product_quantity")
        private int productQuantity;
        @OneToOne(cascade = CascadeType.ALL, mappedBy = "orderItems")
        private InvoiceLineItems invoiceLineItems;
        @JoinColumn(name = "order_id", referencedColumnName = "order_id")
        @ManyToOne
        private Orders orders;
        @JoinColumn(name = "product_id", referencedColumnName = "product_id")
        @ManyToOne
        private Products products;

        public OrderItems() {
        }

        public OrderItems(Integer orderItemId) {
                this.orderItemId = orderItemId;
        }

        public OrderItems(Integer orderItemId, int productQuantity) {
                this.orderItemId = orderItemId;
                this.productQuantity = productQuantity;
        }

        public Integer getOrderItemId() {
                return orderItemId;
        }

        public void setOrderItemId(Integer orderItemId) {
                this.orderItemId = orderItemId;
        }

        public int getProductQuantity() {
                return productQuantity;
        }

        public void setProductQuantity(int productQuantity) {
                this.productQuantity = productQuantity;
        }

        public InvoiceLineItems getInvoiceLineItems() {
                return invoiceLineItems;
        }

        public void setInvoiceLineItems(InvoiceLineItems invoiceLineItems) {
                this.invoiceLineItems = invoiceLineItems;
        }

        public Orders getOrders() {
                return orders;
        }

        public void setOrders(Orders orders) {
                this.orders = orders;
        }

        public Products getProducts() {
                return products;
        }

        public void setProducts(Products products) {
                this.products = products;
        }

        @Override
        public int hashCode() {
                int hash = 0;
                hash += (orderItemId != null ? orderItemId.hashCode() : 0);
                return hash;
        }

        @Override
        public boolean equals(Object object) {
                // TODO: Warning - this method won't work in the case the id fields are not set
                if (!(object instanceof OrderItems)) {
                        return false;
                }
                OrderItems other = (OrderItems) object;
                if ((this.orderItemId == null && other.orderItemId != null) || (this.orderItemId != null && !this.orderItemId.equals(other.orderItemId))) {
                        return false;
                }
                return true;
        }

        @Override
        public String toString() {
                return "com.ifisolution.entity.OrderItems[ orderItemId=" + orderItemId + " ]";
        }
        
}
