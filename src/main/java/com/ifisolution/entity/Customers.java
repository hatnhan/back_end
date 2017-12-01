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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author johnny
 */
@Entity
@Table(name = "customers")
@NamedQueries({
        @NamedQuery(name = "Customers.findAll", query = "SELECT c FROM Customers c")})
public class Customers implements Serializable {

        private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "customer_id")
        private Integer customerId;
        @Basic(optional = false)
        @NotNull
        @Size(min = 1, max = 50)
        @Column(name = "name")
        private String name;
        @Basic(optional = false)
        @NotNull
        @Size(min = 1, max = 100)
        @Column(name = "address")
        private String address;
        // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
        @Basic(optional = false)
        @NotNull
        @Size(min = 1, max = 100)
        @Column(name = "email")
        private String email;
        @Size(max = 15)
        @Column(name = "phone_number")
        private String phoneNumber;
        @OneToMany(mappedBy = "customers")
        @JsonIgnore
        private Collection<Orders> ordersCollection;
        @OneToMany(cascade = CascadeType.ALL, mappedBy = "customers")
        @JsonIgnore
        private Collection<Accounts> accountsCollection;

        public Customers() {
        }

        public Customers(Integer customerId) {
                this.customerId = customerId;
        }

        public Customers(Integer customerId, String name, String address, String email) {
                this.customerId = customerId;
                this.name = name;
                this.address = address;
                this.email = email;
        }

        public Integer getCustomerId() {
                return customerId;
        }

        public void setCustomerId(Integer customerId) {
                this.customerId = customerId;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getAddress() {
                return address;
        }

        public void setAddress(String address) {
                this.address = address;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPhoneNumber() {
                return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
        }

        public Collection<Orders> getOrdersCollection() {
                return ordersCollection;
        }

        public void setOrdersCollection(Collection<Orders> ordersCollection) {
                this.ordersCollection = ordersCollection;
        }

        public Collection<Accounts> getAccountsCollection() {
                return accountsCollection;
        }

        public void setAccountsCollection(Collection<Accounts> accountsCollection) {
                this.accountsCollection = accountsCollection;
        }

        @Override
        public int hashCode() {
                int hash = 0;
                hash += (customerId != null ? customerId.hashCode() : 0);
                return hash;
        }

        @Override
        public boolean equals(Object object) {
                // TODO: Warning - this method won't work in the case the id fields are not set
                if (!(object instanceof Customers)) {
                        return false;
                }
                Customers other = (Customers) object;
                if ((this.customerId == null && other.customerId != null) || (this.customerId != null && !this.customerId.equals(other.customerId))) {
                        return false;
                }
                return true;
        }

        @Override
        public String toString() {
                return "com.ifisolution.entity.Customers[ customerId=" + customerId + " ]";
        }
        
}
