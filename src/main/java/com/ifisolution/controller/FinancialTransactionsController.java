/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifisolution.controller;

import com.ifisolution.entity.FinancialTransactions;
import com.ifisolution.repository.AccountsRepository;
import com.ifisolution.repository.FinancialTransactionsRepository;
import com.ifisolution.repository.InvoicesRepository;
import com.ifisolution.repository.TransactionTypesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author johnny
 */
@RestController
@RequestMapping("api")
public class FinancialTransactionsController {

        @Autowired
        private FinancialTransactionsRepository financialTransactionsRepository;
        @Autowired
        private InvoicesRepository ir;
        @Autowired
        private TransactionTypesRepository ttr;

        @Autowired
        private AccountsRepository ar;

        @GetMapping("financial_transactions")
        public List<FinancialTransactions> getAll() {
                return financialTransactionsRepository.findAll();
        }

        @GetMapping("financial_transactions/{id}")
        public FinancialTransactions getById(@PathVariable("id") Integer id) {
                return financialTransactionsRepository.findOne(id);
        }

        @PostMapping("financial_transactions")
        public String create(@RequestBody FinancialTransactions ft) {
                if (ir.findOne(ft.getInvoices().getInvoiceNumber()) == null) {
                        return "Not found invoiceId";
                }

                if (ttr.findOne(ft.getTransactionTypes().getTransactionTypeCode()) == null) {
                        return "Not found transId";
                }

                if (ar.findOne(ft.getAccounts().getAccountId()) == null) {
                        return "Not found accId";
                }
                ft.setTransactionTypes(ft.getTransactionTypes());
                ft.setInvoices(ft.getInvoices());
                ft.setAccounts(ft.getAccounts());
                financialTransactionsRepository.save(ft);
                return "Success";
        }

        @PutMapping("financial_transactions/{id}")
        public String update(@RequestBody FinancialTransactions ft) {
                if (financialTransactionsRepository.findOne(ft.getTransactionId()) == null) {
                        return "Not found TransactionId";
                }
                if (ir.findOne(ft.getInvoices().getInvoiceNumber()) == null) {
                        return "Not found invoiceId";
                }

                if (ttr.findOne(ft.getTransactionTypes().getTransactionTypeCode()) == null) {
                        return "Not found transId";
                }

                if (ar.findOne(ft.getAccounts().getAccountId()) == null) {
                        return "Not found accId";
                }
                ft.setTransactionTypes(ft.getTransactionTypes());
                ft.setInvoices(ft.getInvoices());
                ft.setAccounts(ft.getAccounts());
                financialTransactionsRepository.save(ft);
                return "Success";
        }

        @DeleteMapping("financial_transactions/{id}")
        public String delete(@PathVariable(value = "id") Integer id) {
                FinancialTransactions accounts = financialTransactionsRepository.findOne(id);
                if (accounts == null) {
                        return "Not found";
                }

                financialTransactionsRepository.delete(accounts);
                return "Success";
        }
}
