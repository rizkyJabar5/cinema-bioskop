package com.rizky.challenge4.backend.controller;

import com.rizky.challenge4.backend.service.InvoiceTicketingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/invoice")
public class InvoiceController {

    @Autowired
    InvoiceTicketingService invoiceTicketingService;
//
//    @PostMapping("/pdf")
//    public ResponseEntity<byte[]> generatedInvoiceToPdf(@RequestParam String title) {
////        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource();
//    }
}
