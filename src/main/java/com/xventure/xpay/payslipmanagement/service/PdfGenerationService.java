package com.xventure.xpay.payslipmanagement.service;

import com.xventure.xpay.payslipmanagement.modals.PdfRequest;
import com.xventure.xpay.payslipmanagement.repository.PdfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PdfGenerationService {
    @Autowired
    private SpringTemplateEngine templateEngine;
    @Autowired
    PdfRepository pdfRepository;

    public void downloadPDF(String template, Map<String, Object> params, HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment;filename=" + "X-pay.pdf");

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(parseTemplate(template, params));
        renderer.layout();
        renderer.createPDF(response.getOutputStream());
    }


    private String parseTemplate(String template, Map<String, Object> params) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();

        if (params != null) {
            params.entrySet().stream().forEach(entry -> context.setVariable(entry.getKey(), entry.getValue()));
        }

        return templateEngine.process(template, context);
    }

    public PdfRequest addPayslip(PdfRequest pdfRequest) {
        return pdfRepository.save(pdfRequest);
    }

    public List<PdfRequest> getAllPayslips() {
        return pdfRepository.findAll();
    }

    public Optional<PdfRequest> findById(String id) {
        return pdfRepository.findById(id);
    }

    public List<PdfRequest> findPayslipsWithPagination(int pageNo,int pageSize){
        Pageable pageable= PageRequest.of(pageNo,pageSize);
        Page<PdfRequest> pageResult=pdfRepository.findAll(pageable);
        return pageResult.toList();
    }

}
