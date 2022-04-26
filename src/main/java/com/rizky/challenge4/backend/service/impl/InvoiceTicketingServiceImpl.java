//package com.rizky.challenge4.backend.service.impl;
//
//import com.rizky.challenge4.backend.model.dto.InvoiceDTO;
//import com.rizky.challenge4.backend.model.entity.HistoryTransaction;
//import com.rizky.challenge4.backend.model.mapper.RequestCreateInvoiceDto;
//import com.rizky.challenge4.backend.repository.HistoryTransactionRepository;
//import com.rizky.challenge4.backend.service.InvoiceTicketingService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//public class InvoiceTicketingServiceImpl implements InvoiceTicketingService {
//
//    @Autowired
//    RequestCreateInvoiceDto invoiceTicket;
//    @Autowired
//    HistoryTransactionRepository repository;
//
//    public HistoryTransaction saveTransaction(InvoiceDTO invoice){
//
//        HistoryTransaction transaction= new HistoryTransaction();
//
//        return repository.save(transaction);
//    }
//}
