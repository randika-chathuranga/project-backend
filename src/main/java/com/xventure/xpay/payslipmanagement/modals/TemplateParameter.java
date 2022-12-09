package com.xventure.xpay.payslipmanagement.modals;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class TemplateParameter {
    private String templateId;
    private String paramId;
    private String paramKey;
    private String paramType;
    private String defaultValue;
    private String availableValues;
    private String value;
}
