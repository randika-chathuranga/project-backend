package com.xventure.xpay.auth.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OTPValidationRequest {
    private String email;
    private String oneTimePasswordCode;
}
