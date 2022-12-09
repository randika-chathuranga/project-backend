package com.xventure.xpay.payslipmanagement.service;

import com.xventure.xpay.payslipmanagement.modals.Payslip;
import com.xventure.xpay.payslipmanagement.modals.Template;
import com.xventure.xpay.payslipmanagement.repository.PayslipRepository;
import com.xventure.xpay.payslipmanagement.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@Service
public class PayslipService {
    @Autowired
    PayslipRepository payslipRepository;
    @Autowired
    TemplateRepository templateRepository;

    @Value("${payslip.template_code}")
    private String payslipTemplateCode;

    public Payslip addPayslipDetails(Payslip payslip) {
        return payslipRepository.save(payslip);
    }

    public Page<Payslip> getAllPayslips(String eno,  Pageable pageable) {
        return payslipRepository.findByEnoContainingIgnoreCase(eno,pageable);
    }



    public Optional<Payslip> findByPayslipId(String id) {
        return payslipRepository.findById(id);
    }

    public Optional<Template> findTemplateByCode(String code){
        return templateRepository.findByCode(payslipTemplateCode);
    }
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
}
