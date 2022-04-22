package com.rizky.challenge4.backend.service;

import com.rizky.challenge4.backend.model.dto.InvoiceDTO;
import com.rizky.challenge4.backend.model.entity.HistoryTransaction;
import com.rizky.challenge4.backend.model.mapper.RequestCreateInvoiceDto;
import com.rizky.challenge4.backend.repository.HistoryTransactionRepository;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Slf4j
@Service
public class InvoiceTicketingService {

    @Autowired
    RequestCreateInvoiceDto invoiceTicket;

    @Autowired
    HistoryTransactionRepository repository;

    public JasperPrint generateInvoice() throws IOException, JRException {

        InputStream fileReport = new ClassPathResource("templates/invoice.jasper").getInputStream();
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fileReport);
//        parameter fill report
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "RizkyAbdulJabar");


        JRBeanCollectionDataSource beanDataSource = new JRBeanCollectionDataSource((Collection<?>) invoiceTicket);

        return JasperFillManager.fillReport(jasperReport, parameters, beanDataSource);
    }

    public HistoryTransaction saveTransaction(InvoiceDTO invoice){

        HistoryTransaction transaction= new HistoryTransaction();


        return repository.save(transaction);
    }
}
