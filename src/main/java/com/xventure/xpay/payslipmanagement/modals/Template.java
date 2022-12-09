package com.xventure.xpay.payslipmanagement.modals;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("Templates")
public class Template {
    private String id;
    private String name;
    private String fileName;
    private String code;
}
