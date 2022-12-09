package com.xventure.xpay.payslipmanagement.service;

import com.xventure.xpay.payslipmanagement.modals.TemplateParameter;
import com.xventure.xpay.payslipmanagement.repository.TemplatePrameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemplateParameterService {
    @Autowired
    TemplatePrameterRepository templatePrameterRepository;
    public TemplateParameter addTemplateParameter(TemplateParameter templateParameter) {
        return templatePrameterRepository.save(templateParameter);
    }

    public List<TemplateParameter> findByTemplateId(String templateId){
        return templatePrameterRepository.findByTemplateId(templateId);
    }
    public void delete(String id) {
        templatePrameterRepository.deleteById(id);
    }

    public TemplateParameter update(TemplateParameter templateParameter) {
        return templatePrameterRepository.save(templateParameter);
    }

    public Optional<TemplateParameter> findByParamId(String paramId) {
        return templatePrameterRepository.findByParamId(paramId);
    }
}
