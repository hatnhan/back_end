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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author johnny
 */
@Entity
@Table(name = "invoices")
@NamedQueries({
        @NamedQuery(name = "Invoices.findAll", query = "SELECT i FROM Invoices i")})
public class Invoices implements Serializable {

        private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "invoice_number")
        private Integer invoiceNumber;
        @Column(name = "invoice_date")
        @Temporal(TemporalType.TIMESTAMP)
        private Date invoiceDate;
        @Size(max = 255)
        @Column(name = "invoice_details")
        private String invoiceDetails;
        @OneToMany(mappedBy = "invoices")
        private Collection<FinancialTransactions> financialTransactionsCollection;
        @JoinColumn(name = "order_id", referencedColumnName = "order_id")
        @ManyToOne
        private Orders orders;
        @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoices")
        private Collection<InvoiceLineItems> invoiceLineItemsCollection;

        public Invoices() {
        }

        public Invoices(Integer invoiceNumber) {
                this.invoiceNumber = invoiceNumber;
        }

        public Integer getInvoiceNumber() {
                return invoiceNumber;
        }

        public void setInvoiceNumber(Integer invoiceNumber) {
                this.invoiceNumber = invoiceNumber;
        }

        public Date getInvoiceDate() {
                return invoiceDate;
        }

        public void setInvoiceDate(Date invoiceDate) {
                this.invoiceDate = invoiceDate;
        }

        public String getInvoiceDetails() {
                return invoiceDetails;
        }

        public void setInvoiceDetails(String invoiceDetails) {
                this.invoiceDetails = invoiceDetails;
        }

        public Collection<FinancialTransactions> getFinancialTransactionsCollection() {
                return financialTransactionsCollection;
        }

        public void setFinancialTransactionsCollection(Collection<FinancialTransactions> financialTransactionsCollection) {
                this.financialTransactionsCollection = financialTransactionsCollection;
        }

        public Orders getOrders() {
                return orders;
        }

        public void setOrders(Orders orders) {
                this.orders = orders;
        }

        public Collection<InvoiceLineItems> getInvoiceLineItemsCollection() {
                return invoiceLineItemsCollection;
        }

        public void setInvoiceLineItemsCollection(Collection<InvoiceLineItems> invoiceLineItemsCollection) {
                this.invoiceLineItemsCollection = invoiceLineItemsCollection;
        }

        @Override
        public int hashCode() {
                int hash = 0;
                hash += (invoiceNumber != null ? invoiceNumber.hashCode() : 0);
                return hash;
        }

        @Override
        public boolean equals(Object object) {
                // TODO: Warning - this method won't work in the case the id fields are not set
                if (!(object instanceof Invoices)) {
                        return false;
                }
                Invoices other = (Invoices) object;
                if ((this.invoiceNumber == null && other.invoiceNumber != null) || (this.invoiceNumber != null && !this.invoiceNumber.equals(other.invoiceNumber))) {
                        return false;
                }
                return true;
        }

        @Override
        public String toString() {
                return "com.ifisolution.entity.Invoices[ invoiceNumber=" + invoiceNumber + " ]";
        }
        
}
