package com.xventure.xpay.auth.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ResetPasswordRequest {

    private String password;
    private  String email;

}
