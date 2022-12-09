package com.xventure.xpay.auth.models;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordResponse implements Serializable {
    private Boolean requestValid;
    private String message;
    private String oneTimePasswordCode;
}
