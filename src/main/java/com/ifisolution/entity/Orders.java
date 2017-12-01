/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifisolution.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author johnny
 */
@Entity
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o")})
public class Orders implements Serializable {

        private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "order_id")
        private Integer orderId;
        @Column(name = "date_order_placed")
        @Temporal(TemporalType.TIMESTAMP)
        private Date dateOrderPlaced;
        @OneToMany(mappedBy = "orders")
        private Collection<Invoices> invoicesCollection;
        @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
        @ManyToOne
        private Customers customers;
        @OneToMany(mappedBy = "orders")
        private Collection<OrderItems> orderItemsCollection;

        public Orders() {
        }

        public Orders(Integer orderId) {
                this.orderId = orderId;
        }

        public Integer getOrderId() {
                return orderId;
        }

        public void setOrderId(Integer orderId) {
                this.orderId = orderId;
        }

        public Date getDateOrderPlaced() {
                return dateOrderPlaced;
        }

        public void setDateOrderPlaced(Date dateOrderPlaced) {
                this.dateOrderPlaced = dateOrderPlaced;
        }

        public Collection<Invoices> getInvoicesCollection() {
                return invoicesCollection;
        }

        public void setInvoicesCollection(Collection<Invoices> invoicesCollection) {
                this.invoicesCollection = invoicesCollection;
        }

        public Customers getCustomers() {
                return customers;
        }

        public void setCustomers(Customers customers) {
                this.customers = customers;
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
                hash += (orderId != null ? orderId.hashCode() : 0);
                return hash;
        }

        @Override
        public boolean equals(Object object) {
                // TODO: Warning - this method won't work in the case the id fields are not set
                if (!(object instanceof Orders)) {
                        return false;
                }
                Orders other = (Orders) object;
                if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
                        return false;
                }
                return true;
        }

        @Override
        public String toString() {
                return "com.ifisolution.entity.Orders[ orderId=" + orderId + " ]";
        }
        
}
