package com.authApp.controller;


import com.authApp.entity.User;
import com.authApp.request.AuthenticationRequest;
import com.authApp.request.RegisterRequest;
import com.authApp.response.AuthenticationResponse;
import com.authApp.service.AuthenticationService;
import com.authApp.service.JwtService;
import com.authApp.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final OtpService otpService;
    private final JwtService jwtService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest authRequest){
         AuthenticationResponse authResponse = authenticationService.register(authRequest);
        String email = authRequest.getEmail();
        otpService.sendOTPCode(email);
        return ResponseEntity.ok(authResponse);

    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authRequest){
        return ResponseEntity.ok(authenticationService.authenticate(authRequest));


    }
    @GetMapping("/activate")
    public ResponseEntity<String> activate(@RequestParam String email,@RequestParam String code){
        boolean enabled = otpService.checkOtpCode(email,code);
        if(enabled){
            authenticationService.enableUser(email);
            return ResponseEntity.ok("Activated");
        }
        return ResponseEntity.ok("Not activated");
    }
    @GetMapping("/regenerateOTP")
    public ResponseEntity<String> regenerateOTP(@RequestParam String email){
        otpService.deleteAllOtpCodes(email);
        otpService.sendOTPCode(email);
        return ResponseEntity.ok("OTP Code Generated and has been reset");
    }

//    @GetMapping("/checskToken")
//    public ResponseEntity<User> checkToken(@RequestParam String token){
//        var valid = jwtService.isTokenValid(token);
//
//    }

}

