/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifisolution.entity;

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
@Table(name = "transaction_types")
@NamedQueries({
        @NamedQuery(name = "TransactionTypes.findAll", query = "SELECT t FROM TransactionTypes t")})
public class TransactionTypes implements Serializable {

        private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "transaction_type_code")
        private Integer transactionTypeCode;
        @Size(max = 255)
        @Column(name = "transaction_type_descreption")
        private String transactionTypeDescreption;
        @OneToMany(mappedBy = "transactionTypes")
        private Collection<FinancialTransactions> financialTransactionsCollection;

        public TransactionTypes() {
        }

        public TransactionTypes(Integer transactionTypeCode) {
                this.transactionTypeCode = transactionTypeCode;
        }

        public Integer getTransactionTypeCode() {
                return transactionTypeCode;
        }

        public void setTransactionTypeCode(Integer transactionTypeCode) {
                this.transactionTypeCode = transactionTypeCode;
        }

        public String getTransactionTypeDescreption() {
                return transactionTypeDescreption;
        }

        public void setTransactionTypeDescreption(String transactionTypeDescreption) {
                this.transactionTypeDescreption = transactionTypeDescreption;
        }

        public Collection<FinancialTransactions> getFinancialTransactionsCollection() {
                return financialTransactionsCollection;
        }

        public void setFinancialTransactionsCollection(Collection<FinancialTransactions> financialTransactionsCollection) {
                this.financialTransactionsCollection = financialTransactionsCollection;
        }

        @Override
        public int hashCode() {
                int hash = 0;
                hash += (transactionTypeCode != null ? transactionTypeCode.hashCode() : 0);
                return hash;
        }

        @Override
        public boolean equals(Object object) {
                // TODO: Warning - this method won't work in the case the id fields are not set
                if (!(object instanceof TransactionTypes)) {
                        return false;
                }
                TransactionTypes other = (TransactionTypes) object;
                if ((this.transactionTypeCode == null && other.transactionTypeCode != null) || (this.transactionTypeCode != null && !this.transactionTypeCode.equals(other.transactionTypeCode))) {
                        return false;
                }
                return true;
        }

        @Override
        public String toString() {
                return "com.ifisolution.entity.TransactionTypes[ transactionTypeCode=" + transactionTypeCode + " ]";
        }
        
}
