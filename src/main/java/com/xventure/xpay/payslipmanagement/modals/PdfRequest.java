package com.xventure.xpay.payslipmanagement.modals;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
public class PdfRequest {
    @Id
    private String id;
    private String templateId;
    private List<TemplateParameter> templateParameters;

}
