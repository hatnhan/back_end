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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "accounts")
@NamedQueries({
        @NamedQuery(name = "Accounts.findAll", query = "SELECT a FROM Accounts a")})
public class Accounts implements Serializable {

        private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "account_id")
        private Integer accountId;
        @Size(max = 45)
        @Column(name = "username")
        private String username;
        @Size(max = 45)
        @Column(name = "password")
        private String password;
        @OneToMany(mappedBy = "accounts")
        @JsonIgnore
        private Collection<FinancialTransactions> financialTransactionsCollection;
        @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
        @ManyToOne(optional = false)
        private Customers customers;

        public Accounts() {
        }

        public Accounts(Integer accountId) {
                this.accountId = accountId;
        }

        public Integer getAccountId() {
                return accountId;
        }

        public void setAccountId(Integer accountId) {
                this.accountId = accountId;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public Collection<FinancialTransactions> getFinancialTransactionsCollection() {
                return financialTransactionsCollection;
        }

        public void setFinancialTransactionsCollection(Collection<FinancialTransactions> financialTransactionsCollection) {
                this.financialTransactionsCollection = financialTransactionsCollection;
        }

        public Customers getCustomers() {
                return customers;
        }

        public void setCustomers(Customers customers) {
                this.customers = customers;
        }

        @Override
        public int hashCode() {
                int hash = 0;
                hash += (accountId != null ? accountId.hashCode() : 0);
                return hash;
        }

        @Override
        public boolean equals(Object object) {
                // TODO: Warning - this method won't work in the case the id fields are not set
                if (!(object instanceof Accounts)) {
                        return false;
                }
                Accounts other = (Accounts) object;
                if ((this.accountId == null && other.accountId != null) || (this.accountId != null && !this.accountId.equals(other.accountId))) {
                        return false;
                }
                return true;
        }

        @Override
        public String toString() {
                return "com.ifisolution.entity.Accounts[ accountId=" + accountId + " ]";
        }
        
}
