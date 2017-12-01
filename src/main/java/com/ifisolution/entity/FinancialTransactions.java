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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author johnny
 */
@Entity
@Table(name = "financial_transactions")
@NamedQueries({
        @NamedQuery(name = "FinancialTransactions.findAll", query = "SELECT f FROM FinancialTransactions f")})
public class FinancialTransactions implements Serializable {

        private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "transaction_id")
        private Integer transactionId;
        @JoinColumn(name = "account_id", referencedColumnName = "account_id")
        @ManyToOne
        private Accounts accounts;
        @JoinColumn(name = "invoice_number", referencedColumnName = "invoice_number")
        @ManyToOne
        private Invoices invoices;
        @JoinColumn(name = "transaction_type_code", referencedColumnName = "transaction_type_code")
        @ManyToOne
        private TransactionTypes transactionTypes;

        public FinancialTransactions() {
        }

        public FinancialTransactions(Integer transactionId) {
                this.transactionId = transactionId;
        }

        public Integer getTransactionId() {
                return transactionId;
        }

        public void setTransactionId(Integer transactionId) {
                this.transactionId = transactionId;
        }

        public Accounts getAccounts() {
                return accounts;
        }

        public void setAccounts(Accounts accounts) {
                this.accounts = accounts;
        }

        public Invoices getInvoices() {
                return invoices;
        }

        public void setInvoices(Invoices invoices) {
                this.invoices = invoices;
        }

        public TransactionTypes getTransactionTypes() {
                return transactionTypes;
        }

        public void setTransactionTypes(TransactionTypes transactionTypes) {
                this.transactionTypes = transactionTypes;
        }

        @Override
        public int hashCode() {
                int hash = 0;
                hash += (transactionId != null ? transactionId.hashCode() : 0);
                return hash;
        }

        @Override
        public boolean equals(Object object) {
                // TODO: Warning - this method won't work in the case the id fields are not set
                if (!(object instanceof FinancialTransactions)) {
                        return false;
                }
                FinancialTransactions other = (FinancialTransactions) object;
                if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId))) {
                        return false;
                }
                return true;
        }

        @Override
        public String toString() {
                return "com.ifisolution.entity.FinancialTransactions[ transactionId=" + transactionId + " ]";
        }
        
}
