package com.xventure.xpay.payslipmanagement.service;

import com.xventure.xpay.payslipmanagement.modals.Template;
import com.xventure.xpay.payslipmanagement.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class TemplateService {
    @Autowired
    TemplateRepository templateRepository;

    public Template addTemplate(Template template) {
        return templateRepository.save(template);
    }
    public List<Template> getAllTemplates(){
        return templateRepository.findAll();
    }
    public Optional<Template> findById(String templateId){
        return templateRepository.findById(templateId);
    }
    public void delete(String id) {
        templateRepository.deleteById(id);
    }

    public Template update(Template template) {
        return templateRepository.save(template);
    }
}
