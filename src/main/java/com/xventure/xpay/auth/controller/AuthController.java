package com.xventure.xpay.auth.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.xventure.xpay.auth.models.ForgotPasswordRequest;
import com.xventure.xpay.auth.models.OTPValidationRequest;
import com.xventure.xpay.auth.models.ResetPasswordRequest;


import com.xventure.xpay.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.xventure.xpay.auth.payload.request.LoginRequest;
import com.xventure.xpay.auth.payload.response.JwtResponse;
import com.xventure.xpay.auth.repository.RoleRepository;
import com.xventure.xpay.auth.security.jwt.JwtUtils;
import com.xventure.xpay.auth.security.services.UserDetailsImpl;
import com.xventure.xpay.employeemanagement.repository.EmployeeRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	EmployeeRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	private EmployeeService employeeService;
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt,
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/forgot-password")
	public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest payload) {
		return ResponseEntity.ok(employeeService.forgotPassword(payload.getEmail()));
	}
	@PostMapping("/otp-verification")
	public ResponseEntity<?> otpVerification(@RequestBody OTPValidationRequest request) {
		return ResponseEntity.ok(employeeService.otpValidation(request));
	}
	@PostMapping(value ="/reset-password")
	public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest){
		return ResponseEntity.ok(employeeService.resetPassword(resetPasswordRequest));

	}



}
