/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifisolution.controller;

import com.ifisolution.entity.InvoiceLineItems;
import com.ifisolution.repository.InvoiceLineItemsRepository;
import com.ifisolution.repository.InvoicesRepository;
import com.ifisolution.repository.OrderItemsRepository;
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
public class InvoiceLineItemsController {

        @Autowired
        private InvoiceLineItemsRepository ilir;

        @Autowired
        private InvoicesRepository invoicesRepository;

        @Autowired
        private OrderItemsRepository itemsRepository;

        @GetMapping("invoice_line_items")
        public List<InvoiceLineItems> getAll() {
                return ilir.findAll();
        }

        @GetMapping("invoice_line_items/{id}")
        public InvoiceLineItems getById(@PathVariable("id") Integer id) {
                return ilir.findOne(id);
        }

        @PostMapping("invoice_line_items")
        public String create(@RequestBody InvoiceLineItems invoiceLineItems) {
                if (itemsRepository.findOne(invoiceLineItems.getOrderItemId()) == null) {
                        return "Not found order items id";
                }
                if (invoicesRepository.findOne(invoiceLineItems.getInvoices().getInvoiceNumber()) == null) {
                        return "Not found Invoice Number";
                }
                ilir.save(invoiceLineItems);
                return "Success";
        }

        @PutMapping("invoice_line_items")
        public String update(@RequestBody InvoiceLineItems invoiceLineItems) {
                if (ilir.findOne(invoiceLineItems.getOrderItemId()) == null) {
                        return "Not found order items id";
                }
                if (invoicesRepository.findOne(invoiceLineItems.getInvoices().getInvoiceNumber()) == null) {
                        return "Not found Invoice Number";
                }
                ilir.save(invoiceLineItems);
                return "Success";
        }

        @DeleteMapping("invoice_line_items/{id}")
        public String delete(@PathVariable(value = "id") Integer id) {
                InvoiceLineItems accounts = ilir.findOne(id);
                if (accounts == null) {
                        return "Not found";
                }

                ilir.delete(accounts);
                return "Success";
        }
}
