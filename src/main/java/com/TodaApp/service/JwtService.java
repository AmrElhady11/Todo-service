package com.TodaApp.service;


import com.TodaApp.request.TokenValidationRequest;
import com.TodaApp.response.UserInfoResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@RequiredArgsConstructor
@Service
public class JwtService {
    private final RestTemplate restTemplate;

    public  UserInfoResponse checkToken(String jwt)  {

            final String url = "http://localhost:8080/api/v1/auth/checkToken";
            TokenValidationRequest  tokenValidationRequest = new TokenValidationRequest(jwt);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<TokenValidationRequest> request = new HttpEntity<>(tokenValidationRequest, headers);
            ResponseEntity<UserInfoResponse> response = restTemplate.postForEntity(url, request, UserInfoResponse.class);
            if(response.getStatusCode()== HttpStatus.OK)
                return response.getBody();
            else
                throw new IllegalStateException("Token validation failed: " + response.getStatusCode());


    }



}
