package com.xventure.xpay.payslipmanagement.controller;

import com.xventure.xpay.payslipmanagement.modals.PdfRequest;
import com.xventure.xpay.payslipmanagement.modals.Template;
import com.xventure.xpay.payslipmanagement.service.PdfGenerationService;
import com.xventure.xpay.payslipmanagement.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/pdf")

public class PdfController {
    @Autowired
    TemplateService templateService;

    @Autowired
    PdfGenerationService templateGeneratorService;

    @PostMapping
    public void downloadPDF(@RequestBody PdfRequest pdfRequest, HttpServletResponse response) throws Exception {
        Optional<Template> template = templateService.findById(pdfRequest.getTemplateId());
        if (template.isPresent()) {
            Template existingTemplate = template.get();
            String fileName = existingTemplate.getFileName();
            Map<String, Object> params = new HashMap<>();
            pdfRequest.getTemplateParameters().stream().forEach(param -> params.put(param.getParamKey(), param.getValue()));
            templateGeneratorService.downloadPDF(fileName, params, response);
        }
    }
    @GetMapping
    public ResponseEntity<List<PdfRequest>> getAllPayslips() {
        return ResponseEntity.ok(templateGeneratorService.getAllPayslips());
    }

    //print payslip by id
    @GetMapping("/payslip")
    public Optional<PdfRequest> findByPayslipId(@RequestParam("id") String id, HttpServletResponse response) throws Exception {
        Optional<PdfRequest> pdfRequest = Optional.of(templateGeneratorService.findById(id).get());
        Optional<Template> template=templateService.findById(pdfRequest.get().getTemplateId());;
        if (template.isPresent()) {
            Template existingTemplate = template.get();
            String fileName = existingTemplate.getFileName();
            Map<String, Object> params = new HashMap<>();
            Optional<PdfRequest> payslip=templateGeneratorService.findById(id);
            payslip.get().getTemplateParameters().stream().forEach(param -> params.put(param.getParamKey(), param.getValue()));
            templateGeneratorService.downloadPDF(fileName, params, response);
        }
        return null;
    }

    @GetMapping("/payslips")
    public List<PdfRequest> getAllPayslip(@RequestParam int pageNo, @RequestParam int pageSize) {
        return templateGeneratorService.findPayslipsWithPagination(pageNo,pageSize);

    }

}
