/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifisolution.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author johnny
 */
@Entity
@Table(name = "invoice_line_items")
@NamedQueries({
        @NamedQuery(name = "InvoiceLineItems.findAll", query = "SELECT i FROM InvoiceLineItems i")})
public class InvoiceLineItems implements Serializable {

        private static final long serialVersionUID = 1L;
        @Id
        @Basic(optional = false)
        @NotNull
        @Column(name = "order_item_id")
        private Integer orderItemId;
        @Basic(optional = false)
        @NotNull
        @Size(min = 1, max = 255)
        @Column(name = "product_title")
        private String productTitle;
        @JoinColumn(name = "invoice_number", referencedColumnName = "invoice_number")
        @ManyToOne(optional = false)
        private Invoices invoices;
        @JoinColumn(name = "order_item_id", referencedColumnName = "order_item_id", insertable = false, updatable = false)
        @OneToOne(optional = false)
        private OrderItems orderItems;

        public InvoiceLineItems() {
        }

        public InvoiceLineItems(Integer orderItemId) {
                this.orderItemId = orderItemId;
        }

        public InvoiceLineItems(Integer orderItemId, String productTitle) {
                this.orderItemId = orderItemId;
                this.productTitle = productTitle;
        }

        public Integer getOrderItemId() {
                return orderItemId;
        }

        public void setOrderItemId(Integer orderItemId) {
                this.orderItemId = orderItemId;
        }

        public String getProductTitle() {
                return productTitle;
        }

        public void setProductTitle(String productTitle) {
                this.productTitle = productTitle;
        }

        public Invoices getInvoices() {
                return invoices;
        }

        public void setInvoices(Invoices invoices) {
                this.invoices = invoices;
        }

        public OrderItems getOrderItems() {
                return orderItems;
        }

        public void setOrderItems(OrderItems orderItems) {
                this.orderItems = orderItems;
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
                if (!(object instanceof InvoiceLineItems)) {
                        return false;
                }
                InvoiceLineItems other = (InvoiceLineItems) object;
                if ((this.orderItemId == null && other.orderItemId != null) || (this.orderItemId != null && !this.orderItemId.equals(other.orderItemId))) {
                        return false;
                }
                return true;
        }

        @Override
        public String toString() {
                return "com.ifisolution.entity.InvoiceLineItems[ orderItemId=" + orderItemId + " ]";
        }
        
}
