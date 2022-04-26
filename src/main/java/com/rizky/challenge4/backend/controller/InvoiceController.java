package com.rizky.challenge4.backend.controller;

import com.rizky.challenge4.backend.model.entity.Films;
import com.rizky.challenge4.backend.model.entity.Schedules;
import com.rizky.challenge4.backend.model.entity.Users;
import com.rizky.challenge4.backend.service.FilmService;
import com.rizky.challenge4.backend.service.ScheduleService;
import com.rizky.challenge4.backend.service.UserService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/invoice")
public class InvoiceController {

    private final UserService userService;
    private final FilmService filmService;
    private final ScheduleService scheduleService;

    @Autowired
    public InvoiceController(UserService userService, FilmService filmService, ScheduleService scheduleService) {
        this.userService = userService;
        this.filmService = filmService;
        this.scheduleService = scheduleService;
    }


    @GetMapping("/generate-pdf")
    public JasperPrint generateInvoice(HttpServletResponse response) throws IOException, JRException {

        InputStream fileReport = new ClassPathResource("templates/invoice.jasper").getInputStream();
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fileReport);

        List<Map<String, String>> dataList = new ArrayList<>();
        Map<String, String> data = new HashMap<>();

        Users user = userService.findByUsername("rizky");
        data.put("username", user.getUsername());
        Films films = filmService.getFilmByCodeName("MPN");
        data.put("titleFilm", films.getTitle());
        Schedules schedules = scheduleService.findScheduleById(1L);
        data.put("scheduleTime", schedules.getStartTime().toString());
        data.put("scheduleDate", schedules.getShowDate().toString());
        data.put("getPrice", schedules.getPrice().toString());
//        Seats seats =
        dataList.add(data);

//        parameter fill report
        JRBeanCollectionDataSource beanDataSource = new JRBeanCollectionDataSource(dataList);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Jabar");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanDataSource);
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

        return jasperPrint;
    }


}
